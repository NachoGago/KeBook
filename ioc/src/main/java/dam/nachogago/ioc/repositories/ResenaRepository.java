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
    @Query(value = "SELECT r FROM ResenaModel r WHERE r.usuario.id=?1")
    Optional<ArrayList<ResenaModel>> obtenerResenasUsuario(int id_usuario);

    @Query(value = "SELECT r FROM ResenaModel r WHERE r.libro.isbn=?1 AND r.usuario.id=?2")
    Optional<ArrayList<ResenaModel>> obtenerResenaDeLibroPorUsuario(String isbn, int id_usuario);

    @Query(value = "SELECT r FROM ResenaModel r WHERE r.id=?1")
    Optional<ResenaModel> obtenerResenaPorId(int id_resena);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ResenaModel r WHERE r.id=?1")
    void eliminarResena(int id_resena);
}
