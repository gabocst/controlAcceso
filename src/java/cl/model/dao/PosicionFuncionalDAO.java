/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model.dao;

import cl.model.dto.PosicionFuncionalDTO;
import cl.model.pojos.Estado;
import cl.model.pojos.Posicionfuncional;
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
public class PosicionFuncionalDAO {
    
    public String crearPosicion(Posicionfuncional p){
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
            response = "Posición Funcional creada exitosamente";
        }
        catch(Exception ex){
            tx.rollback();
            throw new RuntimeException("No se pudo crear la Posición Funcional");
        }
        session.close();
        return response;
    }
    
    public Posicionfuncional leerPosicionFuncional(int id){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Posicionfuncional p = (Posicionfuncional)session.get(Posicionfuncional.class, id);
        if(p != null){
            return p;
        }
        return null;
    }
    
    public List<PosicionFuncionalDTO> listarPosiciones(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query q = session.createQuery("from Posicionfuncional");
        List<Posicionfuncional> lista = q.list();
        List<PosicionFuncionalDTO> posicionesDTO = new ArrayList<>();
        int len = lista.size();  
        for (int i = 0; i < len; i++) {
            PosicionFuncionalDTO pDTO = new PosicionFuncionalDTO(lista.get(i));
            posicionesDTO.add(pDTO);
        }
        session.close();
        return posicionesDTO;
    }
    
    public String actualizarPosicion(Posicionfuncional p){
        SessionFactory sf;
        Session session;
        Transaction tx = null;
        String response;
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            Posicionfuncional pf = (Posicionfuncional)session.get(Posicionfuncional.class, p.getId());
            pf.setNombre(p.getNombre());
            pf.setEstado(p.getEstado());
            tx = session.beginTransaction();
            session.update(pf);
            tx.commit();   
            response = "Posición funcional actualizada exitosamente";
        }
        catch(Exception ex){
            tx.rollback();
            response = "No se pudo actualizar la posición funcional";
        }
        return response;
    }
    
    public String cambiarStatusPosicion(int id){
        SessionFactory sf;
        Session session;
        Transaction tx = null;
        String response;
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            Posicionfuncional pos = (Posicionfuncional)session.get(Posicionfuncional.class, id);
            Estado e = new Estado();
            if(pos.getEstado().getNombre().equals("Activo")){
                e.setId(0);
            }
            else{
                e.setId(1);
            }
            pos.setEstado(e);
            tx = session.beginTransaction();
            session.update(pos);
            tx.commit();   
            response = "El estado de "+pos.getNombre()+" - "+pos.getUnidadorganizacional().getNombre() +" fue actualizado exitosamente";
        }
        catch(Exception ex){
            tx.rollback();
            response = "No se pudo actualizar la posición";
        }
        return response;
    }
    
}
