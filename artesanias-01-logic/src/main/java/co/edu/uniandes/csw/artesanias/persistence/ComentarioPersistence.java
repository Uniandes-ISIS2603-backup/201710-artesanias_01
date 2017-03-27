/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.ComentarioEntity;
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
public class ComentarioPersistence {

    @PersistenceContext(unitName = "feriaPU")
    protected EntityManager em;

    public ComentarioEntity findComentario(Long id) {

        return em.find(ComentarioEntity.class, id);

    }

    public ComentarioEntity createComentario(ComentarioEntity entity) {
        em.persist(entity);

        return entity;
    }

    public List<ComentarioEntity> findAllComentarios() {
        TypedQuery<ComentarioEntity> q = em.createQuery("select u from ComentarioEntity u", ComentarioEntity.class);
        return q.getResultList();

    }

    public void deleteComentario(Long id) {

        ComentarioEntity entity = em.find(ComentarioEntity.class, id);
        em.remove(entity);
    }
}
