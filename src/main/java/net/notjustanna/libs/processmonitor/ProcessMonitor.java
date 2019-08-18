package net.notjustanna.libs.processmonitor;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class ProcessMonitor {
    private static final int availableProcessors = Runtime.getRuntime().availableProcessors();

    private static final double MB = 1048576.0;
    private static final double GB = 1073741824.0;

    private static final OperatingSystemMXBean os = ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean());
    private static final ThreadMXBean thread = ManagementFactory.getThreadMXBean();
    private static final Runtime r = Runtime.getRuntime();
    private static final RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
    private long lastSystemTime = runtime.getUptime();
    private double lastProcessCpuTime = os.getProcessCpuTime();

    public static int availableProcessors() {
        return availableProcessors;
    }

    private double processCpuUsage() {
        long systemTime = runtime.getUptime();
        double processCpuTime = os.getProcessCpuTime();

        double cpuUsage = Math.min(
            99.99,
            (processCpuTime - lastProcessCpuTime) / ((systemTime - lastSystemTime) * 10000.0 * availableProcessors)
        );

        lastSystemTime = systemTime;
        lastProcessCpuTime = processCpuTime;

        return cpuUsage;
    }

    public ProcessSnapshot takeSnapshot() {
        return new ProcessSnapshot(
            System.currentTimeMillis(),
            processCpuUsage(),
            os.getSystemCpuLoad(),
            thread.getThreadCount(),
            r.freeMemory(),
            r.totalMemory(),
            r.maxMemory(),
            os.getFreePhysicalMemorySize(),
            os.getTotalPhysicalMemorySize()
        );
    }

    public Future<ProcessSnapshot> snapshotStream(ScheduledExecutorService service, long period, TimeUnit unit, Consumer<ProcessSnapshot> onSnapshot) {
        return new Future<>() {
            private ProcessSnapshot last;
            private Future<?> future = service.scheduleAtFixedRate(this::updateSnapshot, 0, period, unit);

            private void updateSnapshot() {
                last = takeSnapshot();
                onSnapshot.accept(last);
            }

            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return future.cancel(mayInterruptIfRunning);
            }

            @Override
            public boolean isCancelled() {
                return future.isCancelled();
            }

            @Override
            public boolean isDone() {
                return future.isDone();
            }

            @Override
            public ProcessSnapshot get() {
                return last;
            }

            @Override
            public ProcessSnapshot get(long timeout, TimeUnit unit) throws InterruptedException {
                Thread.sleep(unit.toMillis(timeout));
                return get();
            }
        };
    }
}
