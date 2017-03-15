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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author ds.tapia10
 */

@Entity
public class UsuarioEntity extends BaseEntity implements Serializable{
    
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    
    private int edad;
    
    private String nombre;
    
    private String nacinalidad;
    
    private boolean boleta;
    
    private String infomracionTrabajos;
    
 //asociaciones
 private FeriaArtesanalEntity feria;
 
 @OneToMany
 private ReservadoEntity reservado;
 
 @OneToMany(mappedBy ="Usuario", cascade = CascadeType.ALL, orphanRemoval = true)
 private List<ObraEntity> obras= new ArrayList<>();
 
 @OneToMany
 private ConferenciaEntity conferencia;
 

//    public Long getId() {
//        return id;
//    }

    public int getEdad() {
        return edad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNacinalidad() {
        return nacinalidad;
    }
    
    public boolean getBoleta() {
        return boleta;
    }

    public String getInfomracionTrabajos() {
        return infomracionTrabajos;
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

    public void setNacinalidad(String nacinalidad) {
        this.nacinalidad = nacinalidad;
    }

    public void setBoleta(boolean boleta) {
        this.boleta = boleta;
    }

    public void setInfomracionTrabajos(String infomracionTrabajos) {
        this.infomracionTrabajos = infomracionTrabajos;
    }
    
    
    
    
    
}
