// TODO: eliminar mensajes por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.CrearReservaDTO;
import co.edu.uniandes.csw.artesanias.ejbs.ReservadoLogic;
import co.edu.uniandes.csw.artesanias.entities.ReservadoEntity;
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
import reactor.util.Assert;

/**
 *
 * @author f.velasquez11
 */

@Path("/reservas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservaResource 
{
    private final ReservadoLogic logica;  
    @Inject
    public ReservaResource(ReservadoLogic logica) {
        Assert.notNull(logica, "My logica must not be null");
        this.logica = logica;
    }
      
      
    private List<CrearReservaDTO> listEntity2DTO(List<ReservadoEntity> entityList)
    {
        List<CrearReservaDTO> list = new ArrayList<>();
        for (ReservadoEntity entity : entityList) 
        {
            list.add(new CrearReservaDTO(entity));
        }
        return list;
    }
    
    @GET
    public List<CrearReservaDTO> getReservados(){
        return listEntity2DTO(logica.getReservados());
    }
    
    // TODO: revisar la representación
    @GET
    @Path("{id: \\d+}")
    public CrearReservaDTO getReservado(@PathParam ("id") Long id){
        return new CrearReservaDTO(logica.getReservado(id));
    }
    
    // TODO: revisar la representación
    @POST
    public CrearReservaDTO createReservado(CrearReservaDTO dto){
        return new CrearReservaDTO(logica.createReservado(dto.toEntity()));
    } 
    
    // TODO: revisar la representación
    @PUT
    @Path("{id: \\d+}")
    public CrearReservaDTO updateReservado(CrearReservaDTO dto){
        return new CrearReservaDTO(logica.updateReservado(dto.toEntity()));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteReservado(@PathParam("id") Long id){
        logica.deleteReservado(id);
    }
    
}
