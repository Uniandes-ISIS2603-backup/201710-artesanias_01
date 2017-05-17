/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistance.test;

import co.edu.uniandes.csw.artesanias.ejbs.FotoLogic;
import co.edu.uniandes.csw.artesanias.entities.FotoEntity;
import co.edu.uniandes.csw.artesanias.persistence.FotoPersistence;
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
public class FotoPersistenceTest {
       
  private static final Logger logger = Logger.getLogger(FotoLogic.class.getName());

  @Inject
  FotoPersistence persistence;

  @PersistenceContext(unitName = "feriaPU")
  protected EntityManager em;

  @Inject
  UserTransaction utx;

  @Deployment
  public static JavaArchive creaDespliege() {
    logger.info("Generando archivo de despliegue");
    JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
            .addPackage(FotoEntity.class.getPackage())
            .addPackage(FotoPersistence.class.getPackage())
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
    em.createQuery("delete from FotoEntity").executeUpdate();
  }

  // datos de prueba
  private List<FotoEntity> data = new ArrayList<>();

  // inserta datos de prueba en las tablas
  private void insertData() {
    PodamFactory factory = new PodamFactoryImpl();
    for (int i = 0; i < 3; i++) {
      FotoEntity entity = factory.manufacturePojo(FotoEntity.class);
      em.persist(entity);
      data.add(entity);
    }
  }
  
  @Test
  public void crearFoto() {
    PodamFactory factory = new PodamFactoryImpl();
    FotoEntity newEntity = factory.manufacturePojo(FotoEntity.class);

    FotoEntity result = persistence.createFoto(newEntity);
    Assert.assertNotNull("Al grabar no retornó objeto",
            result);

    FotoEntity entity = em.find(FotoEntity.class, result.getId());
    Assert.assertEquals("No retorna los mismos datos grabados",
            newEntity.getUrl(),
            entity.getUrl());
  }
  
  
  /*
  Prueba conseguir una foto específica.
  */
  @Test
  public void getFotoTest(){
      //Consigue la segunda foto de la lista y la compara con la segunda foto en la base de datos.
      FotoEntity comp = data.get(1);
      
      FotoEntity ent = persistence.findFoto(comp.getId());
      
      Assert.assertEquals(comp.getId(), ent.getId());
      Assert.assertEquals(comp.getUrl(), ent.getUrl());
  }
  
  /*
     * Prueba para eliminar una foto
   */
  @Test
  public void eliminarFoto() {

    //eliminar la segunda obra usando la clase persistencia
    FotoEntity p = data.get(1);
    persistence.deleteFoto(p.getId());
    // leer esa misma foto usando el EntityManager
    FotoEntity c =em.find(FotoEntity.class, p.getId());
    // la foto que retorna el EntityManager debe ser null
    Assert.assertNull(c);
  }
  
}
