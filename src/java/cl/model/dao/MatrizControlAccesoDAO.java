/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model.dao;

import cl.model.pojos.Matrizcontrolacceso;
import cl.model.pojos.Solicitud;
import java.util.Date;
import java.util.Iterator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author JOAQUIN
 */
public class MatrizControlAccesoDAO {
    public String matrizGestionada(int idMatriz, int idUsuario){
        SessionFactory sf;
        Session session;
        Transaction tx = null;
        String response;
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            
            Date date = new Date();
            
            Matrizcontrolacceso matriz = (Matrizcontrolacceso)session.get(Matrizcontrolacceso.class, idMatriz);
            if(matriz != null)
            {
                if(matriz.getEstadoSolicitud().equals("Aprobado") || matriz.getEstadoSolicitud().equals("Devuelto"))
                {
                    tx = session.beginTransaction();
                    matriz.setEstadoSolicitud("Gestionado");
                    matriz.setIdGestor(idUsuario);
                    matriz.setFechaGestion(date);
                    session.update(matriz);
                    Solicitud solicitud = matriz.getSolicitud();
                    if(!solicitud.getEstadoSolicitud().equals("En Gestion")){
                        solicitud.setEstadoSolicitud("En Gestion");
                        session.update(solicitud);
                    }
                    tx.commit();   
                    response = "Solicitud actualizada exitosamente";
                }
                else{
                    response = "Solicitud inválida";
                }
                
            }
            else{
                response = "Solicitud inválida";
            }
            
        }
        catch(Exception ex){
            tx.rollback();
            response = "No se pudo actualizar la solicitud";
        }
        return response;
    }

    public String matrizVerificada(int idMatriz, int idUsuario) {
        SessionFactory sf;
        Session session;
        Transaction tx = null;
        String response;
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            
            Date date = new Date();
            
            Matrizcontrolacceso matriz = (Matrizcontrolacceso)session.get(Matrizcontrolacceso.class, idMatriz);
            if(matriz != null)
            {
                if(matriz.getEstadoSolicitud().equals("Gestionado"))
                {
                    tx = session.beginTransaction();
                    matriz.setEstadoSolicitud("Verificado");
                    matriz.setIdVerificador(idUsuario);
                    matriz.setFechaVerificacion(date);
                    session.update(matriz);
                    Solicitud solicitud = matriz.getSolicitud();
                    if(!solicitud.getEstadoSolicitud().equals("En Verificacion")){
                        solicitud.setEstadoSolicitud("En Verificacion");
                        session.update(solicitud);
                    }
                    tx.commit();   
                    response = "Solicitud actualizada exitosamente";
                }
                else{
                    response = "Solicitud inválida";
                }
                
            }
            else{
                response = "Solicitud inválida";
            }
            
        }
        catch(Exception ex){
            tx.rollback();
            response = "No se pudo actualizar la solicitud";
        }
        return response;
    }
}
