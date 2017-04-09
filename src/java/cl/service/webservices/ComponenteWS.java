/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.service.webservices;

import cl.model.pojos.Componente;
import cl.model.dao.ComponenteDAO;
import cl.model.dto.ComponenteDTO;
import cl.model.pojos.Estado;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.BindingType;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Twins
 */
@WebService(serviceName = "ComponenteWS")
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
public class ComponenteWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "crearComponente")
    public String crearComponente(@WebParam(name = "nombre") String nombre, @WebParam(name = "creadoPor") String creadoPor, @WebParam(name = "descripcion") String descripcion, @WebParam(name = "idEstado") boolean estado) {

        Date date = new Date();

        Componente c = new Componente(nombre, date, creadoPor, descripcion, estado, null);
        ComponenteDAO componenteDAO = new ComponenteDAO();
        return componenteDAO.crearComponete(c);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "buscarComponente")
    public ComponenteDTO buscarComponente(@WebParam(name = "id") int id) {
        ComponenteDAO componenteDAO = new ComponenteDAO();
        Componente c = componenteDAO.leerComponente(id);
        ComponenteDTO cDTO = new ComponenteDTO(c);
        return cDTO;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "listarComponentes")
    public List<ComponenteDTO> listarComponentes() {
        ComponenteDAO compoDAO = new ComponenteDAO();
        List<Componente> compo = compoDAO.listaComponente();
        List<ComponenteDTO> compoDTO = new ArrayList<>();
        int len = compo.size();  
        for (int i = 0; i < len; i++) {
            ComponenteDTO cDTO = new ComponenteDTO(compo.get(i));
            compoDTO.add(cDTO);
        }
        return compoDTO;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "actualizarComponente")
    public String actualizarComponente(@WebParam(name = "id") Integer id, @WebParam(name = "nombre") String nombre, @WebParam(name = "descripcion") String descripcion, @WebParam(name = "idEstado") boolean estado) {
        ComponenteDAO componenteDAO = new ComponenteDAO();
        Componente c = componenteDAO.leerComponente(id);
        c.setDescripcion(descripcion);
        c.setNombre(nombre);
        c.setEstado(estado);
        return componenteDAO.actualizarComponete(c);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "cambiarEstadoComponente")
    public String cambiarEstadoComponente(@WebParam(name = "id") int id) {
        ComponenteDAO componenteDAO = new ComponenteDAO();
        return componenteDAO.cambiarStatusComponete(id);
    }


}
