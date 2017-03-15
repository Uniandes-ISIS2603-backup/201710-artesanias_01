/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Enumeration;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author da.cortes11
 */
 
@Entity 
public class ConferenciaEntity  implements Serializable
{
    //Atributos
@Id
private int id;

private String salon;

@Temporal(TemporalType.DATE)
private Date fechaInicio;

@Temporal(TemporalType.DATE)
private Date fechaFin;

private Enumeration tema;

private int rating;

//asociaciones
//one to one
private FeriaArtesanalEntity feria;

private UsuarioEntity conferencista;

//Setters y getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public Enumeration getTema() {
        return tema;
    }

    public void setTema(Enumeration tema) {
        this.tema = tema;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
 
    

}
