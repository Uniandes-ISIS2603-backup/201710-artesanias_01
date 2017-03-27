/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.StandEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
/**
 *
 * @author jm.munoz14
 */
public class StandPersistence {

    @PersistenceContext(unitName = "feriaPU")
    protected EntityManager em;

    public StandEntity createStand(StandEntity entity) {
        em.persist(entity);

        return entity;
    }

    public StandEntity findStand(Long id) {

        return em.find(StandEntity.class, id);

    }

    public StandEntity updateStand(StandEntity entity) {

        return em.merge(entity);

    }

    public void deleteStand(Long id) {

        StandEntity entity = em.find(StandEntity.class, id);
        em.remove(entity);
    }

    public List<StandEntity> findAllStands() {
        TypedQuery<StandEntity> q = em.createQuery("select u from StandEntity u", StandEntity.class);
        return q.getResultList();

    }

}
