package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AmazonPU");
        EntityManager em = emf.createEntityManager();

        try {

            // 1. CREAR: Guardar un cliente, productos y un pedido completo

            System.out.println("--- INICIANDO PROCESO DE CREACIÓN ---");
            em.getTransaction().begin();

            // Cree dos productos para el catálogo
            Producto laptop = new Producto();
            laptop.setDescripcion("Laptop Gamer XYZ");
            laptop.setPrecio(25000.50);
            laptop.setStock(15);
            laptop.setCodigoBarras("750100100200");

            Producto teclado = new Producto();
            teclado.setDescripcion("Teclado Mecánico RGB");
            teclado.setPrecio(1800.00);
            teclado.setStock(50);
            teclado.setCodigoBarras("750100100300");

            em.persist(laptop);
            em.persist(teclado);

            // Creo un nuevo cliente
            Cliente nuevoCliente = new Cliente();
            nuevoCliente.setNombre("Ana");
            nuevoCliente.setApPaterno("García");
            nuevoCliente.setApMaterno("López");
            nuevoCliente.setFechaNacimiento(new Date()); // Fecha actual como ejemplo
            nuevoCliente.setTelefonos(Arrays.asList("6671234567", "6677654321"));
            nuevoCliente.setCorreos(Arrays.asList("ana.garcia@email.com"));

            // Creo un pedido para el cliente
            Pedido nuevoPedido = new Pedido();
            nuevoPedido.setFecha(new Date());
            nuevoPedido.setCliente(nuevoCliente); // Asociamos el pedido con el cliente

            // Añadi los productos al pedido (la relación N:M)
            List<PedidoProducto> detalles = new ArrayList<>();
            
            // Detalle para la laptop
            PedidoProducto detalleLaptop = new PedidoProducto();
            detalleLaptop.setPedido(nuevoPedido);
            detalleLaptop.setProducto(laptop);
            detalleLaptop.setId(new PedidoProductoId(nuevoPedido.getId(), laptop.getId())); // La ID se genera después
            detalleLaptop.setCantidad(1);
            detalleLaptop.setPrecioVenta(laptop.getPrecio()); // El precio al momento de la venta
            detalles.add(detalleLaptop);

            // Detalle para el teclado
            PedidoProducto detalleTeclado = new PedidoProducto();
            detalleTeclado.setPedido(nuevoPedido);
            detalleTeclado.setProducto(teclado);
            detalleTeclado.setId(new PedidoProductoId(nuevoPedido.getId(), teclado.getId()));
            detalleTeclado.setCantidad(2);
            detalleTeclado.setPrecioVenta(1750.00); 
            detalles.add(detalleTeclado);
            
            nuevoPedido.setDetalles(detalles);

            em.persist(nuevoCliente);

            em.getTransaction().commit();
            System.out.println("Cliente, productos y pedido creados exitosamente.");

            // 2. LEER: Buscar y mostrar la información guardada 
            System.out.println("\n--- INICIANDO PROCESO DE LECTURA ---");
            
            TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.nombre = 'Ana'", Cliente.class);
            Cliente clienteEncontrado = query.getSingleResult();

            System.out.println("Cliente encontrado: " + clienteEncontrado.getNombre() + " " + clienteEncontrado.getApPaterno());
            System.out.println("Teléfonos: " + clienteEncontrado.getTelefonos());

            for (Pedido pedido : clienteEncontrado.getPedidos()) {
                System.out.println("  -> Pedido ID: " + pedido.getId() + " con fecha " + pedido.getFecha());
                double totalCalculado = 0;
                for (PedidoProducto detalle : pedido.getDetalles()) {
                    System.out.println("    --> Producto: " + detalle.getProducto().getDescripcion() 
                                     + ", Cantidad: " + detalle.getCantidad() 
                                     + ", Precio Venta: $" + detalle.getPrecioVenta());
                    totalCalculado += detalle.getCantidad() * detalle.getPrecioVenta();
                }
                System.out.println("  TOTAL CALCULADO DEL PEDIDO: $" + totalCalculado);
            }

            // 3. ACTUALIZAR: Modificar un dato y guardarlo    
            System.out.println("\n--- INICIANDO PROCESO DE ACTUALIZACIÓN ---");
            em.getTransaction().begin();
            
            // Buscamos el producto "Laptop"
            Producto productoParaActualizar = em.find(Producto.class, laptop.getId());
            System.out.println("Stock anterior de la Laptop: " + productoParaActualizar.getStock());
            productoParaActualizar.setStock(10); 
            
            em.merge(productoParaActualizar); 
            em.getTransaction().commit();
            
            System.out.println(" Stock de la Laptop actualizado a: " + productoParaActualizar.getStock());


    
            //4. ELIMINAR: Borrar un cliente (y sus pedidos en cascada) 
            System.out.println("\n--- INICIANDO PROCESO DE ELIMINACIÓN ---");
            em.getTransaction().begin();
            
            Cliente clienteAEliminar = em.find(Cliente.class, clienteEncontrado.getId());
            System.out.println("Eliminando al cliente: " + clienteAEliminar.getNombre());

            em.remove(clienteAEliminar);
            
            em.getTransaction().commit();
            System.out.println(" Cliente y sus datos asociados eliminados correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
            emf.close();
        }
    }
}