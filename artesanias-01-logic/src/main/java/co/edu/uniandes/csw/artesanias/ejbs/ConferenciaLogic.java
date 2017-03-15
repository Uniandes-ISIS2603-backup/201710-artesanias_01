/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import co.edu.uniandes.csw.artesanias.persistence.feriaArtesanalPersistence;
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
    @Inject private feriaArtesanalPersistence persistence;
    
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
