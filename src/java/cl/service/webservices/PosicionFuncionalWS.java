/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.service.webservices;

import cl.model.dao.PosicionFuncionalDAO;
import cl.model.dto.PosicionFuncionalDTO;
import cl.model.pojos.Posicionfuncional;
import cl.model.pojos.Unidadorganizacional;
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
@WebService(serviceName = "PosicionFuncionalWS")
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
public class PosicionFuncionalWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "crearPosicionFuncional")
    public String crearPosicionFuncional(@WebParam(name = "nombre") String nombre, @WebParam(name = "creadoPor") String creadoPor, @WebParam(name = "estado") boolean estado, @WebParam(name = "idUnidadOrganizacional") int idUnidadOrganizacional) {
        
        Date date = new Date();
        Unidadorganizacional u = new Unidadorganizacional(idUnidadOrganizacional);
        Posicionfuncional p = new Posicionfuncional(u, nombre, date, creadoPor, estado);
        PosicionFuncionalDAO pfDAO = new PosicionFuncionalDAO();
        return pfDAO.crearPosicion(p);
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "buscarPosicionFuncional")
    public PosicionFuncionalDTO buscarPosicionFuncional(@WebParam(name = "id") int id) {
        
        PosicionFuncionalDAO pfDAO = new PosicionFuncionalDAO();
        Posicionfuncional p = pfDAO.leerPosicionFuncional(id);
        PosicionFuncionalDTO pfDTO = new PosicionFuncionalDTO(p);
        return pfDTO;
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "listarPosicionesFuncionales")
    public String listarPosicionesFuncionales() {
        
        PosicionFuncionalDAO pfDAO = new PosicionFuncionalDAO();
        return pfDAO.listarPosiciones().toString();
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "actualizarPosicionFuncional")
    public String actualizarPosicionFuncional(@WebParam(name = "id") Integer id, @WebParam(name = "nombre") String nombre, @WebParam(name = "estado") boolean estado) {
        
        PosicionFuncionalDAO pfDAO = new PosicionFuncionalDAO();
        Posicionfuncional p = pfDAO.leerPosicionFuncional(id);
        p.setNombre(nombre);
        p.setEstado(estado);
        return pfDAO.actualizarPosicion(p);
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "cambiarEstadoPosicionFuncional")
    public String cambiarEstadoPosicionFuncional(@WebParam(name = "id") int id) {
        PosicionFuncionalDAO pfDAO = new PosicionFuncionalDAO();
        return pfDAO.cambiarStatusPosicion(id);
    }
}
