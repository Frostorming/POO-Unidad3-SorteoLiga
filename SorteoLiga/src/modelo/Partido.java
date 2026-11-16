package modelo;

import java.io.Serializable;

// Representa un partido entre dos equipos
public class Partido implements Serializable {
    private static final long serialVersionUID = 1L;
    private Equipo local;
    private Equipo visitante;
    private Equipo ganador;

    public Partido(Equipo local, Equipo visitante) {
        this.local = local;
        this.visitante = visitante;
    }

    // Simula el partido: 50% de probabilidad para cada equipo
    public Equipo jugar() {
        ganador = (Math.random() < 0.5) ? local : visitante;
        return ganador;
    }

    public Equipo getGanador() { return ganador; }

    @Override
    public String toString() { return local + " vs " + visitante; }
}
