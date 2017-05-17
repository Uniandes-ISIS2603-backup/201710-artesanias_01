/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
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
    public int getId() {
        return Math.toIntExact(id); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Long getLongId(){
        return id;
    }

    // TODO: Eliminar métodos que sobre-escriben el método pero no hacen nada
    @Override
    public void setId(int id) {
        this.id = (long)id ; 
    }

    public void setLongId(Long id){
        this.id = id;
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
