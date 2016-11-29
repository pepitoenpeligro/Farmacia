/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author pepito
 */
public class Medicamento implements Serializable{
    private long identificador;
    private String nombre;
    


    public Medicamento(int identificador, String nombre) {
        this.identificador = identificador;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(long identificador) {
        this.identificador = identificador;
    }


  
}
