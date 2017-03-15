/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.ObraDTO;
import co.edu.uniandes.csw.artesanias.ejbs.ObraLogic;
import co.edu.uniandes.csw.artesanias.entities.ObraEntity;
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

@Path("/obras")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ObraResource 
{
     @Inject private ObraLogic logica;
    private List<ObraDTO> listEntity2DTO(List<ObraEntity> entityList)
    {
        List<ObraDTO> list = new ArrayList<>();
        for (ObraEntity entity : entityList) 
        {
            list.add(new ObraDTO(entity));
        }
        return list;
    }
    
    @GET
    public List<ObraDTO> getObras(){
        return listEntity2DTO(logica.getObras());
    }
    
    @GET
    @Path("{id: \\d+}")
    public ObraDTO getObra(@PathParam ("id") Long id){
        return new ObraDTO(logica.getObra(id));
    }
    @POST
    public ObraDTO createObra(ObraDTO dto){
        return new ObraDTO(logica.createObra(dto.toEntity()));
    } 
    @PUT
    @Path("{id: \\d+}")
    public ObraDTO updateObra(ObraDTO dto){
        return new ObraDTO(logica.updateObra(dto.toEntity()));
    }
    @DELETE
    @Path("{id: \\d+}")
    public void deleteObra(@PathParam("id") Long id){
        logica.deleteObra(id);
    }
    
}
