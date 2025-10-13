package modelo;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos_productos")
public class PedidoProducto {

    @EmbeddedId
    private PedidoProductoId id;

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "id_pedido_fk")
    private Pedido pedido;

    @ManyToOne
    @MapsId("productoId")
    @JoinColumn(name = "id_producto_fk")
    private Producto producto;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precio_venta")
    private double precioVenta;

    public PedidoProducto() {}

    public PedidoProductoId getId() {
        return id;
    }

    public void setId(PedidoProductoId id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }


}