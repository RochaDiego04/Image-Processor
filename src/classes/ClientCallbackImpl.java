
package classes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;


public class ClientCallbackImpl extends UnicastRemoteObject implements ClientCallback {

    public ClientCallbackImpl() throws RemoteException {

    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        System.out.println("Received message: " + message);

        String inputPath = "src/images/input_images/";
        String outputPath = "src/images/output_images/";

        File inputDirectory = new File(inputPath);
        File outputDirectory = new File(outputPath);

        //  Ensure that the output directory exists
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }

        File[] imageFiles = inputDirectory.listFiles();

        if (imageFiles != null) {
            int numThreads = 2;
            ExecutorService executor = Executors.newFixedThreadPool(numThreads);

            for (File imageFile : imageFiles) {
                if (imageFile.isFile()) {
                    String inputImage = imageFile.getAbsolutePath();
                    String outputImage = outputPath + imageFile.getName();

                    executor.submit(() -> {
                        Image_Concurrent obj = new Image_Concurrent(inputImage);
                        obj.binarizeImage(100);
                        BufferedImage img = obj.printImage();

                        try {
                            ImageIO.write(img, "jpg", new File(outputImage));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        // Puedes imprimir mensajes o realizar otras acciones después de binarizar la imagen
                        System.out.println("Image binarized and saved: " + outputImage);
                    });
                }
            }

            executor.shutdown();
            try {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public String getUsername() throws RemoteException {
        /*return username;*/return null;
        /*return username;*/
    }
    
    public synchronized void binarizeImage(String inputImagePath, String outputImagePath) {

    }

}
