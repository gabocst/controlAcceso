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
import cl.model.pojos.Tiposolicitud;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Twins
 */
public class SolicitudDAO {
    
    public ResponseClass generarSolicitud(Solicitud s){
        SessionFactory sf;
        Session session = null;
        Transaction tx = null;
        ResponseClass response = new ResponseClass();
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            Query aa = session.createQuery("from Matrizcontrolacceso m\n" +
                                                "WHERE m.estadoSolicitud = 'Activo'\n" +
                                                "AND m.idUsuario = " + s.getIdSolicitante());
            List<Matrizcontrolacceso> accesos_activos = aa.list();
            int mcaLen = accesos_activos.size();  
            tx = session.beginTransaction();
            Posicionfuncional p = new Posicionfuncional();
            if(s.getTiposolicitud().getId() == 1){
                session.save(s);
                if(mcaLen > 0){
                    p = (Posicionfuncional)session.get(Posicionfuncional.class, s.getPosicionfuncional().getId());
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
                    p = (Posicionfuncional)session.get(Posicionfuncional.class, s.getPosicionfuncional().getId());
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
                    session.save(s);
                    Iterator<Matrizcontrolacceso> iter = accesos_activos.iterator();
                        while (iter.hasNext()) {
                            Matrizcontrolacceso mca = new Matrizcontrolacceso(); 
                            mca.setAccion("Eliminar");
                            mca.setEstadoSolicitud("Pendiente");
                            mca.setIdUsuario(s.getIdSolicitante());
                            mca.setPerfil(iter.next().getPerfil());
                            mca.setSolicitud(s);
                            session.save(mca);
                        }
                }
                else{
                    session.close();
                    response.setCodigo(400);
                    response.setMensaje("No tiene accesos para remover");
                    return response;
                }
            }
            tx.commit();   
            response.setCodigo(200);
            response.setMensaje("Solicitud creada exitosamente");
            response.setData(s.getId().toString());
            
            Calendar c = new GregorianCalendar(); 

            String dia, mes, annio;
	   
            dia = Integer.toString(c.get(Calendar.DATE));
            mes = Integer.toString(c.get(Calendar.MONTH) + 1);
            annio = Integer.toString(c.get(Calendar.YEAR));
            
            response.setFechaCreacion(dia + "/" + mes +"/" + annio);
            if(s.getTiposolicitud().getId() == 1)
            {
                response.setPosicionFuncional(p.getNombre());
            }
            else
            {
                response.setPosicionFuncional("N/A");
            }    
            
            Tiposolicitud ts = (Tiposolicitud)session.get(Tiposolicitud.class, s.getTiposolicitud().getId());
            response.setTipoSolicitud(ts.getNombre());
        }
        catch(Exception ex){
            tx.rollback();
            response.setCodigo(500);
            response.setMensaje("No se pudo crear la solicitud");
            response.setExcepcion(ex.getMessage());
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
                    boolean gestionadosTodos = true;
                    Iterator<Matrizcontrolacceso> iterMCA = solicitud.getMatrizcontrolaccesos().iterator();
                    while(iterMCA.hasNext() && gestionadosTodos){
                        Matrizcontrolacceso mca = iterMCA.next();
                        gestionadosTodos = mca.getEstadoSolicitud().equals("Gestionado") || mca.getEstadoSolicitud().equals("Verificado");
                    }
                    if(gestionadosTodos){
                        tx = session.beginTransaction();
                        solicitud.setEstadoSolicitud("Gestionado");
                        solicitud.setObservacionAdministrador(observacion);
                        session.update(solicitud);
                        tx.commit();   
                        response = "Solicitud actualizada exitosamente";
                    }
                    else{
                        response = "Faltan perfiles por gestionar";
                    }
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

    public String cancelarSolicitud(int idSolicitud) {
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
                    solicitud.setEstadoSolicitud("Cancelado");
                    tx = session.beginTransaction();
                    Iterator<Matrizcontrolacceso> iterMCA = solicitud.getMatrizcontrolaccesos().iterator();
                    while(iterMCA.hasNext()){
                        Matrizcontrolacceso mca = iterMCA.next();
                        mca.setEstadoSolicitud("Cancelado");
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

    public String finalizarSolicitud(int idSolicitud, int idUsuario, String observacion) {
       SessionFactory sf;
        Session session = null;
        Transaction tx = null;
        String response = "";
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            Date date = new Date();
            
            Solicitud solicitud = (Solicitud)session.get(Solicitud.class, idSolicitud);
            Query aa = session.createQuery("from Matrizcontrolacceso m\n" +
                                                "WHERE m.estadoSolicitud = 'Activo'\n" +
                                                "AND m.idUsuario = " + solicitud.getIdSolicitante());
            List<Matrizcontrolacceso> accesos_activos = aa.list();
            int mcaLen = accesos_activos.size();  
            tx = session.beginTransaction();
            if(solicitud.getTiposolicitud().getId() == 1){
                if(mcaLen > 0){
                    Iterator<Matrizcontrolacceso> iterMCA = solicitud.getMatrizcontrolaccesos().iterator();
                    while (iterMCA.hasNext()) {
                        Matrizcontrolacceso pfp = iterMCA.next();
                        if(pfp.getAccion().equals("Agregar")){
                            pfp.setEstadoSolicitud("Activo");
                            pfp.setInicio(date);
                            session.update(pfp);
                        }
                        else{
                            Perfil perfilPF = pfp.getPerfil();
                            Iterator<Matrizcontrolacceso> iterActivos = accesos_activos.iterator();
                            boolean match = false;
                            while(iterActivos.hasNext() && !match){
                                Matrizcontrolacceso mapf = iterActivos.next();
                                Perfil perfilMCA = mapf.getPerfil();
                                if(perfilPF.getComponente().equals(perfilMCA.getComponente())){
                                    if(pfp.getAccion().equals("Eliminar"))
                                    {
                                        pfp.setEstadoSolicitud("Eliminado");
                                        mapf.setEstadoSolicitud("Finalizado");
                                    }
                                    else if(pfp.getAccion().equals("Mantener"))
                                    {
                                        pfp.setEstadoSolicitud("Activo");
                                        mapf.setEstadoSolicitud("Finalizado");
                                    }
                                    else if(pfp.getAccion().equals("Modificar")){
                                        pfp.setEstadoSolicitud("Activo");
                                        mapf.setEstadoSolicitud("Eliminado");
                                    }
                                    pfp.setInicio(date);
                                    mapf.setFin(date);
                                    session.update(pfp);
                                    session.update(mapf);
                                    iterActivos.remove();
                                    match = true;
                                }
                            }
                        }
                    }
                }
                else{
                    Iterator<Matrizcontrolacceso> iterMCA = solicitud.getMatrizcontrolaccesos().iterator();
                    while(iterMCA.hasNext()){
                        Matrizcontrolacceso mca = iterMCA.next();
                        mca.setEstadoSolicitud("Activo");
                        mca.setInicio(date);
                        session.update(mca);
                    }
                }
            }
            else{
                //Es una solicitud de remocion de accesos, hay que quitar todos los que tenga activo
                if(mcaLen > 0){
                    Iterator<Matrizcontrolacceso> iter = accesos_activos.iterator();
                        while (iter.hasNext()) {
                            Matrizcontrolacceso mca = iter.next();
                            mca.setEstadoSolicitud("Finalizado");
                            mca.setFin(date);
                            session.update(mca);
                        }
                }
                Iterator<Matrizcontrolacceso> iterMCA = solicitud.getMatrizcontrolaccesos().iterator();
                while(iterMCA.hasNext()){
                    Matrizcontrolacceso mca = iterMCA.next();
                    mca.setEstadoSolicitud("Eliminado");
                    mca.setInicio(date);
                    session.update(mca);
                }
            }
            solicitud.setEstadoSolicitud("Finalizado");
            solicitud.setObservacionVerificador(observacion);
            session.update(solicitud);
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

    public Solicitud getSolicitud(int id) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Solicitud p = (Solicitud)session.get(Solicitud.class, id);
        if(p != null){
            return p;
        }
        return null;
    }
    
}
