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
 * @author jlake
 */
@Entity
public class PabellonEntity extends BaseEntity implements Serializable{
    
   
    
    // TODO: implementar la relación en la clase Stand Entity
    //asociaciones
    //compuesta
    @OneToMany(mappedBy="Pabellon", cascade= CascadeType.ALL, orphanRemoval = true )
    private List<StandEntity> stand = new ArrayList<>();   
    
    // TODO: implementar las otras relaciones
    // TODO: revisar esta relación OneToOne: una feria solo tiene un único pabellón?
    //one to one 
    private FeriaArtesanalEntity feriaArtesanal;
    
    private String tema;

    

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
    
    // TODO: implementar los métodos getStand y setStand
    // TODO: implementar los métodos de getFeria y setFeria
    
}
