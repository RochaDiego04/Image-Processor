
package classes;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Servidor extends UnicastRemoteObject implements Service {
    public Map<Integer, String[]> resultadosClientes = new HashMap<>();
    public Map<Integer, ClientCallback> clientes;
    private int contadorClientes;

    public Servidor() throws RemoteException {
        clientes = new HashMap<>();
        contadorClientes = 0;
    }

    @Override
    public void registerClient(ClientCallback cliente) throws RemoteException {
        contadorClientes++;
        clientes.put(contadorClientes, cliente);

        // Imprimir el array de clientes
        System.out.println("Clientes registrados: " + Arrays.toString(clientes.keySet().toArray()));

        System.out.println("Cliente registrado: " + cliente.toString() + " - ID: " + contadorClientes);

    }


    /*@Override
    public void broadcastMessage(String message) throws RemoteException {
        for (ClientCallback client : clients) {
            client.receiveMessage(message);
        }
    }*/
    
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