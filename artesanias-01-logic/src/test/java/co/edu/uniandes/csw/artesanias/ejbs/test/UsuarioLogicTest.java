/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs.test;

import co.edu.uniandes.csw.artesanias.ejbs.UsuarioLogic;
import co.edu.uniandes.csw.artesanias.entities.UsuarioEntity;
import co.edu.uniandes.csw.artesanias.persistence.UsuarioPersistence;
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
 *
 * @author f.velasquez11
 */

@RunWith(Arquillian.class)
public class UsuarioLogicTest 
{
     public static final String DEPLOY = "Prueba";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private UsuarioLogic usuarioLogic;

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
        em.createQuery("delete from BookEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<UsuarioEntity> data = new ArrayList<UsuarioEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Usuario.
     *
     * @generated
     */
    @Test
    public void createUsuarioTest() {
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
        UsuarioEntity result = usuarioLogic.createUsuario(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getNombre(), entity.getNombre());
        Assert.assertEquals(result.getNacionalidad(), entity.getNacionalidad());
        Assert.assertEquals(result.getEdad(), entity.getEdad());
        Assert.assertEquals(result.getFeria(), entity.getFeria());
    }

    /**
     * Prueba para consultar la lista de Usuarios.
     *
     * @generated
     */
    @Test
    public void getUsuariosTest() {
        List<UsuarioEntity> list = usuarioLogic.getUsuarios();
        Assert.assertEquals(data.size(), list.size());
        for (UsuarioEntity entity : list) {
            boolean found = false;
            for (UsuarioEntity storedEntity : data) {
                if (entity.getId() == (storedEntity.getId())) 
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Usuario.
     *
     * @generated
     */
    @Test
    public void getUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        long num = Math.toIntExact(entity.getId());
        UsuarioEntity resultEntity = usuarioLogic.getUsuario(num);
        Assert.assertNotNull(resultEntity);
       Assert.assertEquals(resultEntity.getId(), entity.getId());
        Assert.assertEquals(resultEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(resultEntity.getNacionalidad(), entity.getNacionalidad());
        Assert.assertEquals(resultEntity.getEdad(), entity.getEdad());
        Assert.assertEquals(resultEntity.getFeria(), entity.getFeria());
    }

    /**
     * Prueba para eliminar un Usuario.
     *
     * @generated
     */
    @Test
    public void deleteUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        long num = Math.toIntExact(entity.getId());
        usuarioLogic.deleteUsuario(num);
        UsuarioEntity deleted = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Usuario.
     *
     * @generated
     */
    @Test
    public void updateUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity pojoEntity = factory.manufacturePojo(UsuarioEntity.class);
        pojoEntity.setId(entity.getId());

        usuarioLogic.updateUsuario(pojoEntity);

        UsuarioEntity resp = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertNotNull(resp);
        Assert.assertEquals(pojoEntity.getId(), entity.getId());
        Assert.assertEquals(pojoEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(pojoEntity.getNacionalidad(), entity.getNacionalidad());
        Assert.assertEquals(pojoEntity.getEdad(), entity.getEdad());
        Assert.assertEquals(pojoEntity.getFeria(), entity.getFeria());
        
    }
    
}
