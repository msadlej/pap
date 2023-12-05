package main.java.org.papz20.model;

public class User {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String username;
    private String password;
    private String role;

    public User(int id, String first_name, String last_name, String email, String username, String password) {
        this.setId(id);
        this.setFirstName(first_name);
        this.setLastName(last_name);
        this.setEmail(email);
        this.setUsername(username);
        this.setPassword(password);
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.first_name;
    }

    public String getLastName() {
        return this.last_name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRole() {
        return this.role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) throws IllegalArgumentException{
        if (!firstName.isEmpty() && firstName.length() <= 30) {
            this.first_name = firstName;
        } else {
            throw new IllegalArgumentException("First name cannot be empty or over 30 characters");
        }
    }

    public void setLastName(String lastName) throws IllegalArgumentException{
        if (!lastName.isEmpty() && lastName.length() <= 30) {
            this.last_name = lastName;
        } else {
            throw new IllegalArgumentException("Last name cannot be empty or over 30 characters");
        }
    }

    public void setEmail(String email) throws IllegalArgumentException{
        if (!email.isEmpty() && email.length() <= 30) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email cannot be empty or over 30 characters");
        }
    }

    public void setUsername(String username) throws IllegalArgumentException{
        if (!username.isEmpty() && username.length() <= 30) {
            this.username = username;
        } else {
            throw new IllegalArgumentException("Username cannot be empty or over 30 characters");
        }
    }

    public void setPassword(String password) throws IllegalArgumentException{
        if (!password.isEmpty() && password.length() <= 30) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Password cannot be empty or over 30 characters");
        }
    }
}
