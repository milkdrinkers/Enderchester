<img style="text-align:center;" src="assets/logo-background.png" alt="project banner">

---

<h1 style="text-align:center;">Enderchester</h1>

<p style="text-align:center;">
    <a href="https://github.com/milkdrinkers/Enderchester/blob/main/LICENSE">
        <img alt="GitHub License" src="https://img.shields.io/github/license/milkdrinkers/Enderchester?style=for-the-badge&color=blue&labelColor=141417">
    </a>
    <a href="https://github.com/milkdrinkers/Enderchester/releases">
        <img alt="GitHub Release" src="https://img.shields.io/github/v/release/milkdrinkers/Enderchester?include_prereleases&sort=semver&style=for-the-badge&label=LATEST%20VERSION&labelColor=141417">
    </a>
    <img alt="GitHub Actions Workflow Status" src="https://img.shields.io/github/actions/workflow/status/milkdrinkers/Enderchester/ci.yml?style=for-the-badge&labelColor=141417">
    <a href="https://github.com/milkdrinkers/Enderchester/issues">
        <img alt="GitHub Issues" src="https://img.shields.io/github/issues/milkdrinkers/Enderchester?style=for-the-badge&labelColor=141417">
    </a>
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/milkdrinkers/Enderchester?style=for-the-badge&labelColor=141417">
    <a href="https://discord.gg/cG5uWvUcM6">
        <img alt="Discord Server" src="https://img.shields.io/discord/1008300159333040158?style=for-the-badge&logo=discord&logoColor=ffffff&label=discord&labelColor=141417&color=%235865F2">
    </a>
</p>

This plugin is a tiny quality of life addition to Minecraft. It allows players to open their ender chest by holding one in their hand and right-clicking, or right-clicking one in their inventory.

---

## 🌟 Features

- Right-click Enderchest in the player hand to open.
- Right-click Enderchests in inventories to open.
- Highly configurable!
- Permissions support.
- Supports Minecraft 1.19+ (_The plugin is tiny and is unlikely to break due to Minecraft updates_)
- Developer API

<img src="assets/example.gif" alt="example gif">

---

## 📦 Downloads

<a href="https://github.com/milkdrinkers/Enderchester/releases/latest">
    <img alt="GitHub Downloads (all assets, all releases)" src="https://img.shields.io/github/downloads/milkdrinkers/Enderchester/total?style=for-the-badge&logo=github&logoColor=white&labelColor=141417">
</a>
<a href="https://www.spigotmc.org/resources/121384/">
    <img alt="Spiget Downloads" src="https://img.shields.io/spiget/downloads/121384?style=for-the-badge&logo=spigotmc&logoColor=white&label=SPIGOT&labelColor=141417">
</a>
<a href="https://modrinth.com/plugin/enderchester/">
    <img alt="Modrinth Downloads" src="https://img.shields.io/modrinth/dt/enderchester?style=for-the-badge&logo=modrinth&logoColor=white&label=MODRINTH&labelColor=141417">
</a>
<a href="https://hangar.papermc.io/darksaid98/Enderchester/">
    <img alt="Hangar Downloads" src="https://img.shields.io/hangar/dt/Enderchester?style=for-the-badge&label=HANGAR&labelColor=141417">
</a>

### Stable Releases

Stable releases can be downloaded from the platforms linked above.

### Pre-Releases

Pre-releases/release-candidates are denoted by having `RC` in the name. These releases are made ahead of stable releases and should not be considered entirely stable.

### Experimental Builds

Experimental builds/snapshots are denoted by having `SNAPSHOT` in the name and should be considered unstable. These are bleeding edge builds produced from the latest available code. We do not recommend running these in production environments as these builds are unfinished and may contain serious issues.

---

## 🤝 Bugs & Feature Requests

If you happen to find any bugs or wish to request a feature, please open an issue on our [issue tracker here](https://github.com/milkdrinkers/Enderchester/issues). We provide bug report and feature request templates, so it is important that you fill out all the necessary information.

Making your issue easy to read and follow will usually result in it being handled faster. Failure to provide the requested information in an issue may result in it being closed.

---

## 🚧 API

<a href="https://javadoc.io/doc/io.github.milkdrinkers/enderchester">
    <img alt="Javadoc" src="https://img.shields.io/badge/JAVADOC-8A2BE2?style=for-the-badge&labelColor=141417">
</a>

We provide API for developers accessible on [Maven Central Repository](https://central.sonatype.com/artifact/io.github.milkdrinkers/enderchester). 

<details>
<summary>Gradle Kotlin DSL</summary>

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    compileOnly("io.github.milkdrinkers:enderchester:VERSION")
}
```
</details>

<details>
<summary>Maven</summary>

```xml
<project>
    <dependencies>
        <dependency>
            <groupId>io.github.milkdrinkers</groupId>
            <artifactId>enderchester</artifactId>
            <version>VERSION</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>
</project>
```
</details>

---

## 🔧 Contributing

Contributions are always welcome! Please make sure to read our [Contributor's Guide](CONTRIBUTING.md) for standards and our [Contributor License Agreement (CLA)](CONTRIBUTOR_LICENSE_AGREEMENT.md) before submitting any pull requests.

We also ask that you adhere to our [Contributor Code of Conduct](CODE_OF_CONDUCT.md) to ensure this community remains a place where all feel welcome to participate.

---

## 📝 Licensing

You can find the license the source code and all assets are under [here](../LICENSE). Additionally, contributors agree to the Contributor License Agreement \(*CLA*\) found [here](CONTRIBUTOR_LICENSE_AGREEMENT.md).

---

## ❤️ Acknowledgments

- **[Artillex-Studios:](https://github.com/Artillex-Studios)** _For their excellent plugin [__AxShulkers__](https://github.com/Artillex-Studios/AxShulkers/), which this was inspired by. I highly recommend their plugin providing excellent QoL features for shulkers._

---

![Project Usage](https://bstats.org/signatures/bukkit/Enderchester.svg)