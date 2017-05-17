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
import co.edu.uniandes.csw.artesanias.entities.ObraEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObraDTO implements Serializable{
    
    private Long id;
    private String nombre;
    private int rating;
    private String materiales;
    private String tecnica;
    
    public ObraDTO(){}
    
    public ObraDTO(ObraEntity entity)
    {
        if(entity !=null)
        {
            this.id = entity.getId();
            this.nombre = entity.getNombre();
            this.rating = entity.getRating();
            this.materiales = entity.getMaterial();
            this.tecnica = entity.getTecnica();
        }
    }
    
    public ObraEntity toEntity()
    {
        ObraEntity entity = new ObraEntity();
        entity.setId(id);
        entity.setMaterial(materiales);
        entity.setRating(rating);
        entity.setTecnica(tecnica);
        entity.setNombre(nombre);
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = (long)id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getMateriales() {
        return materiales;
    }

    public void setMateriales(String materiales) {
        this.materiales = materiales;
    }

    public String getTecnica() {
        return tecnica;
    }

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }
        
}
