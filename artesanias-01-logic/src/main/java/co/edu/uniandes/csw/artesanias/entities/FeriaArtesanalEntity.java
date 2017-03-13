/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ds.tapia10
 */

@Entity
public class FeriaArtesanalEntity {
    
    @Id
    private Integer id;
    
    private String nombre;
    
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    
    private String lugar;

    public Integer getId(){
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }     
    
    public Date getFechaFin() {
        return fechaFin;
    }

    public String getLugar() {
        return lugar;
    }
    
    public void setId(Integer id){
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

        
}
