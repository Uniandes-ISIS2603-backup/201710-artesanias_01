/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author da.cortes11
 */
@RunWith(Arquillian.class)
public class ConferenciaPersistenceTest {
    
    @Inject
    private ConferenciaPersistence conferenciaPersistence;
    @PersistenceContext
    private EntityManager em;
    private final PodamFactory factory= new PodamFactoryImpl();
    
    @Inject
    private UserTransaction utx;
    
    private List<ConferenciaEntity> data= new ArrayList<>();
    
    public ConferenciaPersistenceTest() {
    }
    @Deployment 
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ConferenciaEntity.class.getPackage())
                .addPackage(ConferenciaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
                
    }
    
    @Inject
    public void setUp()
    {
         try {
            utx.begin();
            em.joinTransaction();
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
    
    private void clearData()
    {
        em.createQuery("delete from ConferenciaEntity").executeUpdate();
    }
    
    private void insertData()
    {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i=0; i<3;i++)
        {
            ConferenciaEntity entity= factory.manufacturePojo(ConferenciaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    @Test
    public void createConferenciaTest() {
        ConferenciaEntity newEntity= factory.manufacturePojo(ConferenciaEntity.class);
        ConferenciaEntity result= conferenciaPersistence.createConferencia(newEntity);
        Assert.assertNotNull(result);
        ConferenciaEntity entity= em.find(ConferenciaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getConferencista(), entity.getConferencista());
        Assert.assertEquals(newEntity.getFechaFin(), entity.getFechaFin());
        Assert.assertEquals(newEntity.getFechaInicio(), entity.getFechaInicio());
        Assert.assertEquals(newEntity.getFeria(), entity.getFeria());
        Assert.assertEquals(newEntity.getRating(), entity.getRating());
        Assert.assertEquals(newEntity.getSalon(), entity.getSalon());
        Assert.assertEquals(newEntity.getTema(), entity.getTema());
         
    }
    
    @Test
    public void getConferenciasTest(){
        List<ConferenciaEntity> list= conferenciaPersistence.findAllConferencias();
        Assert.assertEquals(data.size(), list.size());
        for(ConferenciaEntity ent: list)
        {
            boolean encontrado=false;
            for (ConferenciaEntity entity : data) {
                if (ent.getId().equals(entity.getId()))
                {
                    encontrado=true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }
  @Test
  public void getConferenciaTest()
  {
      ConferenciaEntity entity= data.get(0);
      ConferenciaEntity newEntity= conferenciaPersistence.findConferencia(entity.getId());
      Assert.assertNotNull(newEntity);
      Assert.assertEquals(entity.getConferencista(), newEntity.getConferencista());
      Assert.assertEquals(entity.getFechaInicio(), newEntity.getFechaInicio());
      Assert.assertEquals(entity.getFechaFin(), newEntity.getFechaFin());
      Assert.assertEquals(entity.getFeria(), newEntity.getFeria());
      Assert.assertEquals(entity.getRating(), newEntity.getRating());
      Assert.assertEquals(entity.getSalon(), newEntity.getSalon());
      Assert.assertEquals(entity.getTema(), newEntity.getTema());
      
  }
  @Test
  public void deleteConferencia()
  {
      ConferenciaEntity entity= data.get(0);
      conferenciaPersistence.deleteConferencia(entity.getId());
      ConferenciaEntity deleted= em.find(ConferenciaEntity.class, entity.getId());
      Assert.assertNull(deleted);
  }
  
  @Test
  public void updateConferenciaTest()
  {
      ConferenciaEntity entity= data.get(0);
      PodamFactory factory= new PodamFactoryImpl();
      ConferenciaEntity newEntity= factory.manufacturePojo(ConferenciaEntity.class);
      newEntity.setId(entity.getId());
      conferenciaPersistence.updateConferencia(newEntity);
      ConferenciaEntity resp=em.find(ConferenciaEntity.class,entity.getId());
      Assert.assertEquals(newEntity.getConferencista(), resp.getConferencista());
      Assert.assertEquals(newEntity.getFechaFin(), resp.getFechaFin());
     Assert.assertEquals(newEntity.getFechaInicio(), resp.getFechaInicio());
      Assert.assertEquals(newEntity.getFeria(), resp.getFeria());
      Assert.assertEquals(newEntity.getRating(), resp.getRating());
      Assert.assertEquals(newEntity.getSalon(), resp.getSalon());
      Assert.assertEquals(newEntity.getTema(), resp.getTema());
  }
}
