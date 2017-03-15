    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.ComentarioEntity;
import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import co.edu.uniandes.csw.artesanias.entities.FeriaArtesanalEntity;
import co.edu.uniandes.csw.artesanias.entities.FotoEntity;
import co.edu.uniandes.csw.artesanias.entities.ObraEntity;
import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;
import co.edu.uniandes.csw.artesanias.entities.ReservadoEntity;
import co.edu.uniandes.csw.artesanias.entities.StandEntity;
import co.edu.uniandes.csw.artesanias.entities.UsuarioEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ds.tapia10
 */
@Stateless
public class feriaArtesanalPersistence {
    @PersistenceContext(unitName="feriaPU")
    protected EntityManager em;
    
    
        public FeriaArtesanalEntity createFeria (FeriaArtesanalEntity entity) 
        {
      
        em.persist(entity);
        
        return entity;
    
        }
        
        public FeriaArtesanalEntity findFeria (Long id) 
        {
      
        return em.find(FeriaArtesanalEntity.class, id);
   
        }
        
      public List<FeriaArtesanalEntity> findAllFerias()
    {
        TypedQuery <FeriaArtesanalEntity> q = em.createQuery("select u from FeriaArtesanalEntity u" , FeriaArtesanalEntity.class);
        return q.getResultList();
        
    }
   
        
        
        public FeriaArtesanalEntity updateFeria(FeriaArtesanalEntity entity) 
        {
       
        return em.merge(entity);
   
        }
        
        public void deleteFeria (Long id) {
        
        FeriaArtesanalEntity entity = em.find(FeriaArtesanalEntity.class, id);
        em.remove(entity);
    }
        
         public List<FeriaArtesanalEntity> findAllFerias()
    {
        TypedQuery <FeriaArtesanalEntity> q = em.createQuery("select u from FeriaEntity u" , FeriaArtesanalEntity.class);
        return q.getResultList(); 
        
    }
        public PabellonEntity createPabellon (PabellonEntity entity)
        {
            em.persist(entity);
            
            return entity;
        }
        
        public ReservadoEntity createReservado (ReservadoEntity entity)
        {
            em.persist(entity);
            
            return entity;
        }
        
         public PabellonEntity findPabellon (Long id) 
        {
      
        return em.find(PabellonEntity.class, id);
   
        }
        
        public PabellonEntity updatePabellon(PabellonEntity entity) 
        {
       
        return em.merge(entity);
   
        } 
        
        public ReservadoEntity updateReservado(ReservadoEntity entity) 
        {
       
        return em.merge(entity);
   
        }
        
        public void deletePabellon (Long id) {
        
        PabellonEntity entity = em.find(PabellonEntity.class, id);
        em.remove(entity);
    }
         
        public List<PabellonEntity> findAllPabellones()
    {
        TypedQuery <PabellonEntity> q = em.createQuery("select u from PabellonEntity u" , PabellonEntity.class);
        return q.getResultList();
        
    }
        
    public StandEntity createStand (StandEntity entity)
        {
            em.persist(entity);
            
            return entity;
        }
        
         public StandEntity findStand (Long id) 
        {
      
        return em.find(StandEntity.class, id);
   
        }
        public StandEntity updateStand(StandEntity entity) 
        {
       
        return em.merge(entity);
   
        } 
        
        public void deleteStand (Long id) {
        
        StandEntity entity = em.find(StandEntity.class, id);
        em.remove(entity);
    }
         
        public List<StandEntity> findAllStands()
    {
        TypedQuery <StandEntity> q = em.createQuery("select u from StandEntity u" , StandEntity.class);
        return q.getResultList();
        
    }
        
            public UsuarioEntity createUsuario (UsuarioEntity entity)
        {
            em.persist(entity);
            
            return entity;
        }
        
         public UsuarioEntity findUsuario (Long id) 
        {
      
        return em.find(UsuarioEntity.class, id);
   
        }
        public UsuarioEntity updateUsuario(UsuarioEntity entity) 
        {
       
        return em.merge(entity);
   
        } 
        
        public void deleteUsuario (Long id) {
        
        UsuarioEntity entity = em.find(UsuarioEntity.class, id);
        em.remove(entity);
    }
         
        public List<UsuarioEntity> findAllUsuarios()
    {
        TypedQuery <UsuarioEntity> q = em.createQuery("select u from UsuarioEntity u" , UsuarioEntity.class);
        return q.getResultList();
        
    }
    
         public ConferenciaEntity createConferencia (ConferenciaEntity entity)
        {
            em.persist(entity);
            
            return entity;
        }
        
         public ConferenciaEntity findConferencia (Long id) 
        {
      
        return em.find(ConferenciaEntity.class, id);
   
        }
        public ConferenciaEntity updateConferencia(ConferenciaEntity entity) 
        {
       
        return em.merge(entity);
   
        } 
        
        public void deleteConferencia (Long id) {
        
        ConferenciaEntity entity = em.find(ConferenciaEntity.class, id);
        em.remove(entity);
    }
         
        public List<ConferenciaEntity> findAllConferencias()
    {
        TypedQuery <ConferenciaEntity> q = em.createQuery("select u from ConferenciaEntity u" , ConferenciaEntity.class);
        return q.getResultList();
        
    }
        
         public ObraEntity createObra (ObraEntity entity)
        {
            em.persist(entity);
            
            return entity;
        }
        
         public ObraEntity findObra (Long id) 
        {
      
        return em.find(ObraEntity.class, id);
   
        }
        public ObraEntity updateObra(ObraEntity entity) 
        {
       
        return em.merge(entity);
   
        } 
        
        public void deleteObra (Long id) {
        
        ObraEntity entity = em.find(ObraEntity.class, id);
        em.remove(entity);
    }
         
        public List<ObraEntity> findAllObras()
    {
        TypedQuery <ObraEntity> q = em.createQuery("select u from ObraEntity u" , ObraEntity.class);
        return q.getResultList();
        
    }
        
         public ComentarioEntity createComentario (ComentarioEntity entity)
        {
            em.persist(entity);
            
            return entity;
        }
        
        
        public void deleteComentario (Long id) {
        
        ComentarioEntity entity = em.find(ComentarioEntity.class, id);
        em.remove(entity);
    }
     
                 public FotoEntity createFoto (FotoEntity entity)
        {
            em.persist(entity);
            
            return entity;
        }
        
        
        public void deleteFoto (Long id) {
        
        FotoEntity entity = em.find(FotoEntity.class, id);
        em.remove(entity);
    }
        
   
 
}
