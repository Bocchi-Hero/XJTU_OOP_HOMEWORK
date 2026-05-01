package hw02.picture;

import hw02.complex.Complex;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MandelbrotGenerator {
    public static void main(String[] args) throws IOException {
        int width = 768;
        int height = 512;

        Picture pic = new Picture(width, height);
        Color[] colorTable = loadColors("picture/colorlist.txt");


        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                // Work 1: Coordinate Transformation
                double x = -3.0 + (double) col / width * 6.0;
                double y = 2.0 - (double) row / height * 4.0;

                // Work 2: Determine whether it is Mandelbrot
                Complex z0 = new Complex(x, y);

                int n = IterativeJudgment(z0);

                // Work 3: Coloring
                if (n == 255) {
                    pic.setColor(col, row, Color.BLACK);
                } else {
                    pic.setColor(col, row, colorTable[n]);
                }
            }
        }
        pic.display();
        pic.save("picture/mandelbrotImage.png");
    }

    // 用于迭代算法的函数
    private static int IterativeJudgment(Complex z0) {
        Complex z = z0;
        int max = 255;
        for (int t = 0; t < max; t++) {
            if (z.abs() > 2) return t;
            z = z.multiply(z).add(z0);
        }
        return max;
    }

    // 用于读取文件中颜色参数并返回颜色数组的函数
    private static Color[] loadColors(String path) throws IOException {
        Color[] colors = new Color[256];
        File file = new File(path);

        try (Scanner sc = new Scanner(file)) {
            int i = 0;
            while (sc.hasNextLine() && i < 256) {
                String line = sc.nextLine();
                if (line.isBlank()) continue;

                String[] parts = line.split(" ");
                int red = Integer.parseInt(parts[0]);
                int green = Integer.parseInt(parts[1]);
                int blue = Integer.parseInt(parts[2]);

                colors[i] = new Color(red, green, blue);
                i++;
            }
        }
        return colors;
    }
}
