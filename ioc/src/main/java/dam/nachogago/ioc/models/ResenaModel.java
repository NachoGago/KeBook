package dam.nachogago.ioc.models;

import javax.persistence.*;

@Entity
@Table(name = "resena")
public class ResenaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @ManyToOne(optional = false, targetEntity = LibroModel.class)
    @JoinColumn(name = "isbn_libro")
    private LibroModel libro;

    @ManyToOne(optional = false, targetEntity = UsuarioModel.class)
    @JoinColumn(name = "id_usuario")
    private UsuarioModel usuario;

    @Column(nullable = false)
    String resena;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LibroModel getLibro() {
        return libro;
    }

    public void setLibro(LibroModel libro) {
        this.libro = libro;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public String getResena() {
        return resena;
    }

    public void setResena(String resena) {
        this.resena = resena;
    }
}
