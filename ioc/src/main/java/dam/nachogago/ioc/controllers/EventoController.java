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

    /**
     * Obtiene una lista de todos los eventos registrados en la base de datos.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @return Devuelve la lista de todos los eventos registrados.
     */
    @GetMapping
    public ArrayList<EventoModel> obtenerEventos(@RequestHeader(value = "Token") String token){
        if(!jwtUtil.findTokenByValue(token)){
            throw new EventoException("Token invalido");
        }else{
            return eventoService.obtenerEventos();
        }
    }

    /**
     * Añade un nuevo evento a la base de datos.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param evento Datos del evento que queremos guardar en la bbdd.
     */
    @PostMapping()
    public void guardarEvento(@RequestHeader(value = "Token") String token,
                                     @RequestBody EventoModel evento){
        if(!jwtUtil.findTokenByValue(token)){
            throw new EventoException("Token invalido");
        }else{
            eventoService.guardarEvento(evento);
        }
    }

    /**
     * Obtiene una lista de todos los eventos sobre un libro concreto.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param isbn ISBN del libro del cual queremos los eventos.
     * @return Devuelve la lista de todos los eventos sobre el libro introducido.
     */
    @GetMapping("/libro")
    public ArrayList<EventoModel> obtenerEventosPorLibro(@RequestHeader(value = "Token") String token,
                                                         @RequestParam(value = "isbn") String isbn){
        if(!jwtUtil.findTokenByValue(token)){
            throw new EventoException("Token invalido");
        }else{
            return eventoService.obtenerEventosPorLibro(isbn);
        }
    }

    /**
     * Obtiene los datos de un evento concreto pasando el id por parametro.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param id ID del evento del que queremos los datos.
     * @return Devuelve los datos del evento.
     */
    @GetMapping("/{id}")
    public EventoModel obtenerEventoPorId(@RequestHeader(value = "Token") String token,
                                          @PathVariable(value = "id") int id){
        if(!jwtUtil.findTokenByValue(token)){
            throw new EventoException("Token invalido");
        }else{
            return eventoService.obtenerEventoPorId(id);
        }
    }

    /**
     * Aprueba un evento de la bbdd.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param idEvento ID del evento que queremos aprobar.
     * @param idAdmin ID del administrador que está aprobando el evento.
     * @return Devuelve true o false dependiendo de si ha podido aprobar el evento o no.
     */
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

    /**
     * Elimina un evento de la base de datos.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param idEvento ID del evento que se quiere eliminar de la bbdd.
     * @return Devuelve true o false dependiendo de si ha podido eliminar o no el evento.
     */
    @DeleteMapping("/{id}")
    public boolean eliminarEvento(@RequestHeader(value = "Token") String token,
                                  @PathVariable(value = "id") int idEvento){
        if(!jwtUtil.findTokenByValue(token)){
            throw new EventoException("Token invalido");
        }else{
            return eventoService.eliminarEvento(idEvento);
        }

    }

    /**
     * Obtiene una lista de todos los eventos aprobados de la bbdd.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @return Devuelve la lista de los eventos aprobados de la bbdd.
     */
    @GetMapping("/aprobados")
    public ArrayList<EventoModel> obtenerEventosAprobados(@RequestHeader(value = "Token") String token){
        if(!jwtUtil.findTokenByValue(token)){
            throw new EventoException("Token invalido");
        }else{
            return eventoService.obtenerEventosAprobados();
        }
    }

    /**
     * Obtiene una lista de todos los eventos pendientes de aprobacion.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @return Devuelve la lista de todos los eventos pendientes de aprobacion.
     */
    @GetMapping("/pendientes")
    public ArrayList<EventoModel> obtenerEventosPendientes(@RequestHeader(value = "Token") String token){
        if(!jwtUtil.findTokenByValue(token)){
            throw new EventoException("Token invalido");
        }else{
            return eventoService.obtenerEventosPendientes();
        }
    }

    /**
     * Obtiene una lista de todos los eventos en una fecha concreta.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param fecha Fecha de la que queremos los eventos.
     * @return Devuelve la lista de los eventos en la fecha deseada.
     */
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
