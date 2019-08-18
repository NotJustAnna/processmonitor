# Process Monitor

Java Process Monitoring, which leverages JMX beans to monitor it.

Licensed under the [MIT License](https://github.com/arudiscord/processmonitor/blob/master/LICENSE).

### Installation

![Latest Version](https://api.bintray.com/packages/arudiscord/maven/processmonitor/images/download.svg)

Using in Gradle:

```gradle
repositories {
  jcenter()
}

dependencies {
  compile 'pw.aru.libs:processmonitor:LATEST' // replace LATEST with the version above
}
```

Using in Maven:

```xml
<repositories>
  <repository>
    <id>central</id>
    <name>bintray</name>
    <url>http://jcenter.bintray.com</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>pw.aru.libs</groupId>
    <artifactId>processmonitor</artifactId>
    <version>LATEST</version> <!-- replace LATEST with the version above -->
  </dependency>
</dependencies>
```

### Usage

```java
ProcessMonitor monitor = new ProcessMonitor();

// taking snapshots manually
monitor.takeSnapshot();

// receiving a async stream of snapshots
monitor.snapshotStream(
    Executors.newSingleThreadScheduledExecutor(),
    1L, TimeUnit.SECONDS,
    snapshot -> System.out.println(snapshot)
);
```

### Support

Support is given on [Aru's Discord Server](https://discord.gg/URPghxg)

[![Aru's Discord Server](https://discordapp.com/api/guilds/403934661627215882/embed.png?style=banner2)](https://discord.gg/URPghxg)
