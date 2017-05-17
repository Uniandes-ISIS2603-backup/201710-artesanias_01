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
    private final StandPersistence persistence;
    @Inject 
    public StandLogic(StandPersistence persistence) {
        Assert.notNull(persistence,"My persistance will not be null");
        this.persistence = persistence;
    }
    
    
    
    public List<StandEntity> getStands(){
        return persistence.findAllStands();
    }
    public StandEntity getStand(int id){
        return persistence.findStand(id);
    }
    
    public StandEntity createStand(StandEntity entity){
        return persistence.createStand(entity);
    }
    
    public StandEntity updateStand(StandEntity entity){
        return persistence.updateStand(entity);
    }
    public void deleteStand (int id){
        persistence.deleteStand(id);
    }
}
