/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.service.webservices;

import cl.model.dao.PosicionFuncionalPerfilDAO;
import cl.model.dto.PosicionFuncionalPerfilDTO;
import cl.model.pojos.Perfil;
import cl.model.pojos.Posicionfuncional;
import cl.model.pojos.Posicionfuncionalperfil;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Twins
 */
@WebService(serviceName = "PosicicionFuncionalPerfilWS")
public class PosicicionFuncionalPerfilWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "crearPosicionFuncionalPerfil")
    public String crearPosicionFuncionalPerfil(@WebParam(name = "creadoPor") String creadoPor, @WebParam(name = "idPosicionFuncional") int idPosicionFuncional, @WebParam(name = "idPerfil") int idPerfil, @WebParam(name = "estado") boolean estado) {
        
        Date date = new Date();
        Perfil p = new Perfil(idPerfil);
        Posicionfuncional pf = new Posicionfuncional(idPosicionFuncional);
        Posicionfuncionalperfil pfp = new Posicionfuncionalperfil(p, pf, date, creadoPor, estado);
        PosicionFuncionalPerfilDAO pfpDAO = new PosicionFuncionalPerfilDAO();
        return pfpDAO.crearPosicionPerfil(pfp);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "listarPerfilesDePosicion")
    public List<PosicionFuncionalPerfilDTO> listarPerfilesDePosicion(@WebParam(name = "idPosicionFuncional") int idPosicionFuncional) {
        PosicionFuncionalPerfilDAO pfpDAO = new PosicionFuncionalPerfilDAO();
        List<PosicionFuncionalPerfilDTO> pfp = pfpDAO.listarPerfilesDePosicion(idPosicionFuncional);
        return pfp;
    }

    /**
     * Web service operation
     */
    /*
    @WebMethod(operationName = "cambiarEstadoPosicionFuncionalPerfil")
    public String cambiarEstadoPosicionFuncionalPerfil(@WebParam(name = "idPosicionFuncional") int idPosicionFuncional, @WebParam(name = "idPerfil") int idPerfil) {
        PosicionFuncionalPerfilDAO pfpDAO = new PosicionFuncionalPerfilDAO();
        return pfpDAO.cambiarEstadoPosicionPerfil(idPosicionFuncional);
    }
    */
}
