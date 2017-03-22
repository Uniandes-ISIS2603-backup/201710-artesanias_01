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
// TODO: eliminar los "import" que no se usan
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
    
    // TODO: eliminar código en comentarios que no se usa
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    
    private int edad;
    
    private String nombre;
    
    private String nacionalidad;
    
    private boolean boleta;
    
    private String informacionTrabajos;
    
 // TODO: implementar la relación con FeriaArtesanal
 //asociaciones
 private FeriaArtesanalEntity feria;
 
 // TODO: revisar el nombre "reservado". No sería mejor "reservas"?
 // TODO: revisar el tipo: OneToMany no requiere una colección?
 @OneToMany
 private ReservadoEntity reservado;
 
 @OneToMany(mappedBy ="Usuario", cascade = CascadeType.ALL, orphanRemoval = true)
 private List<ObraEntity> obras= new ArrayList<>();
 
 // TODO: revisar el tipo: OneToMany no requirere una coleccion?
 @OneToMany
 private ConferenciaEntity conferencia;
 
// TODO: eliminar el códgo innecesario, en comentarios
//    public Long getId() {
//        return id;
//    }

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
    
}
