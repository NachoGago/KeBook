package dam.nachogago.ioc.exceptions;

public class LibroException extends RuntimeException{
    /**
     * Lanzador de exceptions.
     * @param message Mensaje que queremos lanzar con el error.
     */
    public LibroException(String message){
        super(message);
    }
}
