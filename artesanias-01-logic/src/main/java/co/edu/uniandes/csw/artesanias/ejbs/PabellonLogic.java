// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;
import co.edu.uniandes.csw.artesanias.persistence.PabellonPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author f.velasquez11
 */
@Stateless
public class PabellonLogic 
{
    // TODO: implementar las validaciones de negocio
    
    @Inject private PabellonPersistence persistence;
    
    public List<PabellonEntity> getPabellones(){
        return persistence.findAllPabellones();
    }
    public PabellonEntity getPabellon(Long id){
        return persistence.findPabellon(id);
    }
    public PabellonEntity createPabellon(PabellonEntity entity){
        return persistence.createPabellon(entity);
    }
    public PabellonEntity updatePabellon(PabellonEntity entity){
        return persistence.updatePabellon(entity);
    }
    public void deletePabellon(Long id){
        persistence.deletePabellon(id);
    }
}
