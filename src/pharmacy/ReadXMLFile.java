/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mibase;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author pepito
 */
public class ReadXMLFile {
    
    public static void main(String[] args) {
        try{
            
            File fXmlFile = new File("resources/menu.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            
            doc.getDocumentElement().normalize();
            System.out.println("Elemento raíz " + doc.getDocumentElement().getNodeName());
            
            NodeList nList = doc.getElementsByTagName("PlatoPrincipal");
            System.out.println("#######################");
            
            
            for(int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                
                System.out.println("Elemento actual: " + nNode.getNodeName());
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    System.out.println("Producto número [" + i + "]");
                    System.out.println("Código:\t" + eElement.getAttribute("codigo"));
                    System.out.println("Nombre:\t" + eElement.getElementsByTagName("nombre").item(0).getTextContent());
                    System.out.println("Calorias:\t" + eElement.getElementsByTagName("calorias").item(0).getTextContent());
                    System.out.println("Kilojulios:\t" + eElement.getElementsByTagName("kilojulios").item(0).getTextContent());
                    System.out.println("Tamaño de la porción:\t" + eElement.getElementsByTagName("Tamaño_porcion").item(0).getTextContent());
                    System.out.println("Descripción:\t" + eElement.getElementsByTagName("description").item(0).getTextContent());
                    System.out.println("______________________________________");
                    System.out.println();
                }
            }
            
            nList = doc.getElementsByTagName("Postres");
            
            for(int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                
                System.out.println("Elemento actual: " + nNode.getNodeName());
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    System.out.println("Producto número [" + i + "]");
                    System.out.println("Código:\t" + eElement.getAttribute("codigo"));
                    System.out.println("Nombre:\t" + eElement.getElementsByTagName("nombre").item(0).getTextContent());
                    System.out.println("Calorias:\t" + eElement.getElementsByTagName("calorias").item(0).getTextContent());
                    System.out.println("Kilojulios:\t" + eElement.getElementsByTagName("kilojulios").item(0).getTextContent());
                    System.out.println("Tamaño de la porción:\t" + eElement.getElementsByTagName("Tamaño_porcion").item(0).getTextContent());
                    System.out.println("Descripción:\t" + eElement.getElementsByTagName("description").item(0).getTextContent());
                    System.out.println("______________________________________");
                    System.out.println();
                }
            }
            
            nList = doc.getElementsByTagName("Entradas");
            
            for(int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                
                System.out.println("Elemento actual: " + nNode.getNodeName());
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    System.out.println("Producto número [" + i + "]");
                    System.out.println("Código:\t" + eElement.getAttribute("codigo"));
                    System.out.println("Nombre:\t" + eElement.getElementsByTagName("nombre").item(0).getTextContent());
                    System.out.println("Calorias:\t" + eElement.getElementsByTagName("calorias").item(0).getTextContent());
                    System.out.println("Kilojulios:\t" + eElement.getElementsByTagName("kilojulios").item(0).getTextContent());
                    System.out.println("Tamaño de la porción:\t" + eElement.getElementsByTagName("Tamaño_porcion").item(0).getTextContent());
                    System.out.println("Descripción:\t" + eElement.getElementsByTagName("description").item(0).getTextContent());
                    System.out.println("______________________________________");
                    System.out.println();
                }
            }
            
            
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        System.out.println("DIIGOO");
    }
    
    
}
