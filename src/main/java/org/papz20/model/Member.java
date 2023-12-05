package main.java.org.papz20.model;

public class Member extends User{
    public Member(int id, String first_name, String last_name, String email, String username, String password) {
        super(id, first_name, last_name, email, username, password);
        this.role = "member";
    }
}
