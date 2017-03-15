/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.ComentarioEntity;
import co.edu.uniandes.csw.artesanias.entities.FotoEntity;
import co.edu.uniandes.csw.artesanias.persistence.feriaArtesanalPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ds.tapia10
 */
@Stateless
public class ComentarioLogic {
    
    @Inject private feriaArtesanalPersistence persistence;
    
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
