/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model.dao;

import cl.model.pojos.Matrizcontrolacceso;
import cl.model.pojos.Perfil;
import cl.model.pojos.Posicionfuncional;
import cl.model.pojos.Posicionfuncionalperfil;
import cl.model.pojos.Solicitud;
import java.util.Date;
import java.util.Iterator;
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
            Query aa = session.createQuery("from Matrizcontrolacceso m\n" +
                                                "WHERE m.estadoSolicitud = 'Activo'\n" +
                                                "AND m.idUsuario = " + s.getIdSolicitante());
            List<Matrizcontrolacceso> accesos_activos = aa.list();
            List<Matrizcontrolacceso> accesos_activosClone = aa.list();
            int mcaLen = accesos_activos.size();  
            tx = session.beginTransaction();
            session.save(s);
            if(s.getTiposolicitud().getId() == 1){
                if(mcaLen > 0){
                    
                    response = "No tiene accesos para remover";
                    
                    Posicionfuncional p = (Posicionfuncional)session.get(Posicionfuncional.class, s.getPosicionfuncional().getId());
                    if(p != null){
                        Iterator<Posicionfuncionalperfil> iterPeticion = p.getPosicionfuncionalperfils().iterator();
                        while (iterPeticion.hasNext()) {
                            Posicionfuncionalperfil pfp = new Posicionfuncionalperfil();
                            pfp = iterPeticion.next();
                            Perfil perfilPF = new Perfil();
                            perfilPF = pfp.getPerfil();
                            Matrizcontrolacceso mca = new Matrizcontrolacceso(); 
                            Iterator<Matrizcontrolacceso> iterActivos = accesos_activos.iterator();
                            boolean match = false;
                            while(iterActivos.hasNext() && !match){
                                Matrizcontrolacceso mapf = new Matrizcontrolacceso();
                                mapf = iterActivos.next();
                                Perfil perfilMCA = new Perfil();
                                perfilMCA = mapf.getPerfil();
                                if(perfilPF.getComponente().equals(perfilMCA.getComponente())){
                                    if(perfilPF.getId() == perfilMCA.getId())
                                    {
                                        iterActivos.remove();
                                        mca.setAccion("Mantener");
                                    }
                                    else
                                    {
                                        iterActivos.remove();
                                        mca.setAccion("Modificar");
                                    }
                                    match = true;
                                }
                            }
                            if(!match){
                                mca.setAccion("Agregar");
                            }
                            mca.setEstadoSolicitud("Pendiente");
                            mca.setIdUsuario(s.getIdSolicitante());
                            mca.setPerfil(pfp.getPerfil());
                            mca.setSolicitud(s);
                            session.save(mca);
                        }
                        Iterator<Matrizcontrolacceso> iterActivos = accesos_activos.iterator();
                        while(iterActivos.hasNext()){
                            Matrizcontrolacceso mapf = new Matrizcontrolacceso();
                            mapf = iterActivos.next();
                            Matrizcontrolacceso mca = new Matrizcontrolacceso(); 
                            mca.setAccion("Eliminar");
                            mca.setEstadoSolicitud("Pendiente");
                            mca.setIdUsuario(s.getIdSolicitante());
                            mca.setPerfil(mapf.getPerfil());
                            mca.setSolicitud(s);
                            session.save(mca);
                        }
                    }
                }
                else{
                    //No tiene permisos activos, asi que se le agregan todos los que solicito
                    Posicionfuncional p = (Posicionfuncional)session.get(Posicionfuncional.class, s.getPosicionfuncional().getId());
                    if(p != null){

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
    
    public String AprobarSolicitud(int idSolicitud, int idUsuario, String observacion){
        SessionFactory sf;
        Session session;
        Transaction tx = null;
        String response;
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            
            Date date = new Date();
            
            Solicitud solicitud = (Solicitud)session.get(Solicitud.class, idSolicitud);
            if(solicitud != null)
            {
                if(solicitud.getEstadoSolicitud().equals("Pendiente"))
                {
                    solicitud.setEstadoSolicitud("Aprobado");
                    solicitud.setObservacionAprobador(observacion);
                    tx = session.beginTransaction();
                    Iterator<Matrizcontrolacceso> iterMCA = solicitud.getMatrizcontrolaccesos().iterator();
                    while(iterMCA.hasNext()){
                        Matrizcontrolacceso mca = iterMCA.next();
                        mca.setEstadoSolicitud("Aprobado");
                        mca.setIdAprobador(idUsuario);
                        mca.setFechaAprobacion(date);
                        session.update(mca);
                    }
                    session.update(solicitud);
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
    
    public String gestionarSolicitud(int idSolicitud, int idUsuario, String observacion){
        SessionFactory sf;
        Session session;
        Transaction tx = null;
        String response;
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            
            Date date = new Date();
            
            Solicitud solicitud = (Solicitud)session.get(Solicitud.class, idSolicitud);
            if(solicitud != null)
            {
               if(solicitud.getEstadoSolicitud().equals("En Gestion") || solicitud.getEstadoSolicitud().equals("Devuelto"))
                {
                    solicitud.setEstadoSolicitud("Gestionado");
                    solicitud.setObservacionAdministrador(observacion);
                    tx = session.beginTransaction();
                    session.update(solicitud);
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

    public String rechazarSolicitud(int idSolicitud, int idUsuario, String observacion) {
        SessionFactory sf;
        Session session;
        Transaction tx = null;
        String response;
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            
            Date date = new Date();
            int opcion = 0;
            
            Solicitud solicitud = (Solicitud)session.get(Solicitud.class, idSolicitud);
            if(solicitud != null)
            {
                solicitud.setEstadoSolicitud("Rechazado");
                tx = session.beginTransaction();
                Iterator<Matrizcontrolacceso> iterMCA = solicitud.getMatrizcontrolaccesos().iterator();
                while(iterMCA.hasNext()){
                    Matrizcontrolacceso mca = iterMCA.next();
                    mca.setEstadoSolicitud("Rechazado");
                    if(mca.getIdAprobador() == null){
                        opcion = 1;
                        mca.setIdAprobador(idUsuario);
                        mca.setFechaAprobacion(date);
                        
                    }
                    else if(mca.getIdGestor() == null){
                        opcion = 2;
                        mca.setIdGestor(idUsuario);
                        mca.setFechaGestion(date);
                       
                    }
                    else{
                        opcion = 3;
                        mca.setIdVerificador(idUsuario);
                        mca.setFechaVerificacion(date);
                    }
                    session.update(mca);
                }
                if(opcion==1) solicitud.setObservacionAprobador(observacion);
                else if(opcion==2) solicitud.setObservacionAdministrador(observacion);
                else solicitud.setObservacionVerificador(observacion);
                
                session.update(solicitud);
                tx.commit();   
                response = "Solicitud actualizada exitosamente";
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

    public String devolverSolicitud(int idSolicitud, int idUsuario, String observacion) {
        SessionFactory sf;
        Session session;
        Transaction tx = null;
        String response;
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            
            Date date = new Date();
            
            Solicitud solicitud = (Solicitud)session.get(Solicitud.class, idSolicitud);
            if(solicitud != null)
            {
               if(solicitud.getEstadoSolicitud().equals("Gestionado") || solicitud.getEstadoSolicitud().equals("En Verificacion"))
                {
                    solicitud.setEstadoSolicitud("Devuelto");
                    solicitud.setObservacionVerificador(observacion);
                    tx = session.beginTransaction();
                    Iterator<Matrizcontrolacceso> iterMCA = solicitud.getMatrizcontrolaccesos().iterator();
                    while(iterMCA.hasNext()){
                        Matrizcontrolacceso mca = iterMCA.next();
                        if(!mca.getEstadoSolicitud().equals("Verificado"))
                        {
                            mca.setEstadoSolicitud("Devuelto");
                            mca.setIdAprobador(idUsuario);
                            mca.setFechaAprobacion(date);
                            session.update(mca);
                        }
                    }
                    session.update(solicitud);
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
