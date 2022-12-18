package dam.nachogago.ioc.repositories;

import dam.nachogago.ioc.models.EventoModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

public interface EventoRepository extends CrudRepository<EventoModel, Integer> {

    /**
     * Obtiene una lista de todos los eventos sobre un libro concreto.
     * @param isbn ISBN del libro del cual queremos los eventos.
     * @return Devuelve la lista de todos los eventos sobre el libro introducido.
     */
    @Query(value = "SELECT e FROM EventoModel e WHERE e.libro.isbn=?1")
    Optional<ArrayList<EventoModel>> obtenerEventosPorLibro(String isbn);

    /**
     * Aprueba un evento de la bbdd.
     * @param idEvento ID del evento que queremos aprobar.
     * @param idAdmin ID del administrador que está aprobando el evento.
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE EventoModel e SET e.aprobador.id=?2, e.isAproved=true WHERE e.id=?1")
    void aprobarEvento(int idEvento, int idAdmin);

    /**
     * Obtiene una lista de todos los eventos aprobados de la bbdd.
     * @return Devuelve la lista de los eventos aprobados de la bbdd.
     */
    @Query(value = "SELECT e FROM EventoModel e WHERE e.isAproved=true")
    ArrayList<EventoModel> obtenerEventosAprobados();

    /**
     * Obtiene una lista de todos los eventos en una fecha concreta.
     * @param fecha Fecha de la que queremos los eventos.
     * @return Devuelve la lista de los eventos en la fecha deseada.
     */
    @Query(value = "SELECT e FROM EventoModel e WHERE e.fecha=?1")
    ArrayList<EventoModel> obtenerEventosPorFecha(Date fecha);

    /**
     * Obtiene una lista de todos los eventos pendientes de aprobacion.
     * @return Devuelve la lista de todos los eventos pendientes de aprobacion.
     */
    @Query(value = "SELECT e FROM EventoModel e WHERE e.isAproved=false")
    ArrayList<EventoModel> obtenerEventosPendientes();

    //Optional<EventoModel> obtenerEventoPorId(int id);
}
