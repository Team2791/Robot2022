package frc.robot.utils;

public class TimeSpan {
    protected double ms;

    public static TimeSpan fromSeconds(double duration) {
        TimeSpan tSpan = new TimeSpan();
        tSpan.ms = duration * 1000;

        return tSpan;
    }

    public static TimeSpan fromMillis(double duration) {
        TimeSpan tSpan = new TimeSpan();
        tSpan.ms = duration;

        return tSpan;
    }

    public double toSeconds() {
        return this.ms / 1000;
    }

    public double toMillis() {
        return this.ms;
    }
}
