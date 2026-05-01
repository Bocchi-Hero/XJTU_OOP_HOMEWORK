package hw02.picture;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Picture {
    protected BufferedImage image;
    private Graphics2D graphics;
    private int width, height;
    public Picture(int width, int height){
        this.width = width;
        this.height = height;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        graphics = image.createGraphics();
    }
    public Picture(String filename) throws IOException {
        File file = new File(filename);
        image = ImageIO.read(file);
        width = image.getWidth();
        height = image.getHeight();
        graphics = image.createGraphics();
    }
    public int height(){ return image.getHeight(); }
    public int width(){ return image.getWidth(); }
    public Graphics2D getGraphics(){ return graphics;}
    public Color get(int col, int row){
        int rgb = image.getRGB(col, row);
        return new Color(rgb);
    }
    public void setColor(int col, int row, Color color){
        int rgb = color.getRGB();
        image.setRGB(col, row, rgb);
    }
    public void save(String filename) throws IOException {
        String suffix = filename.substring(filename.lastIndexOf('.') + 1);
        if ("jpg".equalsIgnoreCase(suffix) || "png".equalsIgnoreCase(suffix)){
            ImageIO.write(image, suffix, new File(filename));
        }
    }
    public void display(){
        SwingUtilities.invokeLater(() ->
        {
            JFrame frame = new JFrame("显示...");
            JLabel label = new JLabel(new ImageIcon(image));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(width, height);
            frame.add(label);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
