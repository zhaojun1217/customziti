/*
 * Copyright (c) 2018-2020 NetFoundry, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openziti.net.nio

import org.openziti.net.internal.ZitiSSLSocket
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory

/**
 *
 */
class AsyncTLSSocketFactory(val ssl: SSLContext): SSLSocketFactory() {

    constructor(): this(SSLContext.getDefault())

    override fun getDefaultCipherSuites(): Array<String> = ssl.createSSLEngine().enabledCipherSuites
    override fun getSupportedCipherSuites(): Array<String> = ssl.createSSLEngine().supportedCipherSuites

    private val getImplMethod: Method?
    private val implField: Field?

    init {
        var m: Method? = null
        try {
            m = Socket::class.java.getDeclaredMethod("getImpl").apply {
                isAccessible = true
            }
        } catch(ex: NoSuchMethodException) { }

        var f: Field? = null
        try {
            f = Socket::class.java.getDeclaredField("impl").apply { isAccessible = true }
        } catch (ex: Exception){}
        implField = f
        getImplMethod = m
    }

    override fun createSocket(transport: Socket, host: String, port: Int, autoClose: Boolean): Socket {
        val impl = getImplMethod?.invoke(transport) ?: implField?.get(transport)

        if(impl is AsyncSocketImpl) {
            val ch = impl.channel
            return AsyncTLSChannelSocket(ch, host, port, ssl)
        }

        return ZitiSSLSocket(transport, ssl.createSSLEngine(host, port))
    }

    override fun createSocket(host: String?, port: Int): Socket =
        AsyncTLSChannelSocket(InetSocketAddress(host, port), null, ssl)

    override fun createSocket(host: String, port: Int, localHost: InetAddress?, localPort: Int): Socket {
        return AsyncTLSChannelSocket(
            InetSocketAddress(host, port),
            InetSocketAddress(localHost, localPort), ssl)
    }

    override fun createSocket(host: InetAddress?, port: Int): Socket {
        return AsyncTLSChannelSocket(InetSocketAddress(host, port), null, ssl)
    }

    override fun createSocket(address: InetAddress?, port: Int, localAddress: InetAddress?, localPort: Int): Socket {
        return AsyncTLSChannelSocket(
            InetSocketAddress(address, port),
            InetSocketAddress(localAddress, localPort), ssl)
    }
}