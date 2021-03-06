package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.FotoEntity;
import co.edu.uniandes.csw.artesanias.persistence.FotoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;
import reactor.util.Assert;

/**
 *
 * @author ds.tapia10
 */
@Stateless
public class FotoLogic {
    
    // TODO: implementar las validaciones de negocio
    @Inject
    private  FotoPersistence persistence;
     
//     public FotoLogic(FotoPersistence persistence) {
//       Assert.notNull(persistence,"My persistence will not be null");
//        this.persistence = persistence;
//    }
//    
    
     
    public FotoEntity createFoto(FotoEntity entity){
        return persistence.createFoto(entity);
    }
     
    public FotoEntity getFoto(Long id){
        return persistence.findFoto(id);
    }
     
    public void deleteFoto(Long id){
        persistence.deleteFoto(id);
    }        
}
