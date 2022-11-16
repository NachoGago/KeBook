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

    @GetMapping
    public ArrayList<ReservaModel> obtenerReservas (@RequestHeader(value = "Token") String token){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return reservaService.obtenerReservas();
        }
    }

    @PostMapping()
    public void guardarReserva(@RequestHeader(value = "Token") String token,
                                       @RequestBody ReservaModel reserva){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            this.reservaService.guardarReserva(reserva);
        }
    }

    @GetMapping("/usuario")
    public ArrayList<ReservaModel> obtenerReservasPorUsuario(@RequestHeader(value = "Token") String token,
                                                             @RequestParam("idUsuario") int id_usuario){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return this.reservaService.obtenerReservasPorUsuario(id_usuario);
        }
    }

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

    @GetMapping("/recogido")
    public boolean comprobarReservaRecogida(@RequestHeader(value = "Token") String token,
                                            @RequestParam("idReserva") int id_reserva){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return this.reservaService.comprobarReservaRecogida(id_reserva);
        }
    }

    @GetMapping("/devuelto")
    public boolean comprobarReservaDevuelta(@RequestHeader(value = "Token") String token,
                                            @RequestParam("idReserva") int id_reserva){
        if (!jwtUtil.findTokenByValue(token)) {
            throw new UsuarioException("Token invalido");
        } else {
            return this.reservaService.comprobarReservaDevuelta(id_reserva);
        }
    }


}
