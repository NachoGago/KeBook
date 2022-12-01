package dam.nachogago.ioc.services;

import dam.nachogago.ioc.models.ParticipantesModel;
import dam.nachogago.ioc.repositories.ParticipantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Service
public class ParticipantesService {
    @Autowired
    ParticipantesRepository participantesRepository;

    public ArrayList<ParticipantesModel> obtenerParticipantes() {
        return (ArrayList<ParticipantesModel>) participantesRepository.findAll();
    }


    public void guardarParticipante(ParticipantesModel participante) {
         participantesRepository.save(participante);
    }

    public boolean eliminarParticipante(int id) {
        try{
            participantesRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public ArrayList<ParticipantesModel> obtenerParticipacionPorUsuario(int idUsuario) {
        return participantesRepository.obtenerParticipacionPorUsuario(idUsuario);
    }

    public ArrayList<ParticipantesModel> obtenerParticipantesPorEvento(int idEvento) {
        return participantesRepository.obtenerParticipantesPorEvento(idEvento);
    }
}
