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
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author da.cortes11
 */
@Entity
public class ObraEntity extends BaseEntity implements Serializable
{
    //Atributos
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String nombre;

private int rating;

private String material;

private String tecnica;



//asociaciones
// TODO: implementar la relación en la entidad ComentarioEntity
//Asociacion con los comentarios
@PodamExclude
@OneToMany (mappedBy="obra", cascade= CascadeType.ALL, orphanRemoval = true)
private List<ComentarioEntity> comentarios= new ArrayList<>();

// TODO: incluir la relación ManyToOne en FotoEntity
//Asociacion con las fotods
@PodamExclude
@OneToMany(mappedBy="obra", cascade= CascadeType.ALL, orphanRemoval = true)
private List<FotoEntity> fotos= new ArrayList<>();

@PodamExclude
@ManyToOne
private UsuarioEntity usuario;

// TODO: No es claro por que un artesano solo tiene una única obra
//       Si tiene una relación OneToMany, el contrario no debería ser ManyToOne
//one to one

    public ObraEntity() {
    }

    public ObraEntity(int id, String nombre, int rating, String material, String tecnica, UsuarioEntity usuario) {
        this.id = (long)id;
        this.nombre = nombre;
        this.rating = rating;
        this.material = material;
        this.tecnica = tecnica;
        this.usuario = usuario;
    }



//metodos setters y getters

    @Override
   public Long getId(){
       return id;
   }

   @Override
   public void setId(Long id) {
       this.id = id;
   }

    public String getNombre() {
        return nombre;
    }

      public void setNombre(String nombre) {
          this.nombre = nombre; 
      }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTecnica() {
        return tecnica;
    }

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }
    
    public UsuarioEntity getUsuario(){
        return usuario;
    }
    
    public void setUsuario(UsuarioEntity usuario){
        this.usuario = usuario;
    }
    
    // TODO: implementar getComentarios y setComentarios
    // TODO: implementar getFotos y setFotos

    public List<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }

    public List<FotoEntity> getFotos() {
        return fotos;
    }

    public void setFotos(List<FotoEntity> fotos) {
        this.fotos = fotos;
    }
    
    
}
