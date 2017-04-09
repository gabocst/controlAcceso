package cl.model.pojos;
// Generated 03-abr-2017 15:49:09 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Tiposolicitud generated by hbm2java
 */
public class Tiposolicitud  implements java.io.Serializable {


     private Integer id;
     private String nombre;
     private String descripcion;
     private Set solicituds = new HashSet(0);

    public Tiposolicitud() {
    }

	
    public Tiposolicitud(String nombre) {
        this.nombre = nombre;
    }
    public Tiposolicitud(String nombre, String descripcion, Set solicituds) {
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.solicituds = solicituds;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Set getSolicituds() {
        return this.solicituds;
    }
    
    public void setSolicituds(Set solicituds) {
        this.solicituds = solicituds;
    }




}


