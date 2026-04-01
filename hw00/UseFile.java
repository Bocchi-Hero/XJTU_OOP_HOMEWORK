package hw00;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UseFile {
    public static void main(String[] args) throws FileNotFoundException {
        int num1;
        double num2;
        String name;

        Scanner file = new Scanner(new File("data/file.txt"));
        num1 = file.nextInt();
        num2 = file.nextDouble();
        name = file.next();
        System.out.printf("Hi, %s, the sum of %d and %.2f is %.2f \n", name, num1, num2, (num1 + num2));
        file.close();
    }
}
