package co.edu.uniandes.csw.artesanias.persistance.test;

import co.edu.uniandes.csw.artesanias.ejbs.ReservadoLogic;
import co.edu.uniandes.csw.artesanias.entities.ReservadoEntity;
import co.edu.uniandes.csw.artesanias.persistence.ReservadoPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(Arquillian.class)
public class ReservasPersistenceTest {

  private static final Logger logger = Logger.getLogger(ReservadoLogic.class.getName());

  @Inject
  ReservadoPersistence reservadoPersistence;

  @PersistenceContext(unitName = "feriaPU")
  protected EntityManager em;

  @Inject
  UserTransaction utx;

  @Deployment
  public static JavaArchive creaDespliege() {
    logger.info("Generando archivo de despliegue");
    JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
            .addPackage(ReservadoEntity.class.getPackage())
            .addPackage(ReservadoPersistence.class.getPackage())
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
    em.createQuery("delete from ReservadoEntity").executeUpdate();
  }

  // datos de prueba
  private List<ReservadoEntity> data = new ArrayList<>();

  // inserta datos de prueba en las tablas
  private void insertData() {
    PodamFactory factory = new PodamFactoryImpl();
    for (int i = 0; i < 3; i++) {
      ReservadoEntity entity = factory.manufacturePojo(ReservadoEntity.class);
      em.persist(entity);
      data.add(entity);
    }
  }


    /**
     * Prueba para crear un Stand.
     *
     * @generated
     */
    @Test
    public void createReservaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ReservadoEntity newEntity = factory.manufacturePojo(ReservadoEntity.class);
        ReservadoEntity result = reservadoPersistence.createReservado(newEntity);

        Assert.assertNotNull(result);

        ReservadoEntity entity = em.find(ReservadoEntity.class, result.getId());

       
         Assert.assertEquals(entity.getArtesano(), newEntity.getArtesano());
        Assert.assertEquals(entity.getCosto(), newEntity.getCosto());
        Assert.assertEquals(entity.getEstado(), newEntity.getEstado());
         Assert.assertEquals(entity.getStand(), newEntity.getStand());
        
    }

    /**
     * Prueba para consultar la lista de Stands.
     *
     * @generated
     */
    @Test
    public void getReservasTest() {
        List<ReservadoEntity> list = reservadoPersistence.findAllReservados();
        Assert.assertEquals(data.size(), list.size());
        for (ReservadoEntity ent : list) {
            boolean found = false;
            for (ReservadoEntity entity : data) {
                if (ent.getId() == entity.getId()) 
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Stand.
     *
     * @generated
     */
    @Test
    public void getReservaTest() {
        ReservadoEntity entity = data.get(1);
        ReservadoEntity newEntity = reservadoPersistence.findReservado(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getArtesano(), newEntity.getArtesano());
        Assert.assertEquals(entity.getCosto(), newEntity.getCosto());
        Assert.assertEquals(entity.getEstado(), newEntity.getEstado());
        
    }

    /**
     * Prueba para eliminar un Stand.
     *
     * @generated
     */
    @Test
    public void deleteReservaTest() {
        ReservadoEntity entity = data.get(1);
        reservadoPersistence.deleteReservado(entity.getId());
        ReservadoEntity deleted = em.find(ReservadoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Book.
     *
     * @generated
     */
    @Test
    public void updateBookTest() {
        ReservadoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ReservadoEntity newEntity = factory.manufacturePojo(ReservadoEntity.class);
        newEntity.setId(entity.getId());

        reservadoPersistence.updateReservado(newEntity);

        ReservadoEntity resp = em.find(ReservadoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getArtesano(), resp.getArtesano());
        Assert.assertEquals(newEntity.getCosto(), resp.getCosto());
        Assert.assertEquals(newEntity.getEstado(), resp.getEstado());

    }
}
