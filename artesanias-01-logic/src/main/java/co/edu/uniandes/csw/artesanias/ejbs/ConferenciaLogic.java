package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import co.edu.uniandes.csw.artesanias.persistence.ConferenciaPersistence;
import java.util.List;
import javax.inject.Inject;
import javax.ejb.Stateless;

/**
 *
 * @author f.velasquez11
 */
@Stateless
public class ConferenciaLogic 
{
    // TODO: implementar las validaciones de negocio
    
    @Inject private ConferenciaPersistence persistence;
    
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
