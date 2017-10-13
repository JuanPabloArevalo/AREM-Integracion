/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datagramas;

import static java.lang.Thread.sleep;

public class ClienteDatagrama {
    public static void main(String[] args) throws InterruptedException {
        int ultimo = 0;
        String hora = "";
        ThreadDatagrama hiloDataGrama = new ThreadDatagrama(hora);
        hiloDataGrama.start();
        while(true){
            sleep(5000);
            hora = hiloDataGrama.getDateFinal();
            System.out.println("Fecha Y Hora:"+hora);
            if(ultimo == hiloDataGrama.getUltimoEstado().intValue()){
                hiloDataGrama.stop();
                hiloDataGrama = new ThreadDatagrama(hora);
                hiloDataGrama.start();
                ultimo = 0;
            }
            else{
                ultimo = hiloDataGrama.getUltimoEstado().intValue();
            }
        }
    }
}


