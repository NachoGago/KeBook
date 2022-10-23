package dam.nachogago.ioc.controllers;

import dam.nachogago.ioc.models.UsuarioModel;
import dam.nachogago.ioc.services.UsuarioService;
import dam.nachogago.ioc.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class AuthController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    private JWTUtil jwtUtil;

    /**
     * Permite a un usuario hacer login a la aplicacion.
     * @param correo Correo electronico del usuario.
     * @param contrasena Contrasena del usuario.
     * @return Devuelve el JWT Token para que el usuario pueda hacer las consultas al servidor.
     */
    @GetMapping(path = "/login/{correo}/{contrasena}")
    public String login(@PathVariable("correo") String correo, @PathVariable("contrasena") String contrasena){

        UsuarioModel usuario = usuarioService.obtenerUsuarioPorCredenciales(correo, contrasena);

        if(usuario != null){
            String token = jwtUtil.create(String.valueOf(usuario.getId()), usuario.getCorreo());
            jwtUtil.addToken(usuario.getId(), token);
            return token;

        }else{
            return "ERROR";
        }

    }

    /**
     * Permite a un usuario administrador hacer login a la aplicacion.
     * @param correo Correo electronico del usuario.
     * @param contrasena Contrasena del usuario.
     * @return Devuelve el JWT Token para que el usuario administrador pueda hacer las consultas al servidor.
     */
    @GetMapping(path = "/login/admin/{correo}/{contrasena}")
    public String loginAdmin(@PathVariable("correo") String correo, @PathVariable("contrasena") String contrasena){

        UsuarioModel usuario = usuarioService.obtenerUsuarioPorCredencialesAdmin(correo, contrasena);

        if(usuario != null){
            String token = jwtUtil.create(String.valueOf(usuario.getId()), usuario.getCorreo());
            jwtUtil.addToken(usuario.getId(), token);
            return token;

        }else{
            return "ERROR";
        }
    }

    /**
     * Permite a un usuario hacer logout de la aplicacion.
     * @param id El id del usuario que quiere hacer el logout.
     * @return Devuelve true si se ha eliminado el token, y false si no.
     */
    @GetMapping(path = "/logout/{id}")
    public boolean logout(@PathVariable("id") long id){
        if (jwtUtil.deleteToken(id)){
            return true;
        }else{
            return false;
        }
    }

}
