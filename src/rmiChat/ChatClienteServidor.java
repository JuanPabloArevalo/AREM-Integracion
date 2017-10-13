/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiChat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClienteServidor extends Remote {
    public void mostrarMensaje(String cadena) throws RemoteException;
}