package dam.nachogago.ioc.models;

import javax.persistence.*;

@Entity
@Table(name = "participantes")
public class ParticipantesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable = false)
    private int id;

    @ManyToOne(optional = false, targetEntity = UsuarioModel.class)
    @JoinColumn(name = "id_usuario")
    private UsuarioModel usuario;

    @ManyToOne(optional = false, targetEntity = EventoModel.class)
    @JoinColumn(name = "id_evento")
    private EventoModel evento;

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

    public EventoModel getEvento() {
        return evento;
    }

    public void setEvento(EventoModel evento) {
        this.evento = evento;
    }
}
