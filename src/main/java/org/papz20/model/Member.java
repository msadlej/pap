package main.java.org.papz20.model;

public class Member extends User{
    public String role = "member";
    public Member(String firstName, String lastName, String email, String username, String password) {
        super(firstName, lastName, email, username, password);
        this.setRole(this.role);
    }
}
