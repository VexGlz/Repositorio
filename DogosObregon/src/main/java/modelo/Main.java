package modelo;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Cliente;

public class Main {

    public static void main(String[] args) {
        // 1. Crear el EntityManagerFactory usando el nombre de tu persistence-unit
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DogosObregonPU");
        EntityManager em = emf.createEntityManager();

        try {
            // 2. Iniciar una transacción
            em.getTransaction().begin();

            // 3. Crear un nuevo cliente
            Cliente nuevoCliente = new Cliente();
            nuevoCliente.setNombre("Juan");
            nuevoCliente.setApPaterno("Pérez");
            nuevoCliente.setFechaNacimiento(new Date()); // Fecha de hoy como ejemplo

            // 4. Crear un nuevo pedido para ese cliente
            Pedido nuevoPedido = new Pedido();
            nuevoPedido.setFecha(new Date());
            nuevoPedido.setMetodoPago("Efectivo");
            nuevoPedido.setCliente(nuevoCliente); // Asociamos el pedido al cliente

            // 5. Persistir los objetos (guardarlos en la BD)
            // Gracias a CascadeType.ALL en la entidad Cliente, al guardar el cliente, se guardará también el pedido.
            em.persist(nuevoCliente);
            // Si no usaras Cascade, tendrías que guardar ambos:
            // em.persist(nuevoPedido);

            // 6. Confirmar la transacción
            em.getTransaction().commit();
            
            System.out.println("¡Cliente y pedido guardados exitosamente!");

        } catch (Exception e) {
            e.printStackTrace();
            // Si algo sale mal, revertir la transacción
            em.getTransaction().rollback();
        } finally {
            // 7. Cerrar el EntityManager y el Factory
            em.close();
            emf.close();
        }
    }
}