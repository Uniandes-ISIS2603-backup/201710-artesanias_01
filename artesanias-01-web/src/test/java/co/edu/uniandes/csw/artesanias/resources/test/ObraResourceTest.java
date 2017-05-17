/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources.test;

import co.edu.uniandes.csw.artesanias.dtos.ObraDTO;
import co.edu.uniandes.csw.artesanias.entities.ObraEntity;
import co.edu.uniandes.csw.artesanias.resources.ObraResource;
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
 * @author jh.lake
 */
@RunWith(Arquillian.class)
public class ObraResourceTest {
    private WebTarget target;
    private final String apiPath = "api";

    PodamFactory factory = new PodamFactoryImpl();

    private final int Ok = Response.Status.OK.getStatusCode();
    private final int Created = Response.Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Response.Status.NO_CONTENT.getStatusCode();

    private final static List<ObraEntity> oraculo = new ArrayList<>();

    private final String authorPath = "obras";

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
                .addPackage(ObraResource.class.getPackage())
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
        em.createQuery("delete from ObraEntity").executeUpdate();
        oraculo.clear();
    }

    /**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    public void insertData() {
        for (int i = 0; i < 3; i++) {
            ObraEntity obra = factory.manufacturePojo(ObraEntity.class);
            obra.setId(i + 1L);
            em.persist(obra);
            oraculo.add(obra);
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
    public void createObraTest() throws IOException {
       
        ObraDTO obra = factory.manufacturePojo(ObraDTO.class);

        Response response = target
                .request()
                .post(Entity.entity(obra, MediaType.APPLICATION_JSON));
       
        ObraDTO obraTest = (ObraDTO) response.readEntity(ObraDTO.class);

        
       

        Assert.assertEquals(obra.getNombre(), obraTest.getNombre());
        
        Assert.assertEquals(obra.getTecnica(), obraTest.getTecnica());

        ObraEntity entity = em.find(ObraEntity.class, obraTest.getId());
        
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar una Obra
     *
     * @generated
     */
    @Test
    public void getObraByIdTest() {

        ObraDTO obraTest = target
                .path(""+oraculo.get(0).getId())
                .request().get(ObraDTO.class);

        Assert.assertEquals(obraTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(obraTest.getNombre(), oraculo.get(0).getNombre());
    }

    /**
     * Prueba para consultar la lista de obras
     *
     * @generated
     */
    
    @Test
    public void listAuthorTest() throws IOException {

        Response response = target
                .request().get();

        String listObra = response.readEntity(String.class);
        List<ObraDTO> listObraTest = new ObjectMapper().readValue(listObra, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(oraculo.size(), listObraTest.size());
    }

    /**
     * Prueba para actualizar una Obra
     *
     * @generated
     */
    @Test
    public void updateObraest() throws IOException {
        
        ObraDTO obra = new ObraDTO(oraculo.get(0));


        ObraDTO obraChange = factory.manufacturePojo(ObraDTO.class);

        obra.setNombre(obraChange.getNombre());
        obra.setTecnica(obraChange.getTecnica());
        

        Response response = target
                .path(""+obra.getId())
                .request()
                .put(Entity.entity(obra, MediaType.APPLICATION_JSON));

        ObraDTO obraTest = (ObraDTO) response.readEntity(ObraDTO.class);

        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(obra.getNombre(), obraTest.getNombre());
        Assert.assertEquals(obra.getTecnica(), obraTest.getTecnica());
    }

    /**
     * Prueba para eliminar una obra
     *
     * @generated
     */
    @Test
    public void deleteObraTest() {
        ObraDTO obra = new ObraDTO(oraculo.get(0));
        Response response = target
                .path(""+obra.getId())
                .request().delete();

        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
