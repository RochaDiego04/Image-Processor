
package classes;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;


public class Servidor extends UnicastRemoteObject implements Service {
    public Map<Integer, List<byte[]>> resultadosClientes = new HashMap<>();
    public Map<Integer, ClientCallback> clientes;
    private int contadorClientes;

    public Servidor() throws RemoteException {
        clientes = new HashMap<>();
        contadorClientes = 0;
    }

    @Override
    public int registerClient(ClientCallback cliente) throws RemoteException {
        contadorClientes++;
        clientes.put(contadorClientes, cliente);

        // Imprimir el array de clientes
        System.out.println("Clientes registrados: " + Arrays.toString(clientes.keySet().toArray()));

        System.out.println("Cliente registrado: " + cliente.toString() + " - ID: " + contadorClientes);

        return contadorClientes; // Devuelve el ID asignado al cliente
    }
    
    @Override
    public void sendSignalBinarizeImages() throws RemoteException {
        System.out.println("FUNCION BINARIZAR IMAGENES");
        for (ClientCallback client : clientes.values()) {
            try {
                client.receiveMessage("Signal to binarize images received.");
            } catch (RemoteException e) {
                // Manejar excepciones si no se puede enviar el mensaje a un cliente específico
                e.printStackTrace();
            }
        }
    }

    @Override
    public void sendBinarizedImages(int clientId, List<byte[]> binarizedImages) throws RemoteException {
        resultadosClientes.put(clientId, binarizedImages);

        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (int i = 0; i < binarizedImages.size(); i++) {
            final int index = i;  // Hacer final a la variable local
            byte[] imageData = binarizedImages.get(index);

            executor.submit(() -> {
                try {
                    BufferedImage image = ImageUtils.convertBytesToImage(imageData);
                    String outputImagePath = "src/images/output_images/client_" + clientId + "_image_" + index + ".jpg";
                    ImageIO.write(image, "jpg", new File(outputImagePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        // Esperar a que todos los hilos terminen
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Received and saved binarized images from Client ID " + clientId);
    }
    
    public Service connect(String Ip, Service server) {
        try {
            LocateRegistry.createRegistry(9000);

            Service service = server;


            java.rmi.Naming.rebind("rmi://" + Ip + ":9000/ImageProcessor", service);

            System.out.println("Servidor de RMI listo.");
            return service;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}