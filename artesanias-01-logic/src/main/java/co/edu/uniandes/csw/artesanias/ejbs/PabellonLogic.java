package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;
import co.edu.uniandes.csw.artesanias.persistence.PabellonPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import reactor.util.Assert;

/**
 *
 * @author f.velasquez11
 */
@Stateless
public class PabellonLogic 
{
    // TODO: implementar las validaciones de negocio
    
    private final PabellonPersistence persistence;
    @Inject 
    public PabellonLogic(PabellonPersistence persistence) {
        Assert.notNull(persistence,"My persistence will not be null");
        this.persistence = persistence;
    }
    
    
    
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
