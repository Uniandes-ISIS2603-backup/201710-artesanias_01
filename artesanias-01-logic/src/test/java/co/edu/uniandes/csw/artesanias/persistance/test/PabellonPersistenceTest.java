/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistance.test;

import co.edu.uniandes.csw.artesanias.ejbs.PabellonLogic;
import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;
import co.edu.uniandes.csw.artesanias.persistence.PabellonPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import java.util.logging.Level;
//import org.jboss.arquillian.junit.Arquillian;
//import org.junit.runner.RunWith;

/**
 *
 * @author ds.tapia10
 */
public class PabellonPersistenceTest {
    
      private static final Logger logger = Logger.getLogger(PabellonLogic.class.getName());

    @Inject
    PabellonPersistence persistence;

    @PersistenceContext(unitName = "feriaPU")
    protected EntityManager em;

    @Inject
    UserTransaction utx;

    @Deployment
    public static JavaArchive creaDespliege() {
        logger.info("Generando archivo de despliegue");
        JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
                .addPackage(PabellonEntity.class.getPackage())
                .addPackage(PabellonPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml")
                .addAsManifestResource("META-INF/logging.properties", "logging.properties");
        logger.info(archive.toString());
        return archive;
    }

    @Before
    @SuppressWarnings("UseSpecificCatch")
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } 
        catch (Exception e) {
            logger.log(Level.FINE, "Error creando datos de prueba", e);
            try {
                utx.rollback();
            } 
            catch (Exception e1) {
                logger.log(Level.FINE, "Error haciendo rollback de datos de prueba", e1);
            }
        }
    }

    // borra las tablas
    private void clearData() {
      em.createQuery("delete from FeriaArtesanalEntity").executeUpdate();
    }

    // datos de prueba
    private List<PabellonEntity> data = new ArrayList<>();

    // inserta datos de prueba en las tablas
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PabellonEntity entity = factory.manufacturePojo(PabellonEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
        
    
    @Test
    public void createPabellonTest() {   
        
        PodamFactory factory = new PodamFactoryImpl();
        PabellonEntity newEntity = factory.manufacturePojo(PabellonEntity.class);
        PabellonEntity result = persistence.createPabellon(newEntity);

        Assert.assertNotNull(result);

        PabellonEntity entity = em.find(PabellonEntity.class, result.getId());
                   
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getTema(), entity.getTema());
    }
    
    
    @Test
    public void findPabellonTest() {
        PabellonEntity entity = data.get(0);
        PabellonEntity newEntity = persistence.findPabellon(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getTema(), entity.getTema());
    }

    
    @Test
    public void findAllPabellonTest() {
        List<PabellonEntity> list = persistence.findAllPabellones();
        Assert.assertEquals(data.size(), list.size());
        for (PabellonEntity ent : list) {
            boolean found = false;
            for (PabellonEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    
    @Test
    public void updatePabellonTest() {
        PabellonEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PabellonEntity newEntity = factory.manufacturePojo(PabellonEntity.class);
        newEntity.setId(entity.getId());

        persistence.updatePabellon(newEntity);

        PabellonEntity resp = em.find(PabellonEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
        Assert.assertEquals(newEntity.getTema(), resp.getTema());      
    }

    
    @Test
    public void deletePabellonTest() {
        PabellonEntity entity = data.get(0);
        persistence.deletePabellon(entity.getId());
        PabellonEntity deleted = em.find(PabellonEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}

