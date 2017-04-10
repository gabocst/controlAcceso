/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.service.webservices;

import cl.model.dao.TipoSolicitudDAO;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.xml.ws.BindingType;
import org.json.JSONObject;

/**
 *
 * @author Twins
 */
@WebService(serviceName = "TipoSolicitudWS")
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
public class TipoSolicitudWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "listarTiposSolicitud")
    public String listarTiposSolicitud() {
        TipoSolicitudDAO tipoDAO = new TipoSolicitudDAO();
        return tipoDAO.listaTipoSolicitud().toString();
    }
}
