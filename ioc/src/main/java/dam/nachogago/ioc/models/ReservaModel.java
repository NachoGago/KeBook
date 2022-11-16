package dam.nachogago.ioc.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "reserva")
public class ReservaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @ManyToOne(optional = false, targetEntity = UsuarioModel.class)
    @JoinColumn(name = "id_usuario")
    private UsuarioModel usuario;

    @ManyToOne(optional = false, targetEntity = LibroModel.class)
    @JoinColumn(name = "isbn_libro")
    private LibroModel libro;

    @Column(nullable = false)
    private Date fecha_inicio;

    @Column(nullable = false)
    private Date fecha_fin;

    private boolean recogido;

    private boolean devuelto;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public LibroModel getLibro() {
        return libro;
    }

    public void setLibro(LibroModel libro) {
        this.libro = libro;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public boolean isRecogido() {
        return recogido;
    }

    public void setRecogido(boolean recogido) {
        this.recogido = recogido;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }
}
