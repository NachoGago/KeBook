package dam.nachogago.ioc.controllers;

import dam.nachogago.ioc.exceptions.EventoException;
import dam.nachogago.ioc.models.EventoModel;
import dam.nachogago.ioc.services.EventoService;
import dam.nachogago.ioc.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;

@RestController
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    EventoService eventoService;
    @Autowired
    JWTUtil jwtUtil;

    @GetMapping
    public ArrayList<EventoModel> obtenerEventos(@RequestHeader(value = "Token") String token){
        if(!jwtUtil.findTokenByValue(token)){
            throw new EventoException("Token invalido");
        }else{
            return eventoService.obtenerEventos();
        }
    }

    @PostMapping()
    public void guardarEvento(@RequestHeader(value = "Token") String token,
                                     @RequestBody EventoModel evento){
        if(!jwtUtil.findTokenByValue(token)){
            throw new EventoException("Token invalido");
        }else{
            eventoService.guardarEvento(evento);
        }
    }

    @GetMapping("/libro")
    public ArrayList<EventoModel> obtenerEventosPorLibro(@RequestHeader(value = "Token") String token,
                                                         @RequestParam(value = "isbn") String isbn){
        if(!jwtUtil.findTokenByValue(token)){
            throw new EventoException("Token invalido");
        }else{
            return eventoService.obtenerEventosPorLibro(isbn);
        }
    }

    @GetMapping("/{id}")
    public EventoModel obtenerEventoPorId(@RequestHeader(value = "Token") String token,
                                          @PathVariable(value = "id") int id){
        if(!jwtUtil.findTokenByValue(token)){
            throw new EventoException("Token invalido");
        }else{
            return eventoService.obtenerEventoPorId(id);
        }
    }

    @PostMapping("/aprobar")
    public boolean aprobarEvento(@RequestHeader(value = "Token") String token,
                                 @RequestParam(value = "idEvento") int idEvento,
                                 @RequestParam(value = "idAdmin") int idAdmin){
        if(!jwtUtil.findTokenByValue(token)){
            throw new EventoException("Token invalido");
        }else{
            return eventoService.aprobarEvento(idEvento, idAdmin);
        }
    }

    @DeleteMapping("/{id}")
    public boolean eliminarEvento(@RequestHeader(value = "Token") String token,
                                  @PathVariable(value = "id") int idEvento){
        if(!jwtUtil.findTokenByValue(token)){
            throw new EventoException("Token invalido");
        }else{
            return eventoService.eliminarEvento(idEvento);
        }

    }

    @GetMapping("/aprobados")
    public ArrayList<EventoModel> obtenerEventosAprobados(@RequestHeader(value = "Token") String token){
        if(!jwtUtil.findTokenByValue(token)){
            throw new EventoException("Token invalido");
        }else{
            return eventoService.obtenerEventosAprobados();
        }
    }

    @GetMapping("/pendientes")
    public ArrayList<EventoModel> obtenerEventosPendientes(@RequestHeader(value = "Token") String token){
        if(!jwtUtil.findTokenByValue(token)){
            throw new EventoException("Token invalido");
        }else{
            return eventoService.obtenerEventosPendientes();
        }
    }

    @GetMapping("/fecha")
    public ArrayList<EventoModel> obtenerEventosPorFecha(@RequestHeader(value = "Token") String token,
                                                         @RequestParam(value = "fecha") Date fecha){
        if(!jwtUtil.findTokenByValue(token)){
            throw new EventoException("Token invalido");
        }else{
            return eventoService.obtenerEventosPorFecha(fecha);
        }
    }


}
