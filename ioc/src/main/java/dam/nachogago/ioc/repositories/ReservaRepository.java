package dam.nachogago.ioc.repositories;

import dam.nachogago.ioc.models.ReservaModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ReservaRepository extends CrudRepository<ReservaModel, Integer> {

    /**
     * Obtiene una lista de todas las reservas hechas por un usuario.
     * @param id_usuario Id del usuario del que queremos obtener las reservas.
     * @return Devuelve una lista de todas las reservas hechas por un usuario.
     */
    @Query(value = "SELECT r FROM ReservaModel r WHERE r.usuario.id=?1")
    ArrayList<ReservaModel> obtenerReservasPorUsuario(int id_usuario);

    /**
     * Obtiene una lista de todas las reservas que ha hecho un usuario de un libro en concreto.
     * @param isbn ISBN del libro del que queremos comprobar las reservas.
     * @param id_usuario Id del usuario del que queremos comprobar las reservas.
     * @return Devuelve la lista de todas las reservas que ha hecho el usuario de este libro.
     */
    @Query(value = "SELECT r FROM ReservaModel r WHERE r.libro.isbn=?1 AND r.usuario.id=?2")
    Optional<ArrayList<ReservaModel>> obtenerReservasDeLibroPorUsuario(String isbn, int id_usuario);

    /**
     * Comprueba la reserva ha sido recogida.
     * @param id_reserva Id de la reserva que queremos comprobar.
     * @return Devuelve true o false dependiendo de si la reserva ha sido recogida.
     */
    @Query(value = "SELECT r.recogido FROM ReservaModel r WHERE r.id=?1")
    boolean comprobarReservaRecogida(int id_reserva);

    /**
     * Comprueba si la reserva ha sido devuelta.
     * @param id_reserva Id de la reserva que queremos comprobar.
     * @return Devuelve true o false dependiendo de si la reserva ha sido devuelta.
     */
    @Query(value = "SELECT r.devuelto FROM ReservaModel r WHERE r.id=?1")
    boolean comprobarReservaDevuelta(int id_reserva);

    /**
     * Confirma que la reserva ha sido recogida.
     * @param id_reserva Id de la reserva que queremos confimar la recogida.
     * @param recogido booleano true o false.
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE ReservaModel r SET r.recogido=?2 WHERE r.id=?1")
    void confirmarRecogida(int id_reserva, boolean recogido);

    /**
     * Confirma que la reserva ha sido devuelta.
     * @param id_reserva Id de la reserva que queremos confimar la devolucion.
     * @param devuelto booleano true o false.
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE ReservaModel r SET r.devuelto=?2 WHERE r.id=?1")
    void confirmarDevolucion(int id_reserva, boolean devuelto);

    /**
     * Obtiene una lista de todas las reservas que se han hecho sobre un libro.
     * @param isbn ISBN del libro del que queremos obtener las reservas.
     * @return Devuelve la lista de las reservas que se han hecho sobre el libro.
     */
    @Query(value = "SELECT r FROM ReservaModel r WHERE r.libro.isbn=?1")
    ArrayList<ReservaModel> obtenerReservasPorLibro(String isbn);
}
