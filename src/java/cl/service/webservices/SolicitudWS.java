/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.service.webservices;

import cl.model.dao.SolicitudDAO;
import cl.model.pojos.Posicionfuncional;
import cl.model.pojos.Solicitud;
import cl.model.pojos.Tiposolicitud;
import java.util.Date;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Twins
 */
@WebService(serviceName = "SolicitudWS")
public class SolicitudWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "generarSolicitud")
    public String generarSolicitud(@WebParam(name = "idSolicitante") int idSolicitante, @WebParam(name = "razon") String razon, @WebParam(name = "idTipoSolicitud") int idTipoSolicitud, @WebParam(name = "idPosicionFuncional") int idPosicionFuncional, @WebParam(name = "idIntermediario") int idIntermediario) {
        
        Date date = new Date();
        //int idSolicitante, Date fechaCreacion, String razon, String estadoSolicitud, Integer idIntermediario
        Posicionfuncional pf = new Posicionfuncional(idPosicionFuncional);
        Tiposolicitud ts = new Tiposolicitud(idTipoSolicitud);
        Solicitud s = new Solicitud(pf, ts, idSolicitante, date, razon, "Pendiente", idIntermediario);
        SolicitudDAO solDAO = new SolicitudDAO();
        return solDAO.generarSolicitud(s);
        
    }
}
