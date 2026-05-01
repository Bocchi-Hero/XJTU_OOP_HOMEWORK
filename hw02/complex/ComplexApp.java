package hw02.complex;

import java.util.Scanner;

public class ComplexApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter complex number 1 (real and imaginary part): ");
        double real1 = sc.nextDouble();
        double imag1 = sc.nextDouble();
        Complex c1 = new Complex(real1, imag1);
        System.out.print("Enter complex number 1 (real and imaginary part): ");
        double real2 = sc.nextDouble();
        double imag2 = sc.nextDouble();
        Complex c2 = new Complex(real2, imag2);
        System.out.println("Number 1 is: " + c1);
        isRealOrImaginary(c1);
        System.out.println("Number 2 is: " + c2);
        isRealOrImaginary(c2);

        if (c1.equals(c2)) {
            System.out.println("(" + c1 + ")" + "is equals to" + "(" + c2 + ")");
        } else {
            System.out.println("(" + c1 + ")" + "is NOT equals to" + "(" + c2 + ")");
        }

        System.out.println("(" + c1 + ") + (" + c2 + ") = (" + c1.add(c2) + ")");
    }

    private static void isRealOrImaginary(Complex c1) {
        if (c1.isReal()) {
            System.out.println("(" + c1 + ")" + "is a pure real number");
        } else if(c1.isImaginary()) {
            System.out.println("(" + c1 + ")" + "is a pure imaginary number");
        } else {
            System.out.println("(" + c1 + ")" + "is NOT a pure real number");
            System.out.println("(" + c1 + ")" + "is NOT a pure imaginary number");
        }
    }
}
