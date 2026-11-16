package modelo;

// Se lanza cuando el sorteo recursivo encuentra un error lógico
public class SorteoInvalidoException extends Exception {
    private static final long serialVersionUID = 1L;

    public SorteoInvalidoException(String mensaje) {
        super(mensaje); // Pasa el mensaje a Exception
    }
}