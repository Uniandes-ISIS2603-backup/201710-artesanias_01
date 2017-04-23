// TODO: Eliminar los comentarios por defecti
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
    
    
    public ConferenciaResource() {
    }

    public ConferenciaResource(ConferenciaLogic logica) {
        this.logica = logica;
    }
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
    
    // TODO: revisar otra representación para getConferencia
    @GET
    @Path("{id: \\d+}")
    public ConferenciaDTO getConferencia(@PathParam ("id") Long id){
        // TODO: si no existe el id debe retornar un error / código 404
        return new ConferenciaDTO(logica.getConferencia(id));
    }
    
    // TODO: revisar la representación. si ConferenciaDTO no tiene relaciones
    //       como se pueden crear y acualizar las relaciones en la base de datos ?
    @POST
    public ConferenciaDTO createConferencia(ConferenciaDTO dto){
        return new ConferenciaDTO(logica.createConferencia(dto.toEntity()));
    } 
    
    // TODO: revisar si no se pueden actualizar las relaciones 
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
