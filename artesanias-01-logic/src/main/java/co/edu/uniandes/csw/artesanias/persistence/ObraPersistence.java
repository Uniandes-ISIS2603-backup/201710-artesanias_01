/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.ObraEntity;
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
public class ObraPersistence {

    @PersistenceContext(unitName = "feriaPU")
    protected EntityManager em;

    public ObraEntity createObra(ObraEntity entity) {
        em.persist(entity);

        return entity;
    }

    public ObraEntity findObra(Long id) {

        return em.find(ObraEntity.class, id);

    }

    public ObraEntity updateObra(ObraEntity entity) {

        return em.merge(entity);

    }

    public void deleteObra(Long id) {

        ObraEntity entity = em.find(ObraEntity.class, id);
        em.remove(entity);
    }

    public List<ObraEntity> findAllObras() {
        TypedQuery<ObraEntity> q = em.createQuery("select u from ObraEntity u", ObraEntity.class);
        return q.getResultList();

    }
}
