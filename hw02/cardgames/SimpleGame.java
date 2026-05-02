package hw02.cardgames;

import java.util.Scanner;

/**
 * 这个程序是简单的纸牌游戏：比大小。
 * 从一副牌中发一张牌。你必须预测下一张牌是高还是低。
 * 游戏得分是你在猜错之前做出正确预测的次数。
 */
public class SimpleGame {

    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("""
          这个程序是简单的纸牌游戏：比大小。
          从一副牌中发一张牌。你必须预测下一张牌是高还是低。
          游戏得分是你在猜错之前做出正确预测的次数。
          """);
        //局部变量的使用含义请同学们通过阅读代码自行理解。
        int gamesPlayed = 0;
        int sumOfScores = 0;
        double averageScore;
        boolean playAgain;
        do {
            int scoreThisGame;
            scoreThisGame = play();
            sumOfScores += scoreThisGame;
            gamesPlayed++;
            System.out.print("再玩一次？ ");
            playAgain = sc.nextBoolean();
        } while (playAgain);

        averageScore = ((double)sumOfScores) / gamesPlayed;

        System.out.println();
        System.out.println("你玩了 " + gamesPlayed + " 局游戏。");
        System.out.printf("平均每局游戏的得分是： %1.3f。\n", averageScore);

    }

    private static int play() {

        Deck deck = new Deck();

        Card currentCard;
        Card nextCard;

        int correctGuesses ;
        char guess;
        deck.shuffle();
        correctGuesses = 0;
        currentCard = deck.dealCard();
        System.out.println("第1张牌是 " + currentCard);

        while (true) {

            System.out.print("预测下张牌比现在的这张牌大（H）还是小（L）？");
            do {
                guess = sc.next().charAt(0);
                guess = Character.toUpperCase(guess);
                if (guess != 'H' && guess != 'L')
                    System.out.print("只能输入H或者L：  ");
            } while (guess != 'H' && guess != 'L');

            nextCard = deck.dealCard();
            System.out.println("下张牌是： " + nextCard);

            if (nextCard.getValue() == currentCard.getValue()) {
                System.out.println("牌面与上张牌的牌面一样大。");
                System.out.println("可惜了，大小一样就算你输！");
                break;
            }
            else if (nextCard.getValue() > currentCard.getValue()) {
                if (guess == 'H') {
                    System.out.println("你预测对了。");
                    correctGuesses++;
                }
                else {
                    System.out.println("你预测错了。");
                    break;
                }
            }
            else {
                if (guess == 'L') {
                    System.out.println("你预测对了。");
                    correctGuesses++;
                }
                else {
                    System.out.println("你预测错了。");
                    break;
                }
            }

            currentCard = nextCard;
            System.out.println();
            System.out.println("现在的牌是： " + currentCard);

        }
        System.out.println();
        System.out.println("本局游戏结束。");
        System.out.println("你总共完成了 " + correctGuesses
                + " 次正确的预测。");
        System.out.println();

        return correctGuesses;
    }
}
