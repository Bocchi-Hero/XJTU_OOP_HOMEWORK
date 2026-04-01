package hw00;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SortThreeByFile {
    public static void main(String[] args) throws FileNotFoundException {
        int num1, num2, num3;

        Scanner read = new Scanner(new File(args[0]));
        num1 = read.nextInt();
        num2 = read.nextInt();
        num3 = read.nextInt();

        System.out.printf("%d %d %d\n", num3, num2, num1);

        read.close();
    }
}
