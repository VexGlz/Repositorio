package modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PedidoProductoId implements Serializable {

    @Column(name = "id_pedido_fk")
    private int pedidoId;

    @Column(name = "id_producto_fk")
    private int productoId;

    public PedidoProductoId() {}

    public PedidoProductoId(int pedidoId, int productoId) {
        this.pedidoId = pedidoId;
        this.productoId = productoId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedidoId, productoId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PedidoProductoId that = (PedidoProductoId) obj;
        return pedidoId == that.pedidoId && productoId == that.productoId;
    }
}