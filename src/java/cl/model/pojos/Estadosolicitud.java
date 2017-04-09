package cl.model.pojos;
// Generated Apr 9, 2017 12:20:50 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Estadosolicitud generated by hbm2java
 */
public class Estadosolicitud  implements java.io.Serializable {


     private Integer id;
     private String nombre;
     private String descripcion;
     private Set solicituds = new HashSet(0);
     private Set matrizcontrolaccesos = new HashSet(0);

    public Estadosolicitud() {
    }

	
    public Estadosolicitud(String nombre) {
        this.nombre = nombre;
    }
    public Estadosolicitud(String nombre, String descripcion, Set solicituds, Set matrizcontrolaccesos) {
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.solicituds = solicituds;
       this.matrizcontrolaccesos = matrizcontrolaccesos;
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
    public Set getMatrizcontrolaccesos() {
        return this.matrizcontrolaccesos;
    }
    
    public void setMatrizcontrolaccesos(Set matrizcontrolaccesos) {
        this.matrizcontrolaccesos = matrizcontrolaccesos;
    }




}


