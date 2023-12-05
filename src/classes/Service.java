package classes;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
    void registerClient(ClientCallback client) throws RemoteException;
    void sendSignalBinarizeImages() throws RemoteException;
    //void broadcastMessage(String message) throws RemoteException;
}
