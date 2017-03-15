/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.StandDTO;
import co.edu.uniandes.csw.artesanias.ejbs.StandLogic;
import co.edu.uniandes.csw.artesanias.entities.StandEntity;
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

@Path("/stands")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StandResource 
{
    @Inject private StandLogic logica;
    private List<StandDTO> listEntity2DTO(List<StandEntity> entityList)
    {
        List<StandDTO> list = new ArrayList<>();
        for (StandEntity entity : entityList) 
        {
            list.add(new StandDTO(entity));
        }
        return list;
    }
    
    @GET
    public List<StandDTO> getStands(){
        return listEntity2DTO(logica.getStands());
    }
    
    @GET
    @Path("{id: \\d+}")
    public StandDTO getStand(@PathParam ("id") Long id){
        return new StandDTO(logica.getStand(id));
    }
    @POST
    public StandDTO createStand(StandDTO dto){
        return new StandDTO(logica.createStand(dto.toEntity()));
    } 
    @PUT
    @Path("{id: \\d+}")
    public StandDTO updateStand(StandDTO dto){
        return new StandDTO(logica.updateStand(dto.toEntity()));
    }
    @DELETE
    @Path("{id: \\d+}")
    public void deleteStand(@PathParam("id") Long id){
        logica.deleteStand(id);
    }
    
}
