package model;

import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.bson.codecs.pojo.annotations.BsonId;

public class Instructor {
    @BsonId
    private ObjectId _id;
    private String name;
    private String email;
    private List<String> specialties;
    private Integer experienceYears;
    private String bio;
    private Contact contact; 
    private List<ObjectId> coursesTaught; 
    
    private Date createdAt; 
    private Date updatedAt; 
    
    public Instructor() {}

    public Instructor(String name, String email, List<String> specialties, Integer experienceYears, String bio, Contact contact, List<ObjectId> coursesTaught) {
        this.name = name;
        this.email = email;
        this.specialties = specialties;
        this.experienceYears = experienceYears;
        this.bio = bio;
        this.contact = contact;
        this.coursesTaught = coursesTaught;
    }

    public ObjectId get_id() { return _id; }
    public void set_id(ObjectId _id) { this._id = _id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<String> getSpecialties() { return specialties; }
    public void setSpecialties(List<String> specialties) { this.specialties = specialties; }
    public Integer getExperienceYears() { return experienceYears; }
    public void setExperienceYears(Integer experienceYears) { this.experienceYears = experienceYears; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public Contact getContact() { return contact; }
    public void setContact(Contact contact) { this.contact = contact; }
    public List<ObjectId> getCoursesTaught() { return coursesTaught; }
    public void setCoursesTaught(List<ObjectId> coursesTaught) { this.coursesTaught = coursesTaught; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
}