package dam.nachogago.ioc.repositories;

import dam.nachogago.ioc.models.EscritorModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscritorRepository extends CrudRepository<EscritorModel, Integer> {
    /**
     * Devuelve los datos de un escritor introduciendo su nombre.
     * @param nombre Nombre del escritor que queremos buscar.
     * @return Devuelve los datos del escritor.
     */
    public abstract EscritorModel findByNombre(String nombre);
}
