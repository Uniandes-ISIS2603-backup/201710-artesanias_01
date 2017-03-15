/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.persistence.feriaArtesanalPersistence;
import javax.inject.Inject;
import javax.ejb.Stateless;

/**
 *
 * @author f.velasquez11
 */
@Stateless
public class StandLogic 
{
    @Inject private feriaArtesanalPersistence persistence;
    
}
