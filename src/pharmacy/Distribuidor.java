/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author pepito
 */
public class Distribuidor {
    // Datos a manejar
    static private ArrayList<Medicamento> miAlmacen = new ArrayList();

    // Conexión
    static ServerSocket socketServidor = null;
    static Socket socketConexion = null;
    static final int port = 8989;
    
    
    private static ObjectInputStream is = null;
    private static ObjectOutputStream os = null;
    
    
    
    public static void LeerXML(String path) throws ParserConfigurationException, SAXException, IOException{
        
        File fXmlFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
            
        doc.getDocumentElement().normalize();
            
        NodeList nList = doc.getElementsByTagName("dcpf"); // Leo los elementos dcpf
        
        // Leo cada una de las entradas en la base de datos xml
        for(int i = 0; i < nList.getLength(); i++){
            Node nNode = nList.item(i);
                
            
            if(nNode.getNodeType() == Node.ELEMENT_NODE){
                Element eElement = (Element) nNode;

                // Meto el medicamento en el almacén
                miAlmacen.add(new Medicamento(
                                        (int)Long.parseUnsignedLong(eElement.getElementsByTagName("codigodcpf").item(0).getTextContent()),
                                        eElement.getElementsByTagName("nombredcpf").item(0).getTextContent()
                                        ));
            }
            
        }
        
        
    }
    
    
    
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException{
        System.out.println("##########################################################################");
        System.out.println("Bienvenido al sistema de gestión de Farmacias LaVeneno®\n");
        System.out.println("José Antonio Córdoba Gómez");
        System.out.println("Marta Arenas Martínez");
        System.out.println("Motivación: (ninguna) Práctica 2 de Fundamentos de Redes\n");
        // Antes de realizar la conexión del servidor, vamos a cargar los medicamentos disponibles
        LeerXML("resources/DICCIONARIO_DCPF.xml");
        //miAlmacen.toString();
        System.out.println("##########################################################################");
        
        
        
        
        try {
            socketServidor = new ServerSocket(port);
            System.out.println("Estoy esperando a que los señores farmacéuticos se conecten.\nEspero que no tarden mucho porque tengo la práctica 3 de ISE sin acabar\n");

            do {
                
                socketConexion = socketServidor.accept();
                is = new ObjectInputStream(socketConexion.getInputStream()); // Abro el flujo de datos de ENTRADA (Cliente -> Servidor)
                System.out.println("Se conectó:\t" + (String) is.readObject() + "\n");  // Leo qué farmacia se conectó (identificación del cliente)
                
                
                // Comienzo a funcionar
                Pedido p = (Pedido)is.readObject(); // Recibo el pedido de dicho cliente
                System.out.println("He recibido un pedido: " + p.getMiMedicamento().getNombre() + "\t" + p.getNumero() + " unidades" + "\n");
                os = new ObjectOutputStream(socketConexion.getOutputStream()); // Abro el flujo de datos de SALIDA (Servidor -> Cliente)
                
                // Mando lo que me piden
                Pedido nuevo = new Pedido(p.getMiMedicamento(), p.getNumero());
                os.writeObject(nuevo); // Le envío el pedido
                socketConexion.close();

               
            } while (true);

        } catch (IOException e) {
            System.err.println("Error al escuchar en el puerto " + port);
        }
        
    }

}
