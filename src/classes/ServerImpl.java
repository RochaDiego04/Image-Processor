 
package classes;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ServerImpl extends UnicastRemoteObject implements InterfaceServer{
     private Map<String, InterfaceClient> clientes; //Client has username and a remote object
     
    public ServerImpl() throws RemoteException {
        clientes = new HashMap<>();
    }
    
    @Override
    public void RegistrarCliente(InterfaceClient cliente, String NombreUsuario) throws RemoteException {
        // handle when 2 users have the same name
        if (!clientes.containsKey(NombreUsuario)) {
            clientes.put(NombreUsuario, cliente);
            mensajesBroadcast(NombreUsuario + " ha entrado en el chat." + "\n");
        } else {
            // Manejar el caso de nombre de usuario duplicado
            cliente.recibirMensajes("El nombre de usuario ya está en uso. Por favor, elige otro.");
        }
    }

    @Override
    public void mensajesBroadcast(String Mensaje_Grupal) throws RemoteException {
        //All clients must receive this message
        for (InterfaceClient cliente : clientes.values()) {
            cliente.recibirMensajes(Mensaje_Grupal);
        }
    }
    
    @Override
    public void SalirCliente(InterfaceClient cliente, String NombreUsuario) throws RemoteException {
        if (clientes.containsKey(NombreUsuario)) {
            clientes.remove(NombreUsuario, cliente);
            mensajesBroadcast(NombreUsuario + " ha salido del chat." + "\n");
        }
    }

    public void EstablecerConexion(String conexion_IP) {
        try {
            InterfaceServer interfaceServidor = new ServerImpl();
            LocateRegistry.createRegistry(6969);
            java.rmi.Naming.rebind("//" + conexion_IP + ":6969/InterfazServidor", interfaceServidor);
            System.out.println("Image Processor Server is ready!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
