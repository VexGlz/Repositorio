
package model;

public class Contact {
    private String phone;
    private String linkedin;
    private String website;

    public Contact() {}
    public Contact(String phone, String linkedin, String website) {
        this.phone = phone;
        this.linkedin = linkedin;
        this.website = website;
    }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getLinkedin() { return linkedin; }
    public void setLinkedin(String linkedin) { this.linkedin = linkedin; }
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
}