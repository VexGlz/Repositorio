package model;

import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class Course {
    @BsonId 
    private ObjectId _id; 
    private String title;
    private String description;
    private String category;
    private ObjectId instructorId;
    private Double price;
    private Double rating;
    private List<String> tags;
    private List<Module> modules; 
    
    private Date createdAt; 
    private Date updatedAt; 

    public Course() {}

    public Course(String title, String description, String category, ObjectId instructorId, Double price, Double rating, List<String> tags, List<Module> modules) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.instructorId = instructorId;
        this.price = price;
        this.rating = rating;
        this.tags = tags;
        this.modules = modules;
    }

    public ObjectId get_id() { return _id; }
    public void set_id(ObjectId _id) { this._id = _id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public ObjectId getInstructorId() { return instructorId; }
    public void setInstructorId(ObjectId instructorId) { this.instructorId = instructorId; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
    public List<Module> getModules() { return modules; }
    public void setModules(List<Module> modules) { this.modules = modules; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
}