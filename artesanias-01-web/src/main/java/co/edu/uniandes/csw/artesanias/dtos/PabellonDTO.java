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
import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PabellonDTO implements Serializable {
    
    private Long    id;
    private String  tema;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
    
    public PabellonDTO(){}
    
    public PabellonDTO(PabellonEntity entity)
    {
        this.id = entity.getId();
        this.tema = entity.getTema();
        
    }
    
    public PabellonEntity toEntity()
    {
        PabellonEntity entity = new PabellonEntity();
        entity.setId(id);
        entity.setTema(tema);
        return entity;
    }
     
}
