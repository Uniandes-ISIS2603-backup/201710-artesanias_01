package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.ObraEntity;
import co.edu.uniandes.csw.artesanias.persistence.ObraPersistence;
import java.util.List;
import javax.inject.Inject;
import javax.ejb.Stateless;
import reactor.util.Assert;

/**
 *
 * @author f.velasquez11
 */
@Stateless
public class ObraLogic 
{
    // TODO: implementar las validaciones de negocio

    private final ObraPersistence persistence;
    @Inject 
    public ObraLogic(ObraPersistence persistence) {
        Assert.notNull(persistence,"My persistance will not be null");
        this.persistence = persistence;
    }
    
    
    
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
