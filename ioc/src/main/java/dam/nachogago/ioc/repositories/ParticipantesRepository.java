package dam.nachogago.ioc.repositories;

import dam.nachogago.ioc.models.ParticipantesModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ParticipantesRepository extends CrudRepository<ParticipantesModel, Integer> {

    @Query(value = "SELECT p FROM ParticipantesModel p WHERE p.usuario.id=?1")
    ArrayList<ParticipantesModel> obtenerParticipacionPorUsuario(int idUsuario);

    @Query(value = "SELECT p FROM ParticipantesModel p WHERE p.evento.id=?1")
    ArrayList<ParticipantesModel> obtenerParticipantesPorEvento(int idEvento);
}
