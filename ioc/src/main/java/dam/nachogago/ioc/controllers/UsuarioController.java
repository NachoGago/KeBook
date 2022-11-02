package dam.nachogago.ioc.controllers;

import dam.nachogago.ioc.exceptions.UsuarioException;
import dam.nachogago.ioc.models.UsuarioModel;
import dam.nachogago.ioc.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import dam.nachogago.ioc.services.UsuarioService;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    JWTUtil jwtUtil;

    /**
     * Devuelve una lista de todos los usuarios registrados en la base de datos.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @return Devuelve una lista de todos los usuarios registrados en la base de datos.
     */
    @GetMapping()
    public ArrayList<UsuarioModel> obtenerUsuarios(@RequestHeader(value = "Token") String token){
        if(!jwtUtil.findTokenByValue(token)){
            throw new UsuarioException("Token invalido");
        }else{
            return usuarioService.obtenerUsuarios();
        }
    }

    /**
     * Guarda un nuevo usuario a la base de datos.
     * @param usuario Datos del usuario a guardar.
     * @return Devuelve los datos del usuario que se acaba de registrar.
     */
    @PostMapping()
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario){
        return this.usuarioService.guardarUsuario(usuario);
    }

    /**
     * Permite ver los datos de un solo usuario introduciendo su id
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param id Identificador del usuario del cual queremos recuperar los datos.
     * @return Devuelve los datos del usuario requerido.
     * @throws UsuarioException El token no es valido.
     */
    @GetMapping(path = "/{id}")
    public UsuarioModel obtenerUsuarioPorId(@RequestHeader(value = "Token") String token,
                                            @PathVariable("id") long id) throws UsuarioException {
        if(!jwtUtil.findTokenByValue(token)){
            throw new UsuarioException("Token invalido");
        }else{
            return this.usuarioService.obtenerPorId(id);
        }

    }

    /**
     * Elimina el usuario que tenga el id pasado por parametro de la base de datos.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param id Identificador del usuario que se quiere eliminar.
     * @return Devuelve un booleano indicando si la operacion ha tenido exito.
     */
    @DeleteMapping(path = "/{id}")
    public boolean eliminarUsuarioPorId(@RequestHeader(value = "Token") String token,
                                        @PathVariable("id") long id){
        if(!jwtUtil.findTokenByValue(token)){
            throw new UsuarioException("Token invalido");
        }else{
            return this.usuarioService.eliminarUsuario(id);
        }

    }

    /**
     * Obtiene los datos de un usuario introduciendo su correo electronico.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param correo Correo del usuario del que queremos recuperar los datos.
     * @return Devuelve los datos del usuario.
     */
    @GetMapping("/correo")
    public UsuarioModel obtenerUsuarioPorCorreo(@RequestHeader(value = "Token") String token,
                                                @RequestParam("correo") String correo){
        if(!jwtUtil.findTokenByValue(token)){
            throw new UsuarioException("Token invalido");
        }else{
            return this.usuarioService.obtenerPorCorreo(correo);
        }

    }

    /**
     * Cambia la contrasena de un usuario.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param id Id del usuario sobre el que queremos modificar la contrasena.
     * @param contrasenaAntigua Contrasena antigua del usuario.
     * @param contrasenaNueva Nueva contrasena para el usuario.
     * @return Devuelve un booleano indicando si la operacion ha tenido exito o no.
     */
    @PostMapping(path = "/contrasena/cambiar")
    public boolean cambiarContrasena(@RequestHeader(value = "Token") String token,
                                     @RequestParam("id") long id,
                                     @RequestParam("contrasenaAntigua") String contrasenaAntigua,
                                     @RequestParam("contrasenaNueva") String contrasenaNueva){
        if(!jwtUtil.findTokenByValue(token)){
            throw new UsuarioException("Token invalido");
        }else{
            return usuarioService.cambiarContrasena(contrasenaNueva, id, contrasenaAntigua);
        }
    }

}
