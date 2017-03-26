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

public class FotoPersistence {

    @PersistenceContext(unitName = "feriaPU")
    protected EntityManager em;

    public FotoEntity findFoto(Long id) {

        return em.find(FotoEntity.class, id);

    }

    public FotoEntity createFoto(FotoEntity entity) {
        em.persist(entity);

        return entity;
    }

    public void deleteFoto(Long id) {

        FotoEntity entity = em.find(FotoEntity.class, id);
        em.remove(entity);
    }

}
