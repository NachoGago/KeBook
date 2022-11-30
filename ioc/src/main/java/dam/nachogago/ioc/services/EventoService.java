package dam.nachogago.ioc.services;

import dam.nachogago.ioc.exceptions.EventoException;
import dam.nachogago.ioc.models.EventoModel;
import dam.nachogago.ioc.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    EventoRepository eventoRepository;

    public ArrayList<EventoModel> obtenerEventos() {
        return (ArrayList<EventoModel>) eventoRepository.findAll();
    }

    public void guardarEvento(EventoModel evento) {
        eventoRepository.save(evento);
    }


    public ArrayList<EventoModel> obtenerEventosPorLibro(String isbn) {
        return eventoRepository.obtenerEventosPorLibro(isbn).orElseThrow(()-> new EventoException("No hay eventos sobre este libro."));
    }

    public EventoModel obtenerEventoPorId(int id) {
        //return eventoRepository.obtenerEventoPorId(id).orElseThrow(()-> new EventoException("No hay evento con este Id."));
        return eventoRepository.findById(id).orElseThrow(()-> new EventoException("No hay evento con este Id."));
    }

    public boolean aprobarEvento(int idEvento, int idAdmin) {
        Optional<EventoModel> evento = eventoRepository.findById(idEvento);
        if(!evento.isEmpty()) {
            eventoRepository.aprobarEvento(idEvento, idAdmin);
            return true;
        }else{
            return false;
        }
    }

    public boolean eliminarEvento(int idEvento) {
        try{
            eventoRepository.deleteById(idEvento);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public ArrayList<EventoModel> obtenerEventosAprobados(){
        return eventoRepository.obtenerEventosAprobados();
    }

    public ArrayList<EventoModel> obtenerEventosPorFecha(Date fecha) {
        return eventoRepository.obtenerEventosPorFecha(fecha);
    }

    public ArrayList<EventoModel> obtenerEventosPendientes() {
        return eventoRepository.obtenerEventosPendientes();
    }
}
