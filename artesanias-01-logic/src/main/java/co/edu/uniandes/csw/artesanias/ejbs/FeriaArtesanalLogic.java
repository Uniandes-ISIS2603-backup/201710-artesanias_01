package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.FeriaArtesanalEntity;
import co.edu.uniandes.csw.artesanias.persistence.feriaArtesanalPersistence;
import java.util.List;
import javax.inject.Inject;
import javax.ejb.Stateless;
import org.springframework.util.Assert;

/**
 *
 * @author f.velasquez11
 */
@Stateless
public class FeriaArtesanalLogic 
{
    // TODO: implementar las validaciones de negocio
    @Inject
    private  feriaArtesanalPersistence persistence;
   
//    public FeriaArtesanalLogic(feriaArtesanalPersistence persistence) {
//        Assert.notNull(persistence,"My persistence will not be null");
//        this.persistence = persistence;
//    }
//    
    
    public List<FeriaArtesanalEntity> getFerias(){
        return persistence.findAllFerias();
    }
    public FeriaArtesanalEntity getFeria(Long id){
        return persistence.findFeria(id);
    }
    public FeriaArtesanalEntity createFeria(FeriaArtesanalEntity entity){
        return persistence.createFeria(entity);
    }
    public FeriaArtesanalEntity updateFeria(FeriaArtesanalEntity entity){
        return persistence.updateFeria(entity);
    }
    public void deleteFeria(Long id){
        persistence.deleteFeria(id);
    }
    
}
