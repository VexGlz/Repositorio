package error.biblioteca_260305;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.util.List;

@Entity
public class Libro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_libro;

    private String titulo;
    private int anio_publicacion;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @ManyToMany
    @JoinTable(
        name = "libro_categoria",
        joinColumns = @JoinColumn(name = "id_libro"),
        inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private List<Categoria> categorias;

    // Constructores, getters y setters
    public Libro() {}
    public Libro(String titulo, int anio_publicacion) { this.titulo = titulo; this.anio_publicacion = anio_publicacion; }
    public int getId_libro() { return id_libro; }
    public void setId_libro(int id_libro) { this.id_libro = id_libro; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public int getAnio_publicacion() { return anio_publicacion; }
    public void setAnio_publicacion(int anio_publicacion) { this.anio_publicacion = anio_publicacion; }
    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }
    public List<Categoria> getCategorias() { return categorias; }
    public void setCategorias(List<Categoria> categorias) { this.categorias = categorias; }
}