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
    private UsuarioModel usuario;

    @ManyToOne(optional = false, targetEntity = LibroModel.class)
    private LibroModel libro;

    @Column(nullable = false)
    private Date fecha_inicio;

    @Column(nullable = false)
    private Date fecha_fin;

    private boolean recogido;

    private boolean devuelto;

}
