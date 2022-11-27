package dam.nachogago.ioc.controllers;

import dam.nachogago.ioc.exceptions.UsuarioException;
import dam.nachogago.ioc.models.ReservaModel;
import dam.nachogago.ioc.services.ReservaService;
import dam.nachogago.ioc.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    ReservaService reservaService;

    @Autowired
    JWTUtil jwtUtil;

    /**
     * Obtiene una lista de todas las reservas en la base de datos.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @return Devuelve una lista de todas las reservas en la base de datos.
     */
    @GetMapping
    public ArrayList<ReservaModel> obtenerReservas (@RequestHeader(value = "Token") String token){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return reservaService.obtenerReservas();
        }
    }

    /**
     * Guarda una nueva reserva en la base de datos.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param reserva Datos de la reserva que queremos guardar.
     */
    @PostMapping()
    public boolean guardarReserva(@RequestHeader(value = "Token") String token,
                               @RequestBody ReservaModel reserva){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return this.reservaService.guardarReserva(reserva);
        }
    }

    /**
     * Obtiene una lista de todas las reservas hechas por un usuario.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param id_usuario Id del usuario del que queremos obtener las reservas.
     * @return Devuelve una lista de todas las reservas hechas por un usuario.
     */
    @GetMapping("/usuario")
    public ArrayList<ReservaModel> obtenerReservasPorUsuario(@RequestHeader(value = "Token") String token,
                                                             @RequestParam("idUsuario") int id_usuario){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return this.reservaService.obtenerReservasPorUsuario(id_usuario);
        }
    }

    /**
     * Obtiene una lista de todas las reservas que ha hecho un usuario de un libro en concreto.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param isbn ISBN del libro del que queremos comprobar las reservas.
     * @param id_usuario Id del usuario del que queremos comprobar las reservas.
     * @return Devuelve la lista de todas las reservas que ha hecho el usuario de este libro.
     */
    @GetMapping("/{isbn}/usuario")
    public ArrayList<ReservaModel> obtenerReservasDeLibroPorUsuario(@RequestHeader(value = "Token") String token,
                                                                    @PathVariable("isbn") String isbn,
                                                                    @RequestParam("idUsuario") int id_usuario){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return this.reservaService.obtenerReservasDeLibroPorUsuario(isbn, id_usuario);
        }
    }

    /**
     * Comprueba la reserva ha sido recogida.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param id_reserva Id de la reserva que queremos comprobar.
     * @return Devuelve true o false dependiendo de si la reserva ha sido recogida.
     */
    @GetMapping("/recogido")
    public boolean comprobarReservaRecogida(@RequestHeader(value = "Token") String token,
                                            @RequestParam("idReserva") int id_reserva){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return this.reservaService.comprobarReservaRecogida(id_reserva);
        }
    }

    /**
     * Comprueba si la reserva ha sido devuelta.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param id_reserva Id de la reserva que queremos comprobar.
     * @return Devuelve true o false dependiendo de si la reserva ha sido devuelta.
     */
    @GetMapping("/devuelto")
    public boolean comprobarReservaDevuelta(@RequestHeader(value = "Token") String token,
                                            @RequestParam("idReserva") int id_reserva){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return this.reservaService.comprobarReservaDevuelta(id_reserva);
        }
    }

    /**
     * Confirma que la reserva ha sido recogida.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param id_reserva Id de la reserva que queremos confimar la recogida.
     */
    @PostMapping("/{idReserva}/recogido")
    public void confirmarRecogida(@RequestHeader(value = "Token") String token,
                                  @PathVariable("idReserva") int id_reserva){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            reservaService.confirmarRecogida(id_reserva);
        }
    }

    /**
     * Confirma que la reserva ha sido devuelta.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param id_reserva Id de la reserva que queremos confimar la devolucion.
     */
    @PostMapping("/{idReserva}/devuelto")
    public void confirmarDevolucion(@RequestHeader(value = "Token") String token,
                                    @PathVariable("idReserva") int id_reserva){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            reservaService.confirmarDevolucion(id_reserva);
        }
    }

    /**
     * Obtiene una lista de todas las reservas que se han hecho sobre un libro.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param isbn ISBN del libro del que queremos obtener las reservas.
     * @return Devuelve la lista de las reservas que se han hecho sobre el libro.
     */
    @GetMapping("/{isbn}")
    public ArrayList<ReservaModel> obtenerReservasPorLibro (@RequestHeader(value = "Token") String token,
                                                            @PathVariable("isbn") String isbn){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return reservaService.obtenerReservasPorLibro(isbn);
        }
    }


}
