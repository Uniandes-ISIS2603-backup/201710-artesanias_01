package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.ReservadoEntity;
import co.edu.uniandes.csw.artesanias.persistence.ReservadoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import reactor.util.Assert;

/**
 *
 * @author ds.tapia10
 */
@Stateless
public class ReservadoLogic {
    
    // TODO: implementar las validaciones de negocio
    
    @Inject 
    private  ReservadoPersistence persistence;
    
//    public ReservadoLogic(ReservadoPersistence persistence) {
//        Assert.notNull(persistence,"My persistance will not be null");
//        this.persistence = persistence;
//    }
//    
//    
    
    public ReservadoEntity createReservado(ReservadoEntity entity){
        return persistence.createReservado(entity);
    }
    
    public List<ReservadoEntity> getReservados(){
        return persistence.findAllReservados();
    }
    
    public ReservadoEntity getReservado(int id){
         return persistence.findReservado(id);
     }
    
    public ReservadoEntity updateReservado(ReservadoEntity entity){
        return persistence.updateReservado(entity);
    }
    
    public void deleteReservado (int id)
    {
        persistence.deleteReservado(id);
    }
    
}
