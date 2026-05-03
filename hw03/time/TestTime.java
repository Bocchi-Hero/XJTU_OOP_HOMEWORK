package homework3.time;

public class TestTime {
    public static void main(String[] args) {
        MyTime t1 = new MyTime();
        MyTime t2 = new MyTime(2);
        MyTime t3 = new MyTime(21, 34);
        MyTime t4 = new MyTime(12, 25, 42);
        MyTime t5 = new MyTime(t4);

        System.out.println("Constructed with:");
        System.out.println("t1: all arguments defaulted");
        System.out.printf("    %s\n", t1.toUniversalString());
        System.out.printf("    %s\n", t1.toString());

        System.out.println("t2: hour specified; minute and second defaulted");
        System.out.printf("    %s\n", t2.toUniversalString());
        System.out.printf("    %s\n", t2.toString());

        System.out.println("t3: hour and minute specified; second defaulted");
        System.out.printf("    %s\n", t3.toUniversalString());
        System.out.printf("    %s\n", t3.toString());

        System.out.println("t4: hour, minute and second specified");
        System.out.printf("    %s\n", t4.toUniversalString());
        System.out.printf("    %s\n", t4.toString());

        System.out.println("t5: MyTime object t4 specified");
        System.out.printf("    %s\n", t5.toUniversalString());
        System.out.printf("    %s\n", t5.toString());

        // 构建t6对象的参数数值不在合理范围内，请输出这类错误信息。
        MyTime t6 = new MyTime(15, 74, 99);
        System.out.println("t6: invalid values");
        System.out.printf("%s\n", t6.toUniversalString());
    }
}
