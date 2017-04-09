/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.service.webservices;

import cl.model.dao.PerfilDAO;
import cl.model.dto.PerfilDTO;
import cl.model.pojos.Componente;
import cl.model.pojos.Perfil;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.BindingType;

/**
 *
 * @author Twins
 */
@WebService(serviceName = "PerfilWS")
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
public class PerfilWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "crearPerfil")
    public String crearPerfil(@WebParam(name = "nombre") String nombre, @WebParam(name = "creadoPor") String creadoPor, @WebParam(name = "idComponente") int idComponente, @WebParam(name = "estado") boolean estado) {
        
        Date date = new Date();
        Componente c = new Componente(idComponente);
        Perfil p = new Perfil(c, nombre, date, creadoPor, estado);
        PerfilDAO perfilDAO = new PerfilDAO();
        return perfilDAO.crearPerfil(p);
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "buscarPerfil")
    public PerfilDTO buscarPerfil(@WebParam(name = "id") int id) {
       
        PerfilDAO perfilDAO = new PerfilDAO();
        Perfil p = perfilDAO.leerPerfil(id);
        PerfilDTO pDTO = new PerfilDTO(p);
        return pDTO;
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "listarPerfiles")
    public List<PerfilDTO> listarPerfiles() {
        
        PerfilDAO perfilDAO = new PerfilDAO();
        List<PerfilDTO> perfil = perfilDAO.listarPerfiles();
        return perfil;
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "actualizarPerfil")
    public String actualizarPerfil(@WebParam(name = "id") Integer id,@WebParam(name = "nombre") String nombre, @WebParam(name = "estado") boolean estado) {
        
        PerfilDAO perfilDAO = new PerfilDAO();
        Perfil p = perfilDAO.leerPerfil(id);
        p.setNombre(nombre);
        p.setEstado(estado);
        return perfilDAO.actualizarPerfil(p);
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "cambiarEstadoPerfil")
    public String cambiarEstadoPerfil(@WebParam(name = "id") int id) {

        PerfilDAO perfilDAO = new PerfilDAO();
        return perfilDAO.cambiarStatusPerfil(id);
        
    }
}
