/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package error.superheroesjpa;

import dao.ISuperHeroeDAO;
import dao.SuperHeroeDAO;
import entity.*;

/**
 *
 * @author Erick
 */
public class SuperHeroesJPA {

    public static void main(String[] args) {
        ISuperHeroeDAO dao = new SuperHeroeDAO();
        
        SuperHeroe spiderman = new SuperHeroe(
            "Spider-Man",
            Universo.MARVEL,
            new IdentidadSecreta("Peter Parker","New York", "Fotografo"),
            "Un gran poder conlleva a una gran responsabilidad"
        );
        
        SuperHeroe batman = new SuperHeroe(
            "Batman",
            Universo.DC,
            new IdentidadSecreta("Bruce Wayne","Gotham", "Empresario"),
            "Soy la venganza"
        );
        
        SuperHeroe goku = new SuperHeroe(
            "Goku",
            Universo.INDEPENDIENTE,
            new IdentidadSecreta("Kakarotto","Planeta Tierra", "Guerrero"),
            "KAMEHAMEHA!!!!"
        );
                    
        
        dao.insertar(spiderman);
        dao.insertar(batman);
        dao.insertar(goku);
        
        System.out.println("Lista inicial de superheroes");
        dao.listar().forEach(h ->
                System.out.println(h.getId() + " - " + h.getNombre() + " (" + h.getUniverso() + ") ")
        );
        
        SuperHeroe batmanBD = dao.buscar(batman.getId());
        batman.getIdentidadSecreta().setCiudad("Ciudad  Gotica");
        dao.actualizar(batmanBD);
        
        System.out.println("Lista despues de modificar a Batman");
        dao.listar().forEach(h ->
                System.out.println(h.getId() + " - " + h.getNombre() + " (" + h.getUniverso() + ") ")
        );
        
        dao.eliminar(spiderman.getId());
        
        System.out.println("Lista despues de eliminar a Spider-man");
        dao.listar().forEach(h ->
                System.out.println(h.getId() + " - " + h.getNombre() + " (" + h.getUniverso() + ") ")
        );
    }
}
