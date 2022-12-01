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

    /**
     * Obtiene una lista de todos los eventos registrados en la base de datos.
     * @return Devuelve la lista de todos los eventos registrados.
     */
    public ArrayList<EventoModel> obtenerEventos() {
        return (ArrayList<EventoModel>) eventoRepository.findAll();
    }

    /**
     * Añade un nuevo evento a la base de datos.
     * @param evento Datos del evento que queremos guardar en la bbdd.
     */
    public void guardarEvento(EventoModel evento) {
        eventoRepository.save(evento);
    }

    /**
     * Obtiene una lista de todos los eventos sobre un libro concreto.
     * @param isbn ISBN del libro del cual queremos los eventos.
     * @return Devuelve la lista de todos los eventos sobre el libro introducido.
     */
    public ArrayList<EventoModel> obtenerEventosPorLibro(String isbn) {
        return eventoRepository.obtenerEventosPorLibro(isbn).orElseThrow(()-> new EventoException("No hay eventos sobre este libro."));
    }

    /**
     * Obtiene los datos de un evento concreto pasando el id por parametro.
     * @param id ID del evento del que queremos los datos.
     * @return Devuelve los datos del evento.
     */
    public EventoModel obtenerEventoPorId(int id) {
        //return eventoRepository.obtenerEventoPorId(id).orElseThrow(()-> new EventoException("No hay evento con este Id."));
        return eventoRepository.findById(id).orElseThrow(()-> new EventoException("No hay evento con este Id."));
    }

    /**
     * Aprueba un evento de la bbdd.
     * @param idEvento ID del evento que queremos aprobar.
     * @param idAdmin ID del administrador que está aprobando el evento.
     * @return Devuelve true o false dependiendo de si ha podido aprobar el evento o no.
     */
    public boolean aprobarEvento(int idEvento, int idAdmin) {
        Optional<EventoModel> evento = eventoRepository.findById(idEvento);
        if(!evento.isEmpty()) {
            eventoRepository.aprobarEvento(idEvento, idAdmin);
            return true;
        }else{
            return false;
        }
    }

    /**
     * Elimina un evento de la base de datos.
     * @param idEvento ID del evento que se quiere eliminar de la bbdd.
     * @return Devuelve true o false dependiendo de si ha podido eliminar o no el evento.
     */
    public boolean eliminarEvento(int idEvento) {
        try{
            eventoRepository.deleteById(idEvento);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Obtiene una lista de todos los eventos aprobados de la bbdd.
     * @return Devuelve la lista de los eventos aprobados de la bbdd.
     */
    public ArrayList<EventoModel> obtenerEventosAprobados(){
        return eventoRepository.obtenerEventosAprobados();
    }

    /**
     * Obtiene una lista de todos los eventos en una fecha concreta.
     * @param fecha Fecha de la que queremos los eventos.
     * @return Devuelve la lista de los eventos en la fecha deseada.
     */
    public ArrayList<EventoModel> obtenerEventosPorFecha(Date fecha) {
        return eventoRepository.obtenerEventosPorFecha(fecha);
    }

    /**
     * Obtiene una lista de todos los eventos pendientes de aprobacion.
     * @return Devuelve la lista de todos los eventos pendientes de aprobacion.
     */
    public ArrayList<EventoModel> obtenerEventosPendientes() {
        return eventoRepository.obtenerEventosPendientes();
    }
}
