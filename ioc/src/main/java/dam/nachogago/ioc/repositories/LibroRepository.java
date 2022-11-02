package dam.nachogago.ioc.repositories;

import dam.nachogago.ioc.models.LibroModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface LibroRepository extends CrudRepository<LibroModel, String> {

    @Query(value = "SELECT l FROM LibroModel l WHERE l.titulo=?1")
    Optional<LibroModel> findByTitulo(String titulo);

    @Query(value = "SELECT l FROM LibroModel l WHERE l.genero=?1")
    ArrayList<LibroModel> findByGenero(String genero);

    @Query(value = "SELECT l FROM LibroModel l WHERE l.id_autor=?1")
    ArrayList<LibroModel> findByAutor(long id_autor);

    @Query(value = "SELECT l FROM LibroModel l WHERE l.disponible=true")
    ArrayList<LibroModel> obtenerDisponibles();

    @Query(value = "SELECT l FROM LibroModel l WHERE l.isbn=?1 AND l.disponible=true")
    ArrayList<LibroModel> comprobarDisponibilidad(String isbn);
}
