package com.mycompany.ecoactivistas.controller;

import com.mycompany.ecoactivistas.dao.ClienteDAO;
import com.mycompany.ecoactivistas.interfaces.IClienteDAO;
import com.mycompany.ecoactivistas.model.Cliente;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ClienteController {

    private final IClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    // Agregar un cliente con validaciones
    public boolean agregarCliente(String nombre, String direccion, String telefono) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("El nombre del cliente no puede estar vacío.");
            return false;
        }
        if (direccion == null) {
            direccion = "";
        }
        if (telefono == null) {
            telefono = "";
        }
        
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre.trim());
        cliente.setDireccion(direccion.trim());
        cliente.setTelefonos(telefono.trim());

        return clienteDAO.insertar(cliente);
    }

    // Obtener cliente por ID
    public Cliente obtenerCliente(int idCliente) {
        if (idCliente <= 0) {
            System.err.println("ID de cliente inválido.");
            return null;
        }
        return clienteDAO.obtenerPorId(idCliente);
    }

    // Obtener todos los clientes
    public List<Cliente> listarClientes() {
        return clienteDAO.obtenerTodos();
    }

    // Actualizar cliente con validaciones
    public boolean actualizarCliente(int idCliente, String nombre, String direccion, String telefono) {
        if (idCliente <= 0) {
            System.err.println("ID de cliente inválido.");
            return false;
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("El nombre del cliente no puede estar vacío.");
            return false;
        }
        if (direccion == null) {
            direccion = "";
        }
        if (telefono == null) {
            telefono = "";
        }

        Cliente cliente = new Cliente();
        cliente.setIdCliente(idCliente);
        cliente.setNombre(nombre.trim());
        cliente.setDireccion(direccion.trim());
        cliente.setTelefonos(telefono.trim());
        
        return clienteDAO.actualizar(cliente);
    }

    // Eliminar cliente
    public boolean eliminarCliente(int idCliente) {
        if (idCliente <= 0) {
            System.err.println("ID de cliente inválido.");
            return false;
        }
        return clienteDAO.eliminar(idCliente);
    }

    // Obtener tabla completa de clientes para JTable
    public DefaultTableModel obtenerTablaClientes() {
        String[] columnas = {"ID", "NOMBRE", "DIRECCIÓN", "TELÉFONO", "CORREO"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<Cliente> lista = clienteDAO.obtenerTodos();
        for (Cliente c : lista) {
            modelo.addRow(new Object[]{
                c.getIdCliente(),
                c.getNombre(),
                c.getDireccion(),
                c.getTelefonos(),
            });
        }
        return modelo;
    }

    // Obtener tabla de clientes por filtro
    public DefaultTableModel obtenerTablaClientesPorFiltro(String filtro) {
        String[] columnas = {"ID", "NOMBRE", "DIRECCIÓN", "TELÉFONO", "CORREO"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<Cliente> lista = clienteDAO.obtenerTodosPorFiltro(filtro);
        for (Cliente c : lista) {
            modelo.addRow(new Object[]{
                c.getIdCliente(),
                c.getNombre(),
                c.getDireccion(),
                c.getTelefonos(),
            });
        }
        return modelo;
    }
    
    public DefaultTableModel obtenerTablaClientesPorFiltroModal(String filtro) {
    String[] columnas = {"ID", "NOMBRE"};
    DefaultTableModel modelo = new DefaultTableModel(null, columnas);
    List<Cliente> lista = clienteDAO.obtenerTodosPorFiltroModal(filtro); // DAO debe tener este método
    for (Cliente c : lista) {
        modelo.addRow(new Object[]{
            c.getIdCliente(),
            c.getNombre()
        });
    }
    return modelo;
}
    
}
