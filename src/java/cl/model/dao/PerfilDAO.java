/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model.dao;

import cl.model.dto.PerfilDTO;
import cl.model.pojos.Perfil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Twins
 */
public class PerfilDAO {
    
    public String crearPerfil(Perfil p){
        SessionFactory sf;
        Session session = null;
        Transaction tx = null;
        String response = "";
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            tx = session.beginTransaction();
            session.save(p);
            tx.commit();   
            response = "Perfil creado exitosamente";
        }
        catch(Exception ex){
            tx.rollback();
            throw new RuntimeException("No se pudo crear el Perfil");
        }
        session.close();
        return response;
    }
    
    public Perfil leerPerfil(int id){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Perfil p = (Perfil)session.get(Perfil.class, id);
        if(p != null){
            return p;
        }
        return null;
    }
    
    public List<PerfilDTO> listarPerfiles(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        //System.out.println("ajaaaaaaa ");
        Query q = session.createQuery("from Perfil");
        List<Perfil> lista = q.list();
        List<PerfilDTO> perfilesDTO = new ArrayList<>();
        int len = lista.size();  
        for (int i = 0; i < len; i++) {
            PerfilDTO pDTO = new PerfilDTO(lista.get(i));
            perfilesDTO.add(pDTO);
        }
        session.close();
        return perfilesDTO;
    }
    
    public String actualizarPerfil(Perfil p){
        SessionFactory sf;
        Session session;
        Transaction tx = null;
        String response;
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            Perfil perfil = (Perfil)session.get(Perfil.class, p.getId());
            perfil.setNombre(p.getNombre());
            perfil.setEstado(p.isEstado());
            tx = session.beginTransaction();
            session.update(perfil);
            tx.commit();   
            response = "Perfil actualizado exitosamente";
        }
        catch(Exception ex){
            tx.rollback();
            response = "No se pudo actualizar el perfil";
        }
        return response;
    }
    
    public String cambiarStatusPerfil(int id){
        SessionFactory sf;
        Session session;
        Transaction tx = null;
        String response;
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            Perfil perfil = (Perfil)session.get(Perfil.class, id);
            perfil.setEstado(!perfil.isEstado());
            tx = session.beginTransaction();
            session.update(perfil);
            tx.commit();   
            response = "El estado de "+perfil.getNombre()+" - "+perfil.getComponente().getNombre() +" fue actualizado exitosamente";
        }
        catch(Exception ex){
            tx.rollback();
            response = "No se pudo actualizar el perfil";
        }
        return response;
    }

}
