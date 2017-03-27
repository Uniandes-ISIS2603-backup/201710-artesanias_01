/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.ReservadoEntity;
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
public class ReservadoPersistence {

    @PersistenceContext(unitName = "feriaPU")
    protected EntityManager em;

    public ReservadoEntity createReservado(ReservadoEntity entity) {
        em.persist(entity);

        return entity;
    }

    public ReservadoEntity updateReservado(ReservadoEntity entity) {

        return em.merge(entity);

    }

    public ReservadoEntity findReservado(Long id) {

        return em.find(ReservadoEntity.class, id);

    }

    public List<ReservadoEntity> findAllReservados() {
        TypedQuery<ReservadoEntity> q = em.createQuery("select u from ReservadoEntity u", ReservadoEntity.class);
        return q.getResultList();

    }

    public void deleteReservado(Long id) {

        ReservadoEntity entity = em.find(ReservadoEntity.class, id);
        em.remove(entity);
    }

}
