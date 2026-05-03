package homework3.time;


public class TestIncrement {
    public static void main(String[] args) {
        MyTime t1 = new MyTime(10, 20, 59);
        System.out.println("Before: " + t1.toUniversalString());
        t1.incrementSecond();
        System.out.println("After: " + t1.toUniversalString());
        System.out.println();

        MyTime t2 = new MyTime(10, 59, 59);
        System.out.println("Before: " + t2.toUniversalString());
        t2.incrementSecond();
        System.out.println("After: " + t2.toUniversalString());
        System.out.println();

        MyTime t3 = new MyTime(23, 59, 59);
        System.out.println("Before: " + t3.toUniversalString());
        t3.incrementSecond();
        System.out.println("After: " + t3.toUniversalString());
    }
}
