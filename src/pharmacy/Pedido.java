/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;

import java.io.Serializable;

/**
 *
 * @author pepito
 */
public class Pedido implements Serializable {
    private Medicamento miMedicamento;
    private int numero;

    public Medicamento getMiMedicamento() {
        return miMedicamento;
    }

    public void setMiMedicamento(Medicamento miMedicamento) {
        this.miMedicamento = miMedicamento;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Pedido(Medicamento miMedicamento, int numero) {
        this.miMedicamento = miMedicamento;
        this.numero = numero;
    }
    
    
}
