/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
// TODO: remover los "import" que no se requieren
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ds.tapia10
 */

@Entity
public class FeriaArtesanalEntity  implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY )
    private Integer id;
    
    private String nombre;
    
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    
    private String lugar;
    
//asociaciones
//Asociacion con los pabellones
@OneToMany(mappedBy="feria", cascade = CascadeType.ALL, orphanRemoval = true)
private List<PabellonEntity> pabellones= new ArrayList<>();

//Asociacion con la Feria Artesanal
@OneToMany(mappedBy = "feria", cascade = CascadeType.ALL, orphanRemoval = true)
private List<ConferenciaEntity> conferencias= new ArrayList<>();

//Asociacion con los usuarios
@OneToMany(mappedBy ="feria", cascade = CascadeType.ALL, orphanRemoval = true)
 private List<UsuarioEntity> usuarios = new ArrayList<>();

// TODO: remover los métodos que están entre comentarios
//    public Integer getId(){
//        return id;
//    }

    public FeriaArtesanalEntity() {
    }

    public FeriaArtesanalEntity(Integer id, String nombre, Date fechaInicio, Date fechaFin, String lugar) {
        this.id = id;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.lugar = lugar;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }     
    
    public Date getFechaFin() {
        return fechaFin;
    }

    public String getLugar() {
        return lugar;
    }
    
//    public void setId(Integer id){
//        this.id = id;
//    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
 
    // TODO: implementar getPabellones y setPabellones
    // TODO: implementar getConferencas y setConferencias
    // TODO: implementar getUsuarios y setUsuarios
       
}
