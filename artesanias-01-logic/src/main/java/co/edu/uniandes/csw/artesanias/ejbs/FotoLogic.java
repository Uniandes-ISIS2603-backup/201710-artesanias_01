/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.FotoEntity;
import co.edu.uniandes.csw.artesanias.persistence.feriaArtesanalPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ds.tapia10
 */
@Stateless
public class FotoLogic {
    
     @Inject private feriaArtesanalPersistence persistence;
     
     public FotoEntity createFoto(FotoEntity entity){
         return persistence.createFoto(entity);
     }
     
     public FotoEntity getFoto(Long id){
         return persistence.findFoto(id);
     }
     
     public void deleteFoto(Long id){
         persistence.deleteFoto(id);
     }        
}
