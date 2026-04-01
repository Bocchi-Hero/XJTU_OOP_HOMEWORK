package hw01.date;

import java.time.LocalDate;
import java.time.Year;

public class TestDateUtil {
    public static void main(String[] args) {
        // 非法日期下：测试一个方法(isValidDate), 用特殊情况
        assert DateUtil.isValidDate(2024, 2, 29);
        assert !DateUtil.isValidDate(2025, 2, 29):"LeapYearValid Failed!";
        assert !DateUtil.isValidDate(2026, 0, 1):"MonthValid Failed!";
        assert !DateUtil.isValidDate(0, 1, 1):"YearValid Failed!";
        // 合法日期下：共测试两个方法（isLeapYear, getDayOfWeek) ，还有一个数组(MONTH_DAYS)
        for (int year = 1900; year <= 2100; year++) {
            if (DateUtil.isLeapYear(year) != Year.isLeap(year)) {
                System.err.println("Failed isLeapYear!");
            }
            for (int month = 1; month <= 12; month++) {
                int officialMonthDays = LocalDate.of(year, month, 1).lengthOfMonth();
                int myMonthDays = DateUtil.MONTH_DAYS[month - 1];
                if (Year.isLeap(year) && month == 2) {
                    myMonthDays = 29;
                }
                if (officialMonthDays != myMonthDays) {
                    System.err.println("Failed MONTH_DAYS!");
                }
                for (int day = 1; day <= officialMonthDays; day++) {
                    int officialWeek =  LocalDate.of(year, month, day).getDayOfWeek().getValue() % 7;
                    if (DateUtil.getDayOfWeek(year, month, day) != officialWeek) {
                        System.err.println("Failed getWayOfWeek!");
                    }
                }
            }

        }
        // 最后测试一下三个打印类型的方法
        DateUtil.printCalendar(2026);
        DateUtil.printCalendar(2024, 2);
        System.out.println(DateUtil.formatDate(2024, 2, 29));
    }
}
