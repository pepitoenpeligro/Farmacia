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
import java.io.File;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SenioresFarmaceuticos {
    private static ArrayList<String> identificadores = new ArrayList<>();
    
    
    // Buffers de envio y recepción
    private static byte[] buferEnvio;
    private static byte[] buferRecepcion = new byte[256];
    
    // resultado de la conexión auxiliares.
    private static int bytesLeidos = 0;
    
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
    
    static int r = 0;
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

   public static void rellenarAlmacen (String path) throws ParserConfigurationException, SAXException, IOException{
        
        File fXmlFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
            
        doc.getDocumentElement().normalize();
            
        NodeList nList = doc.getElementsByTagName("dcpf"); // Leo los elementos dcpf
        
        // Leo cada una de las entradas en la base de datos xml
        
        int inicio = (int) Math.random()*nList.getLength() + 0;
        int fin = (int) Math.random()+nList.getLength() + inicio;
        
        for(int i= inicio; i < fin; i++){
            Node nNode = nList.item(i);
                
            
            if(nNode.getNodeType() == Node.ELEMENT_NODE){
                Element eElement = (Element) nNode;

                // Meto el medicamento en el almacén
                almacenDisponible.add(new Medicamento(
                                        (int)Long.parseUnsignedLong(eElement.getElementsByTagName("codigodcpf").item(0).getTextContent()),
                                        eElement.getElementsByTagName("nombredcpf").item(0).getTextContent()
                                        ));
            }
            
        }
        
        
    }
    
   public static void main(String[] args) throws ParseException, ClassNotFoundException, ParserConfigurationException, SAXException{
       identificadores.add("Farmacias Farmatodo");
       identificadores.add("Farmacias Poniente");
       identificadores.add("Farmacias La Armillera");
       identificadores.add("Farmacias SanaSana");
       identificadores.add("Farmacias Granada");
       identificadores.add("Farmacias Sevilla");
       identificadores.add("Farmacias CruzVerde");
       identificadores.add("Farmacias OptimaFar");
       identificadores.add("Farmacias Popular");
       int contador = 0;
        do {
            r = (int) Math.random()*(identificadores.size()-1) + 0;
            System.out.println("Identificador " + identificadores.size());
            System.out.println("Random 1 " + r);
            id = identificadores.get(r);
            System.out.println("Farmacias " + identificadores.get(r));


            
            try{


                System.out.println("Bienvenido cliente");
                socketServicio = new Socket(host,port);

                os = new ObjectOutputStream(socketServicio.getOutputStream()); // Abro el flujo de datos (Cliente -> Servidor)
                os.writeObject(id);  // Le mando al servidor mi identificación

                rellenarAlmacen("resources/DICCIONARIO_DCPF.xml");

                r = (int) Math.random()*(almacenDisponible.size()) + 0;

                System.out.println(" RANDOM 2" + r);
                System.out.println("Medicamento " + almacenDisponible.get(r).getNombre());


                Pedido p = new Pedido(almacenDisponible.get(r),(int)Math.random()*20 + 1);

                os.writeObject(p); // Le envio el pedido
                //os.writeObject(new Medicamento(1,"Androcurs"));// Le mando el pedido

                is = new ObjectInputStream(socketServicio.getInputStream()); // Abro el flujo de datos (Servidor -> Cliente)


                recibeMedicamentos(is, almacenDisponible, p);


                socketServicio.close();
                contador++;

            } catch (IOException ex) {
                 Logger.getLogger(SenioresFarmaceuticos.class.getName()).log(Level.SEVERE, null, ex);
                 ex.printStackTrace();
            }
        }while (contador <= 10);
       
       
       
       
   }

    
        
    
    
}
