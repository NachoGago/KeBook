package dam.nachogago.ioc.controllers;

import dam.nachogago.ioc.exceptions.UsuarioException;
import dam.nachogago.ioc.models.EscritorModel;
import dam.nachogago.ioc.services.EscritorService;
import dam.nachogago.ioc.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/escritor")
public class EscritorController {

    @Autowired
    EscritorService escritorService;
    @Autowired
    JWTUtil jwtUtil;

    /**
     * Permite ver una lista de todos los escritores que hay en la base de datos.
     * @return Devuelve una lista con todos los escritores.
     */
    @GetMapping()
    public ArrayList<EscritorModel> obtenerEscritores(@RequestHeader(value = "Token") String token){
        if(!jwtUtil.findTokenByValue(token)){
            throw new UsuarioException("Token invalido");
        }else{
            return escritorService.obtenerEscritores();
        }

    }

    /**
     * Permite guardar un nuevo escritor en la base de datos.
     * @param escritor Datos del escritor a guardar en la base de datos.
     * @return Devuelve los datos que se han guardado en la base de datos.
     */
    @PostMapping EscritorModel guardarEscritor(@RequestHeader(value = "Token") String token,
                                               @RequestBody EscritorModel escritor){

        if(!jwtUtil.findTokenByValue(token)){
            throw new UsuarioException("Token invalido");
        }else{
            return this.escritorService.guardarEscritor(escritor);
        }
    }

    /**
     * Permite ver la informacion del escritor con el id que pasa por parametro.
     * @param id Identificador del escritor del cual queremos la informacion.
     * @return Devuelve la informacion del escritor.
     */
    @GetMapping(path = "/{id}")
    public Optional<EscritorModel> obtenerEscritorPorId(@RequestHeader(value = "Token") String token,
                                                        @PathVariable("id") long id){
        if(!jwtUtil.findTokenByValue(token)){
            throw new UsuarioException("Token invalido");
        }else{
            return this.escritorService.obtenerPorId(id);
        }

    }

    /**
     * Permite eliminar un escritor de la base de datos.
     * @param id Identificador del escritor que se quiere eliminar.
     * @return Devuelve un booleano indicando si la operacion ha tenido exito.
     */
    @DeleteMapping(path = "/{id}")
    public boolean eliminarEscritorPorId(@RequestHeader(value = "Token") String token,
                                         @PathVariable("id") long id){
        if(!jwtUtil.findTokenByValue(token)){
            throw new UsuarioException("Token invalido");
        }else{
            return this.escritorService.eliminarEscritor(id);
        }

    }

    /**
     * Permite recuperar la informacion del escritor introduciendo su nombre.
     * @param nombre Nombre del escritor del que queremos la informacion.
     * @return Devuelve los datos del escritor.
     */
    @GetMapping("/query")
    public EscritorModel obtenerEscritorPorNombre(@RequestHeader(value = "Token") String token,
                                                  @RequestParam("nombre") String nombre){
        if(!jwtUtil.findTokenByValue(token)){
            throw new UsuarioException("Token invalido");
        }else{
            return this.escritorService.obtenerPorNombre(nombre);
        }

    }



}
