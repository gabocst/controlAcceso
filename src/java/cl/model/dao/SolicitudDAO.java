/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model.dao;

import cl.model.pojos.Matrizcontrolacceso;
import cl.model.pojos.Posicionfuncional;
import cl.model.pojos.Posicionfuncionalperfil;
import cl.model.pojos.Solicitud;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
            Query aa = session.createQuery("from Matrizcontrolacceso m\n" +
                                                "WHERE m.estadoSolicitud = 'Activo'\n" +
                                                "AND m.idUsuario = " + s.getIdSolicitante());
            List<Matrizcontrolacceso> accesos_activos = aa.list();
            int mcaLen = accesos_activos.size();  
            tx = session.beginTransaction();
            session.save(s);
            if(s.getTiposolicitud().getId() == 1){
                if(mcaLen > 0){
                    //Tiene permisos ya asignados, hay que evaluar cuales se mantienen, cuales se agregan 
                    // y cuales se eliminan
                }
                else{
                    //No tiene permisos activos, asi que se le agregan todos los que solicito
                    Posicionfuncional p = (Posicionfuncional)session.get(Posicionfuncional.class, s.getPosicionfuncional().getId());
                    if(p != null){
                        int cantidad_perfiles = p.getPosicionfuncionalperfils().size();

                        Iterator<Posicionfuncionalperfil> iter = p.getPosicionfuncionalperfils().iterator();
                        while (iter.hasNext()) {
                            Posicionfuncionalperfil pfp = new Posicionfuncionalperfil();
                            pfp = iter.next();
                            Matrizcontrolacceso mca = new Matrizcontrolacceso(); 
                            mca.setAccion("Agregar");
                            mca.setEstadoSolicitud("Pendiente");
                            mca.setIdUsuario(s.getIdSolicitante());
                            mca.setPerfil(pfp.getPerfil());
                            mca.setSolicitud(s);
                            session.save(mca);
                        }
                    }
                }
            }
            else{
                //Es una solicitud de remocion de accesos, hay que quitar todos los que tenga activo
                if(mcaLen > 0){
                    Iterator<Matrizcontrolacceso> iter = accesos_activos.iterator();
                        while (iter.hasNext()) {
                            Matrizcontrolacceso mca = new Matrizcontrolacceso(); 
                            mca.setAccion("Eliminar");
                            mca.setEstadoSolicitud("Pendiente");
                            mca.setIdUsuario(s.getIdSolicitante());
                            mca.setPerfil(iter.next().getPerfil());
                            session.save(mca);
                        }
                }
                else{
                    response = "No tiene accesos para remover";
                }
            }
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
