package hw01.game2048;

import java.util.Random;

// 这是这个游戏的核心实现部分，是用来实现逻辑的
public class Model {
    // 这是一个4 * 4的游戏面板及分数，设为private，防止误改
    private int[][] board = new int[4][4];
    private int score;
    private final int SIZE = 4;

    // 创建一个调用接口
    public int[][] getBoard() {
        int[][] copy = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            copy[i] = board[i].clone();
        }
        return copy;
    }
    public int getScore() {
        return score;
    }

    // 这是一个在空位随机生成数字的方法
    public void generateNumber() {
        int spaceCount = 0;
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    spaceCount++;
                }
            }
        }
        if (spaceCount == 0) return;
        int randomIndex = random.nextInt(1, spaceCount + 1);
        int probability = random.nextInt(10);
        int rowIndex = 0, columnIndex = 0, currentIndex = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0 && ++currentIndex == randomIndex) {
                    rowIndex = i;
                    columnIndex = j;
                    break;
                }
            }
        }
        if (probability < 9) {
            board[rowIndex][columnIndex] = 2;
        } else {
            board[rowIndex][columnIndex] = 4;
        }
    }

    /* 这是让矩阵整体向左滑动的方法
    * 为了直观起见，分为两个方法
    * 1. 先做移动（压缩）方法，将非零数字全部移到左侧
    * 2. 再做合并方法，将满足条件的数字合并*/
    private boolean compressLeft() {
        boolean status = false;
        for (int i = 0; i < SIZE; i++) {
            int k = 0;
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != 0) {
                    if (j != k) {
                        status = true;
                    }
                    board[i][k++] = board[i][j];
                }
            }
            while (k < SIZE) {
                board[i][k++] = 0;
            }
        }
        return status;
    }

    private boolean mergeLeft() {
        boolean status = false;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE - 1; j++) {
                if (board[i][j] != 0 && board[i][j] == board[i][j + 1]) {
                    board[i][j] *= 2;
                    score += board[i][j];
                    board[i][++j] = 0;
                    status = true;
                }
            }
        }
        return status;
    }

    public boolean moveLeft() {
        boolean change1 = compressLeft();
        boolean change2 = mergeLeft();
        boolean change3 = compressLeft();
        return change1 || change2 || change3;
    }

    // 这是旋转矩阵的方法
    public void rotateMatrix() {
        // 先做转置
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < i; j++) {
                int temp = board[i][j];
                board[i][j] = board[j][i];
                board[j][i] = temp;
            }
        }
        // 再做左右翻转
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE / 2; j++) {
                int temp = board[i][j];
                board[i][j] = board[i][SIZE - 1 - j];
                board[i][SIZE - 1 - j] = temp;
            }
        }
    }

    // 通过旋转矩阵可以复用moveLeft的逻辑将剩下的方向也实现了
    public boolean moveRight() {
        rotateMatrix();
        rotateMatrix();
        boolean change = moveLeft();
        rotateMatrix();
        rotateMatrix();
        return change;
    }

    public boolean moveUp() {
        rotateMatrix();
        rotateMatrix();
        rotateMatrix();
        boolean change = moveLeft();
        rotateMatrix();
        return change;
    }

    public boolean moveDown() {
        rotateMatrix();
        boolean change = moveLeft();
        rotateMatrix();
        rotateMatrix();
        rotateMatrix();
        return change;
    }

    // 最后是对游戏状态的检测，首先是胜利状态，即成功合成2048
    public boolean gameWin() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    // 失败状态，即棋盘被全部填满且无可以合成的方块时
    public boolean gameOver() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
                if (j < SIZE - 1 && board[i][j] == board[i][j + 1]) {
                    return false;
                }
                if (i < SIZE - 1 && board[i][j] == board[i + 1][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
