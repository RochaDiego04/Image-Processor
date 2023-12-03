/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package classes;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Diego
 */
public interface InterfaceServer extends Remote {
        //Pondremos todos los metodos que queremos que se compartan entre si (Cliente-servidor por RMI):
    void RegistrarCliente(InterfaceClient cliente, String NombreUsuario) throws RemoteException;
    void mensajesBroadcast(String Mensaje_Grupal) throws RemoteException;//Necesitamos una funcion para avisar a todos empezar a procesar
    void SalirCliente(InterfaceClient cliente, String NombreUsuario) throws RemoteException; //Para cuando el cliente neccesite salir del chat.
}
