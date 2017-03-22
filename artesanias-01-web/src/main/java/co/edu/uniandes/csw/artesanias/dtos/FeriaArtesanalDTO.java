// TODO: eliminar los comentarios por defecto
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

import co.edu.uniandes.csw.artesanias.entities.FeriaArtesanalEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FeriaArtesanalDTO implements Serializable{
    
    private Integer    id;
    private String  nombre;
    private String  lugar;
    private Date    fechaInicio;
    private Date    fechaFin;
    
    public FeriaArtesanalDTO(){     
    }
    
    public FeriaArtesanalDTO(FeriaArtesanalEntity entity) {
       if (entity != null) {
            this.id = entity.getId();
            this.nombre = entity.getNombre();
            this.lugar = entity.getLugar();
            this.fechaInicio = entity.getFechaInicio();
            this.fechaFin = entity.getFechaFin();
       }
    }
     
    public FeriaArtesanalEntity toEntity()
    {
        FeriaArtesanalEntity entity = new FeriaArtesanalEntity();
        entity.setId(this.getId());
        entity.setNombre(this.getNombre());
        entity.setLugar(this.getLugar());
        entity.setFechaInicio(this.getFechaInicio());
        entity.setFechaFin(this.getFechaFin());
        return entity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
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
     
    
}
