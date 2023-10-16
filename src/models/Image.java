
package models;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;



public class Image {
    public static final int allowedWidth = 1024;
    public static final int allowedHeight = 720;
    private Color[][] array; //Pixels array
    private int width;
    private int height;
    
    public Image(String file) {
        array = new Color[allowedHeight][allowedWidth]; // Initialize pixels array
        loadImage(file);
    }
    
    public void loadImage(String file){
        BufferedImage bf = null;
        try {
            bf = ImageIO.read(new File(file));
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
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
    

    public static void main(String[] args) throws IOException {
        String inputPath = "src/images/input_images/";
        String outputPath = "src/images/output_images/";

        File inputDirectory = new File(inputPath);
        File outputDirectory = new File(outputPath);

        // Ensure that the output directory exists
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }
        
        long startTime = System.currentTimeMillis(); // Time the task(s)
        
        File[] imageFiles = inputDirectory.listFiles(); // get a list of files at input directory

        if (imageFiles != null) {
            for (File imageFile : imageFiles) {
                if (imageFile.isFile()) { // validation of image formats (.jpg, .png) is done when uploading files
                    String inputImage = imageFile.getAbsolutePath();
                    String outputImage = outputPath + imageFile.getName();

                    Image obj = new Image(inputImage);
                    obj.binarizeImage(100);
                    BufferedImage img = obj.printImage();
                    ImageIO.write(img, "jpg", new File(outputImage));

                    System.out.println("Processed: " + imageFile.getName());
                }
            }
        }
       
        long sequentialTime  = System.currentTimeMillis() - startTime; // Finisihing task timing
        System.out.println("Sequential time " + sequentialTime + "ms");
    }
}
