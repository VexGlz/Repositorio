package error.asig09bda_260305;

import dao.EmpleadoDAO;
import java.util.Date;
import java.util.List;
import modelo.Empleado;
import modelo.EstatusEmpleado;
import util.JPAUtil;

public class JPAEmpresa {

    public static void main(String[] args) {
        EmpleadoDAO dao = new EmpleadoDAO();

        // INSERTAR EMPLEADOS
        System.out.println("--- INSERTANDO NUEVOS EMPLEADOS ---");
        Empleado emp1 = new Empleado("Juan Pérez", "juan.perez@empresa.com", 30000.0, EstatusEmpleado.ACTIVO, new Date());
        Empleado emp2 = new Empleado("Ana Gómez", "ana.gomez@empresa.com", 35000.0, EstatusEmpleado.ACTIVO, new Date());
        Empleado emp3 = new Empleado("Carlos Ruiz", "carlos.ruiz@empresa.com", 28000.0, EstatusEmpleado.INACTIVO, new Date());

        dao.insertar(emp1);
        dao.insertar(emp2);
        dao.insertar(emp3);
        System.out.println("Empleados insertados.");

        // LISTAR TODOS LOS EMPLEADOS
        System.out.println("\n--- LISTADO DE EMPLEADOS ---");
        List<Empleado> empleados = dao.listar();
        empleados.forEach(System.out::println);

        // MODIFICAR UN EMPLEADO
        System.out.println("\n--- MODIFICANDO EMPLEADO CON ID 2 ---");
        Empleado empleadoAModificar = dao.buscar(2L); // Buscamos a Ana Gómez
        if (empleadoAModificar != null) {
            empleadoAModificar.setEmail("ana.gomez.updated@empresa.com");
            dao.actualizar(empleadoAModificar);
            System.out.println("Empleado actualizado.");
        }
        
        System.out.println("\n--- LISTADO DESPUÉS DE MODIFICAR ---");
        dao.listar().forEach(System.out::println);

        // USAR LA FUNCIÓN TRANSACCIONAL AUMENTAR SALARIO
        System.out.println("\n--- AUMENTANDO SALARIO AL EMPLEADO CON ID 1 (10%) ---");
        Long idEmpleadoSalario = 1L;
        System.out.println("Salario ANTES: " + dao.buscar(idEmpleadoSalario).getSalario());
        boolean exito = dao.aumentarSalario(idEmpleadoSalario, 10.0); // Aumento del 10%
        if (exito) {
            System.out.println("Salario aumentado con éxito.");
            System.out.println("Salario DESPUÉS: " + dao.buscar(idEmpleadoSalario).getSalario());
        } else {
            System.out.println("No se pudo aumentar el salario.");
        }
        
        System.out.println("\n--- LISTADO DESPUÉS DE AUMENTO DE SALARIO ---");
        dao.listar().forEach(System.out::println);

        // ELIMINAR UN EMPLEADO
        System.out.println("\n--- ELIMINANDO EMPLEADO CON ID 3 ---");
        dao.eliminar(3L); // Eliminamos a Carlos Ruiz
        System.out.println("Empleado eliminado.");

        // ISTAR FINALMENTE
        System.out.println("\n--- LISTADO FINAL DE EMPLEADOS ---");
        dao.listar().forEach(System.out::println);
        
        // CERRAR CONEXIÓN
        JPAUtil.shutdown();
        System.out.println("\nConexión cerrada. Programa finalizado.");
    }
}