package hw01.decode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MorseCodeDecode {

    public static final String[] LETTER_MORSE = {
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."
    };
    public static final char[] LETTER_CHARS = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };
    public static final String[] DIGIT_MORSE = {
            "-----", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----."
    };
    public static final char[] DIGIT_CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };
    public static final String[] PUNCTUATION_MORSE = {
            ".-.-.-", "--..--", "---...", "..--..", ".----.", "-....-", "-..-.", "-.--.-", "-.--.-", ".-..-.", ".--.-.", "-...-", ".-.-.", ".-...", "-.-.--", "-..-"
    };
    public static final char[] PUNCTUATION_CHARS = {
            '.', ',', ':', '?', '\'', '-', '/', '(', ')', '\"', '@', '=', '+', '&', '!', 'x'
    };
    // 封装：解析单个被分割的字符
    public static char decodeSingleMorse(String morseCode) {
        for (int i = 0; i < LETTER_MORSE.length; i++) {
            if (morseCode.equals(LETTER_MORSE[i])) {
                return LETTER_CHARS[i];
            }
        }
        for (int i = 0; i < DIGIT_MORSE.length; i++) {
            if (morseCode.equals(DIGIT_MORSE[i])) {
                return DIGIT_CHARS[i];
            }
        }
        for (int i = 0; i < PUNCTUATION_MORSE.length; i++) {
            if (morseCode.equals(PUNCTUATION_MORSE[i])) {
                return PUNCTUATION_CHARS[i];
            }
        }
        return '6';
    }
    // 封装：分割并使用decodeSingleMorse解析传入的整个字符串
    public static String decodeMorse(String morseSentence) {
        StringBuilder decodedSentence = new StringBuilder();
        String[] words = morseSentence.split(" {3}");
        for (String word : words) {
            String[] letters = word.split(" ");
            for (String letter : letters) {
                decodedSentence.append(decodeSingleMorse(letter));
            }
            decodedSentence.append(" ");
        }
        return decodedSentence.toString().trim();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("decode/encode.txt"));
        String sentence = sc.nextLine();
        System.out.println(decodeMorse(sentence).toLowerCase());
    }
}
