/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos;

/**
 *
 * @author jlake
 */
import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import co.edu.uniandes.csw.artesanias.entities.FeriaArtesanalEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConferenciaDTO implements Serializable {
    
    private int id;
    private String salon;
    private Date fechaInicio;
    private Date fechaFin;
    private ConferenciaEntity.EnumTemasConferencia tema;
    private int rating;
    
    public ConferenciaDTO(){}
    
    public ConferenciaDTO(ConferenciaEntity entity)
    {
        this.id = entity.getId();
        this.salon = entity.getSalon();
        this.rating = entity.getRating();
        this.fechaFin = entity.getFechaFin();
        this.fechaInicio = entity.getFechaInicio();
        this.tema = entity.getTema();
    }
    
    public ConferenciaEntity toEntity()
    {
        ConferenciaEntity entity = new ConferenciaEntity();
        entity.setId(id);
        entity.setSalon(salon);
        entity.setRating(rating);
        entity.setTema(tema);
        entity.setFechaInicio(fechaInicio);
        entity.setFechaFin(fechaFin);
        return entity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
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

    public ConferenciaEntity.EnumTemasConferencia getTema() {
        return tema;
    }

    public void setTema(ConferenciaEntity.EnumTemasConferencia tema) {
        this.tema = tema;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
    
    
}
