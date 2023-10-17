
package classes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;



public class Image_Sequential {
    public static final int allowedWidth = 1024;
    public static final int allowedHeight = 820;
    private Color[][] array; //Pixels array
    private int width;
    private int height;
    
    public Image_Sequential(String file) {
        array = new Color[allowedHeight][allowedWidth]; // Initialize pixels array
        loadImage(file);
    }
    
    public void loadImage(String file){
        BufferedImage bf = null;
        try {
            bf = ImageIO.read(new File(file));
        } catch (IOException ex) {
            Logger.getLogger(Image_Sequential.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (bf.getWidth() < allowedWidth) { // resize image if exceeds max width
            width = bf.getWidth();
        } else {
            width = allowedWidth;
        }
        
        if (bf.getHeight() < allowedHeight) { // resize image if exceeds max height
            height = bf.getHeight();
        } else {
            height = allowedHeight;
        }
        
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                count++;
                array[i][j] = new Color(bf.getRGB(j, i)); // get the rgb from the pixel of our image as an integer
                //System.out.println(count + ":" + "RedGreenBlue:" + bf.getRGB(j, i));
            }
        }
    }
    
    public BufferedImage resize(String file, double percentage){
        BufferedImage bf = null;
        try {
            bf = ImageIO.read(new File(file));
        } catch (IOException ex) {
            Logger.getLogger(Image_Sequential.class.getName()).log(Level.SEVERE, null, ex);
        }
        int originalWidth = bf.getWidth();
        int originalHeight = bf.getHeight();
        int scaledWidth = (int)(percentage * originalWidth);
        int scaledHeight = (int)(percentage * originalHeight);
        BufferedImage newImage = new BufferedImage(scaledWidth, scaledHeight, bf.getType());
        Graphics2D g = newImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bf, 0, 0, scaledWidth, scaledHeight, 0, 0, originalWidth, originalHeight, null);
        g.dispose();
        return newImage;
    }
    
    public void binarizeImage(double threshold){ //go through matrix and
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = array[i][j];
                int average = (pixel.getBlue() + pixel.getRed() + pixel.getGreen()) / 3;
                if (average < threshold) {
                    array[i][j] = Color.BLACK;
                } else {
                    array[i][j] = Color.WHITE;
                }
            }
        }
    }
    
    public BufferedImage printImage(){
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                output.setRGB(j, i, array[i][j].getRGB());
            }
        }
        return output;
    }
    
}
