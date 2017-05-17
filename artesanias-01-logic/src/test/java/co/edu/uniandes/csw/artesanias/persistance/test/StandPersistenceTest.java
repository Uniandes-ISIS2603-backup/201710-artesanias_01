package co.edu.uniandes.csw.artesanias.persistance.test;

import co.edu.uniandes.csw.artesanias.entities.StandEntity;
import co.edu.uniandes.csw.artesanias.persistence.StandPersistence;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class StandPersistenceTest {

    public static final String DEPLOY = "Prueba";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(StandEntity.class.getPackage())
                .addPackage(StandPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private StandPersistence standPersistence;

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
        em.createQuery("delete from StandEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<StandEntity> data = new ArrayList<StandEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            StandEntity entity = factory.manufacturePojo(StandEntity.class);
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
    public void createStandTest() {
        PodamFactory factory = new PodamFactoryImpl();
        StandEntity newEntity = factory.manufacturePojo(StandEntity.class);
        StandEntity result = standPersistence.createStand(newEntity);

        Assert.assertNotNull(result);

        StandEntity entity = em.find(StandEntity.class, result.getNumeroStand());

       
         Assert.assertEquals(entity.getCaracteristicas(), newEntity.getCaracteristicas());
        Assert.assertEquals(entity.getPrecio(), newEntity.getPrecio());
        Assert.assertEquals(entity.getTamanio(), newEntity.getTamanio());
        
    }

    /**
     * Prueba para consultar la lista de Stands.
     *
     * @generated
     */
    @Test
    public void getStandsTest() {
        List<StandEntity> list = standPersistence.findAllStands();
        Assert.assertEquals(data.size(), list.size());
        for (StandEntity ent : list) {
            boolean found = false;
            for (StandEntity entity : data) {
                if (ent.getNumeroStand() == entity.getNumeroStand()) 
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
    public void getBookTest() {
        StandEntity entity = data.get(1);
        StandEntity newEntity = standPersistence.findStand(entity.getNumeroStand());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getCaracteristicas(), newEntity.getCaracteristicas());
        Assert.assertEquals(entity.getPrecio(), newEntity.getPrecio());
        Assert.assertEquals(entity.getTamanio(), newEntity.getTamanio());
        
    }

    /**
     * Prueba para eliminar un Stand.
     *
     * @generated
     */
    @Test
    public void deleteStandTest() {
        StandEntity entity = data.get(1);
        standPersistence.deleteStand(entity.getNumeroStand());
        StandEntity deleted = em.find(StandEntity.class, entity.getNumeroStand());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Book.
     *
     * @generated
     */
    @Test
    public void updateBookTest() {
        StandEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        StandEntity newEntity = factory.manufacturePojo(StandEntity.class);
        newEntity.setNumeroStand(entity.getNumeroStand());

        standPersistence.updateStand(newEntity);

        StandEntity resp = em.find(StandEntity.class, entity.getNumeroStand());

        Assert.assertEquals(newEntity.getCaracteristicas(), resp.getCaracteristicas());
        Assert.assertEquals(newEntity.getPrecio(), resp.getPrecio());
        Assert.assertEquals(newEntity.getTamanio(), resp.getTamanio());

    }
}
