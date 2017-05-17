/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs.test;

import co.edu.uniandes.csw.artesanias.ejbs.FotoLogic;
import co.edu.uniandes.csw.artesanias.entities.FotoEntity;
import co.edu.uniandes.csw.artesanias.entities.ObraEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.persistence.FotoPersistence;
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
public class FotoLogicTest {
     private static final Logger logger = Logger.getLogger(FotoLogic.class.getName());

 
  @Inject
  FotoPersistence fotoPersistence;

  @Inject
  ObraPersistence obraPersistence;
  
   @Inject
  FotoLogic fotoLogic;

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
            .addPackage(FotoLogic.class.getPackage())
            .addPackage(BusinessLogicException.class.getPackage())
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
    em.createQuery("delete from FotoEntity").executeUpdate();
  }

  // datos de prueba
  private ObraEntity obraEnBaseDatos;
  private List<FotoEntity> fotosEnBaseDatos = new ArrayList<>();

  // inserta datos de prueba en las tablas
  private void insertData() {
    PodamFactory factory = new PodamFactoryImpl();
  
    // crea una obra en la base de datos
    obraEnBaseDatos = factory.manufacturePojo(ObraEntity.class);
    em.persist(obraEnBaseDatos);
 
    // crea fotos en la base de datos
    for (int i = 0; i < 3; i++) {
      // crea una reserva de prueba
      FotoEntity entity = factory.manufacturePojo(FotoEntity.class);
     
      entity.setObra(obraEnBaseDatos);
      
      em.persist(entity);
      fotosEnBaseDatos.add(entity);
    }

  }
  
   // Pruebas
  // =======  
  
  /*
  Prueba para agregar una foto.
  */
  @Test
  public void agregaFoto() {

    // crear una foto de prueba   
    PodamFactory factory = new PodamFactoryImpl();

    try {
      // crea una foto de prueba
      FotoEntity foto = factory.manufacturePojo(FotoEntity.class);
      // asigna un usuario que existe en la base de datos
      foto.setObra(obraEnBaseDatos);
      
      // almacena la foto usando la lógica de negocio
      FotoEntity resultado = fotoLogic.createFoto(foto);
      
      // verifica que los datos coincidan
      Assert.assertEquals(foto.getId(), resultado.getId());
      Assert.assertEquals(foto.getObra(), resultado.getObra());

    } catch (Exception e) {
      Assert.fail("Excepcion no esperada : " + e);
    }
  }
  
  /*
  Prueba ingresar una Obra con un usuario nulo.
  */
  @Test
  public void agregaFotoUsuarioNulo(){
      // crear una foto de prueba   
    PodamFactory factory = new PodamFactoryImpl();

    try {
      // crea una foto de prueba
      FotoEntity foto = factory.manufacturePojo(FotoEntity.class);
      // asigna un usuario nulo
      foto.setObra(null);
      
      // almacena la foto usando la lógica de negocio
      FotoEntity resultado = fotoLogic.createFoto(foto);
      Assert.fail();
    } catch (Exception e) {
      Assert.assertTrue(true);
    }
  }
    
}
