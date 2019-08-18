package net.notjustanna.libs.processmonitor;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.StringJoiner;

public class ProcessSnapshot {
    private final double cpuUsage;
    private final double systemCpuUsage;
    private final long threadCount;
    private final long freeMemory;
    private final long allocatedMemory;
    private final long maxMemory;
    private final long systemFreeMemory;
    private final long systemMaxMemory;
    private final long systemUsedMemory;
    private long timeMillis;

    public ProcessSnapshot(long timeMillis,
                           double cpuUsage, double systemCpuUsage, long threadCount, long freeMemory, long maxMemory,
                           long allocatedMemory,
                           long systemFreeMemory, long systemMaxMemory) {
        this.timeMillis = timeMillis;
        this.cpuUsage = cpuUsage;
        this.freeMemory = freeMemory;
        this.maxMemory = maxMemory;
        this.threadCount = threadCount;
        this.allocatedMemory = allocatedMemory;
        this.systemCpuUsage = systemCpuUsage;
        this.systemFreeMemory = systemFreeMemory;
        this.systemMaxMemory = systemMaxMemory;
        this.systemUsedMemory = systemMaxMemory - freeMemory;
    }

    public long timeMillis() {
        return timeMillis;
    }

    public OffsetDateTime time() {
        return OffsetDateTime.ofInstant(Instant.ofEpochMilli(timeMillis), ZoneOffset.UTC);
    }

    public double cpuUsage() {
        return cpuUsage;
    }

    public double systemCpuUsage() {
        return systemCpuUsage;
    }

    public long threadCount() {
        return threadCount;
    }

    public long freeMemory() {
        return freeMemory;
    }

    public long allocatedMemory() {
        return allocatedMemory;
    }

    public long maxMemory() {
        return maxMemory;
    }

    public long systemFreeMemory() {
        return systemFreeMemory;
    }

    public long systemMaxMemory() {
        return systemMaxMemory;
    }

    public long systemUsedMemory() {
        return systemUsedMemory;
    }

    public double freeMemory(MemoryUnit unit) {
        return MemoryUnit.BYTES.convertTo(((double) freeMemory), unit);
    }

    public double allocatedMemory(MemoryUnit unit) {
        return MemoryUnit.BYTES.convertTo(((double) allocatedMemory), unit);
    }

    public double maxMemory(MemoryUnit unit) {
        return MemoryUnit.BYTES.convertTo(((double) maxMemory), unit);
    }

    public double systemFreeMemory(MemoryUnit unit) {
        return MemoryUnit.BYTES.convertTo(((double) systemFreeMemory), unit);
    }

    public double systemMaxMemory(MemoryUnit unit) {
        return MemoryUnit.BYTES.convertTo(((double) systemMaxMemory), unit);
    }

    public double systemUsedMemory(MemoryUnit unit) {
        return MemoryUnit.BYTES.convertTo(((double) systemUsedMemory), unit);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "ProcessSnapshot taken at " + time() + ": ", "")
            .add("Process CPU usage: " + cpuUsage)
            .add("System CPU usage: " + systemCpuUsage)
            .add("Process thread count: " + threadCount)
            .add("Process free memory: " + freeMemory)
            .add("Process allocated memory: " + allocatedMemory)
            .add("Process max memory: " + maxMemory)
            .add("System free memory: " + systemFreeMemory)
            .add("System max memory: " + systemMaxMemory)
            .add("System used memory: " + systemUsedMemory)
            .toString();
    }
}
