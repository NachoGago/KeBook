package dam.nachogago.ioc.exceptions;

public class ParticipantesException extends RuntimeException{
    /**
     * Lanzador de exceptions.
     * @param message Mensaje que queremos lanzar con el error.
     */
    public ParticipantesException(String message){
        super(message);
    }
}
