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
import co.edu.uniandes.csw.artesanias.entities.ReservadoEntity;
import co.edu.uniandes.csw.artesanias.entities.StandEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CrearReservaDTO implements Serializable {
    
    private int id;
    private ReservadoEntity.EstadoEnum estado;
    private double costo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public ReservadoEntity.EstadoEnum getEstado() {
        return estado;
    }

    public void setEstado(ReservadoEntity.EstadoEnum estado) {
        this.estado = estado;
    }
    
    public CrearReservaDTO(){}
    
    public CrearReservaDTO(ReservadoEntity entity){
        this.id = entity.getId();
        this.estado = entity.getEstado();
        this.costo = entity.getCosto();
        /*if(estado == "Reservado")
            this.estado = ReservadoEntity.EstadoEnum.RESERVADO;
        else if(estado =="Cancelado")
            this.estado = ReservadoEntity.EstadoEnum.CANCELADO;
        else 
            this.estado = ReservadoEntity.EstadoEnum.PAGADO;*/
    }
    
    public ReservadoEntity toEntity()
    {
        ReservadoEntity entity = new ReservadoEntity();
        entity.setId(id);
        entity.setCosto(costo);
        entity.setEstado(estado);
        return entity;
    }
    
}
