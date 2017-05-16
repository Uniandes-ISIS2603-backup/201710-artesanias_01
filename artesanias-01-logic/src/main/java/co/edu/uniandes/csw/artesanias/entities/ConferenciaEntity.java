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
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author da.cortes11
 */
 
    @Entity 
public class ConferenciaEntity  implements Serializable
{
    
    //Atributos
@Id
private Long id;

private String salon;

@Temporal(TemporalType.TIMESTAMP)
private Date fechaInicio;

@Temporal(TemporalType.TIMESTAMP)
private Date fechaFin;


// TODO: definir la persistencia de la enumeración
private EnumTemasConferencia tema;

private int rating;

// TODO: implementar las asociaciones entre las entidades
//asociaciones
@PodamExclude
//Asociacion con los usuarios
@ManyToOne
private UsuarioEntity conferencista;
@PodamExclude
//Asociacion con la feria
@ManyToOne
private FeriaArtesanalEntity feria;

    public ConferenciaEntity() {
    }

    public ConferenciaEntity(Long id, String salon, Date fechaInicio, Date fechaFin, EnumTemasConferencia tema, int rating, UsuarioEntity conferencista, FeriaArtesanalEntity feria) {
        this.id = id;
        this.salon = salon;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tema = tema;
        this.rating = rating;
        this.conferencista = conferencista;
        this.feria = feria;
    }

 

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public FeriaArtesanalEntity getFeria() {
        return feria;
    }

    public void setFeria(FeriaArtesanalEntity feria) {
        this.feria = feria;
    }

    public UsuarioEntity getConferencista() {
        return conferencista;
    }

    public void setConferencista(UsuarioEntity conferencista) {
        this.conferencista = conferencista;
    }
    

}
