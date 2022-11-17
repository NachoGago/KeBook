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

    /**
     * Obtiene una lista de todos los libros guardados en la base de datos.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @return Devuelve una lista de todos los libros guardados en la base de datos.
     */
    @GetMapping()
    public ArrayList<LibroModel> obtenerLibros(@RequestHeader(value = "Token") String token) {
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return libroService.obtenerLibros();
        }
    }

    /**
     * Guarda un nuevo libro en la base de datos.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param libro Datos del libro que queremos guardar en la base de datos.
     * @return Devuelve los datos del libro que vamos a guardar.
     */
    @PostMapping()
    public LibroModel guardarLibro(@RequestHeader(value = "Token") String token,
                                   @RequestBody LibroModel libro) {
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return this.libroService.guardarLibro(libro);
        }

    }

    /**
     * Obtiene los datos del libro con el titulo que reciba por parametro.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param titulo Titulo del libro que queremos encontrar.
     * @return Devuelve el libro.
     */
    @GetMapping("/titulo")
    public LibroModel obtenerPorTitulo(@RequestHeader(value = "Token") String token,
                                       @RequestParam("titulo") String titulo) {
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return libroService.obtenerPorTitulo(titulo);
        }

    }

    /**
     * Obtiene los datos del libro con el isbn que reciba por parametro.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param isbn ISBN del libro que queremos encontrar.
     * @return Devuelve el libro.
     */
    @GetMapping("/{isbn}")
    public LibroModel obtenerPorIsbn(@RequestHeader(value = "Token") String token,
                                     @PathVariable("isbn") String isbn) {
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return libroService.obtenerPorIsbn(isbn);
        }

    }

    /**
     * Obtiene una lista de los libros con el genero que reciba por parametro.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param genero Genero de los libros que queremos obtener.
     * @return Devuelve una lista de los libros que sean de ese genero.
     */
    @GetMapping(path = "/genero")
    public ArrayList<LibroModel> obtenerPorGenero(@RequestHeader(value = "Token") String token,
                                                  @RequestParam("genero") String genero) {
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return libroService.obtenerPorGenero(genero);
        }

    }

    /**
     * Obtiene todos los libros escritos por el autor que reciba por parametro.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param nombreAutor Nombre del autor del que queremos obtener los libros.
     * @return Devuelve una lista de los libros escritos por este autor.
     */
    @GetMapping(path = "/autor")
    public ArrayList<LibroModel> obtenerPorAutor(@RequestHeader(value = "Token") String token,
                                                 @RequestParam("autor") String nombreAutor) {
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return libroService.obtenerPorAutor(nombreAutor);
        }

    }

    /**
     * Obtiene una lista de todos los libros disponibles.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @return Devuelve una lista de todos los libros disponibles.
     */
    @GetMapping(path = "/disponibles")
    public ArrayList<LibroModel> obtenerDisponibles(@RequestHeader(value = "Token") String token) {
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return libroService.obtenerDisponibles();
        }

    }

    /**
     * Comprueba si un libro esta disponible.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param isbn ISBN del libro que queremos comprobar.
     * @return Devuelve true o false dependiendo de si el libro esta disponible o no.
     */
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
