package modelo;

import java.io.*;
import java.util.*;

// Clase principal del modelo: guarda equipos, etapas y campeón
public class Torneo implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Random RANDOM = new Random();

    private List<Equipo> equipos = new ArrayList<>();                       // Equipos inscritos
    private Map<String, List<Partido>> etapas = new LinkedHashMap<>();      // Partidos por etapa
    private Equipo campeon;                                                 // Ganador final

    public void agregarEquipo(Equipo e) { equipos.add(e); }
    public Map<String, List<Partido>> getEtapas() { return etapas; }
    public Equipo getCampeon() { return campeon; }
    public int getCantidadEquipos() { return equipos.size(); }

    // Valida que la cantidad sea potencia de 2 (16, 8, 4, 2, 1)
    private void validarNumeroEquipos(int n) throws NumeroEquiposInvalidoException {
        if (n < 1 || (n & (n - 1)) != 0) {
            throw new NumeroEquiposInvalidoException(
                "El numero de equipos (" + n + ") no es potencia de 2 (16, 8, 4, 2).");
        }
    }

    // Punto de entrada: reinicia resultados e inicia el sorteo
    public void realizarSorteo() throws NumeroEquiposInvalidoException, SorteoInvalidoException {
        etapas.clear();
        campeon = null;
        sortearEtapa(new ArrayList<>(equipos));
    }

    // RECURSIVO 1: procesa una etapa y se llama con los ganadores
    private void sortearEtapa(List<Equipo> clasificados)
            throws NumeroEquiposInvalidoException, SorteoInvalidoException {
        validarNumeroEquipos(clasificados.size());

        // Caso base: queda un solo equipo, es el campeón
        if (clasificados.size() == 1) {
            campeon = clasificados.get(0);
            return;
        }

        // Mezcla los equipos para que el sorteo sea aleatorio
        List<Equipo> pendientes = new ArrayList<>(clasificados);
        Collections.shuffle(pendientes);

        // Crea los partidos de la etapa
        List<Partido> partidos = new ArrayList<>();
        emparejar(pendientes, partidos);
        etapas.put(nombreEtapa(clasificados.size()), partidos);

        // Juega los partidos y obtiene los ganadores
        List<Equipo> ganadores = new ArrayList<>();
        for (Partido p : partidos) ganadores.add(p.jugar());

        // Llamada recursiva: siguiente etapa con los ganadores
        sortearEtapa(ganadores);
    }

    // RECURSIVO 2: empareja los equipos de dos en dos
    private void emparejar(List<Equipo> pendientes, List<Partido> partidos)
            throws SorteoInvalidoException {
        // Caso base: ya no quedan equipos por emparejar
        if (pendientes.isEmpty()) return;

        // Un número impar hace imposible el emparejamiento
        if (pendientes.size() % 2 != 0) {
            throw new SorteoInvalidoException("Quedo un numero impar de equipos sin emparejar.");
        }

        // Saca el primer equipo y un rival al azar (así no se repiten)
        Equipo local = pendientes.remove(0);
        int idx = RANDOM.nextInt(pendientes.size());
        Equipo visitante = pendientes.remove(idx);

        // Un equipo no puede enfrentarse a sí mismo
        if (local == visitante) {
            throw new SorteoInvalidoException("Un equipo fue asignado contra si mismo.");
        }

        partidos.add(new Partido(local, visitante));
        emparejar(pendientes, partidos); // Llamada recursiva con los restantes
    }

    // Devuelve el nombre de la etapa según la cantidad de equipos
    private String nombreEtapa(int n) {
        switch (n) {
            case 16: return "OCTAVOS DE FINAL";
            case 8:  return "CUARTOS DE FINAL";
            case 4:  return "SEMIFINALES";
            case 2:  return "FINAL";
            default: return "ETAPA DE " + n + " EQUIPOS";
        }
    }

    // Serialización: guarda el torneo en un archivo binario
    public void guardar(String ruta) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ruta))) {
            out.writeObject(this);
        }
    }

    // Deserialización: reconstruye el torneo desde el archivo
    public static Torneo cargar(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ruta))) {
            return (Torneo) in.readObject();
        }
    }
}
