
package classes;
import java.rmi.Remote;
import java.rmi.RemoteException;



public interface ClientCallback extends Remote{
    void receiveMessage(String message, Service server) throws RemoteException;
}
