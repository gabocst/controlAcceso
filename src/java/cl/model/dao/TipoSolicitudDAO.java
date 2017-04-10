/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model.dao;

import cl.model.pojos.Tiposolicitud;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Twins
 */
public class TipoSolicitudDAO {
    
    public List<Tiposolicitud> listaTipoSolicitud(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query q = session.createQuery("from Tiposolicitud");
        List<Tiposolicitud> lista = q.list();
        session.close();
        ResponseClass resp = new ResponseClass(); 
        resp.setCodigo(200);
        
        
        //resp.setData((Json) data);
        return lista;
    }
    
}
