/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.service.webservices;

import cl.model.dao.UnidadOrganizacionalDAO;
import cl.model.dto.UnidadOrganizacionalDTO;
import cl.model.pojos.Estado;
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
@WebService(serviceName = "UnidadOrganizacionalWS")
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
public class UnidadOrganizacionalWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "crearUnidadOrganizacional")
    public String crearUnidadOrganizacional(@WebParam(name = "nombre") String nombre, @WebParam(name = "creadoPor") String creadoPor, @WebParam(name = "idEstado") Integer idEstado) {
        Date date = new Date();

        Estado e = new Estado(idEstado);
        Unidadorganizacional u = new Unidadorganizacional(e, nombre, date, creadoPor);
        UnidadOrganizacionalDAO unidadDAO = new UnidadOrganizacionalDAO();
        return unidadDAO.crearUnidad(u);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "buscarUnidadOrganizacional")
    public UnidadOrganizacionalDTO buscarUnidadOrganizacional(@WebParam(name = "id") int id) {
        UnidadOrganizacionalDAO unidadDAO = new UnidadOrganizacionalDAO();
        Unidadorganizacional u = unidadDAO.leerUnidad(id);
        UnidadOrganizacionalDTO uDTO = new UnidadOrganizacionalDTO(u);
        return uDTO;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "listarUnidad")
    public List<UnidadOrganizacionalDTO> listarUnidades() {
        UnidadOrganizacionalDAO unidadDAO = new UnidadOrganizacionalDAO();
        List<UnidadOrganizacionalDTO> unidad = unidadDAO.listaUnidad();
        return unidad;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "actualizarUnidadOrganizacional")
    public String actualizarUnidadOrganizacional(@WebParam(name = "id") int id, @WebParam(name = "nombre") String nombre, @WebParam(name = "idEstado") int idEstado) {
        Estado e = new Estado(idEstado);
        UnidadOrganizacionalDAO unidadDAO = new UnidadOrganizacionalDAO();
        Unidadorganizacional u = unidadDAO.leerUnidad(id);
        u.setNombre(nombre);
        u.setEstado(e);
        return unidadDAO.actualizarUnidad(u);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "cambiarEstadoUnidadOrganizacional")
    public String cambiarEstadoUnidadOrganizacional(@WebParam(name = "id") int id) {
        UnidadOrganizacionalDAO unidadDAO = new UnidadOrganizacionalDAO();
        return unidadDAO.cambiarStatusUnidad(id);
    }
}
