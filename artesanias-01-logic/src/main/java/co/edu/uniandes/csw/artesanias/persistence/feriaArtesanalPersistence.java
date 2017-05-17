// TODO: Eliminar los comentarios por defecto  
/*
 *
 *  To change this license header, choose License Headers in Project Properties.
 *  To change this template file, choose Tools | Templates
 *  and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.FeriaArtesanalEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jm.munoz14
 */
@Stateless
// TODO: Crear una clase persistencia para cada clase
public class feriaArtesanalPersistence {

    @PersistenceContext(unitName = "feriaPU")
    protected EntityManager em;

    // TODO: Indentar apropiadamente el código de las clases
    // TODO: COnsiderar las relaciones al momento de crear y actualizar las relaciones
    public FeriaArtesanalEntity createFeria(FeriaArtesanalEntity entity) {

        em.persist(entity);

        return entity;

    }

    public FeriaArtesanalEntity findFeria(Long id) {

        return em.find(FeriaArtesanalEntity.class, id);

    }

    public List<FeriaArtesanalEntity> findAllFerias() {
        TypedQuery<FeriaArtesanalEntity> q = em.createQuery("select u from FeriaArtesanalEntity u", FeriaArtesanalEntity.class);
        return q.getResultList();

    }

    public FeriaArtesanalEntity updateFeria(FeriaArtesanalEntity entity) {

        
        em.merge(entity);
        return entity;
    }

    public void deleteFeria(Long id) {

        FeriaArtesanalEntity entity = em.find(FeriaArtesanalEntity.class, id);
        em.remove(entity);
    }

}
