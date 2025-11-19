
package model;

import java.util.Date;
import org.bson.types.ObjectId;

public class EnrolledCourse {
    private ObjectId courseId; 
    private Date enrollmentDate;
    private Double progress; 
    private Review review;

    public EnrolledCourse() {}
    public EnrolledCourse(ObjectId courseId, Date enrollmentDate, Double progress, Review review) {
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.progress = progress;
        this.review = review;
    }

    public ObjectId getCourseId() { return courseId; }
    public void setCourseId(ObjectId courseId) { this.courseId = courseId; }
    public Date getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(Date enrollmentDate) { this.enrollmentDate = enrollmentDate; }
    public Double getProgress() { return progress; }
    public void setProgress(Double progress) { this.progress = progress; }
    public Review getReview() { return review; }
    public void setReview(Review review) { this.review = review; }
}