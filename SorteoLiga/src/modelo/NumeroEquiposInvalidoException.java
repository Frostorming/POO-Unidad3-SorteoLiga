package modelo;

// Se lanza cuando el número de equipos no es potencia de 2
public class NumeroEquiposInvalidoException extends Exception {
    private static final long serialVersionUID = 1L;

    public NumeroEquiposInvalidoException(String mensaje) {
        super(mensaje); // Pasa el mensaje a Exception
    }
}