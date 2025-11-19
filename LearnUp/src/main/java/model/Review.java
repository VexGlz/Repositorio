
package model;

import java.util.Date;

public class Review {
    private Integer rating; 
    private String comment;
    private Date date;

    public Review() {}
    public Review(Integer rating, String comment, Date date) {
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}