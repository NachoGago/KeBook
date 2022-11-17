package dam.nachogago.ioc.repositories;

import dam.nachogago.ioc.models.LibroModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface LibroRepository extends CrudRepository<LibroModel, String> {

    /**
     * Obtiene los datos del libro con el titulo que reciba por parametro.
     * @param titulo Titulo del libro que queremos encontrar.
     * @return Devuelve el libro.
     */
    @Query(value = "SELECT l FROM LibroModel l WHERE l.titulo=?1")
    Optional<LibroModel> findByTitulo(String titulo);

    /**
     * Obtiene una lista de los libros con el genero que reciba por parametro.
     * @param genero Genero de los libros que queremos obtener.
     * @return Devuelve una lista de los libros que sean de ese genero.
     */
    @Query(value = "SELECT l FROM LibroModel l WHERE l.genero=?1")
    ArrayList<LibroModel> findByGenero(String genero);

    /**
     * Obtiene todos los libros escritos por el autor que reciba por parametro.
     * @param id_autor Id del autor del que queremos obtener los libros.
     * @return Devuelve una lista de los libros escritos por este autor.
     */
    @Query(value = "SELECT l FROM LibroModel l WHERE l.autor.id=?1")
    ArrayList<LibroModel> findByAutor(int id_autor);

    /**
     * Obtiene una lista de todos los libros disponibles.
     * @return Devuelve una lista de todos los libros disponibles.
     */
    @Query(value = "SELECT l FROM LibroModel l WHERE l.disponible=true")
    ArrayList<LibroModel> obtenerDisponibles();

    /**
     * Comprueba si un libro esta disponible.
     * @param isbn ISBN del libro que queremos comprobar.
     * @return Devuelve true o false dependiendo de si el libro esta disponible o no.
     */
    @Query(value = "SELECT l FROM LibroModel l WHERE l.isbn=?1 AND l.disponible=true")
    ArrayList<LibroModel> comprobarDisponibilidad(String isbn);
}
