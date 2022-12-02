package dam.nachogago.ioc.services;

import dam.nachogago.ioc.exceptions.ParticipantesException;
import dam.nachogago.ioc.models.ParticipantesModel;
import dam.nachogago.ioc.repositories.ParticipantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ParticipantesService {
    @Autowired
    ParticipantesRepository participantesRepository;

    /**
     * Obtiene una lista de todos los participantes de los distintos eventos de la bbdd.
     * @return Devuelve la lista de todos los participantes de los distintos eventos.
     */
    public ArrayList<ParticipantesModel> obtenerParticipantes() {
        return (ArrayList<ParticipantesModel>) participantesRepository.findAll();
    }

    /**
     * Guarda un nuevo participante en la base de datos.
     * @param participante Datos del participante (id del usuario e id del evento).
     */
    public void guardarParticipante(ParticipantesModel participante) {
         participantesRepository.save(participante);
    }

    /**
     * Elimina un participante de la bbdd.
     * @param id ID del participante que se quiere eliminar.
     * @return Devuelve true o false dependiendo de si ha podido eliminar el participante o no.
     */
    public boolean eliminarParticipante(int id) {
        try{
            participantesRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    /**
     * Obtiene una lista de todos los eventos en los que ha participado un usuario.
     * @param idUsuario ID del usuario del que queremos la participacion.
     * @return Devuelve la lista de los eventos en los que ha participado el usuario.
     */
    public ArrayList<ParticipantesModel> obtenerParticipacionPorUsuario(int idUsuario) {
        return participantesRepository.obtenerParticipacionPorUsuario(idUsuario);
    }

    /**
     * Obtiene una lista de todos los participantes que tenga un evento en concreto.
     * @param idEvento ID del evento del que queremos comprobar los participantes.
     * @return Devuelve la lista de los participantes del evento.
     */
    public ArrayList<ParticipantesModel> obtenerParticipantesPorEvento(int idEvento) {
        return participantesRepository.obtenerParticipantesPorEvento(idEvento);
    }

    /**
     * Comprueba si un usuario esta apuntado a un evento en concreto.
     * @param idEvento ID del evento.
     * @param idUsuario ID del usuario que queremos comprobar.
     * @return Devuelve true o false dependiendo de si el usuario esta apuntado o no al evento.
     */
    public boolean comprobarParticipante(int idUsuario, int idEvento) {
        Optional<ParticipantesModel> participante = obtenerParticipante(idUsuario, idEvento);

        if(!participante.isEmpty()){
            return true;
        }else{
            return false;
        }

    }

    /**
     * Obtiene los datos de un participante.
     * @param idUsuario ID del usuario.
     * @param idEvento ID del evento.
     * @return Devuelve los datos del participante.
     */
    public Optional<ParticipantesModel> obtenerParticipante(int idUsuario, int idEvento) {
        return participantesRepository.obtenerParticipante(idUsuario, idEvento);//.orElseThrow(()-> new ParticipantesException("No existe este participante."));
    }
}
