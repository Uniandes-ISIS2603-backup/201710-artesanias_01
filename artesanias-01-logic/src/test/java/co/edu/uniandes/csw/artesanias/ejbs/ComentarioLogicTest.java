/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.ComentarioEntity;
import co.edu.uniandes.csw.artesanias.persistence.ComentarioPersistence;
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
import org.junit.Before;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author da.cortes11
 */
@RunWith(Arquillian.class)
public class ComentarioLogicTest {
    
    @PersistenceContext
    private EntityManager em;
    private final PodamFactory factory= new PodamFactoryImpl();
    
    public ComentarioLogicTest() {
    }
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentarioEntity.class.getPackage())
                .addPackage(ComentarioLogic.class.getPackage())
                .addPackage(ComentarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    private ComentarioLogic comentarioLogic;
    
    private List<ComentarioEntity> data= new ArrayList<>();
    
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
    public void createComentarioTest() {
        
        ComentarioEntity newEntity= factory.manufacturePojo(ComentarioEntity.class);
        ComentarioEntity result= comentarioLogic.createComentario(newEntity);
        Assert.assertNotNull(result);
        ComentarioEntity entity= em.find(ComentarioEntity.class, result.getId());
        Assert.assertEquals(result.getComentario(), entity.getComentario());
        Assert.assertEquals(result.getFecha(), entity.getFecha());
        Assert.assertEquals(result.getObra(), entity.getObra());
    }
    
    @Test
    public void getComentariosTest()
    {
        List<ComentarioEntity> list=comentarioLogic.getComentarios();
        Assert.assertEquals(data.size(), list.size());
        for(ComentarioEntity ent:data)
        {
            boolean found=false;
            for (ComentarioEntity entity : list) {
                if(ent.getId().equals(entity.getId()))
                {
                    found=true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getComentarioTest()
    {
        ComentarioEntity entity= data.get(0);
        ComentarioEntity resultEntity= comentarioLogic.getComentario(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getComentario(), resultEntity.getComentario());
        Assert.assertEquals(entity.getFecha(), resultEntity.getFecha());
        Assert.assertEquals(entity.getObra(), resultEntity.getObra());
    }
    
    @Test
    public void deleteComentarioTest()
    {
        ComentarioEntity entity= data.get(0);
        comentarioLogic.deleteComentario(entity.getId());
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
        comentarioLogic.updateComentario(newEntity);
        ComentarioEntity resp=em.find(ComentarioEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getComentario(), resp.getComentario());
        
    }
}
