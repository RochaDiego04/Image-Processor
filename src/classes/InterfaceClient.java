
package classes;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface InterfaceClient extends Remote{
    //Implementaremos los metodos necesarios para poder poner en comunicacion el cliente con el servidor!
    String getNombreUsuario() throws RemoteException;//Obtiene el nombre del usuario
    void recibirMensajes(String mensajeRecibido) throws RemoteException;//Recibirá los mensajes
}
