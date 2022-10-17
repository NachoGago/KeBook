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
     * Permite a un usuario hacer logout de la aplicacion.
     * @param id El id del usuario que quiere hacer el logout.
     */
    @GetMapping(path = "/logout/{id}")
    public void logout(@PathVariable("id") long id){
        jwtUtil.deleteToken(id);
    }

}
