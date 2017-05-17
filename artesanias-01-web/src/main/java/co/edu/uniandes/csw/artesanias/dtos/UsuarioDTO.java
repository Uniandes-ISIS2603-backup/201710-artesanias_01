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
import co.edu.uniandes.csw.artesanias.entities.UsuarioEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsuarioDTO implements Serializable{
    
    private Long id;
    private String nombre;
    private String informacionTrabajo;
    private String nacionalidad;
    private int edad;
    private boolean boleta;
    private boolean administrador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInformacionTrabajo() {
        return informacionTrabajo;
    }

    public void setInformacionTrabajo(String informacionTrabajo) {
        this.informacionTrabajo = informacionTrabajo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isBoleta() {
        return boleta;
    }

    public void setBoleta(boolean boleta) {
        this.boleta = boleta;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }
    
    public UsuarioDTO(){}
    
    public UsuarioDTO(UsuarioEntity entity)
    {
        if (entity != null) {
            this.id = entity.getId();
            this.nombre = entity.getNombre();
            this.nacionalidad = entity.getNacionalidad();
            this.boleta = entity.getBoleta();
            this.administrador = false;
            this.edad = entity.getEdad();
            this.informacionTrabajo = entity.getInformacionTrabajos();
       }
    }
    
    public UsuarioEntity toEntity()
    {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(id);
        entity.setNombre(nombre);
        entity.setNacionalidad(nacionalidad);
        entity.setInformacionTrabajos(informacionTrabajo);
        entity.setEdad(edad);
        entity.setBoleta(boleta);
        return entity;
    }
    
}
