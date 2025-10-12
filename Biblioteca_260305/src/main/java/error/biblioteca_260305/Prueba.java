package error.biblioteca_260305;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Prueba {

    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bibliotecaPU");

        AutorJpaController autorController = new AutorJpaController(emf);

        Autor autorNuevo = new Autor("Gabriel García Márquez", "Colombiana");

        try {
            autorController.create(autorNuevo);
            System.out.println("¡Autor guardado con éxito!");
        } catch (Exception e) {
            System.out.println("Error al guardar el autor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            emf.close();
        }
    }
}