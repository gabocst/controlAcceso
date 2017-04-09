/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model.dao;

import cl.model.dto.UnidadOrganizacionalDTO;
import cl.model.pojos.Estado;
import cl.model.pojos.Unidadorganizacional;
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
public class UnidadOrganizacionalDAO {
    
    public String crearUnidad(Unidadorganizacional u){
        SessionFactory sf;
        Session session = null;
        Transaction tx = null;
        String response = "";
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            tx = session.beginTransaction();
            session.save(u);
            tx.commit();   
            response = "Unidad Organizacional creada exitosamente";
        }
        catch(Exception ex){
            tx.rollback();
            throw new RuntimeException("No se pudo crear la Unidad Organizacional");
        }
        session.close();
        return response;
    }
    
    public Unidadorganizacional leerUnidad(int id){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Unidadorganizacional u = (Unidadorganizacional)session.get(Unidadorganizacional.class, id);
        if(u != null){
            //UnidadOrganizacionalDTO unidad = new UnidadOrganizacionalDTO(u);
            //session.close();
            return u;
        }
        //session.close();
        return null;
    }
    
    public List<UnidadOrganizacionalDTO> listaUnidad(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query q = session.createQuery("from Unidadorganizacional");
        List<Unidadorganizacional> lista = q.list();
        List<UnidadOrganizacionalDTO> unidadesDTO = new ArrayList<>();
        int len = lista.size();  
        for (int i = 0; i < len; i++) {
            UnidadOrganizacionalDTO uDTO = new UnidadOrganizacionalDTO(lista.get(i));
            unidadesDTO.add(uDTO);
        }
        session.close();
        return unidadesDTO;
    }
    
    
    public String actualizarUnidad(Unidadorganizacional u){
        SessionFactory sf;
        Session session;
        Transaction tx = null;
        String response;
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            Unidadorganizacional unidad = (Unidadorganizacional)session.get(Unidadorganizacional.class, u.getId());
            unidad.setNombre(u.getNombre());
            unidad.setEstado(u.getEstado());
            tx = session.beginTransaction();
            session.update(unidad);
            tx.commit();   
            response = "Unidad Organizacional actualizada exitosamente";
        }
        catch(Exception ex){
            tx.rollback();
            response = "No se pudo actualizar la Unidad Organizacional";
        }
        return response;
    }
    
    public String cambiarStatusUnidad(int id){
        SessionFactory sf;
        Session session;
        Transaction tx = null;
        String response;
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            Unidadorganizacional unidad = (Unidadorganizacional)session.get(Unidadorganizacional.class, id);
            Estado e = new Estado();
            if(unidad.getEstado().getNombre().equals("Activo")){
                e.setId(0);
            }
            else{
                e.setId(1);
            }
            unidad.setEstado(e);
            tx = session.beginTransaction();
            session.update(unidad);
            tx.commit();   
            response = "El estado de "+unidad.getNombre()+" fue actualizado exitosamente";
        }
        catch(Exception ex){
            tx.rollback();
            response = "No se pudo actualizar la unidad organizacional";
        }
        return response;
    }
    
}
