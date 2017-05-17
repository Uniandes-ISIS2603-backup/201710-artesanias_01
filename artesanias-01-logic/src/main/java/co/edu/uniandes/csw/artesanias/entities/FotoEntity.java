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

/**
 *
 * @author da.cortes11
 */
@Entity
public class FotoEntity extends BaseEntity implements Serializable{
    
private String url;    

@Temporal(TemporalType.DATE)
private Date fecha;

// TODO: Al parecer Foto no se relaciona con nada. Incluir las relaciones
//Relaciones
// Asociacion con Obra
@ManyToOne
private ObraEntity obra;

    public FotoEntity() {
    }

    public FotoEntity(String url, Date fecha, ObraEntity obra, int id) {
        super(id);
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
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    // TODO: Eliminar métodos que sobre-escriben el método pero no hacen nada
    @Override
    public void setId(int id) {
        super.setId(id); //To change body of generated methods, choose Tools | Templates.
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    // TODO: incluir métodos para manipular las relaciones

}
