/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiChat;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class ClienteServidorChat implements ChatClienteServidor{
    private static ChatClienteServidor servidorChat = null;
    private static ChatClienteServidor clienteChat = null;
    
    @Override
    public void mostrarMensaje(String cadena) throws RemoteException {
        System.out.println("Ha recibido el siguiente mensaje: "+cadena);
        System.out.println ("Mensaje para enviar:");
    }
    
    
    public ClienteServidorChat(String ipRMIregistry,int puertoRMIregistry, String nombreDePublicacion){
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            //Crea el skeleton
            servidorChat = (ChatClienteServidor) UnicastRemoteObject.exportObject(this,0);
            //Localiza el Registry 
            Registry registry = LocateRegistry.getRegistry(ipRMIregistry, puertoRMIregistry);
            //Publicar objeto en el registry 
            registry.rebind(nombreDePublicacion, servidorChat);
            System.out.println("Servidor Listo!!");
        } catch (Exception e) {
            System.err.println("Echo server exception:");
            e.printStackTrace();
        }
        
    }
    
    public static void ejecutaCliente(String ipRmiregistry, int puertoRmiRegistry,String nombreServicio) {
        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            
            //Localiza el registry
            Registry registry = LocateRegistry.getRegistry(ipRmiregistry, puertoRmiRegistry);
            //Obtiene la referencia remota se crea el stub
            clienteChat = (ChatClienteServidor) registry.lookup(nombreServicio);
            enviarMensajes();
            
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteServidorChat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ClienteServidorChat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
    
    public static void enviarMensajes() throws RemoteException{
        while(true){
            System.out.println ("Mensaje para enviar:");
            String entradaTeclado = "";
            Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
            entradaTeclado = entradaEscaner.nextLine (); 
            clienteChat.mostrarMensaje(entradaTeclado);
        }
    }


    
    public static void main(String[] args){
        System.out.println ("Ingrese la Ip del Registry:");
        Scanner entradaEscaner = new Scanner (System.in); 
        String ipRegistry = entradaEscaner.nextLine ();  
        System.out.println ("Ingrese el puerto del registry:");
        entradaEscaner = new Scanner (System.in); 
        String puerto = entradaEscaner.nextLine ();  
        System.out.println ("Ingrese el nombre del objeto en el registry:");
        entradaEscaner = new Scanner (System.in); 
        String nombreRegistry = entradaEscaner.nextLine ();  
        ClienteServidorChat ec = new ClienteServidorChat(ipRegistry, Integer.parseInt(puerto), nombreRegistry);
        
        System.out.println ("Configuración cliente");        
        System.out.println ("Ingrese el nombre del objeto del registry que va a buscar:");
        entradaEscaner = new Scanner (System.in); 
        nombreRegistry = entradaEscaner.nextLine ();  
        System.out.println ("Ingrese el puerto del registry a buscar:");
        entradaEscaner = new Scanner (System.in); 
        puerto = entradaEscaner.nextLine ();  
        System.out.println ("Ingrese la Ip del Registry a buscar:");
        entradaEscaner = new Scanner (System.in); 
        ipRegistry = entradaEscaner.nextLine ();  
        ejecutaCliente(ipRegistry, Integer.parseInt(puerto), nombreRegistry);
        
    }

    
    
}
