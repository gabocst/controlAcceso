/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.service.webservices;

import cl.model.dao.MatrizControlAccesoDAO;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author JOAQUIN
 */
@WebService(serviceName = "MatrizControlAcceso")
public class MatrizControlAccesoWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "matrizGestionada")
    public String matrizGestionada(@WebParam(name = "idMatriz") int idMatriz, @WebParam(name = "idUsuario") int idUsuario) {
        
        MatrizControlAccesoDAO macDAO = new MatrizControlAccesoDAO();
        return macDAO.matrizGestionada(idMatriz, idUsuario);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "matrizVerificada")
    public String matrizVerificada(@WebParam(name = "idMatriz") int idMatriz, @WebParam(name = "idUsuario") int idUsuario) {
        
        MatrizControlAccesoDAO macDAO = new MatrizControlAccesoDAO();
        return macDAO.matrizVerificada(idMatriz, idUsuario);
    }
}
