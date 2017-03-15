/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.entities;

import java.io.Serializable;
import java.util.Enumeration;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author da.cortes11
 */
//Atributos
@Entity
public class ReservadoEntity implements Serializable {
@Id 
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
    
private double costo;

private Enumeration estado;

//asociaciones
//one to one
private StandEntity stand;

private UsuarioEntity artesano;



//metodos

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    
    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Enumeration getEstado() {
        return estado;
    }

    public void setEstado(Enumeration estado) {
        this.estado = estado;
    }

    
    

}
