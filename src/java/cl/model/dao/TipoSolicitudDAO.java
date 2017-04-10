/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model.dao;

import cl.model.pojos.Tiposolicitud;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Twins
 */
public class TipoSolicitudDAO {
    
    public JSONObject listaTipoSolicitud(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query q = session.createQuery("from Tiposolicitud");
        List<Tiposolicitud> lista = q.list();
        session.close();
        JSONObject response = new JSONObject();
        
        response.put("codigo", 200);
        response.put("mensaje", "OK");
        response.put("excepcion", "");
        JSONArray array = new JSONArray();
        for (Tiposolicitud ts : lista)
        {
            JSONObject tipo = new JSONObject();
            tipo.put("id", ts.getId());
            tipo.put("nombre", ts.getNombre());
            array.put(tipo);
        }
        response.put("listaTipoSolicitud", array);
        return response;
    }
    
}
