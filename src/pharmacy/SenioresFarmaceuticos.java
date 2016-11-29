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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class SenioresFarmaceuticos {
    private static ArrayList<String> identificadores = new ArrayList<>();
    
    
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

    private static Medicamento m;
    
    private static ObjectInputStream is = null;
    private static ObjectOutputStream os = null;
    
    static Random r = new Random();
    static String id = null;
    
    String identificadorFarmacia = "Farmacia Berenjeno";
    
    private static ArrayList<Medicamento> almacenDisponible = new ArrayList<>(); // los medicamentos en el almacen
    

   public static void recibeMedicamentos(ObjectInputStream is, ArrayList<Medicamento> a, Pedido p) throws IOException, ClassNotFoundException{
       Pedido nuevo = (Pedido) is.readObject();
       for(int i = 0; i < p.getNumero(); i++){
           almacenDisponible.add(p.getMiMedicamento());
       }
       
       //almacenDisponible.add(nuevo);
       System.out.println("Se ha recibido el siguiente pedido completado: \n"
                            + "Cantidad:\t" +  nuevo.getNumero() + "\n"
                            + "Nombre:\t" + nuevo.getMiMedicamento().getNombre() + "\n"
                            + "\n");
   }

   
   public static void main(String[] args) throws ParseException, ClassNotFoundException{
       identificadores.add("Farmacias Berenjeno");
       identificadores.add("Farmacias Marisol");
       identificadores.add("Farmacias La Armillense");
       identificadores.add("Farmacias el linense junkillero");
       identificadores.add("Farmacias desconocida");
       identificadores.add("Farmacias con el Sevilla no apruebas ni a la tercera");
       identificadores.add("Farmacias aún no tengo la práctica 3 de ISE terminada ni esperanzas de tenerla");
       identificadores.add("A comprar profilacticos a otro sitio");
       identificadores.add("Aquí no se vende lubricante");
       
       id = identificadores.get(r.nextInt(9-0) + 0);
       
       
       try{
           System.out.println("Bienvenido cliente");
           socketServicio = new Socket(host,port);

           os = new ObjectOutputStream(socketServicio.getOutputStream()); // Abro el flujo de datos (Cliente -> Servidor)
           os.writeObject(id);  // Le mando al servidor mi identificación
           Pedido p = new Pedido(new Medicamento(1,"Androcurs"),3);
           os.writeObject(p); // Le envio el pedido
           //os.writeObject(new Medicamento(1,"Androcurs"));// Le mando el pedido
           
           is = new ObjectInputStream(socketServicio.getInputStream()); // Abro el flujo de datos (Servidor -> Cliente)
           //os = new ObjectOutputStream(socketServicio.getOutputStream()); // Para enviar medicamentos
//           Medicamento nuevo = (Medicamento) is.readObject();
//           System.out.println("Se ha recibido un medicamento");
//           System.out.println("El medicamento recibido es: " + nuevo.getNombre() + nuevo.getIdentificador()+ "\n");
           
           recibeMedicamentos(is, almacenDisponible, p);
            //recibeMedicamentos(is, almacenDisponible);
           

//           outPrinter.println(m.getNombre());
//           outPrinter.println(m.getNombre()+"2");
//           outPrinter.flush();

            //os.writeObject(m);
           
           //miCadenitaQueherecibio= inReader.readLine();
           //outPrinter.println(miCadenitaQueherecibio);
           
           socketServicio.close();
       } catch (IOException ex) {
            Logger.getLogger(SenioresFarmaceuticos.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
       }

       
       
       
       
   }

    
        
    
    
}
