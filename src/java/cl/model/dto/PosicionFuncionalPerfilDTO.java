/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model.dto;

import cl.model.pojos.Posicionfuncionalperfil;
import java.util.Date;

/**
 *
 * @author Twins
 */
public class PosicionFuncionalPerfilDTO {
    
    private String perfil;
    private String posicionFuncional;
    private Date fechaCreacion;
    private String creadoPor;
    private Integer idPerfil;
    private Integer idPosicionFuncional;

    public PosicionFuncionalPerfilDTO() {
    }

    public PosicionFuncionalPerfilDTO(Posicionfuncionalperfil pfp) {
       this.perfil = pfp.getPerfil().getNombre();
       this.posicionFuncional = pfp.getPosicionfuncional().getNombre();
       this.fechaCreacion = pfp.getFechaCreacion();
       this.creadoPor = pfp.getCreadoPor();
       this.idPerfil = pfp.getPerfil().getId();
       this.idPosicionFuncional = pfp.getPosicionfuncional().getId();
    }

    /**
     * @return the perfil
     */
    public String getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(String perfil) {
        this.perfil = perfil;
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
     * @return the idPerfil
     */
    public Integer getIdPerfil() {
        return idPerfil;
    }

    /**
     * @param idPerfil the idPerfil to set
     */
    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    /**
     * @return the idPosicionFuncional
     */
    public Integer getIdPosicionFuncional() {
        return idPosicionFuncional;
    }

    /**
     * @param idPosicionFuncional the idPosicionFuncional to set
     */
    public void setIdPosicionFuncional(Integer idPosicionFuncional) {
        this.idPosicionFuncional = idPosicionFuncional;
    }
    
}
