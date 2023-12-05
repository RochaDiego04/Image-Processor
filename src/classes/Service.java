package classes;

import java.awt.image.BufferedImage;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Service extends Remote {
    void registerClient(ClientCallback client) throws RemoteException;
    void sendSignalBinarizeImages() throws RemoteException;
    void sendBinarizedImages(int clientId, List<BufferedImage> binarizedImages) throws RemoteException;
}
