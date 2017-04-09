/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model.dao;

import cl.model.pojos.Componente;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import java.util.List;
/**
 *
 * @author Twins
 */
public class ComponenteDAO {
    public String crearComponete(Componente c){
        SessionFactory sf;
        Session session = null;
        Transaction tx = null;
        String response = "";
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            tx = session.beginTransaction();
            session.save(c);
            tx.commit();   
            response = "Componente creado exitosamente";
        }
        catch(Exception ex){
            tx.rollback();
            throw new RuntimeException("No se pudo crear el componente");
        }
        session.close();
        return response;
    }
    
    public Componente leerComponente(int id){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Componente c = (Componente)session.get(Componente.class, id);
        //session.close();
        if(c != null){
            return c;
        }
        else{
            return null;
        }
    }
    
    public List<Componente> listaComponente(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query q = session.createQuery("from Componente");
        List<Componente> lista = q.list();
        //session.close();
        return lista;
    }
    
    public String actualizarComponete(Componente c){
        SessionFactory sf;
        Session session;
        Transaction tx = null;
        String response;
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            Componente compo = (Componente)session.get(Componente.class, c.getId());
            compo.setDescripcion(c.getDescripcion());
            compo.setNombre(c.getNombre());
            compo.setEstado(c.isEstado());
            tx = session.beginTransaction();
            session.update(compo);
            tx.commit();   
            response = "Componente actualizado exitosamente";
        }
        catch(Exception ex){
            tx.rollback();
            response = "No se pudo actualizar el componente";
        }
        return response;
    }
    
    public String cambiarStatusComponete(int id){
        SessionFactory sf;
        Session session;
        Transaction tx = null;
        String response;
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            Componente compo = (Componente)session.get(Componente.class, id);
            compo.setEstado(!compo.isEstado());
            tx = session.beginTransaction();
            session.update(compo);
            tx.commit();   
            response = "El estado de "+compo.getNombre()+" fue actualizado exitosamente";
        }
        catch(Exception ex){
            tx.rollback();
            response = "No se pudo actualizar el componente";
        }
        return response;
    }
}
