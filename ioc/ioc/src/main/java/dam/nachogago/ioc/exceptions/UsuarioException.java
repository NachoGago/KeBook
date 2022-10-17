package dam.nachogago.ioc.exceptions;

public class UsuarioException extends RuntimeException{
    /**
     * Lanzador de exceptions.
     * @param message Mensaje que queremos lanzar con el error.
     */
    public UsuarioException(String message){
        super(message);
    }
}
