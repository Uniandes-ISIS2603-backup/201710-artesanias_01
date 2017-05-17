/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs.test;

import co.edu.uniandes.csw.artesanias.ejbs.PabellonLogic;
import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;
import co.edu.uniandes.csw.artesanias.persistence.PabellonPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ds.tapia10
 */
public class PabellonLogicTest {
    
    public static final String DEPLOY = "Prueba";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(PabellonEntity.class.getPackage())
                .addPackage(PabellonLogic.class.getPackage())
                .addPackage(PabellonPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private PabellonLogic pabellonLogic;

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
    private List<PabellonEntity> data = new ArrayList<PabellonEntity>();
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            PabellonEntity entity = factory.manufacturePojo(PabellonEntity.class);

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
    public void createFeriaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PabellonEntity entity = factory.manufacturePojo(PabellonEntity.class);
        PabellonEntity result = pabellonLogic.createPabellon(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getTema(), entity.getTema());
        
    }
    /**
     * Prueba para consultar la lista de Books.
     *
     * @generated
     */
    @Test
    public void getFPabellonesTest() {
        List<PabellonEntity> list = pabellonLogic.getPabellones();
        Assert.assertEquals(data.size(), list.size());
        for (PabellonEntity entity : list) {
            boolean found = false;
            for (PabellonEntity storedEntity : data) {
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
    public void getPabellonTest() {
        PabellonEntity entity = data.get(0);
        PabellonEntity resultEntity = pabellonLogic.getPabellon(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getTema(), resultEntity.getTema());
        
    }
    
    /**
     * Prueba para eliminar una Conferencia.
     *
     * @generated
     */
    @Test
    public void deletePabellonTest() {
        PabellonEntity entity = data.get(0);
        pabellonLogic.deletePabellon(entity.getId());
        PabellonEntity deleted = em.find(PabellonEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar un Book.
     *
     * @generated
     */
    @Test
    public void updatePabellonTest() {
        PabellonEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PabellonEntity pojoEntity = factory.manufacturePojo(PabellonEntity.class);
        pojoEntity.setId(entity.getId());

        pabellonLogic.updatePabellon(pojoEntity);

        PabellonEntity resp = em.find(PabellonEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getTema(), resp.getTema());
        
    }
    
}