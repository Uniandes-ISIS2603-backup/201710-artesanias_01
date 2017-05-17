/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources.test;

import co.edu.uniandes.csw.artesanias.dtos.ComentarioDTO;
import co.edu.uniandes.csw.artesanias.dtos.ObraDTO;
import co.edu.uniandes.csw.artesanias.entities.ComentarioEntity;
import co.edu.uniandes.csw.artesanias.resources.ComentarioResource;
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
import static org.glassfish.api.admin.Supplemental.Timing.Before;
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
public class ComentarioResourceTest {
    
     private WebTarget target;
    private final String apiPath = "api";

    PodamFactory factory = new PodamFactoryImpl();

    private final int Ok = Response.Status.OK.getStatusCode();
    private final int Created = Response.Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Response.Status.NO_CONTENT.getStatusCode();
    
    private final static List<ComentarioEntity> oraculo= new ArrayList<>();
    
    private final String authorPath= "comentarios";
    
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
                .addPackage(ComentarioResource.class.getPackage())
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
        em.createQuery("delete from ComentarioEntity").executeUpdate();
        oraculo.clear();
    }

    /**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    public void insertData() {
        for (int i = 0; i < 3; i++) {
            ComentarioEntity comentario = factory.manufacturePojo(ComentarioEntity.class);
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

    public ComentarioResourceTest() {
    }
    /**
     * Prueba para crear una Obra
     *
     * @generated
     */
    @Test
    public void createComentarioTest() throws IOException {
       
        ComentarioDTO comentario = factory.manufacturePojo(ComentarioDTO.class);

        Response response = target
                .request()
                .post(Entity.entity(comentario, MediaType.APPLICATION_JSON));
       
        ComentarioDTO comentarioTest = (ComentarioDTO) response.readEntity(ComentarioDTO.class);

        
       

        Assert.assertEquals(comentario.getComentario(), comentarioTest.getComentario());
        
        Assert.assertEquals(comentario.getFecha(), comentarioTest.getFecha());

        ComentarioEntity entity = em.find(ComentarioEntity.class, comentarioTest.getId());
        
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar una Obra
     *
     * @generated
     */
    @Test
    public void getComentarioByIdTest() {

        ComentarioDTO comentarioTest = target
                .path(""+oraculo.get(0).getId())
                .request().get(ComentarioDTO.class);

        Assert.assertEquals(comentarioTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(comentarioTest.getComentario(), oraculo.get(0).getComentario());
        
    }

    /**
     * Prueba para consultar la lista de obras
     *
     * @generated
     */
    
    @Test
    public void listComentarioTest() throws IOException {

        Response response = target
                .request().get();

        String listComentario = response.readEntity(String.class);
        List<ComentarioDTO> listcomentarioTest = new ObjectMapper().readValue(listComentario, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(oraculo.size(), listcomentarioTest.size());
    }

    /**
     * Prueba para actualizar una Obra
     *
     * @generated
     */
    @Test
    public void updateComentarioTest() throws IOException {
        
        ComentarioDTO comentario = new ComentarioDTO(oraculo.get(0));


        ComentarioDTO comentarioChange = factory.manufacturePojo(ComentarioDTO.class);

        comentario.setComentario(comentarioChange.getComentario());
        

        Response response = target
                .path(""+comentario.getId())
                .request()
                .put(Entity.entity(comentario, MediaType.APPLICATION_JSON));

        ComentarioDTO comentarioTest = (ComentarioDTO) response.readEntity(ComentarioDTO.class);

        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(comentario.getComentario(), comentarioTest.getComentario());
       
    }

    /**
     * Prueba para eliminar una obra
     *
     * @generated
     */
    @Test
    public void deleteComentarioTest() {
        ComentarioDTO comentario = new ComentarioDTO(oraculo.get(0));
        Response response = target
                .path(""+comentario.getId())
                .request().delete();

        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
    
}
