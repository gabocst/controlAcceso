/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model.dto;

import cl.model.pojos.Solicitud;
import java.util.Date;

/**
 *
 * @author Twins
 */
public class SolicitudDTO {
    private Integer id;
    private Integer idEstadoSolicitud;
    private String estadoSolicitud;
    private Integer idPosicionfuncional;
    private String posicionFuncional;
    private Integer idTipoSolicitud;
    private String tipoSolicitud;
    private String solicitante;
    private Date fechaCreacion;
    private String razon;
    private String observacionAprobador;
    private String observacionAdministrador;
    private String observacionVerificador;
    private Integer idIntermediario;
    
    public SolicitudDTO() {
    }
    
    public SolicitudDTO(Solicitud s) {
        this.id = s.getId();
        this.idEstadoSolicitud = s.getEstadosolicitud().getId();
        this.estadoSolicitud = s.getEstadosolicitud().getNombre();
        this.idPosicionfuncional = s.getPosicionfuncional().getId();
        this.posicionFuncional = s.getPosicionfuncional().getNombre();
        this.idTipoSolicitud = s.getTiposolicitud().getId();
        this.tipoSolicitud = s.getTiposolicitud().getNombre();
        this.solicitante = s.getSolicitante();
        this.fechaCreacion = s.getFechaCreacion();
        this.razon = s.getRazon();
        this.observacionAdministrador = s.getObservacionAdministrador();
        this.observacionAprobador = s.getObservacionAprobador();
        this.observacionVerificador = s.getObservacionVerificador();
        this.idIntermediario = s.getIdIntermediario();
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the idEstadoSolicitud
     */
    public Integer getIdEstadoSolicitud() {
        return idEstadoSolicitud;
    }

    /**
     * @param idEstadoSolicitud the idEstadoSolicitud to set
     */
    public void setIdEstadoSolicitud(Integer idEstadoSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
    }

    /**
     * @return the estadoSolicitud
     */
    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    /**
     * @param estadoSolicitud the estadoSolicitud to set
     */
    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    /**
     * @return the idPosicionfuncional
     */
    public Integer getIdPosicionfuncional() {
        return idPosicionfuncional;
    }

    /**
     * @param idPosicionfuncional the idPosicionfuncional to set
     */
    public void setIdPosicionfuncional(Integer idPosicionfuncional) {
        this.idPosicionfuncional = idPosicionfuncional;
    }

    /**
     * @return the posicionFuncional
     */
    public String getPosicionFuncional() {
        return posicionFuncional;
    }

    /**
     * @param posicionFuncional the posicionFuncional to set
     */
    public void setPosicionFuncional(String posicionFuncional) {
        this.posicionFuncional = posicionFuncional;
    }

    /**
     * @return the idTipoSolicitud
     */
    public Integer getIdTipoSolicitud() {
        return idTipoSolicitud;
    }

    /**
     * @param idTipoSolicitud the idTipoSolicitud to set
     */
    public void setIdTipoSolicitud(Integer idTipoSolicitud) {
        this.idTipoSolicitud = idTipoSolicitud;
    }

    /**
     * @return the tipoSolicitud
     */
    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    /**
     * @param tipoSolicitud the tipoSolicitud to set
     */
    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    /**
     * @return the solicitante
     */
    public String getSolicitante() {
        return solicitante;
    }

    /**
     * @param solicitante the solicitante to set
     */
    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    /**
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return the razon
     */
    public String getRazon() {
        return razon;
    }

    /**
     * @param razon the razon to set
     */
    public void setRazon(String razon) {
        this.razon = razon;
    }

    /**
     * @return the observacionAprobador
     */
    public String getObservacionAprobador() {
        return observacionAprobador;
    }

    /**
     * @param observacionAprobador the observacionAprobador to set
     */
    public void setObservacionAprobador(String observacionAprobador) {
        this.observacionAprobador = observacionAprobador;
    }

    /**
     * @return the observacionAdministrador
     */
    public String getObservacionAdministrador() {
        return observacionAdministrador;
    }

    /**
     * @param observacionAdministrador the observacionAdministrador to set
     */
    public void setObservacionAdministrador(String observacionAdministrador) {
        this.observacionAdministrador = observacionAdministrador;
    }

    /**
     * @return the observacionVerificador
     */
    public String getObservacionVerificador() {
        return observacionVerificador;
    }

    /**
     * @param observacionVerificador the observacionVerificador to set
     */
    public void setObservacionVerificador(String observacionVerificador) {
        this.observacionVerificador = observacionVerificador;
    }

    /**
     * @return the idIntermediario
     */
    public Integer getIdIntermediario() {
        return idIntermediario;
    }

    /**
     * @param idIntermediario the idIntermediario to set
     */
    public void setIdIntermediario(Integer idIntermediario) {
        this.idIntermediario = idIntermediario;
    }
}
