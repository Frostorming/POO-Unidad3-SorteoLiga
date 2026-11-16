package vista;

import java.io.IOException;
import java.util.*;

import controlador.TorneoControlador;
import modelo.*;

// Maneja solo la entrada y salida por consola
public class VistaConsola {
    private Scanner sc = new Scanner(System.in);
    private TorneoControlador controlador = new TorneoControlador();

    // Muestra el menú en bucle hasta que el usuario elija salir
    public void iniciar() {
        int op;
        do {
            System.out.println("\n=== LIGA PROFESIONAL ===");
            System.out.println("1. Crear torneo con 16 equipos");
            System.out.println("2. Realizar sorteo");
            System.out.println("3. Guardar torneo");
            System.out.println("4. Cargar torneo");
            System.out.println("5. Crear torneo con 15 equipos (prueba de error)");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");

            // Lee la opción; si no es número, se marca como inválida
            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                op = -1;
            }

            // Ejecuta la opción y captura todas las excepciones posibles
            try {
                switch (op) {
                    case 1: controlador.crearTorneoNuevo(equiposPorDefecto(16));
                            System.out.println("Torneo creado."); break;
                    case 2: controlador.sortear(); mostrarResultados(); break;
                    case 3: controlador.guardar(); System.out.println("Guardado en torneo.ser"); break;
                    case 4: controlador.cargar(); System.out.println("Torneo cargado.");
                            mostrarResultados(); break;
                    case 5: controlador.crearTorneoNuevo(equiposPorDefecto(15));
                            System.out.println("Torneo creado con 15 equipos."); break;
                    case 0: System.out.println("Hasta luego."); break;
                    default: System.out.println("Opcion invalida.");
                }
            } catch (NumeroEquiposInvalidoException e) {
                System.out.println("Error de equipos: " + e.getMessage());
            } catch (SorteoInvalidoException e) {
                System.out.println("Error de sorteo: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error de archivo: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println("Clase no encontrada al cargar: " + e.getMessage());
            }
        } while (op != 0);
    }

    // Imprime los partidos de cada etapa y el campeón
    private void mostrarResultados() {
        for (Map.Entry<String, List<Partido>> etapa : controlador.getEtapas().entrySet()) {
            System.out.println("\n--- " + etapa.getKey() + " ---");
            for (Partido p : etapa.getValue()) {
                System.out.println(p + "  ->  avanza: " + p.getGanador());
            }
        }
        if (controlador.getCampeon() != null)
            System.out.println("\nCAMPEON: " + controlador.getCampeon());
    }

    // Devuelve los primeros "cantidad" equipos de la lista fija
    private List<String> equiposPorDefecto(int cantidad) {
        String[] n = {"Liga de Quito", "Barcelona SC", "Emelec", "Independiente del Valle",
                "Aucas", "Delfin", "Macara", "El Nacional", "Orense", "Tecnico Universitario",
                "Mushuc Runa", "Cumbaya", "Libertad", "Universidad Catolica", "Deportivo Cuenca", "Guayaquil City"};
        return new ArrayList<>(Arrays.asList(n).subList(0, cantidad));
    }
}
