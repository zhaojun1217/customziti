package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final JjwtLibraryAccessors laccForJjwtLibraryAccessors = new JjwtLibraryAccessors(owner);
    private final JupiterLibraryAccessors laccForJupiterLibraryAccessors = new JupiterLibraryAccessors(owner);
    private final KotlinLibraryAccessors laccForKotlinLibraryAccessors = new KotlinLibraryAccessors(owner);
    private final NettyLibraryAccessors laccForNettyLibraryAccessors = new NettyLibraryAccessors(owner);
    private final Slf4jLibraryAccessors laccForSlf4jLibraryAccessors = new Slf4jLibraryAccessors(owner);
    private final TlsLibraryAccessors laccForTlsLibraryAccessors = new TlsLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

        /**
         * Creates a dependency provider for gson (com.google.code.gson:gson)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getGson() {
            return create("gson");
    }

        /**
         * Creates a dependency provider for sodium (com.goterl:lazysodium-java)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getSodium() {
            return create("sodium");
    }

    /**
     * Returns the group of libraries at jjwt
     */
    public JjwtLibraryAccessors getJjwt() {
        return laccForJjwtLibraryAccessors;
    }

    /**
     * Returns the group of libraries at jupiter
     */
    public JupiterLibraryAccessors getJupiter() {
        return laccForJupiterLibraryAccessors;
    }

    /**
     * Returns the group of libraries at kotlin
     */
    public KotlinLibraryAccessors getKotlin() {
        return laccForKotlinLibraryAccessors;
    }

    /**
     * Returns the group of libraries at netty
     */
    public NettyLibraryAccessors getNetty() {
        return laccForNettyLibraryAccessors;
    }

    /**
     * Returns the group of libraries at slf4j
     */
    public Slf4jLibraryAccessors getSlf4j() {
        return laccForSlf4jLibraryAccessors;
    }

    /**
     * Returns the group of libraries at tls
     */
    public TlsLibraryAccessors getTls() {
        return laccForTlsLibraryAccessors;
    }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Returns the group of bundles at bundles
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class JjwtLibraryAccessors extends SubDependencyFactory {

        public JjwtLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for api (io.jsonwebtoken:jjwt-api)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getApi() {
                return create("jjwt.api");
        }

            /**
             * Creates a dependency provider for gson (io.jsonwebtoken:jjwt-gson)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getGson() {
                return create("jjwt.gson");
        }

            /**
             * Creates a dependency provider for impl (io.jsonwebtoken:jjwt-impl)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getImpl() {
                return create("jjwt.impl");
        }

    }

    public static class JupiterLibraryAccessors extends SubDependencyFactory {

        public JupiterLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for api (org.junit.jupiter:junit-jupiter-api)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getApi() {
                return create("jupiter.api");
        }

            /**
             * Creates a dependency provider for engine (org.junit.jupiter:junit-jupiter-engine)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getEngine() {
                return create("jupiter.engine");
        }

    }

    public static class KotlinLibraryAccessors extends SubDependencyFactory {
        private final KotlinCoroutinesLibraryAccessors laccForKotlinCoroutinesLibraryAccessors = new KotlinCoroutinesLibraryAccessors(owner);

        public KotlinLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for lib (org.jetbrains.kotlin:kotlin-stdlib)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getLib() {
                return create("kotlin.lib");
        }

            /**
             * Creates a dependency provider for test (org.jetbrains.kotlin:kotlin-test-junit)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTest() {
                return create("kotlin.test");
        }

        /**
         * Returns the group of libraries at kotlin.coroutines
         */
        public KotlinCoroutinesLibraryAccessors getCoroutines() {
            return laccForKotlinCoroutinesLibraryAccessors;
        }

    }

    public static class KotlinCoroutinesLibraryAccessors extends SubDependencyFactory {

        public KotlinCoroutinesLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for lib (org.jetbrains.kotlinx:kotlinx-coroutines-core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getLib() {
                return create("kotlin.coroutines.lib");
        }

            /**
             * Creates a dependency provider for test (org.jetbrains.kotlinx:kotlinx-coroutines-test)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTest() {
                return create("kotlin.coroutines.test");
        }

    }

    public static class NettyLibraryAccessors extends SubDependencyFactory {

        public NettyLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for http (io.netty:netty-codec-http)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getHttp() {
                return create("netty.http");
        }

            /**
             * Creates a dependency provider for transport (io.netty:netty-transport)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTransport() {
                return create("netty.transport");
        }

    }

    public static class Slf4jLibraryAccessors extends SubDependencyFactory {

        public Slf4jLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for api (org.slf4j:slf4j-api)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getApi() {
                return create("slf4j.api");
        }

            /**
             * Creates a dependency provider for simple (org.slf4j:slf4j-simple)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getSimple() {
                return create("slf4j.simple");
        }

    }

    public static class TlsLibraryAccessors extends SubDependencyFactory {

        public TlsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for channel (com.github.marianobarrios:tls-channel)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getChannel() {
                return create("tls.channel");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final LazysodiumVersionAccessors vaccForLazysodiumVersionAccessors = new LazysodiumVersionAccessors(providers, config);
        private final SemverVersionAccessors vaccForSemverVersionAccessors = new SemverVersionAccessors(providers, config);
        private final TlsVersionAccessors vaccForTlsVersionAccessors = new TlsVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: coroutines (1.7.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCoroutines() { return getVersion("coroutines"); }

            /**
             * Returns the version associated to this alias: dokka (1.8.20)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getDokka() { return getVersion("dokka"); }

            /**
             * Returns the version associated to this alias: gson (2.10.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getGson() { return getVersion("gson"); }

            /**
             * Returns the version associated to this alias: jjwt (0.11.5)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJjwt() { return getVersion("jjwt"); }

            /**
             * Returns the version associated to this alias: jupiter (5.10.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJupiter() { return getVersion("jupiter"); }

            /**
             * Returns the version associated to this alias: kotlin (1.9.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKotlin() { return getVersion("kotlin"); }

            /**
             * Returns the version associated to this alias: netty (4.1.96.Final)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getNetty() { return getVersion("netty"); }

            /**
             * Returns the version associated to this alias: nexus (1.3.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getNexus() { return getVersion("nexus"); }

            /**
             * Returns the version associated to this alias: slf4j (2.0.7)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getSlf4j() { return getVersion("slf4j"); }

        /**
         * Returns the group of versions at versions.lazysodium
         */
        public LazysodiumVersionAccessors getLazysodium() {
            return vaccForLazysodiumVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.semver
         */
        public SemverVersionAccessors getSemver() {
            return vaccForSemverVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.tls
         */
        public TlsVersionAccessors getTls() {
            return vaccForTlsVersionAccessors;
        }

    }

    public static class LazysodiumVersionAccessors extends VersionFactory  {

        public LazysodiumVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: lazysodium.java (5.1.4)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJava() { return getVersion("lazysodium.java"); }

    }

    public static class SemverVersionAccessors extends VersionFactory  {

        public SemverVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: semver.git (2.3.7)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getGit() { return getVersion("semver.git"); }

    }

    public static class TlsVersionAccessors extends VersionFactory  {

        public TlsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: tls.channel (0.8.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getChannel() { return getVersion("tls.channel"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {
        private final NexusPluginAccessors paccForNexusPluginAccessors = new NexusPluginAccessors(providers, config);
        private final SemverPluginAccessors paccForSemverPluginAccessors = new SemverPluginAccessors(providers, config);

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for dokka to the plugin id 'org.jetbrains.dokka'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getDokka() { return createPlugin("dokka"); }

            /**
             * Creates a plugin provider for kotlin to the plugin id 'org.jetbrains.kotlin.jvm'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getKotlin() { return createPlugin("kotlin"); }

        /**
         * Returns the group of plugins at plugins.nexus
         */
        public NexusPluginAccessors getNexus() {
            return paccForNexusPluginAccessors;
        }

        /**
         * Returns the group of plugins at plugins.semver
         */
        public SemverPluginAccessors getSemver() {
            return paccForSemverPluginAccessors;
        }

    }

    public static class NexusPluginAccessors extends PluginFactory {

        public NexusPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for nexus.publish to the plugin id 'io.github.gradle-nexus.publish-plugin'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getPublish() { return createPlugin("nexus.publish"); }

    }

    public static class SemverPluginAccessors extends PluginFactory {

        public SemverPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for semver.git to the plugin id 'io.wusa.semver-git-plugin'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getGit() { return createPlugin("semver.git"); }

    }

}
