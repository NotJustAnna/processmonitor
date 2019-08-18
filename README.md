# Process Monitor

Java Process Monitoring, which leverages JMX beans to monitor it.

Licensed under the [MIT License](https://github.com/notjustanna/processmonitor/blob/master/LICENSE).

### Installation

![Latest Version](https://api.bintray.com/packages/notjustanna/maven/processmonitor/images/download.svg)

Using in Gradle:

```gradle
repositories {
  jcenter()
}

dependencies {
  compile 'net.notjustanna.libs:processmonitor:LATEST' // replace LATEST with the version above
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
    <groupId>net.notjustanna.libs</groupId>
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




