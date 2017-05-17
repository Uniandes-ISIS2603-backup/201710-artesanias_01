/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.artesanias.persistance.test;

import co.edu.uniandes.csw.artesanias.ejbs.FeriaArtesanalLogic;
import co.edu.uniandes.csw.artesanias.entities.FeriaArtesanalEntity;
import co.edu.uniandes.csw.artesanias.persistence.feriaArtesanalPersistence;
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
//@RunWith(Arquillian.class)
public class FeriaPersistenceTest {
    
    private static final Logger logger = Logger.getLogger(FeriaArtesanalLogic.class.getName());

    @Inject
    feriaArtesanalPersistence persistence;

    @PersistenceContext(unitName = "feriaPU")
    protected EntityManager em;

    @Inject
    UserTransaction utx;

    @Deployment
    public static JavaArchive creaDespliege() {
        logger.info("Generando archivo de despliegue");
        JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
                .addPackage(FeriaArtesanalEntity.class.getPackage())
                .addPackage(feriaArtesanalPersistence.class.getPackage())
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
    private List<FeriaArtesanalEntity> data = new ArrayList<>();

    // inserta datos de prueba en las tablas
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            FeriaArtesanalEntity entity = factory.manufacturePojo(FeriaArtesanalEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
        
    
    @Test
    public void createFeriaTest() {   
        
        PodamFactory factory = new PodamFactoryImpl();
        FeriaArtesanalEntity newEntity = factory.manufacturePojo(FeriaArtesanalEntity.class);
        FeriaArtesanalEntity result = persistence.createFeria(newEntity);

        Assert.assertNotNull(result);

        FeriaArtesanalEntity entity = em.find(FeriaArtesanalEntity.class, result.getId());
                   
        Assert.assertEquals(newEntity.getFechaFin(), entity.getFechaFin());
        Assert.assertEquals(newEntity.getFechaInicio(), entity.getFechaInicio());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getLugar(), entity.getLugar());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    
    
    @Test
    public void findFeriaTest() {
        FeriaArtesanalEntity entity = data.get(0);
        FeriaArtesanalEntity newEntity = persistence.findFeria(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getFechaFin(), entity.getFechaFin());
        Assert.assertEquals(newEntity.getFechaInicio(), entity.getFechaInicio());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getLugar(), entity.getLugar());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    
    @Test
    public void findAllFeriasTest() {
        List<FeriaArtesanalEntity> list = persistence.findAllFerias();
        Assert.assertEquals(data.size(), list.size());
        for (FeriaArtesanalEntity ent : list) {
            boolean found = false;
            for (FeriaArtesanalEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    
    @Test
    public void updateFeriaTest() {
        FeriaArtesanalEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FeriaArtesanalEntity newEntity = factory.manufacturePojo(FeriaArtesanalEntity.class);
        newEntity.setId(entity.getId());

        persistence.updateFeria(newEntity);

        FeriaArtesanalEntity resp = em.find(FeriaArtesanalEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getFechaFin(), resp.getFechaFin());
        Assert.assertEquals(newEntity.getFechaInicio(), resp.getFechaInicio());
        Assert.assertEquals(newEntity.getId(), resp.getId());
        Assert.assertEquals(newEntity.getLugar(), resp.getLugar());
        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());        
    }

    
    @Test
    public void deleteFeriaTest() {
        FeriaArtesanalEntity entity = data.get(0);
        persistence.deleteFeria(entity.getId());
        FeriaArtesanalEntity deleted = em.find(FeriaArtesanalEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
