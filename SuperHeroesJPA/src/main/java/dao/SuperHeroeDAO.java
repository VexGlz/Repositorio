/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.SuperHeroe;
import jakarta.persistence.EntityManager;
import java.util.List;
import util.JPAUtil;


/**
 *
 * @author Erick
 */
public class SuperHeroeDAO implements ISuperHeroeDAO{
    @Override
    public void insertar(SuperHeroe e){
        EntityManager em =  JPAUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
        }catch(Exception ex){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw ex;
        }finally{
            em.close();
        }
    }
    
    @Override
    public void actualizar(SuperHeroe e){
    
        EntityManager em = JPAUtil.getEntityManager();
        try{
            em.getTransaction();
            em.merge(e);
            em.getTransaction().commit();
        }catch(Exception ex){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw ex;
        }finally{
            em.close();
        }
        
    }
    
    @Override
    public void eliminar(Long id){
        EntityManager em =  JPAUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            SuperHeroe heroe = em.find(SuperHeroe.class, id);
            
            if(heroe != null){
                em.remove(heroe);
            }
            em.getTransaction().commit();
        }catch(Exception ex){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw ex;
        }finally{
            em.close();
        }
    }
    
    @Override
    public SuperHeroe buscar(Long id){
        EntityManager em =  JPAUtil.getEntityManager();
        try{
            return em.find(SuperHeroe.class, id);
        }finally{
            em.close();
        }
    }
    
    @Override
    public List<SuperHeroe> listar(){
    
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return em.createQuery("SELECT s FROM SuperHeroe s", SuperHeroe.class)
                    .getResultList();
        }finally{
            em.close();
        }
    
    }
    
}   
