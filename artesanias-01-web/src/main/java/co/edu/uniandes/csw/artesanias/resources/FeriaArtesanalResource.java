/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.FeriaArtesanalDTO;
import co.edu.uniandes.csw.artesanias.ejbs.FeriaArtesanalLogic;
import co.edu.uniandes.csw.artesanias.entities.FeriaArtesanalEntity;
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
@Path("/feria")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FeriaArtesanalResource {
    @Inject private FeriaArtesanalLogic logica;

    public FeriaArtesanalResource() {
    }

    public FeriaArtesanalResource(FeriaArtesanalLogic logica) {
        this.logica = logica;
    }
    
    
    
     private List<FeriaArtesanalDTO> listEntity2DTO(List<FeriaArtesanalEntity> entityList)
    {
        List<FeriaArtesanalDTO> list = new ArrayList<>();
        for (FeriaArtesanalEntity entity : entityList) 
        {
            list.add(new FeriaArtesanalDTO(entity));
        }
        return list;
    }
    @GET
    public List<FeriaArtesanalDTO> getFerias(){
        return listEntity2DTO(logica.getFerias());
    }
     
    // TODO: revisar otra representación para GET con un id
    @GET
    @Path("{id: \\d+}")
    public FeriaArtesanalDTO getFeria(@PathParam ("id") Long id){
        return new FeriaArtesanalDTO(logica.getFeria(id));
    }
    
    // TODO: revisar otra representación. Si no se tienen datos de las relaciones
    //       como se pueden crear o actualzar las relaciones en la BD
    @POST
    public FeriaArtesanalDTO createFeria(FeriaArtesanalDTO dto){
        return new FeriaArtesanalDTO(logica.createFeria(dto.toEntity()));
    }
    
    // TODO: revisar si no se pueden actualizar las relaciones
    @PUT
    @Path("{id: \\d+}")
    public FeriaArtesanalDTO updateFeria(FeriaArtesanalDTO dto){
        return new FeriaArtesanalDTO(logica.updateFeria(dto.toEntity()));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteFeria(@PathParam("id") Integer id){
        logica.deleteFeria(id);
    }
}
