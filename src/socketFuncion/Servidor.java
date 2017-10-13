/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketFuncion;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author 2087559
 */
public class Servidor {
        public static void main(String[] args){
            try {
                ServerSocket serverSocket = null;
                try {
                    serverSocket = new ServerSocket(35000);
                } catch (IOException e) {
                    System.err.println("Could not listen on port: 35000.");
                    System.exit(1);
                }
                Socket clientSocket = null;
                try {
                    clientSocket = serverSocket.accept();
                } catch (IOException e) {
                    System.err.println("Accept failed.");
                    System.exit(1);
                }
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                
                String operacionActual = "COS";
                double resultadoOperacion = 0;
                String respuesta;
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("El dato: "+inputLine+" llego al servidor");
                    if(inputLine.trim().contains("fun:") || inputLine.trim().contains("FUN:")){
                        inputLine = inputLine.toUpperCase();
                        operacionActual = inputLine.replace("FUN:", "");
                        respuesta = "Cambio de funcion a:"+operacionActual;
                    }
                    else{
                        switch (operacionActual.trim()) {
                            case "COS":
                                resultadoOperacion = Math.cos(Double.parseDouble(inputLine));
                                break;
                            case "SIN":
                                resultadoOperacion = Math.sin(Double.parseDouble(inputLine));
                                break;
                            case "TAN":
                                resultadoOperacion = Math.tan(Double.parseDouble(inputLine));
                                break;
                            default:
                                break;
                        }
                        respuesta = String.valueOf(resultadoOperacion);
                    }
                   
                    out.println(respuesta);
                    if (respuesta.equals("Respuestas: Bye."))
                        break;
                }
                out.close();
                in.close();
                clientSocket.close();
                serverSocket.close();
            } catch (IOException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
