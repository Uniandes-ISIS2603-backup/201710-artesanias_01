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

@XmlRootElement
public class StandDTO implements Serializable{
    
    private int numeroStand;
    private int tamanio;
    private double precio;
    private String características;

    public int getNumeroStand() {
        return numeroStand;
    }

    public void setNumeroStand(int numeroStand) {
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
        return características;
    }

    public void setCaracterísticas(String características) {
        this.características = características;
    }
    
    public StandDTO()
    {
        
    }
    
    public StandDTO(StandEntity entity)
    {
        if(entity !=null){
            this.numeroStand = entity.getNumeroStand();
            this.precio = entity.getPrecio();
            this.características = entity.getCaracteristicas();
            this.tamanio = entity.getTamanio();
        }
    }
    
    public StandEntity toEntity()
    {
        StandEntity entity = new StandEntity();
        entity.setNumeroStand(numeroStand);
        entity.setPrecio(precio);
        entity.setTamanio(tamanio);
        entity.setCaracteristicas(características);
        return entity;
    }
    
}
