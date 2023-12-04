
package classes;

import java.rmi.Naming;
import java.util.Scanner;

public class Cliente {
    public void connect(String ip, Service service) {
        try {
            // Busca el objeto remoto

            service = (Service) Naming.lookup("rmi://" + ip + ":9000/ImageProcessor");

            ClientCallbackImpl clientCallback = new ClientCallbackImpl();
            service.registerClient(clientCallback);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}