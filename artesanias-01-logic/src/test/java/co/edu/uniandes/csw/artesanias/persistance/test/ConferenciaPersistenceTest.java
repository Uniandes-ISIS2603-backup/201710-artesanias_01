/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistance.test;

import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import co.edu.uniandes.csw.artesanias.persistence.ConferenciaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author da.cortes11
 */

@RunWith(Arquillian.class)
public class ConferenciaPersistenceTest {
    public static final String DEPLOY= "Prueba";
    
    @Deployment
    public static WebArchive createDeployment()
    {
      return ShrinkWrap.create(WebArchive.class, DEPLOY +".war")
              .addPackage(ConferenciaEntity.class.getPackage())
                .addPackage(ConferenciaPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * @generated
     */
    @Inject
    private ConferenciaPersistence conferenciaPersistence;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    UserTransaction utx;

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from ConferenciaEntity").executeUpdate();
    }
    
     /**
     * @generated
     */
    private List<ConferenciaEntity> data = new ArrayList<ConferenciaEntity>();
    
     /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            ConferenciaEntity entity = factory.manufacturePojo(ConferenciaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear una Conferencia.
     *
     * @generated
     */
    @Test
    public void createConferenciaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ConferenciaEntity newEntity = factory.manufacturePojo(ConferenciaEntity.class);
        ConferenciaEntity result = conferenciaPersistence.createConferencia(newEntity);

        Assert.assertNotNull(result);

        ConferenciaEntity entity = em.find(ConferenciaEntity.class, result.getId());
        
        Assert.assertEquals(newEntity.getTema(), entity.getTema());
        Assert.assertEquals(newEntity.getSalon(), entity.getSalon());
        Assert.assertEquals(newEntity.getRating(), entity.getRating());
        Assert.assertEquals(newEntity.getFechaInicio(), entity.getFechaInicio());
        Assert.assertEquals(newEntity.getFechaFin(), entity.getFechaFin());
        
    }
    
    /**
     * Prueba para consultar la lista de Conferencias.
     *
     * @generated
     */
    @Test
    public void getConferenciasTest() {
        List<ConferenciaEntity> list = conferenciaPersistence.findAllConferencias();
        Assert.assertEquals(data.size(), list.size());
        for (ConferenciaEntity ent : list) {
            boolean found = false;
            for (ConferenciaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar una Conferencia.
     *
     * @generated
     */
    @Test
    public void getConferenciaTest() {
        ConferenciaEntity entity = data.get(0);
        ConferenciaEntity newEntity = conferenciaPersistence.findConferencia(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getTema(), newEntity.getTema());
        Assert.assertEquals(entity.getSalon(), newEntity.getSalon());
        Assert.assertEquals(entity.getFechaInicio(), newEntity.getFechaInicio());
        Assert.assertEquals(entity.getFechaFin(), newEntity.getFechaFin());
    }
    /**
     * Prueba para eliminar un Conferencia.
     *
     * @generated
     */
    @Test
    public void deleteConferenciaTest() {
        ConferenciaEntity entity = data.get(0);
        conferenciaPersistence.deleteConferencia(entity.getId());
        ConferenciaEntity deleted = em.find(ConferenciaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
     /**
     * Prueba para actualizar una Conferencia.
     *
     * @generated
     */
    @Test
    public void updateConferenciaTest() {
        ConferenciaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ConferenciaEntity newEntity = factory.manufacturePojo(ConferenciaEntity.class);
        newEntity.setId(entity.getId());

       conferenciaPersistence.updateConferencia(newEntity);

        ConferenciaEntity resp = em.find(ConferenciaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getTema(), resp.getTema());
        Assert.assertEquals(newEntity.getSalon(), resp.getSalon());
        Assert.assertEquals(newEntity.getRating(), resp.getRating());
        Assert.assertEquals(newEntity.getFechaInicio(), resp.getFechaInicio());
        Assert.assertEquals(newEntity.getFechaFin(), resp.getFechaFin());
        
    }
    
    
    
}
