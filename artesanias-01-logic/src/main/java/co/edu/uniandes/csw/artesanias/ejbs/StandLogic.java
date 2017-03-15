/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.StandEntity;
import co.edu.uniandes.csw.artesanias.persistence.feriaArtesanalPersistence;
import java.util.List;
import javax.inject.Inject;
import javax.ejb.Stateless;

/**
 *
 * @author f.velasquez11
 */
@Stateless
public class StandLogic 
{
    @Inject private feriaArtesanalPersistence persistence;
    
    public List<StandEntity> getStands(){
        return persistence.findAllStands();
    }
    public StandEntity getStand(Long id){
        return persistence.findStand(id);
    }
    
    public StandEntity createStand(StandEntity entity){
        return persistence.createStand(entity);
    }
    
    public StandEntity updateStand(StandEntity entity){
        return persistence.updateStand(entity);
    }
    public void deleteStand (Long id){
        persistence.deleteStand(id);
    }
}
