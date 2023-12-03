
package classes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class ConnectedClient extends UnicastRemoteObject implements InterfaceClient {
    
    private final String NombreUsuario;

    public ConnectedClient(String NombreUsuario) throws RemoteException {
        this.NombreUsuario = NombreUsuario;
    }

    @Override
    public void recibirMensajes(String mensaje_Recibido) throws RemoteException {
        SwingUtilities.invokeLater(() -> {
            System.out.println("Cliente recibiendo mensaje...");
        });
    }

    @Override
    public String getNombreUsuario() throws RemoteException {
        return NombreUsuario;
    }
}
