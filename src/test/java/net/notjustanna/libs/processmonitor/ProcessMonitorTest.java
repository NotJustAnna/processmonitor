package net.notjustanna.libs.processmonitor;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProcessMonitorTest {
    public static void main(String[] args) {
        ProcessMonitor monitor = new ProcessMonitor();

        // taking snapshots manually
        System.out.println(monitor.takeSnapshot());

        // receiving a async stream of snapshots
        monitor.snapshotStream(
            Executors.newSingleThreadScheduledExecutor(),
            1L, TimeUnit.SECONDS,
            System.out::println
        );
    }
}