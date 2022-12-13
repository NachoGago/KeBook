package dam.nachogago.ioc.services;

import dam.nachogago.ioc.exceptions.UsuarioException;
import dam.nachogago.ioc.models.UsuarioModel;
import dam.nachogago.ioc.utils.Encryption;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import dam.nachogago.ioc.repositories.UsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Entity;
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
    @Autowired
    Encryption encryption;

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
    public UsuarioModel guardarUsuario(UsuarioModel usuario) throws Exception{
        encryption.initFromStrings(Encryption.STRING_KEY, Encryption.STRING_IV);
        String contrasenaEncriptada = encryption.encrypt(usuario.getContrasena());
        usuario.setContrasena(contrasenaEncriptada);
        return usuarioRepository.save(usuario);
    }

    /**
     * Obtiene los datos de un usuario pasando su id.
     * @param id Identificador del usuario del que queremos los datos.
     * @return Devuelve los datos del usuario.
     */
    public UsuarioModel obtenerPorId(int id) {
        return usuarioRepository.findById(id).orElseThrow(()-> new UsuarioException("No s'ha trobat l'usuari."));
    }

    /**
     * Obtiene los datos de un usuario pasando su correo.
     * @param correo Correo del usuario del que queremos los datos.
     * @return Devuelve los datos del usuario.
     */
    public UsuarioModel obtenerPorCorreo(String correo){
        return usuarioRepository.findByCorreo(correo).orElseThrow(()-> new UsuarioException("No s'ha trobat l'usuari."));
    }

    /**
     * Elimina un usuario de la base de datos.
     * @param id Identificador del usuario que queremos eliminar.
     * @return Devuelve un booleano indicando si la operacion ha tenido operacio.
     */
    public boolean eliminarUsuario(int id){
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
    public UsuarioModel obtenerUsuarioPorCredenciales(String correo, String contrasena) throws Exception{
        encryption.initFromStrings(Encryption.STRING_KEY, Encryption.STRING_IV);
        String contrasenaEncriptada = encryption.encrypt(contrasena);
        List<UsuarioModel> lista = usuarioRepository.obtenerUsuarioPorCredenciales(correo, contrasenaEncriptada);

        if(lista.isEmpty()){
            return null;
        }else {
            UsuarioModel usuario = lista.get(0);
            String contrasenaDesencriptada = encryption.decrypt(usuario.getContrasena());
            usuario.setContrasena(contrasenaDesencriptada);
            return usuario;
        }
    }

    /**
     * Obtiene los datos de un usuario pasando su correo y su contrasena comprobando que coincidan y si es administrador.
     * @param correo Correo del usuario del que queremos los datos.
     * @param contrasena Contrasena del usuario del que queremos los datos.
     * @return Devuelve los datos del usuario.
     */
    public UsuarioModel obtenerUsuarioPorCredencialesAdmin(String correo, String contrasena) throws Exception{
        encryption.initFromStrings(Encryption.STRING_KEY, Encryption.STRING_IV);
        String contrasenaEncriptada = encryption.encrypt(contrasena);
        List<UsuarioModel> lista = usuarioRepository.obtenerUsuarioPorCredencialesAdmin(correo, contrasenaEncriptada);

        if(lista.isEmpty()){
            return null;
        }else {
            UsuarioModel usuario = lista.get(0);
            String contrasenaDesencriptada = encryption.decrypt(usuario.getContrasena());
            usuario.setContrasena(contrasenaDesencriptada);
            return usuario;
        }
    }

    /**
     * Cambia la contrasena de un usuario.
     * @param contrasenaNueva Nueva contrasena para el usuario.
     * @param id Id del usuario sobre el que queremos modificar la contrasena.
     * @param contrasenaAntigua Contrasena antigua del usuario.
     * @return Devuelve un booleano indicando si la operacion ha tenido exito o no.
     */
    public boolean cambiarContrasena(String contrasenaNueva, int id, String contrasenaAntigua) throws Exception{
        UsuarioModel usuario = this.obtenerPorId(id);

        if(usuario != null){
            encryption.initFromStrings(Encryption.STRING_KEY, Encryption.STRING_IV);
            String contrasenaAntiguaEncriptada = encryption.encrypt(contrasenaAntigua);
            if(usuario.getContrasena().equals(contrasenaAntiguaEncriptada)){
                String contrasenaNuevaEncriptada = encryption.encrypt(contrasenaNueva);
                usuarioRepository.cambiarContrasena(contrasenaNuevaEncriptada, id, contrasenaAntiguaEncriptada);
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

}
