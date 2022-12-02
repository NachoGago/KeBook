package dam.nachogago.ioc.controllers;

import dam.nachogago.ioc.exceptions.ParticipantesException;
import dam.nachogago.ioc.models.ParticipantesModel;
import dam.nachogago.ioc.services.ParticipantesService;
import dam.nachogago.ioc.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/participante")
public class ParticipantesController {

    @Autowired
    ParticipantesService participantesService;
    @Autowired
    JWTUtil jwtUtil;

    /**
     * Obtiene una lista de todos los participantes de los distintos eventos de la bbdd.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @return Devuelve la lista de todos los participantes de los distintos eventos.
     */
    @GetMapping()
    public ArrayList<ParticipantesModel> obtenerParticipantes(@RequestHeader(value = "Token") String token){
        if(!jwtUtil.findTokenByValue(token)){
            throw new ParticipantesException("Token invalido");
        }else{
            return participantesService.obtenerParticipantes();
        }
    }

    /**
     * Guarda un nuevo participante en la base de datos.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param participante Datos del participante (id del usuario e id del evento).
     */
    @PostMapping()
    public void guardarParticipante(@RequestHeader(value = "Token") String token,
                                    @RequestBody ParticipantesModel participante){
        if(!jwtUtil.findTokenByValue(token)){
            throw new ParticipantesException("Token invalido");
        }else{
            participantesService.guardarParticipante(participante);
        }
    }

    /**
     * Elimina un participante de la bbdd.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param id ID del participante que se quiere eliminar.
     * @return Devuelve true o false dependiendo de si ha podido eliminar el participante o no.
     */
    @DeleteMapping("/{id}")
    public boolean eliminarParticipante(@RequestHeader(value = "Token") String token,
                                        @PathVariable(value = "id") int id){
        if(!jwtUtil.findTokenByValue(token)){
            throw new ParticipantesException("Token invalido");
        }else{
            return participantesService.eliminarParticipante(id);
        }
    }

    /**
     * Obtiene una lista de todos los eventos en los que ha participado un usuario.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param idUsuario ID del usuario del que queremos la participacion.
     * @return Devuelve la lista de los eventos en los que ha participado el usuario.
     */
    @GetMapping("/usuario")
    public ArrayList<ParticipantesModel> obtenerParticipacionPorUsuario(@RequestHeader(value = "Token") String token,
                                                                        @RequestParam(value = "idUsuario") int idUsuario){
        if(!jwtUtil.findTokenByValue(token)){
            throw new ParticipantesException("Token invalido");
        }else{
            return participantesService.obtenerParticipacionPorUsuario(idUsuario);
        }
    }

    /**
     * Obtiene una lista de todos los participantes que tenga un evento en concreto.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param idEvento ID del evento del que queremos comprobar los participantes.
     * @return Devuelve la lista de los participantes del evento.
     */
    @GetMapping("/evento")
    public ArrayList<ParticipantesModel> obtenerParticipantesPorEvento(@RequestHeader(value = "Token") String token,
                                                                       @RequestParam(value = "idEvento") int idEvento){
        if(!jwtUtil.findTokenByValue(token)){
            throw new ParticipantesException("Token invalido");
        }else{
            return participantesService.obtenerParticipantesPorEvento(idEvento);
        }
    }

    /**
     * Comprueba si un usuario esta apuntado a un evento en concreto.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param idEvento ID del evento.
     * @param idUsuario ID del usuario que queremos comprobar.
     * @return Devuelve true o false dependiendo de si el usuario esta apuntado o no al evento.
     */
    @GetMapping("/comprobar")
    public boolean comprobarParticipante(@RequestHeader(value = "Token") String token,
                                         @RequestParam(value = "idEvento") int idEvento,
                                         @RequestParam(value = "idUsuario") int idUsuario){
        if(!jwtUtil.findTokenByValue(token)){
            throw new ParticipantesException("Token invalido");
        }else{
            return participantesService.comprobarParticipante(idUsuario, idEvento);
        }
    }

    /**
     * Obtiene los datos de un participante concreto.
     * @param token Codigo para verificar que el usuario esta autorizado para hacer la consulta.
     * @param idUsuario ID del usuario.
     * @param idEvento ID del evento.
     * @return Devuelve los datos del participante.
     */
    @GetMapping("/{idUsuario}/{idEvento}")
    public ParticipantesModel obtenerParticipante(@RequestHeader(value = "Token") String token,
                                                  @PathVariable(value = "idUsuario") int idUsuario,
                                                  @PathVariable(value = "idEvento") int idEvento){
        if(!jwtUtil.findTokenByValue(token)){
            throw new ParticipantesException("Token invalido");
        }else{
            return participantesService.obtenerParticipante(idUsuario, idEvento).orElseThrow(()-> new ParticipantesException("No existe este participante."));
        }
    }

}
