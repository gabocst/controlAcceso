/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model.dto;

import cl.model.pojos.Unidadorganizacional;
import java.util.Date;

/**
 *
 * @author Twins
 */
public class UnidadOrganizacionalDTO {
    private Integer id;
    private String nombre;
    private Date fechaCreacion;
    private String creadoPor;
    private boolean estado;
    
    public UnidadOrganizacionalDTO() {
    }

	
    public UnidadOrganizacionalDTO(Integer id, String nombre, Date fechaCreacion, String creadoPor, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.creadoPor = creadoPor;
        this.estado = estado;
    }
    
    public UnidadOrganizacionalDTO(Unidadorganizacional u) {
        this.id = u.getId();
        this.nombre = u.getNombre();
        this.fechaCreacion = u.getFechaCreacion();
        this.creadoPor = u.getCreadoPor();
        this.estado = u.isEstado();
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
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
