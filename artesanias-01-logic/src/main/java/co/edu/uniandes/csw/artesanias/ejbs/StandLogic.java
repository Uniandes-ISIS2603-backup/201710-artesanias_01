package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.StandEntity;
import co.edu.uniandes.csw.artesanias.persistence.StandPersistence;
import java.util.List;
import javax.inject.Inject;
import javax.ejb.Stateless;
import reactor.util.Assert;

/**
 *
 * @author f.velasquez11
 */
@Stateless
public class StandLogic 
{
    // TODO: implementar las validaciones de negocio  
    @Inject 
    private  StandPersistence persistence;
  

    
    
    
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
