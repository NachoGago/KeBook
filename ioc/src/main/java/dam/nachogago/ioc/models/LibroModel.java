package dam.nachogago.ioc.models;

import javax.persistence.*;

@Entity
@Table(name = "libro")
public class LibroModel {
    @Id
    @Column(unique = true, nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String titulo;

    @ManyToOne(optional = false, targetEntity = EscritorModel.class)
    private EscritorModel autor;

    private String sinopsis;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private boolean disponible;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public EscritorModel getAutor() {
        return autor;
    }

    public void setAutor(EscritorModel autor) {
        this.autor = autor;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
