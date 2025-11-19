package config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class MongoClientProvider {
    private static final String CONNECTION_STRING = "mongodb://localhost:27017"; 
    private static final String DATABASE_NAME = "bda_conexion"; 
    
    private static MongoClientProvider instance;
    private final MongoClient mongoClient;
    private final MongoDatabase database;

    private MongoClientProvider() {
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry mongoClientCodecRegistry = fromRegistries(
            com.mongodb.MongoClient.getDefaultCodecRegistry(), 
            pojoCodecRegistry
        );

        this.mongoClient = MongoClients.create(CONNECTION_STRING);
        this.database = mongoClient.getDatabase(DATABASE_NAME).withCodecRegistry(mongoClientCodecRegistry);
    }

    public static MongoClientProvider getInstance() {
        if (instance == null) {
            instance = new MongoClientProvider();
        }
        return instance;
    }

    public <T> MongoCollection<T> getCollection(String collectionName, Class<T> clazz) {
        return database.getCollection(collectionName, clazz);
    }

    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}