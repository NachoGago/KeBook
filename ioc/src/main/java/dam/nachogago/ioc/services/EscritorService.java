package dam.nachogago.ioc.services;

import dam.nachogago.ioc.models.EscritorModel;
import dam.nachogago.ioc.repositories.EscritorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EscritorService {

    @Autowired
    EscritorRepository escritorRepository;

    /**
     * Recupera de la base de datos una lista con todos los escritores.
     * @return Devuelve la lista de escritores de la base de datos.
     */
    public ArrayList<EscritorModel> obtenerEscritores(){
        return (ArrayList<EscritorModel>) escritorRepository.findAll();
    }

    /**
     * Guarda un nuevo escritor en la base de datos.
     * @param escritor Datos del escritor que queremos guardar.
     * @return Devuelve los datos del escritor guardado.
     */
    public EscritorModel guardarEscritor(EscritorModel escritor){
        return escritorRepository.save(escritor);
    }

    /**
     * Obtiene los datos de un escritor introduciendo su id.
     * @param id Identificador del escritor que queremos buscar.
     * @return Devuevle los datos del escritor que tenga el id.
     */
    public Optional<EscritorModel> obtenerPorId(long id){
        return escritorRepository.findById(id);
    }

    /**
     * Obtiene los datos de un escritor pasando su nombre.
     * @param nombre Nombre del escritor del que queremos los datos.
     * @return Devuelve los datos del escritor.
     */
    public EscritorModel obtenerPorNombre(String nombre){
        return escritorRepository.findByNombre(nombre);
    }

    /**
     * Elimina un escritor de la base de datos.
     * @param id Identificador del escritor que queremos eliminar.
     * @return Devuelve un booleano que indica si la operacion ha tenido exito.
     */
    public boolean eliminarEscritor(long id){
        try{
            escritorRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
