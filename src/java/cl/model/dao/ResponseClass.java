/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model.dao;

import javax.json.Json;

/**
 *
 * @author Twins
 */
public class ResponseClass {
    private int codigo;
    private String mensaje;
    private String excepcion;
    private String data;
    private String fechaCreacion;
    private String tipoSolicitud;
    private String posicionFuncional;

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the excepcion
     */
    public String getExcepcion() {
        return excepcion;
    }

    /**
     * @param excepcion the excepcion to set
     */
    public void setExcepcion(String excepcion) {
        this.excepcion = excepcion;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return the fechaCreacion
     */
    public String getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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
    
}
