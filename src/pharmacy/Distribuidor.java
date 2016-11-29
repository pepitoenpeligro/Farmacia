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
    static int bytesLeidos = 0;
    static byte []buffer = new byte[256];
    
    // Conexión
    static ServerSocket socketServidor = null;
    static Socket socketConexion = null;
    static int port = 8989;
    private static BufferedReader inReader;
    private static String miCadenitaQueherecibio;
    
    private static ObjectInputStream is = null;
    private static ObjectOutputStream os = null;
    
    public static void LeerXML(String path) throws ParserConfigurationException, SAXException, IOException{
        
        File fXmlFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
            
        doc.getDocumentElement().normalize();
        System.out.println("Elemento raíz " + doc.getDocumentElement().getNodeName());
            
        NodeList nList = doc.getElementsByTagName("dcpf"); // Leo los elementos dcpf
        System.out.println("#######################");
        
        // Leo cada una de las entradas en la base de datos xml
        for(int i = 0; i < nList.getLength(); i++){
            Node nNode = nList.item(i);
                
            System.out.println("Elemento actual: " + nNode.getNodeName());
            if(nNode.getNodeType() == Node.ELEMENT_NODE){
                Element eElement = (Element) nNode;
                System.out.println("Producto número [" + i + "]");
                System.out.println("Código:\t" + eElement.getElementsByTagName("codigodcpf").item(0).getTextContent());
                System.out.println("Nombre:\t" + eElement.getElementsByTagName("nombredcpf").item(0).getTextContent());
                System.out.println("______________________________________");
                System.out.println();
                // Meto el medicamento en el almacén
                miAlmacen.add(new Medicamento(
                                        (int)Long.parseUnsignedLong(eElement.getElementsByTagName("codigodcpf").item(0).getTextContent()),
                                        eElement.getElementsByTagName("nombredcpf").item(0).getTextContent()
                                        ));
            }
            
        }
        
        
    }
    
    
    
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
        
        // Antes de realizar la conexión del servidor, vamos a cargar los medicamentos disponibles
        LeerXML("resources/DICCIONARIO_DCPF.xml");
        miAlmacen.toString();
        
        
        
        try {
            socketServidor = new ServerSocket(port);
            System.out.println("Estoy esperando maricón");

            do {
                socketConexion = socketServidor.accept();
                System.out.println("Hay al menos un cliente que se ha conectado a mi");
                //inReader = new BufferedReader(new InputStreamReader(socketConexion.getInputStream()));
                
            //is = new ObjectInputStream(socketConexion.getInputStream()); // Para recibir medicamentos
            os = new ObjectOutputStream(socketConexion.getOutputStream()); // Para enviar medicamentos
            
            Medicamento m = new Medicamento(1,"Androcur");
            os.writeObject(m);
            socketConexion.close();
//                miCadenitaQueherecibio = inReader.readLine();
//                System.out.println(miCadenitaQueherecibio.toString());
//                miCadenitaQueherecibio = inReader.readLine();
//                System.out.println(miCadenitaQueherecibio.toString());
                
                // Aquí gestiono mi estructra de datos
            } while (true);

        } catch (IOException e) {
            System.err.println("Error al escuchar en el puerto " + port);
        }
        
        
        
        
    }
    
    
    
    
    
}
