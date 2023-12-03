
package classes;

import java.rmi.Naming;
import java.rmi.RemoteException;
import javax.swing.JTextArea;


public class ClientImpl {
    InterfaceServer conexionServidor;

    public void ComenzarCliente(String NombreUsuario, String direccion_IP) {
        try {
            //direccion_IP = "localhost";
            String direccion_URL = "//" + direccion_IP + ":6969/InterfazServidor";
            conexionServidor = (InterfaceServer) Naming.lookup(direccion_URL);

            ConnectedClient interfaceCliente = new ConnectedClient(NombreUsuario);
            conexionServidor.RegistrarCliente(interfaceCliente, NombreUsuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enviarMensajeGrupal(String mensaje_Grupal, String NombreUsuario) throws RemoteException {
        if (conexionServidor != null) {
            conexionServidor.mensajesBroadcast(NombreUsuario + ": " + mensaje_Grupal);
        } else {
            // Manejo de la situación en la que conexionServidor es nulo
        }
    }
}
