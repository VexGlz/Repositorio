package error.biblioteca_260305;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
public class Autor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_autor;

    private String nombre;
    private String nacionalidad;

    @OneToMany(mappedBy = "autor")
    private List<Libro> libros;

    // Constructores, getters y setters
    public Autor() {}
    public Autor(String nombre, String nacionalidad) { this.nombre = nombre; this.nacionalidad = nacionalidad; }
    public int getId_autor() { return id_autor; }
    public void setId_autor(int id_autor) { this.id_autor = id_autor; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    public List<Libro> getLibros() { return libros; }
    public void setLibros(List<Libro> libros) { this.libros = libros; }
}