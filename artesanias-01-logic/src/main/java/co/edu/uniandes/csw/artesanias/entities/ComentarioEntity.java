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
import javax.persistence.Transient;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author da.cortes11
 */
//Atributos

@Entity
public class ComentarioEntity implements Serializable{

@Id 
private Long id;

private String comentario;

@Temporal(TemporalType.TIMESTAMP)
private Date fecha;

// TODO: implementar las relaciones en las entidades
//asociaciones
@PodamExclude
@ManyToOne
//@Transient
private ObraEntity obra;
//metodos setters y getters

    public ComentarioEntity()
    {
        
    }

    public ComentarioEntity(Long id, String comentario, Date fecha, ObraEntity obra) {
        this.id = id;
        this.comentario = comentario;
        this.fecha = fecha;
        this.obra = obra;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public ObraEntity getObra() {
        return obra;
    }

    public void setObra(ObraEntity obra) {
        this.obra = obra;
    }
    
    


}
