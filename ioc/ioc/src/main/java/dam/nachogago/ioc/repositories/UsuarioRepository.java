package dam.nachogago.ioc.repositories;

import dam.nachogago.ioc.models.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {
    /**
     * Busca los datos de un usuario pasando su correo.
     * @param correo Correo del usuario que queremos recuperar.
     * @return Devuelve los datos del usuario que use el correo proporcionado.
     */
    public abstract UsuarioModel findByCorreo(String correo);
}
