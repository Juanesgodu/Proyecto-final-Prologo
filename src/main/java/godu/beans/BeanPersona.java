package godu.beans;

import java.io.Serializable;

public class BeanPersona implements Serializable{
    private String nombre;
    
    public BeanPersona(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "PersonaBean{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}