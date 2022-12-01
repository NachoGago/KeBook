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

    @GetMapping()
    public ArrayList<ParticipantesModel> obtenerParticipantes(@RequestHeader(value = "Token") String token){
        if(!jwtUtil.findTokenByValue(token)){
            throw new ParticipantesException("Token invalido");
        }else{
            return participantesService.obtenerParticipantes();
        }
    }

    @PostMapping()
    public void guardarParticipante(@RequestHeader(value = "Token") String token,
                                    @RequestBody ParticipantesModel participante){
        if(!jwtUtil.findTokenByValue(token)){
            throw new ParticipantesException("Token invalido");
        }else{
            participantesService.guardarParticipante(participante);
        }
    }

    @DeleteMapping("/{id}")
    public boolean eliminarParticipante(@RequestHeader(value = "Token") String token,
                                        @PathVariable(value = "id") int id){
        if(!jwtUtil.findTokenByValue(token)){
            throw new ParticipantesException("Token invalido");
        }else{
            return participantesService.eliminarParticipante(id);
        }
    }

    @GetMapping("/usuario")
    public ArrayList<ParticipantesModel> obtenerParticipacionPorUsuario(@RequestHeader(value = "Token") String token,
                                                                        @RequestParam(value = "idUsuario") int idUsuario){
        if(!jwtUtil.findTokenByValue(token)){
            throw new ParticipantesException("Token invalido");
        }else{
            return participantesService.obtenerParticipacionPorUsuario(idUsuario);
        }
    }

    @GetMapping("/evento")
    public ArrayList<ParticipantesModel> obtenerParticipantesPorEvento(@RequestHeader(value = "Token") String token,
                                                                       @RequestParam(value = "idEvento") int idEvento){
        if(!jwtUtil.findTokenByValue(token)){
            throw new ParticipantesException("Token invalido");
        }else{
            return participantesService.obtenerParticipantesPorEvento(idEvento);
        }
    }

}
