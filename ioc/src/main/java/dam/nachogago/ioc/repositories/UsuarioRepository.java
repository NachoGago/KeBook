package dam.nachogago.ioc.repositories;

import dam.nachogago.ioc.exceptions.UsuarioException;
import dam.nachogago.ioc.models.UsuarioModel;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Integer>{
    /**
     * Busca los datos de un usuario pasando su correo.
     * @param correo Correo del usuario que queremos recuperar.
     * @return Devuelve los datos del usuario que use el correo proporcionado.
     */
    Optional<UsuarioModel> findByCorreo(String correo);

    /**
     * Obtiene los datos de un usuario pasando su correo y su contrasena comprobando que coincidan.
     * @param correo Correo del usuario del que queremos los datos.
     * @param contrasena Contrasena del usuario del que queremos los datos.
     * @return Devuelve los datos del usuario.
     */
    @Query(value = "SELECT u FROM UsuarioModel u WHERE u.correo = ?1 AND u.contrasena = ?2")
    List<UsuarioModel> obtenerUsuarioPorCredenciales(String correo, String contrasena);

    /**
     * Obtiene los datos de un usuario pasando su correo y su contrasena comprobando que coincidan y si es un administrador.
     * @param correo Correo del usuario del que queremos los datos.
     * @param contrasena Contrasena del usuario del que queremos los datos.
     * @return Devuelve los datos del usuario.
     */
    @Query(value = "SELECT u FROM UsuarioModel u WHERE u.correo = ?1 AND u.contrasena = ?2 AND u.isAdmin = true")
    List<UsuarioModel> obtenerUsuarioPorCredencialesAdmin(String correo, String contrasena);

    /**
     * Cambia la contrasena de un usuario.
     * @param contrasenaNueva Nueva contrasena para el usuario.
     * @param id Id del usuario sobre el que queremos modificar la contrasena.
     * @param contrasenaAntigua Contrasena antigua del usuario.
     */
    @Query(value = "UPDATE UsuarioModel u SET u.contrasena = ?1 WHERE u.id = ?2 AND u.contrasena = ?3")
    void cambiarContrasena(String contrasenaNueva, int id, String contrasenaAntigua);
}
