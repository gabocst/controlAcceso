/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model.dto;

import cl.model.pojos.Estado;

/**
 *
 * @author Twins
 */
public class EstadoDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    
    
    public EstadoDTO() {
    }

	
    public EstadoDTO(Estado e) {
        this.id = e.getId();
        this.nombre = e.getNombre();
        this.descripcion = e.getDescripcion();
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
}
