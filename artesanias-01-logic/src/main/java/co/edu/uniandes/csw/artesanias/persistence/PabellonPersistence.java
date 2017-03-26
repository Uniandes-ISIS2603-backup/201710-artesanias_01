/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistence;

@Stateless
/**
 *
 * @author jm.munoz14
 */
public class PabellonPersistence {

    @PersistenceContext(unitName = "feriaPU")
    protected EntityManager em;

    public PabellonEntity createPabellon(PabellonEntity entity) {
        em.persist(entity);

        return entity;
    }


    public PabellonEntity findPabellon(Long id) {

        return em.find(PabellonEntity.class, id);

    }

    public PabellonEntity updatePabellon(PabellonEntity entity) {

        return em.merge(entity);

    }



    public void deletePabellon(Long id) {

        PabellonEntity entity = em.find(PabellonEntity.class, id);
        em.remove(entity);
    }

    public List<PabellonEntity> findAllPabellones() {
        TypedQuery<PabellonEntity> q = em.createQuery("select u from PabellonEntity u", PabellonEntity.class);
        return q.getResultList();

    }
}
