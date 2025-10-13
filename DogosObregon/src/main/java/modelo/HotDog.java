package modelo;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "hotdogs")
public class HotDog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hotdog")
    private int id;

    @Column(name = "nombre", length = 100, unique = true)
    private String nombre;

    @Column(name = "precio")
    private double precio;

    @OneToMany(mappedBy = "hotdog")
    private List<DetallePedido> detallesPedido;

    // Constructores, Getters y Setters
    public HotDog() {}
}