package model;

import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.bson.codecs.pojo.annotations.BsonId;

public class Student {
    @BsonId
    private ObjectId _id;
    private String name;
    private String email;
    private List<EnrolledCourse> enrolledCourses; 
    private List<String> interests;
    private Settings settings; 

    private Date createdAt; 
    private Date updatedAt; 
    
    public Student() {}

    public Student(String name, String email, List<EnrolledCourse> enrolledCourses, List<String> interests, Settings settings) {
        this.name = name;
        this.email = email;
        this.enrolledCourses = enrolledCourses;
        this.interests = interests;
        this.settings = settings;
    }

    public ObjectId get_id() { return _id; }
    public void set_id(ObjectId _id) { this._id = _id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<EnrolledCourse> getEnrolledCourses() { return enrolledCourses; }
    public void setEnrolledCourses(List<EnrolledCourse> enrolledCourses) { this.enrolledCourses = enrolledCourses; }
    public List<String> getInterests() { return interests; }
    public void setInterests(List<String> interests) { this.interests = interests; }
    public Settings getSettings() { return settings; }
    public void setSettings(Settings settings) { this.settings = settings; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
}