package dam.nachogago.ioc.services;

import dam.nachogago.ioc.exceptions.ReservaException;
import dam.nachogago.ioc.models.ReservaModel;
import dam.nachogago.ioc.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    ReservaRepository reservaRepository;

    public ArrayList<ReservaModel> obtenerReservas() {
        return (ArrayList<ReservaModel>) reservaRepository.findAll();
    }

    public void guardarReserva(ReservaModel reserva) {
        reservaRepository.save(reserva);
    }

    public ArrayList<ReservaModel> obtenerReservasPorUsuario(int id_usuario) {
        return reservaRepository.obtenerReservasPorUsuario(id_usuario);
    }

    public ArrayList<ReservaModel> obtenerReservasDeLibroPorUsuario(String isbn, int id_usuario) {
        return reservaRepository.obtenerReservasDeLibroPorUsuario(isbn, id_usuario).orElseThrow(()->new ReservaException("El usuario no ha reservado este libro."));
    }

    public boolean comprobarReservaRecogida(int id_reserva) {
        return reservaRepository.comprobarReservaRecogida(id_reserva);
    }

    public boolean comprobarReservaDevuelta(int id_reserva) {
        return reservaRepository.comprobarReservaDevuelta(id_reserva);
    }
}
