/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistance.test;

import co.edu.uniandes.csw.artesanias.ejbs.ObraLogic;
import co.edu.uniandes.csw.artesanias.entities.ObraEntity;
import co.edu.uniandes.csw.artesanias.persistence.ObraPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jlake
 */
@RunWith(Arquillian.class)
public class ObraPersistenceTest {
    
    private static final Logger logger = Logger.getLogger(ObraLogic.class.getName());

  @Inject
  ObraPersistence persistence;

  @PersistenceContext(unitName = "Parcial3PU")
  protected EntityManager em;

  @Inject
  UserTransaction utx;

  @Deployment
  public static JavaArchive creaDespliege() {
    logger.info("Generando archivo de despliegue");
    JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
            .addPackage(ObraEntity.class.getPackage())
            .addPackage(ObraPersistence.class.getPackage())
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource("META-INF/beans.xml", "beans.xml");
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
    } catch (Exception e) {
      logger.log(Level.FINE, "Error creando datos de prueba", e);
      try {
        utx.rollback();
      } catch (Exception e1) {
        logger.log(Level.FINE, "Error haciendo rollback de datos de prueba", e1);
      }
    }
  }
    
  // borra las tablas
  private void clearData() {
    em.createQuery("delete from ObraEntity").executeUpdate();
  }

  // datos de prueba
  private List<ObraEntity> data = new ArrayList<>();

  // inserta datos de prueba en las tablas
  private void insertData() {
    PodamFactory factory = new PodamFactoryImpl();
    for (int i = 0; i < 3; i++) {
      ObraEntity entity = factory.manufacturePojo(ObraEntity.class);
      em.persist(entity);
      data.add(entity);
    }
  }
  
  /*
  Prueba conseguir toda la lista de obras.
  */
  
  @Test
  public void crearObra() {
    PodamFactory factory = new PodamFactoryImpl();
    ObraEntity newEntity = factory.manufacturePojo(ObraEntity.class);

    ObraEntity result = persistence.createObra(newEntity);
    Assert.assertNotNull("Al grabar no retornó objeto",
            result);

    ObraEntity entity = em.find(ObraEntity.class, result.getId());
    Assert.assertEquals("No retorna los mismos datos grabados",
            newEntity.getNombre(),
            entity.getNombre());
  }
  
  @Test
  public void getObrasTest(){
      List<ObraEntity> list = persistence.findAllObras();
      
      Assert.assertEquals(data.size(), list.size());
      for (ObraEntity ent : list){
          boolean found = false;
          for(ObraEntity entity : data)
              if(ent.getId()==entity.getId())
                  found = true;
          Assert.assertTrue(found);
      }
  }
  
  
  /*
  Prueba conseguir una obra específica.
  */
  @Test
  public void getObraTest(){
      //Consigue la segunda obra de la lista y la compara con la segunda obra en la base de datos.
      ObraEntity comp = data.get(1);
      
      ObraEntity ent = persistence.findObra(new Long(comp.getId()));
      Assert.assertEquals(comp.getId(), ent.getId());
      Assert.assertEquals(comp.getNombre(), ent.getNombre());
  }
}
