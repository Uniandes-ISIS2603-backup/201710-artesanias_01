/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos;

import co.edu.uniandes.csw.artesanias.entities.FotoEntity;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jlake
 */
@XmlRootElement
public class FotoDTO {
    
    private Long id;
    private String url;
    private Date fecha;
    
    public FotoDTO(){}
    
    public FotoDTO(FotoEntity entity)
    {
        this.id = entity.getId();
        this.fecha=entity.getFecha();
        this.url=entity.getUrl();
    }
    
    public FotoEntity toEntity()
    {
        FotoEntity entity = new FotoEntity();
        entity.setId(Long.valueOf(id));
        entity.setFecha(fecha);
        entity.setUrl(url);
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
