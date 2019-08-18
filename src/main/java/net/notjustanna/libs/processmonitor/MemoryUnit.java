package net.notjustanna.libs.processmonitor;

public enum MemoryUnit {
    BYTES(MemoryUnit.BYTE_SCALE),
    KILOBYTES(MemoryUnit.KILO_SCALE),
    MEGABYTES(MemoryUnit.MEGA_SCALE),
    GIGABYTES(MemoryUnit.GIGA_SCALE),
    TERABYTES(MemoryUnit.TERA_SCALE),
    PETABYTES(MemoryUnit.PETA_SCALE);

    public static final MemoryUnit B = BYTES;
    public static final MemoryUnit kB = KILOBYTES;
    public static final MemoryUnit MB = MEGABYTES;
    public static final MemoryUnit GB = GIGABYTES;
    public static final MemoryUnit TB = TERABYTES;
    public static final MemoryUnit PB = PETABYTES;

    private static final long BYTE_SCALE = 1L;
    private static final long KILO_SCALE = 1024L * BYTE_SCALE;
    private static final long MEGA_SCALE = 1024L * KILO_SCALE;
    private static final long GIGA_SCALE = 1024L * MEGA_SCALE;
    private static final long TERA_SCALE = 1024L * GIGA_SCALE;
    private static final long PETA_SCALE = 1024L * TERA_SCALE;

    private final long scale;

    MemoryUnit(long scale) {
        this.scale = scale;
    }

    public double convertTo(double value, MemoryUnit unit) {
        final double scaling = ((double) scale) / unit.scale;
        return value * scaling;
    }

    public long convertTo(long value, MemoryUnit unit) {
        final int delta = (unit.ordinal() - ordinal()) * 10;

        if (delta > 0) {
            return value >> delta;
        } else {
            return value << (-delta);
        }
    }
}