/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.service.webservices;

import cl.model.dao.ResponseClass;
import cl.model.dao.SolicitudDAO;
import cl.model.dto.SolicitudDTO;
import cl.model.pojos.Posicionfuncional;
import cl.model.pojos.Solicitud;
import cl.model.pojos.Tiposolicitud;
import java.util.Date;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.BindingType;

/**
 *
 * @author Twins
 */
@WebService(serviceName = "SolicitudWS")
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
public class SolicitudWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "generarSolicitud")
    public ResponseClass generarSolicitud(@WebParam(name = "idSolicitante") int idSolicitante, @WebParam(name = "razon") String razon, @WebParam(name = "idTipoSolicitud") int idTipoSolicitud, @WebParam(name = "idPosicionFuncional") int idPosicionFuncional, @WebParam(name = "idIntermediario") int idIntermediario) {
        
        Date date = new Date();
        Posicionfuncional pf = (idPosicionFuncional == 0) ? null : new Posicionfuncional(idPosicionFuncional);
        Tiposolicitud ts = new Tiposolicitud(idTipoSolicitud);
        if(idIntermediario != 0)
        {
            int aux = idIntermediario;
            idIntermediario = idSolicitante;
            idSolicitante = aux;
        }
        Solicitud s = new Solicitud(pf, ts, idSolicitante, date, razon, "Pendiente", idIntermediario);
        SolicitudDAO solDAO = new SolicitudDAO();
        return solDAO.generarSolicitud(s);
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "aprobarSolicitud")
    public String AprobarSolicitud(@WebParam(name = "idSolicitud") int idSolicitud, @WebParam(name = "idUsuario") int idUsuario, @WebParam(name = "obervacion") String observacion) {
        
        SolicitudDAO solDAO = new SolicitudDAO();
        return solDAO.AprobarSolicitud(idSolicitud, idUsuario, observacion);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "gestionarSolicitud")
    public String gestionarSolicitud(@WebParam(name = "idSolicitud") int idSolicitud, @WebParam(name = "idUsuario") int idUsuario, @WebParam(name = "observacion") String observacion) {
        
        SolicitudDAO solDAO = new SolicitudDAO();
        return solDAO.gestionarSolicitud(idSolicitud, idUsuario, observacion);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "rechazarSolicitud")
    public String rechazarSolicitud(@WebParam(name = "idSolicitud") int idSolicitud, @WebParam(name = "idUsuario") int idUsuario, @WebParam(name = "observacion") String observacion) {
        
        SolicitudDAO solDAO = new SolicitudDAO();
        return solDAO.rechazarSolicitud(idSolicitud, idUsuario, observacion);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "devolverSolicitud")
    public String devolverSolicitud(@WebParam(name = "idSolicitud") int idSolicitud, @WebParam(name = "idUsuario") int idUsuario, @WebParam(name = "observacion") String observacion) {
        
        SolicitudDAO solDAO = new SolicitudDAO();
        return solDAO.devolverSolicitud(idSolicitud, idUsuario, observacion);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "cancelarSolicitud")
    public String cancelarSolicitud(@WebParam(name = "idSolicitud") int idSolicitud) {
       
        SolicitudDAO solDAO = new SolicitudDAO();
        return solDAO.cancelarSolicitud(idSolicitud);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "finalizarSolicitud")
    public String finalizarSolicitud(@WebParam(name = "idSolicitud") int idSolicitud, @WebParam(name = "idUsuario") int idUsuario, @WebParam(name = "observacion") String observacion) {
        
        SolicitudDAO solDAO = new SolicitudDAO();
        return solDAO.finalizarSolicitud(idSolicitud, idUsuario, observacion);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getSolicitud")
    public SolicitudDTO getSolicitud(@WebParam(name = "id") int id) {
        SolicitudDAO solDAO = new SolicitudDAO();
        Solicitud solicitud = solDAO.getSolicitud(id);
        SolicitudDTO solicitudDTO = new SolicitudDTO(solicitud); 
        return solicitudDTO;
    }

}
