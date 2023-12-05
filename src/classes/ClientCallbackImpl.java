
package classes;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;


public class ClientCallbackImpl extends UnicastRemoteObject implements ClientCallback {
    
     private int clientId;

    public ClientCallbackImpl() throws RemoteException {

    }
    
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        System.out.println("Received message: " + message);

        String inputPath = "src/images/input_images/";
        String outputPath = "src/images/output_images/";

        File inputDirectory = new File(inputPath);


        File[] imageFiles = inputDirectory.listFiles();

        if (imageFiles != null) {
            int numThreads = 2;
            ExecutorService executor = Executors.newFixedThreadPool(numThreads);
            List<byte[]> binarizedImages = new ArrayList<>();

            for (File imageFile : imageFiles) {
                if (imageFile.isFile()) {
                    String inputImage = imageFile.getAbsolutePath();

                    executor.submit(() -> {
                        Image_Concurrent obj = new Image_Concurrent(inputImage);
                        obj.binarizeImage(100);
                        BufferedImage img = obj.printImage();
                        
                        try {
                            byte[] imageBytes = convertToBytes(img);
                            synchronized (binarizedImages) {
                                binarizedImages.add(imageBytes);
                            }
                            // Puedes imprimir mensajes o realizar otras acciones después de binarizar la imagen
                            System.out.println("Image binarized and saved: " + inputImage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }

            executor.shutdown();
            try {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                sendBinarizedImagesToServer(binarizedImages);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void sendBinarizedImagesToServer(List<byte[]> binarizedImages) {
        try {
            Service server = (Service) java.rmi.Naming.lookup("rmi://localhost:9000/ImageProcessor");

            server.sendBinarizedImages(clientId, binarizedImages);

            System.out.println("Binarized images sent to the server.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private byte[] convertToBytes(BufferedImage image) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

}
