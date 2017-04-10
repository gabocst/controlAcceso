/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.service.webservices;

import cl.model.dao.ResponseClass;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Twins
 */
@WebService(serviceName = "TipoSolicitudWS")
public class TipoSolicitudWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "listarTiposSolicitud")
    public ResponseClass listarTiposSolicitud() {
        //TODO write your implementation code here:
        return null;
    }
}
