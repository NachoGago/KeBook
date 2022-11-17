package dam.nachogago.ioc.exceptions;

public class ReservaException extends RuntimeException{
    /**
     * Lanzador de exceptions.
     * @param message Mensaje que queremos lanzar con el error.
     */
    public ReservaException(String message){
        super(message);
    }
}
