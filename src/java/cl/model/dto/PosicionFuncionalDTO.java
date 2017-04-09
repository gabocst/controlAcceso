/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model.dto;

import cl.model.pojos.Posicionfuncional;
import java.util.Date;

/**
 *
 * @author Twins
 */
public class PosicionFuncionalDTO {
    
    private Integer id;
    private String nombre;
    private Date fechaCreacion;
    private String creadoPor;
    private Integer idUnidadorganizacional;
    private boolean estado;
    private String unidadorganizacional;
    
    
    public PosicionFuncionalDTO() {
    }

	
    public PosicionFuncionalDTO(Posicionfuncional p) {
        this.id = p.getId();
        this.nombre = p.getNombre();
        this.fechaCreacion = p.getFechaCreacion();
        this.creadoPor = p.getCreadoPor();
        this.idUnidadorganizacional = p.getUnidadorganizacional().getId();
        this.estado = p.isEstado();
        this.unidadorganizacional = p.getUnidadorganizacional().getNombre();
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * @return the creadoPor
     */
    public String getCreadoPor() {
        return creadoPor;
    }

    /**
     * @param creadoPor the creadoPor to set
     */
    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * @return the idEstado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param idEstado the idEstado to set
     */
    public void setIdEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * @return the idUnidadorganizacional
     */
    public Integer getIdUnidadorganizacional() {
        return idUnidadorganizacional;
    }

    /**
     * @param idUnidadorganizacional the idUnidadorganizacional to set
     */
    public void setIdUnidadorganizacional(Integer idUnidadorganizacional) {
        this.idUnidadorganizacional = idUnidadorganizacional;
    }

    /**
     * @return the unidadorganizacional
     */
    public String getUnidadorganizacional() {
        return unidadorganizacional;
    }

    /**
     * @param unidadorganizacional the unidadorganizacional to set
     */
    public void setUnidadorganizacional(String unidadorganizacional) {
        this.unidadorganizacional = unidadorganizacional;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
}
