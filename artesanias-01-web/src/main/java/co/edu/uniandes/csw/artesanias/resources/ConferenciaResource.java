/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.ConferenciaDTO;
import co.edu.uniandes.csw.artesanias.ejbs.ConferenciaLogic;
import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
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

@Path("/conferencias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConferenciaResource 
{
    @Inject private ConferenciaLogic logica;
    private List<ConferenciaDTO> listEntity2DTO(List<ConferenciaEntity> entityList)
    {
        List<ConferenciaDTO> list = new ArrayList<>();
        for (ConferenciaEntity entity : entityList) 
        {
            list.add(new ConferenciaDTO(entity));
        }
        return list;
    }
    
    @GET
    public List<ConferenciaDTO> getConferencias(){
        return listEntity2DTO(logica.getConferencias());
    }
    
    @GET
    @Path("{id: \\d+}")
    public ConferenciaDTO getConferencia(@PathParam ("id") Long id){
        return new ConferenciaDTO(logica.getConferencia(id));
    }
    @POST
    public ConferenciaDTO createConferencia(ConferenciaDTO dto){
        return new ConferenciaDTO(logica.createConferencia(dto.toEntity()));
    } 
    @PUT
    @Path("{id: \\d+}")
    public ConferenciaDTO updateConferencia(ConferenciaDTO dto){
        return new ConferenciaDTO(logica.updateConferencia(dto.toEntity()));
    }
    @DELETE
    @Path("{id: \\d+}")
    public void deleteConferencia(@PathParam("id") Long id){
        logica.deleteConferencia(id);
    }
    
}
