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

    @GetMapping
    public ArrayList<ResenaModel> obtenerResenas (@RequestHeader(value = "Token") String token){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return resenaService.obtenerResenas();
        }
    }

    @PostMapping
    public void guardarResena(@RequestHeader(value = "Token") String token,
                              @RequestBody ResenaModel resena){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            resenaService.guardarResena(resena);
        }

    }

    @GetMapping("/{id}")
    public ResenaModel obtenerResenaPorId(@RequestHeader(value = "Token") String token,
                                          @PathVariable("id") int id_resena){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return resenaService.obtenerResenaPorId(id_resena);
        }
    }

    @GetMapping("/usuario")
    public ArrayList<ResenaModel> obtenerResenasUsuario(@RequestHeader(value = "Token") String token,
                                                        @RequestParam("idUsuario") int id_usuario){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return resenaService.obtenerResenasUsuario(id_usuario);
        }
    }

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
