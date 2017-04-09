/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model.dao;

import cl.model.pojos.Matrizcontrolacceso;
import cl.model.pojos.Posicionfuncional;
import cl.model.pojos.Solicitud;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Twins
 */
public class SolicitudDAO {
    
    public String generarSolicitud(Solicitud s){
        SessionFactory sf;
        Session session = null;
        Transaction tx = null;
        String response = "";
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            if(s.getTiposolicitud().getId() == 1){
                Query q = session.createQuery("from Posicionfuncional");
                List<Posicionfuncional> lista = q.list();
                int len = lista.size();  
                for (int i = 0; i < len; i++) {
                    Matrizcontrolacceso mca = new Matrizcontrolacceso();
                    
                }
                
            }
            tx = session.beginTransaction();
            session.save(s);
            tx.commit();   
            response = "Solicitud creada exitosamente";
        }
        catch(Exception ex){
            tx.rollback();
            throw new RuntimeException("No se pudo crear la solicitud");
        }
        session.close();
        return response;
    }
    
}
