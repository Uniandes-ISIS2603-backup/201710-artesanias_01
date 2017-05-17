/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources.test;

import co.edu.uniandes.csw.artesanias.dtos.UsuarioDTO;
import co.edu.uniandes.csw.artesanias.entities.UsuarioEntity;
import co.edu.uniandes.csw.artesanias.resources.UsuarioResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
/**
 *
 * @author f.velasquez11
 */
@RunWith(Arquillian.class)
public class UsuarioResourceTest 
{
    private WebTarget target;
    private final String apiPath = "api";

    PodamFactory factory = new PodamFactoryImpl();

    private final int Ok = Response.Status.OK.getStatusCode();
    private final int Created = Response.Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Response.Status.NO_CONTENT.getStatusCode();

    private final static List<UsuarioEntity> oraculo = new ArrayList<>();

    private final String authorPath = "reservas";

    @ArquillianResource
    private URL deploymentURL;

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                // Se agrega las dependencias del pom
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                        .importRuntimeDependencies().resolve()
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de recursos
                .addPackage(UsuarioResource.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos.
                .addAsWebInfResource(
                        new File("src/main/webapp/WEB-INF/glassfish-resources.xml"),
                        "glassfish-resources.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"));
        
        // Show the deploy structure
        return war;
    }

    private WebTarget createWebTarget() {
        return ClientBuilder.newClient().target(deploymentURL.toString()).path(apiPath);
    }

    @PersistenceContext(unitName = "feriaPU")
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private void clearData() {
        em.createQuery("delete from UsuarioEntity").executeUpdate();
        oraculo.clear();
    }

    /**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    public void insertData() {
        for (int i = 0; i < 3; i++) {
            UsuarioEntity reserva = factory.manufacturePojo(UsuarioEntity.class);
            reserva.setId((i + 1L));
            em.persist(reserva);
            oraculo.add(reserva);
        }
    }

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void setUpTest() {
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
        target = createWebTarget()
                .path(authorPath);
    }

    /**
     * Prueba para crear una usuario
     *
     * @generated
     */
    @Test
    public void createUsuarioTest() throws IOException {
       
        UsuarioDTO usuario = factory.manufacturePojo(UsuarioDTO.class);

        Response response = target
                .request()
                .post(Entity.entity(usuario, MediaType.APPLICATION_JSON));
       
        UsuarioDTO usuarioTest = (UsuarioDTO) response.readEntity(UsuarioDTO.class);

        
       

        Assert.assertEquals(usuario.getId(), usuarioTest.getId());
        Assert.assertEquals(usuario.getNombre(), usuarioTest.getNombre());
        Assert.assertEquals(usuario.getNacionalidad(), usuarioTest.getNacionalidad());
        Assert.assertEquals(usuario.getEdad(), usuarioTest.getEdad());
      

        UsuarioEntity entity = em.find(UsuarioEntity.class, usuarioTest.getId());
        
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar una usuario
     *
     * @generated
     */
    @Test
    public void getUsuarioByIdTest() {

        UsuarioDTO usuarioTest = target
                .path(""+oraculo.get(0).getId())
                .request().get(UsuarioDTO.class);

        Assert.assertEquals(usuarioTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(usuarioTest.getEdad(), oraculo.get(0).getEdad());
        Assert.assertEquals(usuarioTest.getInformacionTrabajo(), oraculo.get(0).getInformacionTrabajos());
        Assert.assertEquals(usuarioTest.getNacionalidad(), oraculo.get(0).getNacionalidad());
        
    }

   
    /**
     * Prueba para actualizar un usuario
     *
     * @generated
     */
    @Test
    public void updateUsuarioTest() throws IOException {
        
        UsuarioDTO usuario = new UsuarioDTO(oraculo.get(0));


        UsuarioDTO reservaChange = factory.manufacturePojo(UsuarioDTO.class);

        usuario.setId(reservaChange.getId());
        usuario.setNombre(reservaChange.getNombre());
        usuario.setEdad(reservaChange.getEdad());
        usuario.setNacionalidad(reservaChange.getNacionalidad());
        
        

        Response response = target
                .path(""+usuario.getId())
                .request()
                .put(Entity.entity(usuario, MediaType.APPLICATION_JSON));

        UsuarioDTO usuarioTest = (UsuarioDTO) response.readEntity(UsuarioDTO.class);

        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(usuario.getId(), usuarioTest.getId());
        Assert.assertEquals(usuario.getNombre(), usuarioTest.getNombre());
        Assert.assertEquals(usuario.getNacionalidad(), usuarioTest.getNacionalidad());
        Assert.assertEquals(usuario.getEdad(), usuarioTest.getEdad());
      
    }  
}
