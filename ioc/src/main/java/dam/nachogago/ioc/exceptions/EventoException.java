package dam.nachogago.ioc.exceptions;

public class EventoException extends RuntimeException{
    /**
     * Lanzador de exceptions
     * @param message Mensaje que queremos lanzar con el error.
     */
    public EventoException(String message){
        super(message);
    }

}
