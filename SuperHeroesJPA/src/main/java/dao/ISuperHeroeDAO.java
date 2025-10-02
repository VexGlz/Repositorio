/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.SuperHeroe;
import java.util.List;

/**
 *
 * @author Erick
 */
public interface ISuperHeroeDAO {
    
    void insertar(SuperHeroe e);
    
    void actualizar(SuperHeroe e);
    
    void eliminar(Long id);
    
    SuperHeroe buscar(Long id);
    
    List<SuperHeroe> listar();
    
}
