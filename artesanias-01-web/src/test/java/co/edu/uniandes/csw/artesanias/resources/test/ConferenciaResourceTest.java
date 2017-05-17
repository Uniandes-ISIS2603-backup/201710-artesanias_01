/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources.test;

import co.edu.uniandes.csw.artesanias.dtos.ComentarioDTO;
import co.edu.uniandes.csw.artesanias.dtos.ConferenciaDTO;
import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import co.edu.uniandes.csw.artesanias.resources.ConferenciaResource;
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
 * @author da.cortes11
 */
@RunWith(Arquillian.class)
public class ConferenciaResourceTest {
    
    

    private WebTarget target;
    private final String apiPath = "api";

    PodamFactory factory = new PodamFactoryImpl();

    private final int Ok = Response.Status.OK.getStatusCode();
    private final int Created = Response.Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Response.Status.NO_CONTENT.getStatusCode();
    
    private final static List<ConferenciaEntity> oraculo= new ArrayList<>();
    
    private final String authorPath= "comentarios";
    
    @ArquillianResource
    private URL deploymentURL;
   
    public ConferenciaResourceTest() {
    }
    @Deployment
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                // Se agrega las dependencias del pom
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                        .importRuntimeDependencies().resolve()
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de recursos
                .addPackage(ConferenciaResource.class.getPackage())
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
        em.createQuery("delete from ConferenciaEntity").executeUpdate();
        oraculo.clear();
    }

    /**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    public void insertData() {
        for (int i = 0; i < 3; i++) {
            ConferenciaEntity comentario = factory.manufacturePojo(ConferenciaEntity.class);
            comentario.setId(i+1L);
            em.persist(comentario);
            oraculo.add(comentario);
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
     * Prueba para crear una Obra
     *
     * @generated
     */
    @Test
    public void createConferenciaTest() throws IOException {
       
        ConferenciaDTO conferencia = factory.manufacturePojo(ConferenciaDTO.class);

        Response response = target
                .request()
                .post(Entity.entity(conferencia, MediaType.APPLICATION_JSON));
       
        ConferenciaDTO conferenciaTest = (ConferenciaDTO) response.readEntity(ConferenciaDTO.class);

        
       

        Assert.assertEquals(conferencia.getTema(), conferenciaTest.getTema());
        
        Assert.assertEquals(conferencia.getFechaInicio(), conferenciaTest.getFechaInicio());

        ConferenciaEntity entity = em.find(ConferenciaEntity.class, conferenciaTest.getId());
        
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar una Obra
     *
     * @generated
     */
    @Test
    public void getConferenciaByIdTest() {

        ConferenciaDTO conferenciaTest = target
                .path(""+oraculo.get(0).getId())
                .request().get(ConferenciaDTO.class);

        Assert.assertEquals(conferenciaTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(conferenciaTest.getTema(), oraculo.get(0).getTema());
        
    }

    /**
     * Prueba para consultar la lista de obras
     *
     * @generated
     */
    
    @Test
    public void listConferenciaTest() throws IOException {

        Response response = target
                .request().get();

        String listComentario = response.readEntity(String.class);
        List<ConferenciaDTO> listcomentarioTest = new ObjectMapper().readValue(listComentario, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(oraculo.size(), listcomentarioTest.size());
    }

    /**
     * Prueba para actualizar una Obra
     *
     * @generated
     */
    @Test
    public void updateConferenciaTest() throws IOException {
        
        ConferenciaDTO conferencia = new ConferenciaDTO(oraculo.get(0));


        ConferenciaDTO conferenciaChange = factory.manufacturePojo(ConferenciaDTO.class);

        conferencia.setTema(conferenciaChange.getTema());
        

        Response response = target
                .path(""+conferencia.getId())
                .request()
                .put(Entity.entity(conferencia, MediaType.APPLICATION_JSON));

        ConferenciaDTO conferenciaTest = (ConferenciaDTO) response.readEntity(ConferenciaDTO.class);

        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(conferencia.getTema(), conferenciaTest.getTema());
       
    }

    /**
     * Prueba para eliminar una obra
     *
     * @generated
     */
    @Test
    public void deleteConferenciaTest() {
        ConferenciaDTO conferencia = new ConferenciaDTO(oraculo.get(0));
        Response response = target
                .path(""+conferencia.getId())
                .request().delete();

        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
    
}
