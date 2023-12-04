
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
        /*SwingUtilities.invokeLater(() -> {
            if (jTextArea != null) {
                jTextArea.append(message + "\n");
            } else {
                System.out.println("JTextArea not set for user: " + username);
            }
        });*/
    }


    @Override
    public String getUsername() throws RemoteException {
        /*return username;*/return null;
        /*return username;*/
    }
}
