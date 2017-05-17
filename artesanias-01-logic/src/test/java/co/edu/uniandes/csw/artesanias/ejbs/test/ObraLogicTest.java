 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs.test;

import co.edu.uniandes.csw.artesanias.ejbs.ObraLogic;
import co.edu.uniandes.csw.artesanias.entities.ObraEntity;
import co.edu.uniandes.csw.artesanias.entities.FotoEntity;
import co.edu.uniandes.csw.artesanias.entities.ComentarioEntity;
import co.edu.uniandes.csw.artesanias.entities.UsuarioEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.persistence.FotoPersistence;
import co.edu.uniandes.csw.artesanias.persistence.ObraPersistence;
import co.edu.uniandes.csw.artesanias.persistence.ComentarioPersistence;
import co.edu.uniandes.csw.artesanias.persistence.UsuarioPersistence;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jlake
 */

public class ObraLogicTest {
    
  private static final Logger logger = Logger.getLogger(ObraLogic.class.getName());

 
  @Inject
  FotoPersistence fotoPersistence;

  @Inject
  ObraPersistence obraPersistence;
  
   @Inject
  ObraLogic obraLogic;

  @Inject
  ComentarioPersistence comentarioPersistence;
  
  @Inject
  UsuarioPersistence usuarioPersistance;

  @PersistenceContext(unitName = "feriaPU")
  protected EntityManager em;

  @Inject
  UserTransaction utx;
  
  @Deployment
  public static JavaArchive creaDespliege() {
    logger.info("Generando archivo de despliegue");
    JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
            .addPackage(ObraEntity.class.getPackage())
            .addPackage(ObraPersistence.class.getPackage())
            .addPackage(ObraLogic.class.getPackage())
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
    em.createQuery("delete from ComentarioEntity").executeUpdate();
    em.createQuery("delete from FotoEntity").executeUpdate();
    em.createQuery("delete from UsuarioEntity").executeUpdate();
  }

  // datos de prueba
  private FotoEntity fotoEnBaseDatos;
  private ComentarioEntity comentarioEnBaseDatos;
  private UsuarioEntity usuarioEnBaseDatos;
  private List<ObraEntity> obrasEnBaseDatos = new ArrayList<>();

  // inserta datos de prueba en las tablas
  private void insertData() {
    PodamFactory factory = new PodamFactoryImpl();
  
    // crea un usuario en la base de datos
    usuarioEnBaseDatos = factory.manufacturePojo(UsuarioEntity.class);
    em.persist(usuarioEnBaseDatos);

    // crea una foto en la base de datos
    fotoEnBaseDatos = factory.manufacturePojo(FotoEntity.class);
    em.persist(fotoEnBaseDatos);
    
    //crea un comentario en la base de datos
    comentarioEnBaseDatos = factory.manufacturePojo(ComentarioEntity.class);
    em.persist(comentarioEnBaseDatos);
 
    // crea obras en la base de datos
    for (int i = 0; i < 3; i++) {
      // crea una reserva de prueba
      ObraEntity entity = factory.manufacturePojo(ObraEntity.class);
     
      entity.setUsuario(usuarioEnBaseDatos);
      
      em.persist(entity);
      obrasEnBaseDatos.add(entity);
    }

  }
  
   // Pruebas
  // =======  
  
  /*
  Prueba para agregar una obra.
  */
  @Test
  public void agregaObra() {

    // crear una obra de prueba   
    PodamFactory factory = new PodamFactoryImpl();

    try {
      // crea una obra de prueba
      ObraEntity obra = factory.manufacturePojo(ObraEntity.class);
      // asigna un usuario que existe en la base de datos
      obra.setUsuario(usuarioEnBaseDatos);
      
      // almacena la obra usando la lógica de negocio
      ObraEntity resultado = obraLogic.createObra(obra);
      
      // verifica que los datos coincidan
      Assert.assertEquals(obra.getId(), resultado.getId());
      Assert.assertEquals(obra.getUsuario(), resultado.getUsuario());

    } catch (Exception e) {
      Assert.fail("Excepcion no esperada : " + e);
    }
  }
  
  /*
  Prueba ingresar una obra con un usuario nulo.
  */
  @Test
  public void agregaObraUsuarioNulo(){
      // crear una obra de prueba   
    PodamFactory factory = new PodamFactoryImpl();

    try {
      // crea una obra de prueba
      ObraEntity obra = factory.manufacturePojo(ObraEntity.class);
      // asigna un usuario nulo
      obra.setUsuario(null);
      
      // almacena la obra usando la lógica de negocio
      ObraEntity resultado = obraLogic.createObra(obra);

    } catch (Exception e) {
      Assert.assertTrue(true);
    }
  }
}