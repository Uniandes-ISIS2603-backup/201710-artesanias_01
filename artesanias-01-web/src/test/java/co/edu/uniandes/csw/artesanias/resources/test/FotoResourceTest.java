/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources.test;

import co.edu.uniandes.csw.artesanias.dtos.FotoDTO;
import co.edu.uniandes.csw.artesanias.entities.FotoEntity;
import co.edu.uniandes.csw.artesanias.resources.FotoResource;
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
 * @author jlake
 */
@RunWith(Arquillian.class)
public class FotoResourceTest {
    private WebTarget target;
    private final String apiPath = "api";

    PodamFactory factory = new PodamFactoryImpl();

    private final int Ok = Response.Status.OK.getStatusCode();
    private final int Created = Response.Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Response.Status.NO_CONTENT.getStatusCode();

    private final static List<FotoEntity> oraculo = new ArrayList<>();

    private final String authorPath = "fotos";

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
                .addPackage(FotoResource.class.getPackage())
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
        em.createQuery("delete from FotoEntity").executeUpdate();
        oraculo.clear();
    }

    /**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    public void insertData() {
        for (int i = 0; i < 3; i++) {
            FotoEntity foto = factory.manufacturePojo(FotoEntity.class);
            foto.setId(i + 1L);
            em.persist(foto);
            oraculo.add(foto);
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
     * Prueba para crear una foto
     *
     * @generated
     */
    @Test
    public void createFotoTest() throws IOException {
       
        FotoDTO foto = factory.manufacturePojo(FotoDTO.class);

        Response response = target
                .request()
                .post(Entity.entity(foto, MediaType.APPLICATION_JSON));
       
        FotoDTO fotoTest = (FotoDTO) response.readEntity(FotoDTO.class);

        
       

        Assert.assertEquals(foto.getFecha(), fotoTest.getFecha());
        Assert.assertEquals(foto.getUrl(), fotoTest.getUrl());

        FotoEntity entity = em.find(FotoEntity.class, fotoTest.getId());
        
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar una Foto
     *
     * @generated
     */
    @Test
    public void getFotoByIdTest() {

        FotoDTO fotoTest = target
                .path(""+oraculo.get(0).getId())
                .request().get(FotoDTO.class);

        Assert.assertEquals(fotoTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(fotoTest.getUrl(), oraculo.get(0).getUrl());
    }

    /**
     * Prueba para consultar la lista de fotos
     *
     * @generated
     */
    
    @Test
    public void listFotosTest() throws IOException {

        Response response = target
                .request().get();

        String listFotos = response.readEntity(String.class);
        List<FotoDTO> listFotoTest = new ObjectMapper().readValue(listFotos, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(oraculo.size(), listFotoTest.size());
    }

    /**
     * Prueba para actualizar una Foto
     *
     * @generated
     */
    @Test
    public void updateFotoTest() throws IOException {
        
        FotoDTO foto = new FotoDTO(oraculo.get(0));


        FotoDTO fotoChange = factory.manufacturePojo(FotoDTO.class);

        foto.setUrl(fotoChange.getUrl());
        foto.setFecha(fotoChange.getFecha());
        

        Response response = target
                .path(""+foto.getId())
                .request()
                .put(Entity.entity(foto, MediaType.APPLICATION_JSON));

        FotoDTO fotoTest = (FotoDTO) response.readEntity(FotoDTO.class);

        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(foto.getUrl(), fotoTest.getUrl());
        Assert.assertEquals(foto.getFecha(), fotoTest.getFecha());
    }

    /**
     * Prueba para eliminar una foto
     *
     * @generated
     */
    @Test
    public void deleteFotoTest() {
        FotoDTO foto = new FotoDTO(oraculo.get(0));
        Response response = target
                .path(""+foto.getId())
                .request().delete();

        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
    
}
