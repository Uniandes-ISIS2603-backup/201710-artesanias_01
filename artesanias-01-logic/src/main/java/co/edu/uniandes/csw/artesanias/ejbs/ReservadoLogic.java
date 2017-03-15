/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.ReservadoEntity;
import co.edu.uniandes.csw.artesanias.persistence.feriaArtesanalPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ds.tapia10
 */
@Stateless
public class ReservadoLogic {
    
    @Inject private feriaArtesanalPersistence persistence;
    
    public ReservadoEntity createObra(ReservadoEntity entity){
        return persistence.createReservado(entity);
    }
    public ReservadoEntity updateObra(ReservadoEntity entity){
        return persistence.updateReservado(entity);
    }
    
}
