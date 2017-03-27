// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.ObraEntity;
import co.edu.uniandes.csw.artesanias.persistence.ObraPersistence;
import java.util.List;
import javax.inject.Inject;
import javax.ejb.Stateless;

/**
 *
 * @author f.velasquez11
 */
@Stateless
public class ObraLogic 
{
    // TODO: implementar las validaciones de negocio

    @Inject private ObraPersistence persistence;
    
    public List<ObraEntity> getObras(){
        return persistence.findAllObras();
    }
    public ObraEntity getObra(Long id){
        return persistence.findObra(id);
    }
    public ObraEntity createObra(ObraEntity entity){
        return persistence.createObra(entity);
    }
    public ObraEntity updateObra(ObraEntity entity){
        return persistence.updateObra(entity);
    }
    public void deleteObra(Long id){
        persistence.deleteObra(id);
        
    }
}
