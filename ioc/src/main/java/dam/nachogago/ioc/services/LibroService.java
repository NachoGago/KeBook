package dam.nachogago.ioc.services;

import dam.nachogago.ioc.exceptions.LibroException;
import dam.nachogago.ioc.models.LibroModel;
import dam.nachogago.ioc.repositories.EscritorRepository;
import dam.nachogago.ioc.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LibroService {
    @Autowired
    LibroRepository libroRepository;
    @Autowired
    EscritorRepository escritorRepository;

    public ArrayList<LibroModel> obtenerLibros() {
        return (ArrayList<LibroModel>) libroRepository.findAll();
    }

    public LibroModel guardarLibro(LibroModel libro) {
        return libroRepository.save(libro);
    }

    public LibroModel obtenerPorIsbn(String isbn){
        return libroRepository.findById(isbn).orElseThrow(()-> new LibroException("Libro no encontrado."));
    }

    public LibroModel obtenerPorTitulo(String nombre){
        return libroRepository.findByTitulo(nombre).orElseThrow(()-> new LibroException("No s'ha trobat el llibre."));
    }

    public ArrayList<LibroModel> obtenerPorGenero(String genero){
        return libroRepository.findByGenero(genero);
    }

    public ArrayList<LibroModel> obtenerPorAutor(String nombreAutor){
        long id_autor = escritorRepository.findByNombre(nombreAutor).getId();
        return libroRepository.findByAutor(id_autor);
    }

    public ArrayList<LibroModel> obtenerDisponibles() {
        return libroRepository.obtenerDisponibles();
    }

    public boolean comprobarDisponibilidad(String isbn) {
        ArrayList<LibroModel> list = libroRepository.comprobarDisponibilidad(isbn);
        if(list.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
}
