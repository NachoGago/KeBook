package dam.nachogago.ioc.repositories;

import dam.nachogago.ioc.models.ResenaModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ResenaRepository extends CrudRepository<ResenaModel, Integer> {

    /**
     * Obtiene todas las resenas hechas por un usuario.
     * @param id_usuario Id del usuario del que queremos obtener las resenas.
     * @return Devuelve todas las resenas de un usuario.
     */
    @Query(value = "SELECT r FROM ResenaModel r WHERE r.usuario.id=?1")
    Optional<ArrayList<ResenaModel>> obtenerResenasUsuario(int id_usuario);

    /**
     * Obtiene las resenas que ha hecho un usuario sobre un libro en concreto.
     * @param isbn ISBN del libro del que queremos las resenas.
     * @param id_usuario Id del usuario el que queremos las resenas.
     * @return Devuelve las resenas hechas por un usuario sobre un libro.
     */
    @Query(value = "SELECT r FROM ResenaModel r WHERE r.libro.isbn=?1 AND r.usuario.id=?2")
    Optional<ArrayList<ResenaModel>> obtenerResenaDeLibroPorUsuario(String isbn, int id_usuario);

    /**
     * Obtiene los datos de una resena pasando el id como parametro.
     * @param id_resena Id de la resena que queremos obtener.
     * @return Devuelve la resena.
     */
    @Query(value = "SELECT r FROM ResenaModel r WHERE r.id=?1")
    Optional<ResenaModel> obtenerResenaPorId(int id_resena);

    /**
     * Elimina una resena de la base de datos pasando el id como parametro.
     * @param id_resena Id de la resena que queremos eliminar.
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ResenaModel r WHERE r.id=?1")
    void eliminarResena(int id_resena);

    /**
     * Obtiene todas las resenas sobre un libro concreto.
     * @param isbn ISBN del libro del cual queremos la resena.
     * @return Devuelve una lista de todas las resenas sobre el libro.
     */
    @Query(value = "SELECT r FROM ResenaModel r WHERE r.libro.isbn=?1")
    ArrayList<ResenaModel> obtenerResenaPorLibro(String isbn);
}
