/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.entities;

import java.io.Serializable;
import java.util.Date;
// TODO: remover los "import" que no se requieren
import java.util.Enumeration;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author da.cortes11
 */
 
@Entity 
public class ConferenciaEntity  implements Serializable
{
    
    //Atributos
@Id
private int id;

private String salon;

@Temporal(TemporalType.DATE)
private Date fechaInicio;

@Temporal(TemporalType.DATE)
private Date fechaFin;


// TODO: definir la persistencia de la enumeración
private EnumTemasConferencia tema;

private int rating;

// TODO: implementar las asociaciones entre las entidades
//asociaciones

//Asociacion con los usuarios
@ManyToOne
private UsuarioEntity conferencista = new UsuarioEntity();

//Asociacion con la feria
@ManyToOne
private FeriaArtesanalEntity feria = new FeriaArtesanalEntity();



public enum EnumTemasConferencia{
     
  COMERCIO {
      public String toString() {
          return "Comercialización de las artesanías";
      }
  },

  NEGOCIOS {
      public String toString() {
          return "Negocios en internet para artesanos";
      }
    },
  ARTE {
      public String toString(){
          return "Arte y Artesanos";
      }
  },
  LUJOS
          {
      public String toString(){
          return "Artesanias y Lujos";
      }
  },
  HISTORIA
          {
      public String toString()
      {
          return "Historia de las artesanias";
      }
  },
  OTROS
          {
      public String toString()
      {
          return "Otros";
      }
  };
}

//Setters y getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public EnumTemasConferencia getTema() {
        return tema;
    }

    public void setTema(EnumTemasConferencia tema) {
        this.tema = tema;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
 
    // TODO: implementar getFeria y setFeria
    // TODO: implementar getConferencias y setConferencista

}
