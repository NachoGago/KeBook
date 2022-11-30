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
    @Query(value = "SELECT e FROM EventoModel e WHERE e.libro.isbn=?1")
    Optional<ArrayList<EventoModel>> obtenerEventosPorLibro(String isbn);

    @Modifying
    @Transactional
    @Query(value = "UPDATE EventoModel e SET e.aprobador.id=?2, e.isAproved=true WHERE e.id=?1")
    void aprobarEvento(int idEvento, int idAdmin);

    @Query(value = "SELECT e FROM EventoModel e WHERE e.isAproved=true")
    ArrayList<EventoModel> obtenerEventosAprobados();

    @Query(value = "SELECT e FROM EventoModel e WHERE e.fecha=?1")
    ArrayList<EventoModel> obtenerEventosPorFecha(Date fecha);

    @Query(value = "SELECT e FROM EventoModel e WHERE e.isAproved=false")
    ArrayList<EventoModel> obtenerEventosPendientes();

    //Optional<EventoModel> obtenerEventoPorId(int id);
}
