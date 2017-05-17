/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import co.edu.uniandes.csw.artesanias.persistence.ConferenciaPersistence;
import java.util.ArrayList;
import java.util.List;
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
 * @author da.cortes11
 */
@RunWith(Arquillian.class)
public class ConferenciaLogicTest {
    
    @PersistenceContext
    private EntityManager em;
    private final PodamFactory factory= new PodamFactoryImpl();
    
    public ConferenciaLogicTest() {
    }
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ConferenciaEntity.class.getPackage())
                .addPackage(ConferenciaLogic.class.getPackage())
                .addPackage(ConferenciaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    private ConferenciaLogic conferenciaLogic;
    
    private List<ConferenciaEntity> data= new ArrayList<>();
    
    @Inject
    UserTransaction utx;
     /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void setUp() {
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
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from ConferenciaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ConferenciaEntity entity = factory.manufacturePojo(ConferenciaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    @Test
    public void createConferenciaTest() {
        ConferenciaEntity newEntity= factory.manufacturePojo(ConferenciaEntity.class);
        ConferenciaEntity result= conferenciaLogic.createConferencia(newEntity);
        Assert.assertNotNull(result);
        ConferenciaEntity entity= em.find(ConferenciaEntity.class, result.getId());
        Assert.assertEquals(result.getConferencista(), entity.getConferencista());
        Assert.assertEquals(result.getFechaFin(), entity.getFechaFin());
        Assert.assertEquals(result.getFechaInicio(), entity.getFechaInicio());
        Assert.assertEquals(result.getFeria(), entity.getFeria());
        Assert.assertEquals(result.getRating(), entity.getRating());
        Assert.assertEquals(result.getSalon(), entity.getSalon());
        Assert.assertEquals(result.getTema(), entity.getTema());
    }
    
     @Test
    public void getConferenciasTest()
    {
        List<ConferenciaEntity> list=conferenciaLogic.getConferencias();
        Assert.assertEquals(data.size(), list.size());
        for(ConferenciaEntity ent:data)
        {
            boolean found=false;
            for (ConferenciaEntity entity : list) {
                if(ent.getId().equals(entity.getId()))
                {
                    found=true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getConferenciaTest()
    {
        ConferenciaEntity entity= data.get(0);
        ConferenciaEntity resultEntity= conferenciaLogic.getConferencia(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getConferencista(), resultEntity.getConferencista());
        Assert.assertEquals(entity.getFechaFin(), resultEntity.getFechaFin());
        Assert.assertEquals(entity.getFechaInicio(), resultEntity.getFechaInicio());
        Assert.assertEquals(entity.getFeria(), resultEntity.getFeria());
        Assert.assertEquals(entity.getRating(), resultEntity.getRating());
        Assert.assertEquals(entity.getSalon(), resultEntity.getSalon());
        Assert.assertEquals(entity.getTema(), resultEntity.getTema());
    }
    
    @Test
    public void deleteConferenciaTest()
    {
        ConferenciaEntity entity= data.get(0);
        conferenciaLogic.deleteConferencia(entity.getId());
        ConferenciaEntity deleted=em.find(ConferenciaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void updateComentarioTest()
    {
        ConferenciaEntity entity= data.get(0);
        PodamFactory factory= new PodamFactoryImpl();
        ConferenciaEntity newEntity= factory.manufacturePojo(ConferenciaEntity.class);
        newEntity.setId(entity.getId());
        conferenciaLogic.updateConferencia(newEntity);
        ConferenciaEntity resp=em.find(ConferenciaEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getConferencista(), resp.getConferencista());
        Assert.assertEquals(newEntity.getFechaFin(), resp.getFechaFin());
        Assert.assertEquals(newEntity.getFechaInicio(), resp.getFechaInicio());
        Assert.assertEquals(newEntity.getFeria(), resp.getFeria());
        Assert.assertEquals(newEntity.getRating(), resp.getRating());
        Assert.assertEquals(newEntity.getSalon(), resp.getSalon());
        Assert.assertEquals(newEntity.getTema(), resp.getTema());
        
        
    }
}
