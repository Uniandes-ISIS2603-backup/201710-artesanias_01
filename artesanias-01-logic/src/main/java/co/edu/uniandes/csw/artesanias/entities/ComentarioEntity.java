/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;

/**
 *
 * @author da.cortes11
 */
//Atributos

@Entity
public class ComentarioEntity implements Serializable{

@Id 
private int id;

private String comentario;

@Temporal(TemporalType.DATE)
private Date fecha;

// TODO: implementar las relaciones en las entidades
//asociaciones
@ManyToOne
private ObraEntity obra;
//metodos setters y getters

    public ComentarioEntity()
    {
        
    }

    public ComentarioEntity(int id, String comentario, Date fecha, ObraEntity obra) {
        this.id = id;
        this.comentario = comentario;
        this.fecha = fecha;
        this.obra = obra;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    // TODO: implementar getObra y setObra
    
    


}
