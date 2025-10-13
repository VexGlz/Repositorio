package modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DetallePedidoId implements Serializable {

    @Column(name = "id_pedido_fk")
    private int pedidoId;

    @Column(name = "id_hotdog_fk")
    private int hotdogId;

    public DetallePedidoId() {}

    public DetallePedidoId(int pedidoId, int hotdogId) {
        this.pedidoId = pedidoId;
        this.hotdogId = hotdogId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedidoId, hotdogId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DetallePedidoId that = (DetallePedidoId) obj;
        return pedidoId == that.pedidoId && hotdogId == that.hotdogId;
    }
}