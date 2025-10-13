package modelo;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {

    @EmbeddedId
    private DetallePedidoId id;

    // Relación con Pedido
    @ManyToOne
    @MapsId("pedidoId") 
    @JoinColumn(name = "id_pedido_fk")
    private Pedido pedido;

    // Relación con HotDog
    @ManyToOne
    @MapsId("hotdogId")
    @JoinColumn(name = "id_hotdog_fk")
    private HotDog hotdog;

    @Column(name = "cantidad")
    private int cantidad;

    // Constructores, Getters y Setters
    public DetallePedido() {}

}