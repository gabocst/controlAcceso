package cl.model.pojos;
// Generated 03-abr-2017 15:49:09 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Unidadorganizacional generated by hbm2java
 */
public class Unidadorganizacional  implements java.io.Serializable {


     private Integer id;
     private Estado estado;
     private String nombre;
     private Date fechaCreacion;
     private String creadoPor;
     private Set posicionfuncionals = new HashSet(0);

    public Unidadorganizacional() {
    }
    
    public Unidadorganizacional(Integer id) {
        this.id = id;
    }
	
    public Unidadorganizacional(Estado estado, String nombre, Date fechaCreacion, String creadoPor) {
        this.estado = estado;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.creadoPor = creadoPor;
    }
    public Unidadorganizacional(Estado estado, String nombre, Date fechaCreacion, String creadoPor, Set posicionfuncionals) {
       this.estado = estado;
       this.nombre = nombre;
       this.fechaCreacion = fechaCreacion;
       this.creadoPor = creadoPor;
       this.posicionfuncionals = posicionfuncionals;
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
    public Set getPosicionfuncionals() {
        return this.posicionfuncionals;
    }
    
    public void setPosicionfuncionals(Set posicionfuncionals) {
        this.posicionfuncionals = posicionfuncionals;
    }




}


