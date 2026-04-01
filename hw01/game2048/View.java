package hw01.game2048;

import java.util.Scanner;

public class View {
    private final Scanner sc = new Scanner(System.in);
    private static final String RESET = "\u001B[0m";

    // 为不同数字添加不同颜色
    private String colorForValue(int value) {
        return switch (value) {
            case 2 -> "\u001B[37m";
            case 4 -> "\u001B[36m";
            case 8 -> "\u001B[32m";
            case 16 -> "\u001B[33m";
            case 32 -> "\u001B[34m";
            case 64 -> "\u001B[31m";
            case 128 -> "\u001B[35m";
            case 256 -> "\u001B[96m";
            case 512 -> "\u001B[92m";
            case 1024 -> "\u001B[93m";
            case 2048 -> "\u001B[91m";
            default -> "\u001B[95m";
        };
    }

    // 以下show开头的都是表面工作
    private static final String EMPTY_COLOR = "\u001B[90m";
    public void showWelcome() {
        System.out.println("Hello, welcome to the 2048 game! ");
        System.out.println("You can slide the blocks on the board up, down, left, and right by typing w/a/s/d. ");
        System.out.println("You can restart or quit by typing r/q at any time.");
        System.out.println("Your goal is to create a number of 2048 or higher to get a high score.");
        System.out.println("Good luck, young player!");
    }
    public void showBye(int finalScore) {
        System.out.println("The game is over.");
        System.out.println("Your final score is: " + finalScore);
    }
    public void showInvalidInput() {
        System.out.println("Your input is invalid. Please re-enter.");
    }
    public void showNoMove() {
        System.out.println("Your previous operation was ineffective; you can try a different approach.");
    }
    public void showWin() {
        System.out.println("Well done, you won this game!");
    }

    public void showGameOver() {
        System.out.println("You're unbelievable. Losing a game this easy? Practice more.");
    }

    public void showBoardAndScore(int[][] board, int score) {
        String tableLine = "+-------+-------+-------+-------+";
        for (int i = 0; i < board.length; i++) {
            System.out.println(tableLine);
            for (int j = 0; j < board[i].length; j++) {
                int value = board[i][j];
                if (value == 0) {
                    System.out.printf("| %5s ",  " ");
                } else {
                    System.out.printf("| %s%5d%s ", colorForValue(value), value, RESET);
                }
            }
            System.out.println("|");
        }
        System.out.println(tableLine);
        System.out.println("Current Score: " + score);
        System.out.print("Action > ");
    }

    // 将用户输入的操作转化为自己的枚举类型
    public Command readCommand() {
        String operator = sc.nextLine().toLowerCase().trim();
        return switch(operator) {
            case "w" -> Command.UP;
            case "a" -> Command.LEFT;
            case "s" -> Command.DOWN;
            case "d" -> Command.RIGHT;
            case "c" -> Command.CONTINUE;
            case "r" -> Command.RESTART;
            case "q" -> Command.QUIT;
            default -> Command.INVALID;
        };
    }

    // 在基础胜利是抛出选择
    public boolean askContinue() {
        while(true) {
            System.out.println("Continue or Quit? Type 'c' to continue, type 'q' to exit:");
            Command operator = readCommand();
            if (operator == Command.CONTINUE) return true;
            if (operator == Command.QUIT) return false;
            showInvalidInput();
        }
    }

    // 在游戏结束时抛出选择
    public boolean askRestart() {
        while(true) {
            System.out.println("Restart or Quit? Type 'r' to restart, type 'q' to exit:");
            Command operator = readCommand();
            if (operator == Command.RESTART) return true;
            if (operator == Command.QUIT) return false;
            showInvalidInput();
        }
    }
}
