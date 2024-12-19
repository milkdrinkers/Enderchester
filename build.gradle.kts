import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import java.time.Instant

plugins {
    `java-library`

    alias(libs.plugins.shadow) // Shades and relocates dependencies, see https://gradleup.com/shadow/
    alias(libs.plugins.run.paper) // Built in test server using runServer and runMojangMappedServer tasks
    alias(libs.plugins.plugin.yml) // Automatic plugin.yml generation

    eclipse
    idea
}

val mainPackage = "${project.group}.${rootProject.name.lowercase()}"
applyCustomVersion()

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17)) // Configure the java toolchain. This allows gradle to auto-provision JDK 17 on systems that only have JDK 8 installed for example.
    withJavadocJar() // Enable Javadoc generation
    withSourcesJar()
}

repositories {
    mavenCentral()

    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://mvn-repo.arim.space/lesser-gpl3/")

    maven("https://maven.athyrium.eu/releases")

    maven("https://jitpack.io/") {
        content {
            includeGroup("com.github.MilkBowl")
        }
    }
}

dependencies {
    // Core dependencies
    compileOnly(libs.annotations)
    annotationProcessor(libs.annotations)
    compileOnly(libs.paper.api)
    implementation(libs.morepaperlib)

    // API
    implementation(libs.crate.api)
    implementation(libs.crate.yaml)
    implementation(libs.colorparser) {
        exclude("net.kyori")
    }

    // Plugin dependencies
    implementation(libs.bstats)
}

tasks {
    build {
        dependsOn(shadowJar)
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
        options.compilerArgs.addAll(arrayListOf("-Xlint:all", "-Xlint:-processing", "-Xdiags:verbose"))
    }

    javadoc {
        isFailOnError = false
        val options = options as StandardJavadocDocletOptions
        options.encoding = Charsets.UTF_8.name()
        options.overview = "src/main/javadoc/overview.html"
        options.windowTitle = "${rootProject.name} Javadoc"
        options.tags("apiNote:a:API Note:", "implNote:a:Implementation Note:", "implSpec:a:Implementation Requirements:")
        options.addStringOption("Xdoclint:none", "-quiet")
        options.use()
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }

    shadowJar {
        archiveBaseName.set(project.name)
        archiveClassifier.set("")

        // Shadow classes
        fun reloc(originPkg: String, targetPkg: String) = relocate(originPkg, "${mainPackage}.lib.${targetPkg}")

        reloc("space.arim.morepaperlib", "morepaperlib")
        reloc("com.github.milkdrinkers.crate", "crate")
        reloc("com.github.milkdrinkers.colorparser", "colorparser")
        reloc("org.bstats", "bstats")

        minimize()
    }

    runServer {
        // Configure the Minecraft version for our task.
        minecraftVersion("1.21.4")

        // IntelliJ IDEA debugger setup: https://docs.papermc.io/paper/dev/debugging#using-a-remote-debugger
        jvmArgs("-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-DPaper.IgnoreJavaVersion=true", "-Dcom.mojang.eula.agree=true", "-DIReallyKnowWhatIAmDoingISwear")
        systemProperty("terminal.jline", false)
        systemProperty("terminal.ansi", true)

        // Automatically install dependencies
        downloadPlugins {
            github("MilkBowl", "Vault", "1.7.3", "Vault.jar")
        }
    }
}

tasks.withType(xyz.jpenilla.runtask.task.AbstractRun::class) {
    javaLauncher = javaToolchains.launcherFor {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

bukkit { // Options: https://github.com/Minecrell/plugin-yml#bukkit
    // Plugin main class (required)
    main = "${mainPackage}.${rootProject.name}"

    // Plugin Information
    name = project.name
    prefix = project.name
    version = "${project.version}"
    description = "${project.description}"
    authors = listOf("darksaid98")
    contributors = listOf()
    apiVersion = "1.19"

    // Misc properties
    load = net.minecrell.pluginyml.bukkit.BukkitPluginDescription.PluginLoadOrder.POSTWORLD // STARTUP or POSTWORLD
    depend = listOf()
    softDepend = listOf()

    permissions {
        register("enderchester.use") {
            description = "Allows you to open ender chests by hand or through your inventory"
            default = BukkitPluginDescription.Permission.Default.TRUE;
        }
    }
}

fun applyCustomVersion() {
    // Apply custom version arg or append snapshot version
    val ver = properties["altVer"]?.toString() ?: "${rootProject.version}-SNAPSHOT-${Instant.now().epochSecond}"

    // Strip prefixed "v" from version tag
    rootProject.version = (if (ver.first().equals('v', true)) ver.substring(1) else ver.uppercase()).uppercase()
}