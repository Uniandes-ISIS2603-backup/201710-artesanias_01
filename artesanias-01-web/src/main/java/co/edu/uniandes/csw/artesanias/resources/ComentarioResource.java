/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.ComentarioDTO;
import co.edu.uniandes.csw.artesanias.ejbs.ComentarioLogic;
import co.edu.uniandes.csw.artesanias.entities.ComentarioEntity;
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

@Path("/comentarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ComentarioResource 
{
     @Inject private ComentarioLogic logica;
    private List<ComentarioDTO> listEntity2DTO(List<ComentarioEntity> entityList)
    {
        List<ComentarioDTO> list = new ArrayList<>();
        for (ComentarioEntity entity : entityList) 
        {
            list.add(new ComentarioDTO(entity));
        }
        return list;
    }
    
    @GET
    public List<ComentarioDTO> getComentarios(){
        return listEntity2DTO(logica.getComentarios());
    }
    
    @GET
    @Path("{id: \\d+}")
    public ComentarioDTO getComentario(@PathParam ("id") Long id){
        return new ComentarioDTO(logica.getComentario(id));
    }
    @POST
    public ComentarioDTO createComentario(ComentarioDTO dto){
        return new ComentarioDTO(logica.createComentario(dto.toEntity()));
    } 
    
    //
    // No se Implementa metodo PUT ya que no se toma en cuenta editar un comentario
    //
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteComentario(@PathParam("id") Long id){
        logica.deleteComentario(id);
    }
    
}
