/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model.dto;

import cl.model.pojos.Componente;
import java.util.Date;

/**
 *
 * @author Twins
 */
public class ComponenteDTO {

    private Integer id;
    private String nombre;
    private Date fechaCreacion;
    private String creadoPor;
    private String descripcion;
    private boolean estado;
    
    public ComponenteDTO() {
    }
    
    public ComponenteDTO(Integer id, String nombre, Date fechaCreacion, String creadoPor, String descripcion, boolean estado) {
       this.id = id;
       this.estado = estado;
       this.nombre = nombre;
       this.fechaCreacion = fechaCreacion;
       this.creadoPor = creadoPor;
       this.descripcion = descripcion;
    }
    
    public ComponenteDTO(Componente c) {
       this.id = c.getId();
       this.estado = c.isEstado();
       this.nombre = c.getNombre();
       this.fechaCreacion = c.getFechaCreacion();
       this.creadoPor = c.getCreadoPor();
       this.descripcion = c.getDescripcion();
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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
