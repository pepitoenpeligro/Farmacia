/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;

/**
 *
 * @author pepito
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SenioresFarmaceuticos {
    // Buffers de envio y recepción
    private static byte[] buferEnvio;
    private static byte[] buferRecepcion = new byte[256];
    
    // resultado de la conexión auxiliares.
    private static int bytesLeidos = 0;
    private static String recibido;
    private static String miCadenitaQueherecibio;
    
    // Conexión
    // Nombre del host donde se ejecuta el servidor:
    private static String host = "localhost";
    // Puerto en el que espera el servidor:
    private static int port = 8989;
    
    // Elemento de conexión
    private static Socket socketServicio = null;
    private static PrintWriter outPrinter;
    private static BufferedReader inReader;
    private static Medicamento m;

    


   public static void main(String[] args) throws ParseException{
       try{
           socketServicio = new Socket(host,port);
           outPrinter = new PrintWriter(socketServicio.getOutputStream(),true);
           inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
           
           OutputStream ss = socketServicio.getOutputStream();
     
           
           m = new Medicamento("Androcur");

           outPrinter.println(m.getNombre());
           outPrinter.println(m.getNombre()+"2");
           outPrinter.flush();
           
           //miCadenitaQueherecibio= inReader.readLine();
           //outPrinter.println(miCadenitaQueherecibio);
           
           socketServicio.close();
       } catch (IOException ex) {
            Logger.getLogger(SenioresFarmaceuticos.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
       }

       
       
       
       
   }

    
        
    
    
}
