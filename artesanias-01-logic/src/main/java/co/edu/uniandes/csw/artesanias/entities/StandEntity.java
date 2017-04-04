/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import co.edu.uniandes.csw.artesanias.entities.ReservadoEntity;

/**
 *
 * @author ds.tapia10
 */

@Entity
public class StandEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numeroStand;
    
    private int tamanio;
    
    private double precio;
    
    private String caracteristicas;
    
    //asociaciones
    // TODO: implementar las asociaciones
    // TODO: revisar esta relación OneToOne: un pabellón solo tiene un stand ?
    @ManyToOne
    private PabellonEntity pabellon;
   
    @OneToOne
    private ReservadoEntity reservado;
    

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
    
    // TODO: implementar getPabellon y setPabellon
    // TODO: implementar getReservas y setReservas
        
    
}
