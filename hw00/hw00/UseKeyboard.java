package hw00;

import java.util.Scanner;

public class UseKeyboard {
    public static void main(String[] args) {
        int num1;
        double num2;
        String name;

        Scanner in = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        num1 = in.nextInt();
        System.out.print("Enter a floating point number :");
        num2 = in.nextDouble();
        System.out.print("Enter your name: ");
        name = in.next();

        System.out.printf("Hi, %s, the sum of %d and %.2f is %.2f \n", name, num1, num2, (num1 + num2));
    }
}
