package app;

import dao.IProductoDAO;
import dao.exceptions.EntityNotFoundException;
import dao.mongo.ProductoDAO;
import model.Producto;
import model.Proveedor;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        
        // 1. Inicializar la conexión (se hace al instanciar el DAO por primera vez)
        IProductoDAO productoDAO = new ProductoDAO();
        
        System.out.println("--- PRUEBA CRUD MONGO CON POJOS ---");

        try {
            // Limpiar la colección para pruebas
            System.out.println("\n[Limpiando colección...]");
            long borrados = productoDAO.deleteAll();
            System.out.println("Se borraron " + borrados + " documentos.");

            // --- 2. CREATE ---
            System.out.println("\n[1. CREAR Producto...]");
            Proveedor prov1 = new Proveedor("Importadora Guti", "ventas@guti.com", "China");
            Producto laptop = new Producto(
                    "Laptop XYZ Pro", 
                    1499.99, 
                    50, 
                    prov1, 
                    Arrays.asList("electronica", "computadoras", "oficina")
            );
            
            Producto creado = productoDAO.create(laptop);
            System.out.println("Producto CREADO: " + creado);
            ObjectId idCreado = creado.get_id();

            // --- 3. READ (FindById) ---
            System.out.println("\n[2. LEER por ID: " + idCreado + "]");
            Optional<Producto> optProducto = productoDAO.findById(idCreado);
            
            if (optProducto.isPresent()) {
                Producto encontrado = optProducto.get();
                System.out.println("Producto ENCONTRADO: " + encontrado);

                // --- 4. UPDATE ---
                System.out.println("\n[3. ACTUALIZAR Producto...]");
                encontrado.setPrecio(1399.00); // Rebaja
                encontrado.setStock(encontrado.getStock() - 5); // Venta
                
                boolean actualizado = productoDAO.update(encontrado);
                System.out.println("¿Producto ACTUALIZADO? " + actualizado);
                
                // Volvemos a leer para verificar
                Producto actualizadoDB = productoDAO.findById(idCreado).get();
                System.out.println("Producto post-actualización: " + actualizadoDB);

            } else {
                System.out.println("ERROR: No se encontró el producto que acabamos de crear.");
            }
            
            // --- 5. LISTAR TODOS ---
            System.out.println("\n[4. LISTAR TODOS...]");
            // Creamos otro producto para tener más de uno
             Producto teclado = new Producto(
                    "Teclado Mecánico K88", 
                    89.50, 
                    200, 
                    prov1, 
                    Arrays.asList("electronica", "perifericos")
            );
            productoDAO.create(teclado);
            
            List<Producto> todos = productoDAO.findAll();
            System.out.println("Total de productos: " + todos.size());
            todos.forEach(p -> System.out.println("  -> " + p.getNombre()));

            // --- PRUEBA BÚSQUEDA ESPECÍFICA ---
            System.out.println("\n[Búsqueda por categoría 'electronica']");
            List<Producto> electronicos = productoDAO.findByCategoria("electronica");
            System.out.println("Encontrados: " + electronicos.size());
            
            // --- 6. DELETE ---
            System.out.println("\n[5. ELIMINAR por ID: " + idCreado + "]");
            boolean eliminado = productoDAO.deleteById(idCreado);
            System.out.println("¿Producto ELIMINADO? " + eliminado);

            // Verificar listando todos
            System.out.println("\n[Lista final...]");
            todos = productoDAO.findAll();
            System.out.println("Total de productos: " + todos.size());
            todos.forEach(p -> System.out.println("  -> " + p.getNombre()));

            // --- Prueba de Excepción ---
            System.out.println("\n[Prueba Error: Eliminar ID que no existe]");
            try {
                productoDAO.deleteById(idCreado); // Ya fue borrado
            } catch (EntityNotFoundException e) {
                System.out.println("Excepción capturada (CORRECTO): " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Ha ocurrido un error inesperado:");
            e.printStackTrace();
        }
    }
}