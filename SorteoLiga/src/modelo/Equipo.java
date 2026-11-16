package modelo;

import java.io.Serializable;

// Representa un equipo de la liga (Serializable para guardarlo en archivo)
public class Equipo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;

    public Equipo(String nombre) { this.nombre = nombre; }

    public String getNombre() { return nombre; }

    @Override
    public String toString() { return nombre; } // Se imprime solo el nombre
}
