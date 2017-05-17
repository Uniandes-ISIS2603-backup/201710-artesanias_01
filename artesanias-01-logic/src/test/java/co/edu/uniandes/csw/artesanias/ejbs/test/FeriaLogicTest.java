/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs.test;

import co.edu.uniandes.csw.artesanias.ejbs.FeriaArtesanalLogic;
import co.edu.uniandes.csw.artesanias.entities.FeriaArtesanalEntity;
import co.edu.uniandes.csw.artesanias.persistence.feriaArtesanalPersistence;
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
//@RunWith(Arquillian.class)
public class FeriaLogicTest {
    
    public static final String DEPLOY = "Prueba";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(FeriaArtesanalEntity.class.getPackage())
                .addPackage(FeriaArtesanalLogic.class.getPackage())
                .addPackage(feriaArtesanalPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private FeriaArtesanalLogic feriaLogic;

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
    private List<FeriaArtesanalEntity> data = new ArrayList<FeriaArtesanalEntity>();
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            FeriaArtesanalEntity entity = factory.manufacturePojo(FeriaArtesanalEntity.class);

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
        FeriaArtesanalEntity entity = factory.manufacturePojo(FeriaArtesanalEntity.class);
        FeriaArtesanalEntity result = feriaLogic.createFeria(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getFechaFin(), entity.getFechaFin());
        Assert.assertEquals(result.getFechaInicio(), entity.getFechaInicio());
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getLugar(), entity.getLugar());
        Assert.assertEquals(result.getNombre(), entity.getNombre());
        
    }
    /**
     * Prueba para consultar la lista de Books.
     *
     * @generated
     */
    @Test
    public void getFeriasTest() {
        List<FeriaArtesanalEntity> list = feriaLogic.getFerias();
        Assert.assertEquals(data.size(), list.size());
        for (FeriaArtesanalEntity entity : list) {
            boolean found = false;
            for (FeriaArtesanalEntity storedEntity : data) {
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
    public void getFeriaTest() {
        FeriaArtesanalEntity entity = data.get(0);
        FeriaArtesanalEntity resultEntity = feriaLogic.getFeria(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getFechaFin(), resultEntity.getFechaFin());
        Assert.assertEquals(entity.getFechaInicio(), resultEntity.getFechaInicio());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getLugar(), resultEntity.getLugar());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        
    }
    
    /**
     * Prueba para eliminar una Conferencia.
     *
     * @generated
     */
    @Test
    public void deleteFeriaTest() {
        FeriaArtesanalEntity entity = data.get(0);
        feriaLogic.deleteFeria(entity.getId());
        FeriaArtesanalEntity deleted = em.find(FeriaArtesanalEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar un Book.
     *
     * @generated
     */
    @Test
    public void updateFeriaTest() {
        FeriaArtesanalEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FeriaArtesanalEntity pojoEntity = factory.manufacturePojo(FeriaArtesanalEntity.class);
        pojoEntity.setId(entity.getId());

        feriaLogic.updateFeria(pojoEntity);

        FeriaArtesanalEntity resp = em.find(FeriaArtesanalEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getFechaFin(), resp.getFechaFin());
        Assert.assertEquals(pojoEntity.getFechaInicio(), resp.getFechaInicio());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getLugar(), resp.getLugar());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        
    }
    
}

