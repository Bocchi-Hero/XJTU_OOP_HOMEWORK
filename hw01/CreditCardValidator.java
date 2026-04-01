package hw01;

import java.util.Scanner;

public class CreditCardValidator {
    private long creditCardNumber;
    public CreditCardValidator(long number) {
        this.creditCardNumber = number;
    }
    // 检查输入格式是否合法
    public boolean checkFormat() {
        if (this.creditCardNumber < 0) {
            return false;
        }
        int count = 0;
        long tempNumber = this.creditCardNumber;
        while (tempNumber > 0) {
            tempNumber /= 10;
            count++;
        }
        return count >= 13 && count <= 16;
    }
    // 用题目算法验证卡号合法性
    public boolean luhnCheck() {
        int result = 0;
        boolean isEvenIndex = false;
        long tempNumber = this.creditCardNumber;
        while (tempNumber > 0) {
            int currentNumber = (int) (tempNumber % 10);
            if (isEvenIndex) {
                currentNumber *= 2;
                if (currentNumber >= 10) {
                    currentNumber = currentNumber % 10 + currentNumber / 10;
                }
            }
            result += currentNumber;
            isEvenIndex = !isEvenIndex;
            tempNumber /= 10;
        }
        return result % 10 == 0;
    }
    // 实行整体验证流程
    public boolean verify() {
        if (!checkFormat()) {
            return false;
        }
        return luhnCheck();
    }
    // 测试类主函数
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long creditCardNumber = sc.nextLong();

        CreditCardValidator validator = new CreditCardValidator(creditCardNumber);
        if (validator.verify()) {
            System.out.println("Valid!");
        } else {
            System.out.println("Not Valid!");
        }
    }
}
