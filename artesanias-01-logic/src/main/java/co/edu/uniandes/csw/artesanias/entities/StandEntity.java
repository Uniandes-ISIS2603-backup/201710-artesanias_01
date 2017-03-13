/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author ds.tapia10
 */

@Entity
public class StandEntity {
    
    @Id
    private int numeroStand;
    
    private int tamanio;
    
    private double precio;
    
    private String caracteristicas;

    public int getNumeroStand() {
        return numeroStand;
    }

    public int getTamanio() {
        return tamanio;
    }

    public double getPrecio() {
        return precio;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setNumeroStand(int numeroStand) {
        this.numeroStand = numeroStand;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
    
    
    
    
    
}
