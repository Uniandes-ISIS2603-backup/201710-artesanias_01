/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.ComentarioEntity;
import java.util.ArrayList;
import java.util.Date;
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
public class ComentarioPersistenceTest {
    @Inject
    private ComentarioPersistence comentarioPersistence;
    
    @PersistenceContext
    private EntityManager em;
    private final PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private ObraPersistence obra;
    
      /**
     * @generated
     */
    private List<ComentarioEntity> data = new ArrayList<>();
    
    public ComentarioPersistenceTest() {
    }

    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentarioEntity.class.getPackage())
                .addPackage(ComentarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
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
        em.createQuery("delete from ComentarioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ComentarioEntity entity = factory.manufacturePojo(ComentarioEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void crearComentarioTest() {
        ComentarioEntity newEntity= factory.manufacturePojo(ComentarioEntity.class);
//        obra.createObra(newEntity.getObra());
       // newEntity.setFecha(new Date());
        ComentarioEntity result= comentarioPersistence.createComentario(newEntity);
        
        Assert.assertNotNull(result);
        ComentarioEntity entity= em.find(ComentarioEntity.class, result.getId());
        Assert.assertEquals(newEntity.getFecha(),entity.getFecha());
        Assert.assertEquals(newEntity.getComentario(), entity.getComentario());
        Assert.assertEquals(newEntity.getObra(), entity.getObra());
    }
    
    @Test
    public void getComentariosTest()
    {
        List<ComentarioEntity> list=comentarioPersistence.findAllComentarios();
        Assert.assertEquals(data.size(),list.size());
        for(ComentarioEntity ent: list)
        {
            boolean encontrado=false;
            for(ComentarioEntity entity:data)
            {
                if(ent.getId().equals(entity.getId()))
                {
                    encontrado= true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }
    
    @Test
    public void getComentarioTest()
    {
        ComentarioEntity entity =data.get(0);
        ComentarioEntity newEntity =comentarioPersistence.findComentario(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getComentario(), newEntity.getComentario());
        
    }
    @Test
    public void deleteComentarioTest()
    {
        ComentarioEntity entity= data.get(0);
        comentarioPersistence.deleteComentario(entity.getId());
        ComentarioEntity deleted=em.find(ComentarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void updateComentarioTest()
    {
        ComentarioEntity entity= data.get(0);
        PodamFactory factory= new PodamFactoryImpl();
        ComentarioEntity newEntity= factory.manufacturePojo(ComentarioEntity.class);
        newEntity.setId(entity.getId());
        comentarioPersistence.update(newEntity);
        ComentarioEntity resp=em.find(ComentarioEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getComentario(), resp.getComentario());
        
    }
    
    
    
    
}
