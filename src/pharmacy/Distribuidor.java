/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author pepito
 */
public class Distribuidor {
    // Datos a manejar
    static private ArrayList<Medicamento> miAlmacen = new ArrayList(1000);
    static int bytesLeidos = 0;
    static byte []buffer = new byte[256];
    
    // Conexión
    static ServerSocket socketServidor = null;
    static Socket socketConexion = null;
    static int port = 8989;
    private static BufferedReader inReader;
    private static String miCadenitaQueherecibio;
    
    
    
    public static void main(String[] args){
        
        try {
            socketServidor = new ServerSocket(port);
            System.out.println("Estoy esperando maricón");

            do {
                socketConexion = socketServidor.accept();
                System.out.println("Hay al menos un cliente que se ha conectado a mi");
                inReader = new BufferedReader(new InputStreamReader(socketConexion.getInputStream()));
                miCadenitaQueherecibio = inReader.readLine();
                System.out.println(miCadenitaQueherecibio.toString());
                miCadenitaQueherecibio = inReader.readLine();
                System.out.println(miCadenitaQueherecibio.toString());
                
                // Aquí gestiono mi estructra de datos
            } while (true);

        } catch (IOException e) {
            System.err.println("Error al escuchar en el puerto " + port);
        }
        
        
        
        
    }
    
    
    
    
    
}
