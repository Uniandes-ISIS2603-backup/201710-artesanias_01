// TODO: eliminar los mensajes por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.PabellonDTO;
import co.edu.uniandes.csw.artesanias.ejbs.PabellonLogic;
import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author f.velasquez11
 */
@Path("/pabellones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PabellonResource {
    @Inject private PabellonLogic logica;
    private List<PabellonDTO> listEntity2DTO(List<PabellonEntity> entityList)
    {
        List<PabellonDTO> list = new ArrayList<>();
        for (PabellonEntity entity : entityList) 
        {
            list.add(new PabellonDTO(entity));
        }
        return list;
    }
    
    @GET
    public List<PabellonDTO> getPabellones(){
        return listEntity2DTO(logica.getPabellones());
    }
    
    // TODO: revisar la representación
    @GET
    @Path("{id: \\d+}")
    public PabellonDTO getPabellon(@PathParam ("id") Long id){
        return new PabellonDTO(logica.getPabellon(id));
    }
    
    // TODO: revisar la representación
    @POST
    public PabellonDTO createPabellon(PabellonDTO dto){
        return new PabellonDTO(logica.createPabellon(dto.toEntity()));
    } 
    
    // TODO: revisar la representación
    @PUT
    @Path("{id: \\d+}")
    public PabellonDTO updatePabellon(PabellonDTO dto){
        return new PabellonDTO(logica.updatePabellon(dto.toEntity()));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deletePabellon(@PathParam("id") Long id){
        logica.deletePabellon(id);
    }
    
}
