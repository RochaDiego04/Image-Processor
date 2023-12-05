
package classes;

import java.rmi.Naming;
import java.util.Scanner;

public class Cliente {
    public int connect(String ip, Service service) {
        try {
            // Busca el objeto remoto
            service = (Service) Naming.lookup("rmi://" + ip + ":9000/ImageProcessor");

            ClientCallbackImpl clientCallback = new ClientCallbackImpl();

            // Registra al cliente y obtén el ID asignado
            int clientId = service.registerClient(clientCallback);

            // Configura el ID en la instancia de ClientCallbackImpl
            clientCallback.setClientId(clientId);

            // Puedes almacenar el ID para su uso posterior si es necesario
            System.out.println("Cliente conectado con ID: " + clientId);

            return clientId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Manejo de error, devuelve un valor que indique un fallo
    }
}