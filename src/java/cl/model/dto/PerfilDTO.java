/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model.dto;

import cl.model.pojos.Perfil;
import java.util.Date;

/**
 *
 * @author Twins
 */
public class PerfilDTO {
    private Integer id;
    private String nombre;
    private Date fechaCreacion;
    private String creadoPor;
    private String componente;
    private String estado;
    private Integer idEstado;
    private Integer idComponente;
    
    public PerfilDTO() {
    }

	
    public PerfilDTO(Perfil p) {
        this.id = p.getId();
        this.nombre = p.getNombre();
        this.fechaCreacion = p.getFechaCreacion();
        this.creadoPor = p.getCreadoPor();
        this.componente = p.getComponente().getNombre();
        this.estado = p.getEstado().getNombre();
        this.idEstado = p.getEstado().getId();
        this.idComponente = p.getComponente().getId();
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
     * @return the componente
     */
    public String getComponente() {
        return componente;
    }

    /**
     * @param componente the componente to set
     */
    public void setComponente(String componente) {
        this.componente = componente;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the idEstado
     */
    public Integer getIdEstado() {
        return idEstado;
    }

    /**
     * @param idEstado the idEstado to set
     */
    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    /**
     * @return the idComponente
     */
    public Integer getIdComponente() {
        return idComponente;
    }

    /**
     * @param idComponente the idComponente to set
     */
    public void setIdComponente(Integer idComponente) {
        this.idComponente = idComponente;
    }
}
