package dam.nachogago.ioc.controllers;

import dam.nachogago.ioc.exceptions.UsuarioException;
import dam.nachogago.ioc.models.LibroModel;
import dam.nachogago.ioc.services.LibroService;
import dam.nachogago.ioc.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/libro")
public class LibroController {
    @Autowired
    LibroService libroService;
    @Autowired
    JWTUtil jwtUtil;

    @GetMapping()
    public ArrayList<LibroModel> obtenerLibros(@RequestHeader(value = "Token") String token) {
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return libroService.obtenerLibros();
        }
    }

    @PostMapping()
    public LibroModel guardarLibro(@RequestHeader(value = "Token") String token,
                                   @RequestBody LibroModel libro) {
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return this.libroService.guardarLibro(libro);
        }

    }

    @GetMapping("/titulo")
    public LibroModel obtenerPorTitulo(@RequestHeader(value = "Token") String token,
                                       @RequestParam("titulo") String titulo) {
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return libroService.obtenerPorTitulo(titulo);
        }

    }

    @GetMapping("/{isbn}")
    public LibroModel obtenerPorIsbn(@RequestHeader(value = "Token") String token,
                                     @PathVariable("isbn") String isbn) {
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return libroService.obtenerPorIsbn(isbn);
        }

    }

    @GetMapping(path = "/genero")
    public ArrayList<LibroModel> obtenerPorGenero(@RequestHeader(value = "Token") String token,
                                                  @RequestParam("genero") String genero) {
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return libroService.obtenerPorGenero(genero);
        }

    }

    @GetMapping(path = "/autor")
    public ArrayList<LibroModel> obtenerPorAutor(@RequestHeader(value = "Token") String token,
                                                 @RequestParam("autor") String nombreAutor) {
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return libroService.obtenerPorAutor(nombreAutor);
        }

    }

    @GetMapping(path = "/disponibles")
    public ArrayList<LibroModel> obtenerDisponibles(@RequestHeader(value = "Token") String token) {
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return libroService.obtenerDisponibles();
        }

    }

    @GetMapping(path = "/disponible/{isbn}")
    public boolean comprobarDisponibilidad(@RequestHeader(value = "Token") String token,
                                           @PathVariable("isbn")String isbn){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return libroService.comprobarDisponibilidad(isbn);
        }

    }

}
