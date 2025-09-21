/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.ecoactivistas.interfaces;

import com.mycompany.ecoactivistas.model.Problema;
import java.util.List;

/**
 *
 * @author martinbl
 */
public interface IProblemaDAO {
    int insertar(Problema problema);
    Problema obtenerPorId(int idProblema);
    List<Problema> obtenerTodos();
    List<Problema> obtenerTodosPorCliente(int idCliente);
    boolean actualizar(Problema problema);
    boolean eliminar(int idProblema);
}
