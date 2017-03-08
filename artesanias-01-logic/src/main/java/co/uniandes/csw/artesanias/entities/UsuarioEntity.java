/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.uniandes.csw.artesanias.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author ds.tapia10
 */

@Entity
public class UsuarioEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private int edad;
    
    private String nombre;
    
    private String nacinalidad;
    
    private boolean boleta;
    
    private String infomracionTrabajos;

    public Long getId() {
        return id;
    }

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

    public void setId(Long id) {
        this.id = id;
    }
    
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
