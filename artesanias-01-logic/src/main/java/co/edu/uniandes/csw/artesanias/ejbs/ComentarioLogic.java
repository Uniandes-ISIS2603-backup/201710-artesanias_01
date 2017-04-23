package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.ComentarioEntity;
import co.edu.uniandes.csw.artesanias.persistence.ComentarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ds.tapia10
 */
@Stateless
public class ComentarioLogic {
    
    // TODO: implementar las validaciones de negocio
    
    @Inject private ComentarioPersistence persistence;

    public ComentarioLogic() {
    }

    public ComentarioLogic(ComentarioPersistence persistence) {
        this.persistence = persistence;
    }
    
    
    public List<ComentarioEntity> getComentarios(){
        return persistence.findAllComentarios();
    }
    
    public ComentarioEntity createComentario(ComentarioEntity entity){
        return persistence.createComentario(entity);
    }
    
    public ComentarioEntity getComentario(Long id){
        return persistence.findComentario(id);
    }
     
    public void deleteComentario(Long id){
        persistence.deleteComentario(id);
    }
}
