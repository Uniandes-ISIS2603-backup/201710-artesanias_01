/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources.test;

import co.edu.uniandes.csw.artesanias.dtos.CrearReservaDTO;
import co.edu.uniandes.csw.artesanias.entities.ReservadoEntity;
import co.edu.uniandes.csw.artesanias.resources.ReservaResource;
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
 * @author jm.munoz14
 */
@RunWith(Arquillian.class)
public class ReservadoResourceTest {
    private WebTarget target;
    private final String apiPath = "api";

    PodamFactory factory = new PodamFactoryImpl();

    private final int Ok = Response.Status.OK.getStatusCode();
    private final int Created = Response.Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Response.Status.NO_CONTENT.getStatusCode();

    private final static List<ReservadoEntity> oraculo = new ArrayList<>();

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
                .addPackage(ReservaResource.class.getPackage())
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
        em.createQuery("delete from ReservadoEntity").executeUpdate();
        oraculo.clear();
    }

    /**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    public void insertData() {
        for (int i = 0; i < 3; i++) {
            ReservadoEntity reserva = factory.manufacturePojo(ReservadoEntity.class);
            reserva.setId(Math.toIntExact(i + 1L));
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
     * Prueba para crear una reserva
     *
     * @generated
     */
    @Test
    public void createReservaTest() throws IOException {
       
        CrearReservaDTO reserva = factory.manufacturePojo(CrearReservaDTO.class);

        Response response = target
                .request()
                .post(Entity.entity(reserva, MediaType.APPLICATION_JSON));
       
        CrearReservaDTO reservaTest = (CrearReservaDTO) response.readEntity(CrearReservaDTO.class);

        
       

        Assert.assertEquals(reserva.getCosto(), reservaTest.getCosto());
        Assert.assertEquals(reserva.getEstado(), reservaTest.getEstado());

        ReservadoEntity entity = em.find(ReservadoEntity.class, reservaTest.getId());
        
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar una reserva
     *
     * @generated
     */
    @Test
    public void getReservaByIdTest() {

        CrearReservaDTO reservaTest = target
                .path(""+oraculo.get(0).getId())
                .request().get(CrearReservaDTO.class);

        Assert.assertEquals(reservaTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(reservaTest.getEstado(), oraculo.get(0).getEstado());
    }

   
    /**
     * Prueba para actualizar una reserva
     *
     * @generated
     */
    @Test
    public void updateReservaTest() throws IOException {
        
        CrearReservaDTO reserva = new CrearReservaDTO(oraculo.get(0));


        CrearReservaDTO reservaChange = factory.manufacturePojo(CrearReservaDTO.class);

        reserva.setEstado(reservaChange.getEstado());
        reserva.setCosto(reservaChange.getCosto());
        

        Response response = target
                .path(""+reserva.getId())
                .request()
                .put(Entity.entity(reserva, MediaType.APPLICATION_JSON));

        CrearReservaDTO reservaTest = (CrearReservaDTO) response.readEntity(CrearReservaDTO.class);

        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(reserva.getEstado(), reservaTest.getEstado());
        Assert.assertEquals(reserva.getCosto(), reservaTest.getCosto());
    }

    /**
     * Prueba para eliminar una reserva
     *
     * @generated
     */
    @Test
    public void deleteFotoTest() {
        CrearReservaDTO reserva = new CrearReservaDTO(oraculo.get(0));
        Response response = target
                .path(""+reserva.getId())
                .request().delete();

        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
    
}
