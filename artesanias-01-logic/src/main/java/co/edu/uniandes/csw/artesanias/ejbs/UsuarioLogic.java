package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.UsuarioEntity;
import co.edu.uniandes.csw.artesanias.persistence.UsuarioPersistence;
import java.util.List;
import javax.inject.Inject;
import javax.ejb.Stateless;
import reactor.util.Assert;

/**
 *
 * @author f.velasquez11
 */
@Stateless
public class UsuarioLogic 
{
    // TODO: implementar las validaciones de negocio
    private final UsuarioPersistence persistence;
    @Inject 
    public UsuarioLogic(UsuarioPersistence persistence) {
        Assert.notNull(persistence,"My persistance will not be null");
        this.persistence = persistence;
    }
    
    
    
    
    public List<UsuarioEntity> getUsuarios(){
        return persistence.findAllUsuarios();
    }
    public UsuarioEntity getUsuario(Long id){
        return persistence.findUsuario(id);
    }
    public UsuarioEntity createUsuario(UsuarioEntity entity){
        return persistence.createUsuario(entity);
    }
    public UsuarioEntity updateUsuario(UsuarioEntity entity){
            return persistence.updateUsuario(entity);
    }
    public void deleteUsuario(Long id){
        persistence.deleteUsuario(id);
    }
}
