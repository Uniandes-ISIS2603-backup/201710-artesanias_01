// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.ReservadoEntity;
import co.edu.uniandes.csw.artesanias.persistence.feriaArtesanalPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ds.tapia10
 */
@Stateless
public class ReservadoLogic {
    
    // TODO: implementar las validaciones de negocio
    
    @Inject private feriaArtesanalPersistence persistence;
    
    public ReservadoEntity createReservado(ReservadoEntity entity){
        return persistence.createReservado(entity);
    }
    
    public List<ReservadoEntity> getReservados(){
        return persistence.findAllReservados();
    }
    
    public ReservadoEntity getReservado(Long id){
         return persistence.findReservado(id);
     }
    
    public ReservadoEntity updateReservado(ReservadoEntity entity){
        return persistence.updateReservado(entity);
    }
    
    public void deleteReservado (Long id)
    {
        persistence.deleteReservado(id);
    }
    
}
