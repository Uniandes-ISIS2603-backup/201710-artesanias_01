/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistence;

import java.util.List;

@Stateless
/**
 *
 * @author jm.munoz14
 */
public class ConferenciaPersistence {

    @PersistenceContext(unitName = "feriaPU")
    protected EntityManager em;

    public ConferenciaEntity createConferencia(ConferenciaEntity entity) {
        em.persist(entity);

        return entity;
    }

    public ConferenciaEntity findConferencia(Long id) {

        return em.find(ConferenciaEntity.class, id);

    }

    public ConferenciaEntity updateConferencia(ConferenciaEntity entity) {

        return em.merge(entity);

    }

    public void deleteConferencia(Long id) {

        ConferenciaEntity entity = em.find(ConferenciaEntity.class, id);
        em.remove(entity);
    }

    public List<ConferenciaEntity> findAllConferencias() {
        TypedQuery<ConferenciaEntity> q = em.createQuery("select u from ConferenciaEntity u", ConferenciaEntity.class);
        return q.getResultList();

    }

}
