/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;


import co.edu.uniandes.csw.artesanias.dtos.PabellonDTO;
import co.edu.uniandes.csw.artesanias.ejbs.UsuarioLogic;
import co.edu.uniandes.csw.artesanias.entities.UsuarioEntity;
import co.edu.uniandes.csw.artesanias.dtos.UsuarioDTO;
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
@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {
   @Inject private UsuarioLogic logic;
   
   private List<UsuarioDTO> listEntity2DTO(List<UsuarioEntity> entityList)
    {
        List<UsuarioDTO> list = new ArrayList<>(); 
        for (UsuarioEntity entity : entityList) 
        {
            list.add(new UsuarioDTO(entity));
        }
        return list;
    }
   
    @GET
    public List<UsuarioDTO> getPabellones(){
        return listEntity2DTO(logic.getUsuarios());
    }
    
    @GET
    @Path("{id: \\d+}")
    public UsuarioDTO getPabellon(@PathParam ("id") Long id){
        return new UsuarioDTO(logic.getUsuario(id));
    }
    @POST
    public UsuarioDTO createPabellon(UsuarioDTO dto){
        return new UsuarioDTO(logic.createUsuario(dto.toEntity()));
    } 
    @PUT
    @Path("{id: \\d+}")
    public UsuarioDTO updatePabellon(UsuarioDTO dto){
        return new UsuarioDTO(logic.updateUsuario(dto.toEntity()));
    }
    @DELETE
    @Path("{id: \\d+}")
    public void deletePabellon(@PathParam("id") Long id){
        logic.deleteUsuario(id);
    }
}
