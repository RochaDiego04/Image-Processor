
package classes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


public class ClientCallbackImpl extends UnicastRemoteObject implements ClientCallback {

    public ClientCallbackImpl() throws RemoteException {

    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        System.out.println("Received message: " + message);
    }


    @Override
    public String getUsername() throws RemoteException {
        /*return username;*/return null;
        /*return username;*/
    }
}
