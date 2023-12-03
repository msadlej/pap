package main.java.org.papz20.model;

public class Admin extends User {
    public String role = "admin";
    public Admin(String firstName, String lastName, String email, String username, String password) {
        super(firstName, lastName, email, username, password);
        this.setRole(this.role);
    }

}
