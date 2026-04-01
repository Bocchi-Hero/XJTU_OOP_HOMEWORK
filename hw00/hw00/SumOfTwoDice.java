package hw00;

public class SumOfTwoDice {
    public static void main(String[] args) {
        int a = 1 + (int)(Math.random() * 6);
        int b = 1 + (int)(Math.random() * 6);
        System.out.printf("%d %d\n", a, b);
    }
}
