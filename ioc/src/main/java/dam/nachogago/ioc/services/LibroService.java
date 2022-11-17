package dam.nachogago.ioc.services;

import dam.nachogago.ioc.exceptions.LibroException;
import dam.nachogago.ioc.models.LibroModel;
import dam.nachogago.ioc.repositories.EscritorRepository;
import dam.nachogago.ioc.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LibroService {
    @Autowired
    LibroRepository libroRepository;
    @Autowired
    EscritorRepository escritorRepository;

    /**
     * Devuelve una lista de todos los libros guardados en la base de datos.
     * @return Devuelve una lista de todos los libros guardados en la base de datos.
     */
    public ArrayList<LibroModel> obtenerLibros() {
        return (ArrayList<LibroModel>) libroRepository.findAll();
    }

    /**
     * Guarda un nuevo libro en la base de datos.
     * @param libro Datos del libro que queremos guardar en la base de datos.
     * @return Devuelve los datos del libro que vamos a guardar.
     */
    public LibroModel guardarLibro(LibroModel libro) {
        return libroRepository.save(libro);
    }

    /**
     * Obtiene los datos del libro con el isbn que reciba por parametro.
     * @param isbn ISBN del libro que queremos encontrar.
     * @return Devuelve el libro.
     */
    public LibroModel obtenerPorIsbn(String isbn){
        return libroRepository.findById(isbn).orElseThrow(()-> new LibroException("Libro no encontrado."));
    }

    /**
     * Obtiene los datos del libro con el titulo que reciba por parametro.
     * @param titulo Titulo del libro que queremos encontrar.
     * @return Devuelve el libro.
     */
    public LibroModel obtenerPorTitulo(String titulo){
        return libroRepository.findByTitulo(titulo).orElseThrow(()-> new LibroException("No s'ha trobat el llibre."));
    }

    /**
     * Obtiene una lista de los libros con el genero que reciba por parametro.
     * @param genero Genero de los libros que queremos obtener.
     * @return Devuelve una lista de los libros que sean de ese genero.
     */
    public ArrayList<LibroModel> obtenerPorGenero(String genero){
        return libroRepository.findByGenero(genero);
    }

    /**
     * Obtiene todos los libros escritos por el autor que reciba por parametro.
     * @param nombreAutor Nombre del autor del que queremos obtener los libros.
     * @return Devuelve una lista de los libros escritos por este autor.
     */
    public ArrayList<LibroModel> obtenerPorAutor(String nombreAutor){
        int id_autor = escritorRepository.findByNombre(nombreAutor).getId();
        return libroRepository.findByAutor(id_autor);
    }

    /**
     * Obtiene una lista de todos los libros disponibles.
     * @return Devuelve una lista de todos los libros disponibles.
     */
    public ArrayList<LibroModel> obtenerDisponibles() {
        return libroRepository.obtenerDisponibles();
    }

    /**
     * Comprueba si un libro esta disponible.
     * @param isbn ISBN del libro que queremos comprobar.
     * @return Devuelve true o false dependiendo de si el libro esta disponible o no.
     */
    public boolean comprobarDisponibilidad(String isbn) {
        ArrayList<LibroModel> list = libroRepository.comprobarDisponibilidad(isbn);
        if(list.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
}
