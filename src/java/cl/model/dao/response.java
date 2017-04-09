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
public class response {
    private int codigo;
    private String mensaje;
    private String excepcion;
    private Json data;

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
    public Json getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Json data) {
        this.data = data;
    }
    
}
