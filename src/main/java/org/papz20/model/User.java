package main.java.org.papz20.model;

public abstract class User {
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

    public void setFirstName(String firstName) {
        if (!firstName.isEmpty() && firstName.length() < 30) {
            this.first_name = firstName;
        }
    }

    public void setLastName(String lastName) {
        if (!lastName.isEmpty() && lastName.length() < 30) {
            this.last_name = lastName;
        }
    }

    public void setEmail(String email) {
        if (!email.isEmpty() && email.length() < 30) {
            this.email = email;
        }
    }

    public void setUsername(String username) {
        if (!username.isEmpty() && username.length() < 30) {
            this.username = username;
        }
    }

    public void setPassword(String password) {
        if (!password.isEmpty() && password.length() < 30) {
            this.password = password;
        }
    }
}
