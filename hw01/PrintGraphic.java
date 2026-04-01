package hw01;

public class PrintGraphic {

    public static void graphA(int size) {
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    public static void graphB(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= size - i; k++) {
                System.out.print(k);
            }
            System.out.println();
        }
    }

    public static void graphC(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - i; j++) {
                System.out.print(" ");
            }
            for (int k = i + 1; k >= 1; k--) {
                System.out.print(k);
            }
            System.out.println();
        }
    }

    public static void graphD(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = size - i; j >= 1; j--) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    public static void graphE(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - i; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= i + 1; k++) {
                System.out.print(k);
            }
            for (int t = i; t >= 1; t--) {
                System.out.print(t);
            }
            System.out.println();
        }
    }

    public static void graphF(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= size - i; k++) {
                System.out.print(k);
            }
            for (int t = size - i - 1; t >= 1; t--) {
                System.out.print(t);
            }
            System.out.println();
        }
    }

    public static void graphG(int size) {
        for (int i = 1; i < size; i++) {
            for (int left = 1; left <= i; left++) {
                System.out.print(left);
            }
            for (int space = 2 * (size - i) - 1; space >= 1; space--) {
                System.out.print(" ");
            }
            for (int right = i; right >= 1; right--) {
                System.out.print(right);
            }
            System.out.println();
        }
        for (int i = 1; i <= size; i++) {
            System.out.print(i);
        }
        for (int j = size - 1; j >= 1; j--) {
            System.out.print(j);
        }
        System.out.println();
    }

    public static void graphH(int size) {
        for (int i = 1; i <= size; i++) {
            System.out.print(i);
        }
        for (int j = size - 1; j >= 1; j--) {
            System.out.print(j);
        }
        System.out.println();
        for (int i = size - 1; i >= 1; i--) {
            for (int left = 1; left <= i; left++) {
                System.out.print(left);
            }
            for (int space = 1; space <= 2 * (size - i) - 1; space++) {
                System.out.print(" ");
            }
            for (int right = i; right >= 1; right--) {
                System.out.print(right);
            }
            System.out.println();
        }
    }

    public static void graphI(int size) {
        for (int i = 1; i <= size; i++) {
            for (int space = size - i; space > 0; space--) {
                System.out.print(" ");
            }
            for (int j = i; j < 2 * i; j++) {
                System.out.print(j % 10);
            }
            for (int k = 2 * (i - 1); k >= i; k--) {
                System.out.print(k % 10);
            }
            System.out.println();
        }
    }
    // 封装：图形类型筛选
    public static void selectGraphType(String type, int size) {
        switch (type) {
            case "a" -> graphA(size);
            case "b" -> graphB(size);
            case "c" -> graphC(size);
            case "d" -> graphD(size);
            case "e" -> graphE(size);
            case "f" -> graphF(size);
            case "g" -> graphG(size);
            case "h" -> graphH(size);
            case "i" -> graphI(size);
            default -> System.out.println("Error!");
        }
    }

    public static void main(String[] args) {
        String type = args[0];
        int size = Integer.parseInt(args[1]);
        selectGraphType(type, size);
    }
}
