package controlador;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import modelo.*;

// Intermediario entre la Vista y el Modelo (sin lógica de sorteo)
public class TorneoControlador {
    private static final String ARCHIVO = "torneo.ser"; // Archivo de persistencia
    private Torneo torneo;

    public TorneoControlador() {
        torneo = new Torneo();
    }

    // Crea un torneo nuevo con los nombres recibidos
    public void crearTorneoNuevo(List<String> nombres) {
        torneo = new Torneo();
        for (String n : nombres) torneo.agregarEquipo(new Equipo(n));
    }

    // Pide al modelo que realice el sorteo
    public void sortear() throws NumeroEquiposInvalidoException, SorteoInvalidoException {
        torneo.realizarSorteo();
    }

    // Guarda el torneo en disco
    public void guardar() throws IOException { torneo.guardar(ARCHIVO); }

    // Carga el torneo desde disco (verifica que el archivo exista)
    public void cargar() throws IOException, ClassNotFoundException {
        if (!new File(ARCHIVO).exists()) throw new IOException("No existe el archivo " + ARCHIVO);
        torneo = Torneo.cargar(ARCHIVO);
    }

    // Datos que la Vista necesita mostrar
    public Map<String, List<Partido>> getEtapas() { return torneo.getEtapas(); }
    public Equipo getCampeon() { return torneo.getCampeon(); }
}
