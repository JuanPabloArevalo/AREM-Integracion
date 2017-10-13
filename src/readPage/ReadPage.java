/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readPage;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.net.URL;
import java.io.File;
/**
 *
 * @author 2087559
 */
public class ReadPage {
    private static final String NAME_LOGFILE = "resultado.html";
    private static File LOG_FILE = new File(NAME_LOGFILE);
    
    
    public static void main(String[] args){
	//Pedir la URL
        String urlIngreso = "";
        System.out.println ("Por favor ingrese la URL: ");
        Scanner entradaEscaner = new Scanner (System.in); 
        urlIngreso = entradaEscaner.nextLine (); 
        //Crear URL
        URL url = null;
        try {
            url = new URL(urlIngreso);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine = null;
            FileWriter out = new FileWriter( LOG_FILE, true );
            PrintWriter log = new PrintWriter( out );
            while ((inputLine = reader.readLine()) != null) {
            //Ecrbir en el html
	        log.println(inputLine);
            }	
            log.close( );
            out.close( ); 
	} catch (MalformedURLException e) {
            e.printStackTrace();
	} catch (IOException e) {
            e.printStackTrace();
	}
	
    }
}
