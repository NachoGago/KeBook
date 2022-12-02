package dam.nachogago.ioc.repositories;

import dam.nachogago.ioc.models.ParticipantesModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ParticipantesRepository extends CrudRepository<ParticipantesModel, Integer> {

    /**
     * Obtiene una lista de todos los eventos en los que ha participado un usuario.
     * @param idUsuario ID del usuario del que queremos la participacion.
     * @return Devuelve la lista de los eventos en los que ha participado el usuario.
     */
    @Query(value = "SELECT p FROM ParticipantesModel p WHERE p.usuario.id=?1")
    ArrayList<ParticipantesModel> obtenerParticipacionPorUsuario(int idUsuario);

    /**
     * Obtiene una lista de todos los participantes que tenga un evento en concreto.
     * @param idEvento ID del evento del que queremos comprobar los participantes.
     * @return Devuelve la lista de los participantes del evento.
     */
    @Query(value = "SELECT p FROM ParticipantesModel p WHERE p.evento.id=?1")
    ArrayList<ParticipantesModel> obtenerParticipantesPorEvento(int idEvento);

    /**
     * Obtiene los datos de un participante.
     * @param idEvento ID del evento.
     * @param idUsuario ID del usuario que queremos.
     * @return Devuelve los datos del participante.
     */
    @Query(value = "SELECT p FROM ParticipantesModel p WHERE p.usuario.id=?1 AND p.evento.id=?2")
    Optional<ParticipantesModel> obtenerParticipante(int idUsuario, int idEvento);

}
