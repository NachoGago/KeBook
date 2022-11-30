package dam.nachogago.ioc.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "evento")
public class EventoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @ManyToOne(optional = false, targetEntity = UsuarioModel.class)
    @JoinColumn(name = "id_proponente")
    private UsuarioModel proponente;

    @ManyToOne(optional = false, targetEntity = LibroModel.class)
    @JoinColumn(name = "isbn_libro")
    private LibroModel libro;

    @Column(nullable = false, name = "fecha_evento")
    private Date fecha;

    private boolean isAproved;

    @ManyToOne(targetEntity = UsuarioModel.class)
    @JoinColumn(name = "id_admin_aprobador")
    private UsuarioModel aprobador;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UsuarioModel getProponente() {
        return proponente;
    }

    public void setProponente(UsuarioModel proponente) {
        this.proponente = proponente;
    }

    public LibroModel getLibro() {
        return libro;
    }

    public void setLibro(LibroModel libro) {
        this.libro = libro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isAproved() {
        return isAproved;
    }

    public void setAproved(boolean aproved) {
        isAproved = aproved;
    }

    public UsuarioModel getAprobador() {
        return aprobador;
    }

    public void setAprobador(UsuarioModel aprobador) {
        this.aprobador = aprobador;
    }
}
