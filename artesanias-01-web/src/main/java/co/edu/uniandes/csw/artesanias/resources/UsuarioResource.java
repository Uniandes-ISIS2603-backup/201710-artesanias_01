/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.ejbs.UsuarioLogic;
import co.edu.uniandes.csw.artesanias.entities.UsuarioEntity;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
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
   /**
    * 
    * @param entityList
    * @return 
    
   private List<UsuarioDTO> listEntity2DTO(List<UsuarioEntity> entityList)
    {
        List<UsuarioDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) 
        {
            list.add(new UsuarioDTO(entity));
        }
        return list;
    }
    */
}
