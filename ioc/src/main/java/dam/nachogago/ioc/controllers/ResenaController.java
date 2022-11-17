package dam.nachogago.ioc.controllers;

import dam.nachogago.ioc.exceptions.UsuarioException;
import dam.nachogago.ioc.models.ResenaModel;
import dam.nachogago.ioc.services.ResenaService;
import dam.nachogago.ioc.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/resena")
public class ResenaController {

    @Autowired
    ResenaService resenaService;

    @Autowired
    JWTUtil jwtUtil;

    /**
     * Obtiene una lista de todas las resenas en la base de datos.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @return Devuelve una lista de todas las resenas en la base de datos.
     */
    @GetMapping
    public ArrayList<ResenaModel> obtenerResenas (@RequestHeader(value = "Token") String token){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return resenaService.obtenerResenas();
        }
    }

    /**
     * Guarda una nueva resena en la base de datos.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param resena Datos de la resena que queremos guardar en la base de datos.
     */
    @PostMapping
    public void guardarResena(@RequestHeader(value = "Token") String token,
                              @RequestBody ResenaModel resena){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            resenaService.guardarResena(resena);
        }

    }

    /**
     * Obtiene los datos de una resena pasando el id como parametro.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param id_resena Id de la resena que queremos obtener.
     * @return Devuelve la resena.
     */
    @GetMapping("/{id}")
    public ResenaModel obtenerResenaPorId(@RequestHeader(value = "Token") String token,
                                          @PathVariable("id") int id_resena){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return resenaService.obtenerResenaPorId(id_resena);
        }
    }

    /**
     * Obtiene todas las resenas hechas por un usuario.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param id_usuario Id del usuario del que queremos obtener las resenas.
     * @return Devuelve todas las resenas de un usuario.
     */
    @GetMapping("/usuario")
    public ArrayList<ResenaModel> obtenerResenasUsuario(@RequestHeader(value = "Token") String token,
                                                        @RequestParam("idUsuario") int id_usuario){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return resenaService.obtenerResenasUsuario(id_usuario);
        }
    }

    /**
     * Obtiene las resenas que ha hecho un usuario sobre un libro en concreto.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param isbn ISBN del libro del que queremos las resenas.
     * @param id_usuario Id del usuario el que queremos las resenas.
     * @return Devuelve las resenas hechas por un usuario sobre un libro.
     */
    @GetMapping("/{isbn}/usuario")
    public ArrayList<ResenaModel> obtenerResenasDeLibroPorUsuario(@RequestHeader(value = "Token") String token,
                                                                  @PathVariable("isbn") String isbn,
                                                                  @RequestParam("idUsuario") int id_usuario){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return resenaService.obtenerResenaDeLibroPorUsuario(isbn, id_usuario);
        }
    }

    /**
     * Elimina una resena de la base de datos pasando el id como parametro.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param id_resena Id de la resena que queremos eliminar.
     * @return Devuelve true o false dependiendo de si la resena se ha podido eliminar correctamente o no.
     */
    @DeleteMapping("/borrar")
    public boolean eliminarResena(@RequestHeader(value = "Token") String token,
                                  @RequestParam("idResena") int id_resena){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return resenaService.eliminarResena(id_resena);
        }
    }

}
