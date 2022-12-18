package dam.nachogago.ioc.services;

import dam.nachogago.ioc.exceptions.ReservaException;
import dam.nachogago.ioc.models.LibroModel;
import dam.nachogago.ioc.models.ReservaModel;
import dam.nachogago.ioc.repositories.LibroRepository;
import dam.nachogago.ioc.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    ReservaRepository reservaRepository;
    @Autowired
    LibroRepository libroRepository;

    /**
     * Obtiene una lista de todas las reservas en la base de datos.
     * @return Devuelve una lista de todas las reservas en la base de datos.
     */
    public ArrayList<ReservaModel> obtenerReservas() {
        return (ArrayList<ReservaModel>) reservaRepository.findAll();
    }

    /**
     * Guarda una nueva reserva en la base de datos.
     *
     * @param reserva Datos de la reserva que queremos guardar.
     * @return Devuelve true o false dependiendo de si puede guardar una nueva reserva.
     */
    public boolean guardarReserva(ReservaModel reserva) {
        ArrayList<LibroModel> libro = libroRepository.comprobarDisponibilidad(reserva.getLibro().getIsbn());
        if (!libro.isEmpty()){
            reservaRepository.save(reserva);
            libroRepository.cambiarDisponibilidadAFalse(reserva.getLibro().getIsbn());
            return true;
        }else{
            return false;
        }
    }

    /**
     * Obtiene una lista de todas las reservas hechas por un usuario.
     * @param id_usuario Id del usuario del que queremos obtener las reservas.
     * @return Devuelve una lista de todas las reservas hechas por un usuario.
     */
    public ArrayList<ReservaModel> obtenerReservasPorUsuario(int id_usuario) {
        return reservaRepository.obtenerReservasPorUsuario(id_usuario);
    }

    /**
     * Obtiene una lista de todas las reservas que ha hecho un usuario de un libro en concreto.
     * @param isbn ISBN del libro del que queremos comprobar las reservas.
     * @param id_usuario Id del usuario del que queremos comprobar las reservas.
     * @return Devuelve la lista de todas las reservas que ha hecho el usuario de este libro.
     */
    public ArrayList<ReservaModel> obtenerReservasDeLibroPorUsuario(String isbn, int id_usuario) {
        return reservaRepository.obtenerReservasDeLibroPorUsuario(isbn, id_usuario).orElseThrow(()->new ReservaException("El usuario no ha reservado este libro."));
    }

    /**
     * Comprueba la reserva ha sido recogida.
     * @param id_reserva Id de la reserva que queremos comprobar.
     * @return Devuelve true o false dependiendo de si la reserva ha sido recogida.
     */
    public boolean comprobarReservaRecogida(int id_reserva) {
        return reservaRepository.comprobarReservaRecogida(id_reserva);
    }

    /**
     * Comprueba si la reserva ha sido devuelta.
     * @param id_reserva Id de la reserva que queremos comprobar.
     * @return Devuelve true o false dependiendo de si la reserva ha sido devuelta.
     */
    public boolean comprobarReservaDevuelta(int id_reserva) {
        return reservaRepository.comprobarReservaDevuelta(id_reserva);
    }

    /**
     * Confirma que la reserva ha sido recogida.
     * @param id_reserva Id de la reserva que queremos confimar la recogida.
     */
    public void confirmarRecogida(int id_reserva) {
        reservaRepository.confirmarRecogida(id_reserva, true);
    }

    /**
     * Confirma que la reserva ha sido devuelta.
     * @param id_reserva Id de la reserva que queremos confimar la devolucion.
     */
    public void confirmarDevolucion(int id_reserva) {
        reservaRepository.confirmarDevolucion(id_reserva, true);
        Optional<ReservaModel> reserva = reservaRepository.findById(id_reserva);
        if (!reserva.isEmpty()){
            libroRepository.cambiarDisponibilidadATrue(reserva.get().getLibro().getIsbn());
        }
    }

    /**
     * Obtiene una lista de todas las reservas que se han hecho sobre un libro.
     * @param isbn ISBN del libro del que queremos obtener las reservas.
     * @return Devuelve la lista de las reservas que se han hecho sobre el libro.
     */
    public ArrayList<ReservaModel> obtenerReservasPorLibro(String isbn) {
        return reservaRepository.obtenerReservasPorLibro(isbn);
    }
}
