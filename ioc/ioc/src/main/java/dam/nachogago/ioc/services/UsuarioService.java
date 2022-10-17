package dam.nachogago.ioc.services;

import dam.nachogago.ioc.exceptions.UsuarioException;
import dam.nachogago.ioc.models.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import dam.nachogago.ioc.repositories.UsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    EntityManager entityManager;

    /**
     * Devuelve una lista de todos los usuarios de la base de datos.
     * @return Devuelve una lista de todos los usuarios de la base de datos.
     */
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    /**
     * Guarda un nuevo usuario a la base de datos.
     * @param usuario Datos del usuario a registrar.
     * @return Devuelve la informacion del usuario registrado.
     */
    public UsuarioModel guardarUsuario(UsuarioModel usuario){
        return usuarioRepository.save(usuario);
    }

    /**
     * Obtiene los datos de un usuario pasando su id.
     * @param id Identificador del usuario del que queremos los datos.
     * @return Devuelve los datos del usuario.
     */
    public UsuarioModel obtenerPorId(long id) {
        return usuarioRepository.findById(id).orElseThrow(()-> new UsuarioException("No s'ha trobat l'usuari."));
    }

    /**
     * Obtiene los datos de un usuario pasando su correo.
     * @param correo Correo del usuario del que queremos los datos.
     * @return Devuelve los datos del usuario.
     */
    public UsuarioModel obtenerPorCorreo(String correo){
        return usuarioRepository.findByCorreo(correo);
    }

    /**
     * Elimina un usuario de la base de datos.
     * @param id Identificador del usuario que queremos eliminar.
     * @return Devuelve un booleano indicando si la operacion ha tenido operacio.
     */
    public boolean eliminarUsuario(long id){
        try{
            usuarioRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtiene los datos de un usuario pasando su correo y su contrasena comprobando que coincidan.
     * @param correo Correo del usuario del que queremos los datos.
     * @param contrasena Contrasena del usuario del que queremos los datos.
     * @return Devuelve los datos del usuario.
     */
    public UsuarioModel obtenerUsuarioPorCredenciales(String correo, String contrasena) {
        String query = "FROM UsuarioModel WHERE correo = :correo AND contrasena = :contrasena";
        List<UsuarioModel> lista = entityManager.createQuery(query).
                setParameter("correo", correo).
                setParameter("contrasena", contrasena).
                getResultList();

        if(lista.isEmpty()){
            return null;
        }else {
            return lista.get(0);
        }
    }



}
