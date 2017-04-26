
import co.edu.uniandes.csw.artesanias.entities.ComentarioEntity;
import co.edu.uniandes.csw.artesanias.persistence.ComentarioPersistence;
import static com.sun.corba.ee.spi.transport.TcpTimeouts.factory;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import static org.glassfish.pfl.basic.tools.argparser.ElementParser.factory;
import org.junit.runner.RunWith;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author da.cortes11
 */
@RunWith(Arquillian.class)
public class ComentarioPersistenceTest {
    
    public static final String DEPLOY="Prueba";
    
    @Deployment
    public static WebArchive createDeplyment(){
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(ComentarioEntity.class.getPackage())
                .addPackage(ComentarioPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml","beans.xml");
                
    }
    
    @Inject
    private ComentarioPersistence comentarioPersistence;
    
    @PersistenceContext
    private  EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest()
    {
        try {
           utx.begin();
           clearData();
           insertData();
           utx.commit();
        } 
        catch (Exception e) {
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
        em.createQuery("delete from ComentarioEntity").executeUpdate();
    }
    
    private List<ComentarioEntity> data = new ArrayList<ComentarioEntity>();
    
    private void insertData(){
        for(int i=0; i<3;i++){
            PodamFactory factory= new PodamFactoryImpl();
            ComentarioEntity entity= factory.manufacturePojo(ComentarioEntity.class);
            em.persist(entity);
            data.add(entity);
                    
        }
    }
    @Test
    public void createComentarioTest(){
        PodamFactory factory= new PodamFactoryImpl();    
        ComentarioEntity newEntity= factory.manufacturePojo(ComentarioEntity.class);
        ComentarioEntity result= comentarioPersistence.createComentario(newEntity);
        Assert.assertNotNull(result);
        
        ComentarioEntity entity= em.find(ComentarioEntity.class, result.getId());
        
        Assert.assertEquals(newEntity.getId(), entity.getComentario());
        Assert.assertEquals(newEntity.getFecha(), entity.getFecha());
        
    }
    
    @Test
    public void getComentariosTest()
    {
      List<ComentarioEntity> list= comentarioPersistence.findAllComentarios();
      Assert.assertEquals(data.size(), list.size());
      for(ComentarioEntity ent: list){
          boolean found = false;
          for(ComentarioEntity entity:data){
              //verificar por que con el id no sirve
              if(ent.getComentario().equals(entity.getComentario())){
                  found=true;
              }
          }
          Assert.assertTrue(found);
      }
      
    }
    
    @Test 
    public void getComentarioTest(){
        ComentarioEntity entity= data.get(0);
        ComentarioEntity newEntity= comentarioPersistence.findComentario(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());
        Assert.assertEquals(entity.getComentario(), newEntity.getComentario());
        
    }
    
    @Test
    public void deleteComentarioTest(){
        ComentarioEntity entity= data.get(0);
        comentarioPersistence.deleteComentario(entity.getId());
        ComentarioEntity deleted= em.find(ComentarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    @Test
    public void updateComentarioTest()
    {
        ComentarioEntity entity=data.get(0);
        PodamFactory factory= new PodamFactoryImpl();
        ComentarioEntity newEntity= factory.manufacturePojo(ComentarioEntity.class);
        newEntity.setId(entity.getId());
        
        //revisar los id de que tipo de dato estan declarados
        //revisar lo metodos que faltan en los persistence
        comentarioPersistence.update(newEntity);
        ComentarioEntity resp= em.find(ComentarioEntity.class,entity.getId());
        Assert.assertEquals(newEntity.getFecha(), resp.getFecha() );
    }

}

