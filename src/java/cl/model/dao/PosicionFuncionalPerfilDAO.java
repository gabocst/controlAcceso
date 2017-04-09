/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model.dao;

import cl.model.dto.PosicionFuncionalPerfilDTO;
import cl.model.pojos.Estado;
import cl.model.pojos.Posicionfuncionalperfil;
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
public class PosicionFuncionalPerfilDAO {
    
    public String crearPosicionPerfil(Posicionfuncionalperfil p){
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
            response = "Perfil asociado exitosamente";
        }
        catch(Exception ex){
            tx.rollback();
            throw new RuntimeException("No se pudo asociar el perfil a la Posición Funcional");
        }
        session.close();
        return response;
    }
    
    
    public List<PosicionFuncionalPerfilDTO> listarPerfilesDePosicion(int idPosicion){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query q = session.createQuery("from Posicionfuncionalperfil where idPosicionFuncional = " + idPosicion);
        List<Posicionfuncionalperfil> lista = q.list();
        List<PosicionFuncionalPerfilDTO> pfpDTO = new ArrayList<>();
        int len = lista.size();  
        for (int i = 0; i < len; i++) {
            PosicionFuncionalPerfilDTO pDTO = new PosicionFuncionalPerfilDTO(lista.get(i));
            pfpDTO.add(pDTO);
        }
        session.close();
        return pfpDTO;
    }
    
    /*
    public String cambiarEstadoPosicionPerfil(int id){
        SessionFactory sf;
        Session session;
        Transaction tx = null;
        String response;
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            Posicionfuncionalperfil pfp = (Posicionfuncionalperfil)session.get(Posicionfuncionalperfil.class, id);
            Estado e = new Estado();
            if(pfp.getEstado().getNombre().equals("Activo")){
                e.setId(0);
            }
            else{
                e.setId(1);
            }
            pfp.setEstado(e);
            tx = session.beginTransaction();
            session.update(pfp);
            tx.commit();   
            response = "El estado de la asociación fue actualizado exitosamente";
        }
        catch(Exception ex){
            tx.rollback();
            response = "No se pudo cambiar el estado de la asociación";
        }
        return response;
    }
    */
    
    
}
