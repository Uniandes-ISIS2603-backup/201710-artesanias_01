package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import co.edu.uniandes.csw.artesanias.persistence.ConferenciaPersistence;
import java.util.List;
import javax.inject.Inject;
import javax.ejb.Stateless;
import reactor.util.Assert;

/**
 *
 * @author f.velasquez11
 */
@Stateless
public class ConferenciaLogic 
{
    // TODO: implementar las validaciones de negocio
    
    private ConferenciaPersistence persistence;
    
    @Inject 

    public ConferenciaLogic() {
    }

    public ConferenciaLogic(ConferenciaPersistence persistence) {
        Assert.notNull(persistence, "My conference persistance must not be null");
        this.persistence = persistence;
    }
    
    
    
    public List<ConferenciaEntity> getConferencias(){
        return persistence.findAllConferencias();
    } 
    public ConferenciaEntity getConferencia(Long id){
        return persistence.findConferencia(id);
    }
    public ConferenciaEntity createConferencia(ConferenciaEntity entity){
        return persistence.createConferencia(entity);
    }
    public ConferenciaEntity updateConferencia(ConferenciaEntity entity){
        return persistence.updateConferencia(entity);
    }
    public void deleteConferencia(Long id){
        persistence.deleteConferencia(id);
    }
    
}
