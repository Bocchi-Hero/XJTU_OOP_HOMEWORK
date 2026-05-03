package homework3.time;

public class MyTime {
    private int hour;
    private int minute;
    private int second;

    public MyTime() {
        this(0, 0, 0);
    }

    public MyTime(int hour) {
        this(hour, 0, 0);
    }

    public MyTime(int hour, int minute) {
        this(hour, minute, 0);
    }

    public MyTime(int hour, int minute, int second) {
        if (hour >= 0 && hour < 24) {
            this.hour = hour;
        } else {
            System.out.println("hour must be 0-23");
            this.hour = 0;
        }

        if (minute >= 0 && minute < 60) {
            this.minute = minute;
        } else {
            System.out.println("minute must be 0-59");
            this.minute = 0;
        }

        if (second >= 0 && second < 60) {
            this.second = second;
        } else {
            System.out.println("second must be 0-59");
            this.second = 0;
        }
    }

    public MyTime(MyTime time) {
        this(time.hour, time.minute, time.second);
    }

    public void incrementHour() { hour = (hour + 1) % 24; }

    public void incrementMinute() {
        if (++minute == 60) {
            incrementHour();
            minute = 0;
        }
    }

    public void incrementSecond() {
        if (++second == 60) {
            incrementMinute();
            second = 0;
        }
    }

    public String toUniversalString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d %s", ((hour == 0 || hour == 12) ? 12 : hour % 12),
        minute, second, ((hour < 12) ? "AM" : "PM"));
    }
}
