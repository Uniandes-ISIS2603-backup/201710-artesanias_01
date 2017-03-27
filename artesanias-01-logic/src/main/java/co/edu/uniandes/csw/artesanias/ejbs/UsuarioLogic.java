package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.UsuarioEntity;
import co.edu.uniandes.csw.artesanias.persistence.UsuarioPersistence;
import java.util.List;
import javax.inject.Inject;
import javax.ejb.Stateless;

/**
 *
 * @author f.velasquez11
 */
@Stateless
public class UsuarioLogic 
{
    // TODO: implementar las validaciones de negocio
    
    @Inject private UsuarioPersistence persistence;
    
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
