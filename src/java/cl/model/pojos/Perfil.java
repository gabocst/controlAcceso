package cl.model.pojos;
// Generated 03-abr-2017 15:49:09 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Perfil generated by hbm2java
 */
public class Perfil  implements java.io.Serializable {


     private Integer id;
     private Componente componente;
     private Estado estado;
     private String nombre;
     private Date fechaCreacion;
     private String creadoPor;
     private Set posicionfuncionalperfils = new HashSet(0);

    public Perfil() {
    }
    
    public Perfil(Integer id) {
        this.id = id;
    }

	
    public Perfil(Componente componente, Estado estado, String nombre, Date fechaCreacion, String creadoPor) {
        this.componente = componente;
        this.estado = estado;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.creadoPor = creadoPor;
    }
    public Perfil(Componente componente, Estado estado, String nombre, Date fechaCreacion, String creadoPor, Set posicionfuncionalperfils) {
       this.componente = componente;
       this.estado = estado;
       this.nombre = nombre;
       this.fechaCreacion = fechaCreacion;
       this.creadoPor = creadoPor;
       this.posicionfuncionalperfils = posicionfuncionalperfils;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Componente getComponente() {
        return this.componente;
    }
    
    public void setComponente(Componente componente) {
        this.componente = componente;
    }
    public Estado getEstado() {
        return this.estado;
    }
    
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public String getCreadoPor() {
        return this.creadoPor;
    }
    
    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }
    public Set getPosicionfuncionalperfils() {
        return this.posicionfuncionalperfils;
    }
    
    public void setPosicionfuncionalperfils(Set posicionfuncionalperfils) {
        this.posicionfuncionalperfils = posicionfuncionalperfils;
    }




}


