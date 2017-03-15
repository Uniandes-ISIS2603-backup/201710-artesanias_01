/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.UsuarioEntity;
import co.edu.uniandes.csw.artesanias.persistence.feriaArtesanalPersistence;
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
    @Inject private feriaArtesanalPersistence persistence;
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
