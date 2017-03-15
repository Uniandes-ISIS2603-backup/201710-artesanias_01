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
 * @author jlake
 */
@Entity
public class PabellonEntity extends BaseEntity implements Serializable{
    
   
    
    //asociaciones
    //compuesta
    @OneToMany(mappedBy="Pabellon", cascade= CascadeType.ALL, orphanRemoval = true )
    private List<StandEntity> stand = new ArrayList<>();   
    //one to one 
    private FeriaArtesanalEntity feriaArtesanal;
    private String tema;

    

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
    
    
    
}
