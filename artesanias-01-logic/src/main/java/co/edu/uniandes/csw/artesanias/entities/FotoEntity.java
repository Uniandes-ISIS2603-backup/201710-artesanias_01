/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author da.cortes11
 */
@Entity
public class FotoEntity extends BaseEntity implements Serializable{
    
@Id
@GeneratedValue(strategy =GenerationType.IDENTITY)
private Long id;
    
private String url;    

@Temporal(TemporalType.DATE)
private Date fecha;

// TODO: Al parecer Foto no se relaciona con nada. Incluir las relaciones
//Relaciones
// Asociacion con Obra
@PodamExclude
@ManyToOne
private ObraEntity obra;

    public FotoEntity() {
    }

    public FotoEntity(String url, Date fecha, ObraEntity obra, int id) {
        this.id = (long)id;
        this.url = url;
        this.fecha = fecha;
        this.obra = obra;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // TODO: Eliminar métodos que sobre-escriben el método pero no hacen nada

    @Override
    public Long getId(){
        return id;
    }

    // TODO: Eliminar métodos que sobre-escriben el método pero no hacen nada
    @Override
    public void setId(Long id) {
        this.id = (long)id ; 
    }

    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    // TODO: incluir métodos para manipular las relaciones

    public ObraEntity getObra() {
        return obra;
    }

    public void setObra(ObraEntity obra) {
        this.obra = obra;
    }
    
    
}
