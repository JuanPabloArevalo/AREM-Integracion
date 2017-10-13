/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datagramas;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JuanArevaloMerchan
 */
public class ThreadDatagrama extends Thread{
    private String dateFinal;
    private AtomicInteger ultimoEstado = new AtomicInteger(0);
    
    public ThreadDatagrama(String fecha){
        this.dateFinal = fecha;
    }
    
    public void run(){
        while(true){
            try {
                sleep(1000);
                try {
                    byte[] sendBuf = new byte[256];
                    DatagramSocket socket = null;
                    byte[] buf = new byte[256];
                    InetAddress address = null;
                    address = InetAddress.getByName("127.0.0.1");
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
                    socket = new DatagramSocket();
                    socket.send(packet);
                    packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    dateFinal = new String(packet.getData(), 0, packet.getLength());
                    ultimoEstado.incrementAndGet();
                } catch (SocketException ex) {
                    Logger.getLogger(ClienteDatagrama.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(ClienteDatagrama.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ClienteDatagrama.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadDatagrama.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Retorna la fecha final
     * @return 
     */
    public String getDateFinal(){
        return dateFinal;
    }
    
    public AtomicInteger getUltimoEstado(){
        return ultimoEstado;
    }
}
