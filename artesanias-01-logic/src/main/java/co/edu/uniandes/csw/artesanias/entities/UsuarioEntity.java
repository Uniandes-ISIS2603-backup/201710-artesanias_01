/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
// TODO: eliminar los "import" que no se usan
import javax.persistence.OneToMany;

/**
 *
 * @author ds.tapia10
 */

@Entity
public class UsuarioEntity extends BaseEntity implements Serializable{
    
 
    
    private int edad;
    
    private String nombre;
    
    private String nacionalidad;
    
    private boolean boleta;
    
    private String informacionTrabajos;
    
 // TODO: implementar la relación con FeriaArtesanal
 //asociaciones
 @ManyToOne
 private FeriaArtesanalEntity feria;
 
 // TODO: revisar el nombre "reservado". No sería mejor "reservas"?
 // TODO: revisar el tipo: OneToMany no requiere una colección?
 @OneToMany(mappedBy ="artesano", cascade = CascadeType.ALL, orphanRemoval = true)
 private ReservadoEntity reservas;
 
 @OneToMany(mappedBy ="usuario", cascade = CascadeType.ALL, orphanRemoval = true)
 private List<ObraEntity> obras= new ArrayList<>();
 
 // TODO: revisar el tipo: OneToMany no requirere una coleccion?
 @OneToMany(mappedBy ="conferencista", cascade = CascadeType.ALL, orphanRemoval = true)
 private List<ConferenciaEntity> conferencias = new ArrayList<>();
 


    public int getEdad() {
        return edad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }
    
    public boolean getBoleta() {
        return boleta;
    }

    public String getInformacionTrabajos() {
        return informacionTrabajos;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setBoleta(boolean boleta) {
        this.boleta = boleta;
    }

    public void setInformacionTrabajos(String informacionTrabajos) {
        this.informacionTrabajos = informacionTrabajos;
    }
    
    // TODO: implemenentar los métodos getXXX y setXXX para las relaciones

    public List<ConferenciaEntity> getConferencias() {
        return conferencias;
    }

    public void setConferencias(List<ConferenciaEntity> conferencia) {
        this.conferencias = conferencia;
    }

    public FeriaArtesanalEntity getFeria() {
        return feria;
    }

    public void setFeria(FeriaArtesanalEntity feria) {
        this.feria = feria;
    }

    public List<ObraEntity> getObras() {
        return obras;
    }

    public void setObras(List<ObraEntity> obras) {
        this.obras = obras;
    }

    public ReservadoEntity getReservas() {
        return reservas;
    }

    public void setReservas(ReservadoEntity reservas) {
        this.reservas = reservas;
    }
    
}
