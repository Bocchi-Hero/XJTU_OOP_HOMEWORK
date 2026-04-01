package hw01.date;

public class DateUtil {
    public static final int[] MONTH_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static final String[] MONTH_NAMES = {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };
    public static final String[] WEEK_NAMES = {
            "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    };
    // 判断闰年
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
    // 判断日期合法性
    public static boolean isValidDate(int year, int month, int day) {
        if (year < 1 || year > 9999 || month < 1 || month > 12) {
            return false;
        }
        int monthDay = MONTH_DAYS[month - 1];
        if (isLeapYear(year) && month == 2) {
            monthDay = 29;
        }
        return day >= 1 && day <= monthDay;
    }
    // 获取某一天的星期
    public static int getDayOfWeek(int year, int month, int day) {
        if (!isValidDate(year, month, day)) {
            return 0;
        }
        int[] centuryTable = {6, 4, 2, 0};
        int[] monthTable = {0, 3, 3, 6, 1, 4, 6, 2 ,5 ,0, 3, 5};
        int monthCode = monthTable[month - 1];
        if (isLeapYear(year)) {
            if (month == 1) {
                monthCode = 6;
            } else if (month == 2) {
                monthCode = 2;
            }
        }

        return (year % 100 + year % 100 / 4 +  centuryTable[(year / 100) % 4] + monthCode + day) % 7;
    }
    // 打印带有年月的日历
    public static void printCalendar(int year, int month) {
        int day = MONTH_DAYS[month - 1];
        if (isLeapYear(year) && month == 2) {
             day = 29;
        }
        System.out.println("MON\tTUE\tWEN\tTHU\tFRI\tSAT\tSUN");
        int firstDay = getDayOfWeek(year, month, 1);
        int tabCount = (firstDay == 0) ? 6 : (firstDay - 1);
        for (int i = 0; i < tabCount; i++) {
            System.out.print("\t");
        }
        for (int i = 1; i <= day; i++) {
            System.out.print(i + "\t");
            if ((i + tabCount) % 7 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }
    // 打印一年的日历
    public static void printCalendar(int year) {
        for (int i = 1; i <= 12; i++) {
            System.out.println("---" + i + "---");
            printCalendar(year, i);
            System.out.println();
        }
    }
    // 格式化日期形式
    public static String formatDate(int year, int month, int day) {
        String weekName = WEEK_NAMES[getDayOfWeek(year, month, day)];
        String monthName = MONTH_NAMES[month - 1];
        return weekName + " " + day + " " + monthName + " " + year;
    }
}
