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
    private Long numeroStand;
    
    private int tamanio;
    
    private double price;
    
    private String caracteristicas;
    
    //asociaciones
    // TODO: implementar las asociaciones
    // TODO: revisar esta relación OneToOne: un pabellón solo tiene un stand ?
    @ManyToOne
    private PabellonEntity pabellon;
   
    @OneToOne
    private ReservadoEntity reservado;

    public StandEntity() {
    }

    public StandEntity(Long numeroStand, int tamanio, double precio, String caracteristicas, PabellonEntity pabellon, ReservadoEntity reservado) {
        this.numeroStand = numeroStand;
        this.tamanio = tamanio;
        this.price = precio;
        this.caracteristicas = caracteristicas;
        this.pabellon = pabellon;
        this.reservado = reservado;
    }
    

    
    public Long getNumeroStand() {
        return numeroStand;
    }

    public int getTamanio() {
        return tamanio;
    }

    public double getPrecio() {
        return price;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }
    
        public PabellonEntity getPabellon() {
        return pabellon;
    }

    public void setNumeroStand(Long numeroStand) {
        this.numeroStand = numeroStand;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }
    
    public void setPrecio(double precio) {
        this.price = precio;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
    
    public void setPabellon(PabellonEntity pabellon) {
        this.pabellon = pabellon;
    }
    
    // TODO: implementar getPabellon y setPabellon
    // TODO: implementar getReservas y setReservas
        
    
}
