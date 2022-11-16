package dam.nachogago.ioc.repositories;

import dam.nachogago.ioc.models.ReservaModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ReservaRepository extends CrudRepository<ReservaModel, Integer> {

    @Query(value = "SELECT r FROM ReservaModel r WHERE r.usuario.id=?1")
    ArrayList<ReservaModel> obtenerReservasPorUsuario(int id_usuario);

    @Query(value = "SELECT r FROM ReservaModel r WHERE r.libro.isbn=?1 AND r.usuario.id=?2")
    Optional<ArrayList<ReservaModel>> obtenerReservasDeLibroPorUsuario(String isbn, int id_usuario);

    @Query(value = "SELECT r.recogido FROM ReservaModel r WHERE r.id=?1")
    boolean comprobarReservaRecogida(int id_reserva);

    @Query(value = "SELECT r.devuelto FROM ReservaModel r WHERE r.id=?1")
    boolean comprobarReservaDevuelta(int id_reserva);
}
