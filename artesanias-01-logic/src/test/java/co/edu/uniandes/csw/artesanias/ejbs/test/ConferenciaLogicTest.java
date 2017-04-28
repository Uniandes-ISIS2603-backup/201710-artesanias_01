/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs.test;

import co.edu.uniandes.csw.artesanias.ejbs.ConferenciaLogic;
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
public class ConferenciaLogicTest {
    
    public static final String DEPLOY = "Prueba";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(ConferenciaEntity.class.getPackage())
                .addPackage(ConferenciaLogic.class.getPackage())
                .addPackage(ConferenciaPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private ConferenciaLogic conferenciaLogic;

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
        em.createQuery("delete from BookEntity").executeUpdate();
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
     * Prueba para crear un Book.
     *
     * @generated
     */
    @Test
    public void createConferenciaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ConferenciaEntity entity = factory.manufacturePojo(ConferenciaEntity.class);
        ConferenciaEntity result = conferenciaLogic.createConferencia(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getTema(), entity.getTema());
        Assert.assertEquals(result.getSalon(), entity.getSalon());
        Assert.assertEquals(result.getRating(), entity.getRating());
        Assert.assertEquals(result.getFeria(), entity.getFeria());
        Assert.assertEquals(result.getFechaInicio(), entity.getFechaInicio());
        Assert.assertEquals(result.getFechaFin(), entity.getFechaFin());
        Assert.assertEquals(result.getConferencista(), entity.getConferencista());
        
    }
    /**
     * Prueba para consultar la lista de Books.
     *
     * @generated
     */
    @Test
    public void getConferenciasTest() {
        List<ConferenciaEntity> list = conferenciaLogic.getConferencias();
        Assert.assertEquals(data.size(), list.size());
        for (ConferenciaEntity entity : list) {
            boolean found = false;
            for (ConferenciaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un Book.
     *
     * @generated
     */
    @Test
    public void getConferenciaTest() {
        ConferenciaEntity entity = data.get(0);
        ConferenciaEntity resultEntity = conferenciaLogic.getConferencia(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getTema(), resultEntity.getTema());
        Assert.assertEquals(entity.getSalon(), resultEntity.getSalon());
        Assert.assertEquals(entity.getRating(), resultEntity.getRating());
        Assert.assertEquals(entity.getFeria(), resultEntity.getFeria());
        Assert.assertEquals(entity.getFechaInicio(), resultEntity.getFechaInicio());
        Assert.assertEquals(entity.getFechaFin(), resultEntity.getFechaFin());
        Assert.assertEquals(entity.getConferencista(), resultEntity.getConferencista());
        
    }
    
    /**
     * Prueba para eliminar una Conferencia.
     *
     * @generated
     */
    @Test
    public void deleteBookTest() {
        ConferenciaEntity entity = data.get(0);
        conferenciaLogic.deleteConferencia(entity.getId());
        ConferenciaEntity deleted = em.find(ConferenciaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar un Book.
     *
     * @generated
     */
    @Test
    public void updateBookTest() {
        ConferenciaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ConferenciaEntity pojoEntity = factory.manufacturePojo(ConferenciaEntity.class);
        pojoEntity.setId(entity.getId());

        conferenciaLogic.updateConferencia(pojoEntity);

        ConferenciaEntity resp = em.find(ConferenciaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getTema(), resp.getTema());
        Assert.assertEquals(pojoEntity.getSalon(), resp.getSalon());
        Assert.assertEquals(pojoEntity.getRating(), resp.getRating());
        Assert.assertEquals(pojoEntity.getFeria(), resp.getFeria());
        Assert.assertEquals(pojoEntity.getFechaInicio(), resp.getFechaInicio());
        Assert.assertEquals(pojoEntity.getFechaFin(), resp.getFechaFin());
        Assert.assertEquals(pojoEntity.getConferencista(), resp.getConferencista());
        
    }
    
}
