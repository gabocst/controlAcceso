package cl.model.pojos;
// Generated Apr 8, 2017 9:54:33 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Posicionfuncional generated by hbm2java
 */
public class Posicionfuncional  implements java.io.Serializable {


     private Integer id;
     private Estado estado;
     private Unidadorganizacional unidadorganizacional;
     private String nombre;
     private Date fechaCreacion;
     private String creadoPor;
     private Set solicituds = new HashSet(0);
     private Set posicionfuncionalperfils = new HashSet(0);

    public Posicionfuncional() {
    }
    
    public Posicionfuncional(Integer id) {
        this.id = id;
    }
	
    public Posicionfuncional(Estado estado, Unidadorganizacional unidadorganizacional, String nombre, Date fechaCreacion, String creadoPor) {
        this.estado = estado;
        this.unidadorganizacional = unidadorganizacional;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.creadoPor = creadoPor;
    }
    public Posicionfuncional(Estado estado, Unidadorganizacional unidadorganizacional, String nombre, Date fechaCreacion, String creadoPor, Set solicituds, Set posicionfuncionalperfils) {
       this.estado = estado;
       this.unidadorganizacional = unidadorganizacional;
       this.nombre = nombre;
       this.fechaCreacion = fechaCreacion;
       this.creadoPor = creadoPor;
       this.solicituds = solicituds;
       this.posicionfuncionalperfils = posicionfuncionalperfils;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Estado getEstado() {
        return this.estado;
    }
    
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    public Unidadorganizacional getUnidadorganizacional() {
        return this.unidadorganizacional;
    }
    
    public void setUnidadorganizacional(Unidadorganizacional unidadorganizacional) {
        this.unidadorganizacional = unidadorganizacional;
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
    public Set getSolicituds() {
        return this.solicituds;
    }
    
    public void setSolicituds(Set solicituds) {
        this.solicituds = solicituds;
    }
    public Set getPosicionfuncionalperfils() {
        return this.posicionfuncionalperfils;
    }
    
    public void setPosicionfuncionalperfils(Set posicionfuncionalperfils) {
        this.posicionfuncionalperfils = posicionfuncionalperfils;
    }




}

