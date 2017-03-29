/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.entities;

import java.io.Serializable;
// TODO: eliminar los "import" que no se usan
import java.util.Enumeration;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author da.cortes11
 */
//Atributos
@Entity
public class ReservadoEntity implements Serializable {
@Id 
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
    
private double costo;

public enum EstadoEnum{
    PAGADO,
    RESERVADO,
    CANCELADO;
}

// TODO: definir la forma de implementar la persistencia     
private EstadoEnum estado;

//asociaciones

// TODO: implementar las asociaciones
// TODO: revisar esta relación OneToOne: un stand solo puede tener una reserva (toda la vida?)\
//Asociacion con Stand
@OneToOne
private StandEntity stand;

// TODO: implementar la asociación con Usuario
//Asociacion con Usuario
@ManyToOne
private UsuarioEntity artesano;



//metodos

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

    public EstadoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnum estado) {
        this.estado = estado;
    }

    // TODO: implementar los métodos getStand y setStand
    // TODO: implementar los métodos getArtesano y setArtesano

    public StandEntity getStand()
    {
        return stand;
    }
    
    public void setStand(StandEntity stand)
    {
        this.stand = stand;
    }
    
    public UsuarioEntity getArtesano()
    {
        return artesano;
    }
    
    public void setArtesano(UsuarioEntity artesano)
    {
        this.artesano = artesano;
    }
    

}
