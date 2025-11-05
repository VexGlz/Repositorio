// 1. EL PAQUETE (basado en tu pom.xml)
package error.gestorrestaurantes; 

import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;

import java.util.Arrays;
import java.util.List;

import javax.swing.text.Document;


public class GestorRestaurantes {

    public static void main(String[] args) {
        String uri = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("restaurants");
            MongoCollection<Document> collection = database.getCollection("cafes");

            collection.drop();
            System.out.println("Colección 'cafes' eliminada para empezar de cero.");

            System.out.println("\n--- 1. Insertando un documento ---");
            Document cafePlaza = new Document("name", "Café de la Plaza")
                    .append("stars", 4.3)
                    .append("categories", Arrays.asList("Café", "Postres", "Desayuno"));
            collection.insertOne(cafePlaza);
            System.out.println("Insertado: " + cafePlaza.toJson());

            System.out.println("\n--- 2. Insertando varios documentos ---");
            List<Document> cafesNuevos = Arrays.asList(
                    new Document("name", "Espresso Express")
                            .append("stars", 4.8)
                            .append("categories", Arrays.asList("Café", "Rápido", "Takeaway")),
                    new Document("name", "The Tea House")
                            .append("stars", 3.9)
                            .append("categories", Arrays.asList("Té", "Infusiones", "Postres")),
                    new Document("name", "Morning Brew")
                            .append("stars", 4.0)
                            .append("categories", Arrays.asList("Café", "Desayuno", "Bakery"))
            );
            collection.insertMany(cafesNuevos);
            System.out.println("Se insertaron " + cafesNuevos.size() + " documentos nuevos.");

            System.out.println("\n--- 3. Filtros para mostrar ---");

            Bson filtroStars = gte("stars", 4.5);
            imprimirResultados(collection.find(filtroStars), "Cafés con stars >= 4.5");

            Bson filtroNombreCafe = regex("name", "Café");
            imprimirResultados(collection.find(filtroNombreCafe), "Cafés cuyo nombre contiene 'Café'");

            Bson filtroPostres = eq("categories", "Postres");
            imprimirResultados(collection.find(filtroPostres), "Cafés con categoría 'Postres'");

            Bson filtroRangoStars = and(gte("stars", 3.0), lte("stars", 4.3));
            imprimirResultados(collection.find(filtroRangoStars), "Cafés con stars entre 3.0 y 4.3");

            Bson filtroNombreT = regex("name", "^T");
            imprimirResultados(collection.find(filtroNombreT), "Cafés cuyo nombre empieza con 'T'");

            System.out.println("\n--- 4. Updates (Actualizaciones) ---");

            Bson filtroMorning = eq("name", "Morning Brew");
            Bson updateStars = set("stars", 4.5);
            UpdateResult resultMorning = collection.updateOne(filtroMorning, updateStars);
            System.out.println("Stars de 'Morning Brew' actualizadas: " + resultMorning.getModifiedCount());

            Bson filtroCategoria = in("categories", Arrays.asList("Bakery", "Desayuno"));
            Bson updateIncremento = inc("stars", 0.2);
            UpdateResult resultInc = collection.updateMany(filtroCategoria, updateIncremento);
            System.out.println("Se incrementaron " + resultInc.getModifiedCount() + " documentos con 'Bakery' o 'Desayuno'");

            Bson filtroPlaza = eq("name", "Café de la Plaza");
            Bson updateCampos = combine(
                    set("phone", "555-111-2222"),
                    set("open", true)
            );
            UpdateResult resultPlaza = collection.updateOne(filtroPlaza, updateCampos);
            System.out.println("'Café de la Plaza' actualizado: " + resultPlaza.getModifiedCount());

            System.out.println("\n--- Documentos después de todas las actualizaciones ---");
            imprimirResultados(collection.find(), "Estado actual");

            System.out.println("\n--- 5. Deletes (Eliminaciones) ---");

            DeleteResult resultEspresso = collection.deleteOne(eq("name", "Espresso Express"));
            System.out.println("Documentos eliminados (Espresso Express): " + resultEspresso.getDeletedCount());

            DeleteResult resultStarsBajas = collection.deleteMany(lt("stars", 4.0));
            System.out.println("Documentos eliminados (stars < 4): " + resultStarsBajas.getDeletedCount());

            Bson filtroCategoriasEliminar = in("categories", Arrays.asList("Takeaway", "Infusiones"));
            DeleteResult resultCategorias = collection.deleteMany(filtroCategoriasEliminar);
            System.out.println("Documentos eliminados (Takeaway o Infusiones): " + resultCategorias.getDeletedCount());


            System.out.println("\n--- Documentos finales en la colección ---");
            imprimirResultados(collection.find(), "Estado final de la colección 'cafes'");

        } catch (Exception e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void imprimirResultados(FindIterable<Document> documents, String titulo) {
        System.out.println("\n>> Resultados para: " + titulo);
        try (MongoCursor<Document> cursor = documents.iterator()) {
            if (!cursor.hasNext()) {
                System.out.println("No se encontraron documentos que coincidan.");
            }
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        }
    }
}