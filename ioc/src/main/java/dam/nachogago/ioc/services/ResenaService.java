package dam.nachogago.ioc.services;

import dam.nachogago.ioc.exceptions.ReservaException;
import dam.nachogago.ioc.models.ResenaModel;
import dam.nachogago.ioc.exceptions.ResenaException;
import dam.nachogago.ioc.repositories.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ResenaService {

    @Autowired
    ResenaRepository resenaRepository;


    /**
     * Obtiene una lista de todas las resenas en la base de datos.
     * @return Devuelve una lista de todas las resenas en la base de datos.
     */
    public ArrayList<ResenaModel> obtenerResenas() {
        return (ArrayList<ResenaModel>) resenaRepository.findAll();
    }


    /**
     * Guarda una nueva resena en la base de datos.
     * @param resena Datos de la resena que queremos guardar en la base de datos.
     */
    public void guardarResena(ResenaModel resena) {
        resenaRepository.save(resena);
    }

    /**
     * Obtiene todas las resenas hechas por un usuario.
     * @param id_usuario Id del usuario del que queremos obtener las resenas.
     * @return Devuelve todas las resenas de un usuario.
     */
    public ArrayList<ResenaModel> obtenerResenasUsuario(int id_usuario) {
        return resenaRepository.obtenerResenasUsuario(id_usuario).orElseThrow(()->new ReservaException("El usuario no tiene ninguna reseña."));
    }

    /**
     * Obtiene las resenas que ha hecho un usuario sobre un libro en concreto.
     * @param isbn ISBN del libro del que queremos las resenas.
     * @param id_usuario Id del usuario el que queremos las resenas.
     * @return Devuelve las resenas hechas por un usuario sobre un libro.
     */
    public ArrayList<ResenaModel> obtenerResenaDeLibroPorUsuario(String isbn, int id_usuario) {
        return resenaRepository.obtenerResenaDeLibroPorUsuario(isbn, id_usuario).orElseThrow(()->new ReservaException("El usuario no ha hecho reseña de este libro."));
    }

    /**
     * Elimina una resena de la base de datos pasando el id como parametro.
     * @param id_resena Id de la resena que queremos eliminar.
     * @return Devuelve true o false dependiendo de si la resena se ha podido eliminar correctamente o no.
     */
    public boolean eliminarResena(int id_resena) {
        ResenaModel resena = resenaRepository.obtenerResenaPorId(id_resena).orElseThrow(()->new ReservaException("No existe reseña con este id."));
        if (resena == null){
            return false;
        }else{
            resenaRepository.eliminarResena(id_resena);
            return true;
        }
    }

    /**
     * Obtiene los datos de una resena pasando el id como parametro.
     * @param id_resena Id de la resena que queremos obtener.
     * @return Devuelve la resena.
     */
    public ResenaModel obtenerResenaPorId(int id_resena) {
        return resenaRepository.obtenerResenaPorId(id_resena).orElseThrow(()->new ReservaException("No existe reseña con este id."));
    }
}
