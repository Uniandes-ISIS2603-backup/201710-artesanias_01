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
import co.edu.uniandes.csw.artesanias.entities.StandEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;


public class StandDTO {
    
    private Long numeroStand;
    private int tamanio;
    private double precio;
    private String caracteristicas;

    public Long getNumeroStand() {
        return numeroStand;
    }

    public void setNumeroStand(Long numeroStand) {
        this.numeroStand = numeroStand;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCaracterísticas() {
        return caracteristicas;
    }

    public void setCaracterísticas(String características) {
        this.caracteristicas = características;
    }
    
    public StandDTO()
    {
        
    }
    
    public StandDTO(StandEntity entity)
    {
        if(entity !=null){
            this.numeroStand = entity.getNumeroStand();
            this.precio = entity.getPrecio();
            this.caracteristicas = entity.getCaracteristicas();
            this.tamanio = entity.getTamanio();
        }
    }
    
    public StandEntity toEntity()
    {
        StandEntity entity = new StandEntity();
        entity.setNumeroStand(this.numeroStand);
        entity.setPrecio(this.precio);
        entity.setTamanio(this.tamanio);
        entity.setCaracteristicas(this.caracteristicas);
        return entity;
    }
    
}
