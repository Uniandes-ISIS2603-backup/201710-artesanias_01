/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

/**
 *
 * @author jlake
 */
import co.edu.uniandes.csw.artesanias.dtos.FotoDTO;
import co.edu.uniandes.csw.artesanias.ejbs.FotoLogic;
import co.edu.uniandes.csw.artesanias.entities.FotoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import reactor.util.Assert;

@Path("/fotos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FotoResource {
    
    @Inject
      FotoLogic logica;
     
   
    
    
    private List<FotoDTO> listEntity2DTO(List<FotoEntity> entityList)
    {
        List<FotoDTO> list = new ArrayList<>();
        for (FotoEntity entity : entityList) 
        {
            list.add(new FotoDTO(entity));
        }
        return list;
    }
    
    // TODO: revisar la representación. 
    @GET
    @Path("{id: \\d+}")
    public FotoDTO getFoto(@PathParam ("id") Long id){
        return new FotoDTO(logica.getFoto(id));
    }
    
    // TODO: revisar la representación. Si la representación no tiene relaciones
    //       como se pueden crear y actualizar las relaciones? 
    @POST
    public FotoDTO createFoto(FotoDTO dto){
        return new FotoDTO(logica.createFoto(dto.toEntity()));
    } 
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteFoto(@PathParam("id") Long id){
        logica.deleteFoto(id);
    }
    
}
