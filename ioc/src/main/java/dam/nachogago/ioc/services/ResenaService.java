package dam.nachogago.ioc.services;

import dam.nachogago.ioc.exceptions.ReservaException;
import dam.nachogago.ioc.models.ResenaModel;
import dam.nachogago.ioc.exceptions.ResenaException;
import dam.nachogago.ioc.repositories.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ResenaService {

    @Autowired
    ResenaRepository resenaRepository;


    public ArrayList<ResenaModel> obtenerResenas() {
        return (ArrayList<ResenaModel>) resenaRepository.findAll();
    }


    public void guardarResena(ResenaModel resena) {
        resenaRepository.save(resena);
    }

    public ArrayList<ResenaModel> obtenerResenasUsuario(int id_usuario) {
        return resenaRepository.obtenerResenasUsuario(id_usuario).orElseThrow(()->new ReservaException("El usuario no tiene ninguna reseña."));
    }

    public ArrayList<ResenaModel> obtenerResenaDeLibroPorUsuario(String isbn, int id_usuario) {
        return resenaRepository.obtenerResenaDeLibroPorUsuario(isbn, id_usuario).orElseThrow(()->new ReservaException("El usuario no ha hecho reseña de este libro."));
    }

    public boolean eliminarResena(int id_resena) {
        ResenaModel resena = resenaRepository.obtenerResenaPorId(id_resena).orElseThrow(()->new ReservaException("No existe reseña con este id."));
        if (resena == null){
            return false;
        }else{
            resenaRepository.eliminarResena(id_resena);
            return true;
        }
    }

    public ResenaModel obtenerResenaPorId(int id_resena) {
        return resenaRepository.obtenerResenaPorId(id_resena).orElseThrow(()->new ReservaException("No existe reseña con este id."));
    }
}
