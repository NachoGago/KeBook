package dam.nachogago.ioc.exceptions;

public class ResenaException extends RuntimeException{
    /**
     * Lanzador de exceptions.
     * @param message Mensaje que queremos lanzar con el error.
     */
    public ResenaException(String message){
        super(message);
    }

}
