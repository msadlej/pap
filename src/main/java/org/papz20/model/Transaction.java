package main.java.org.papz20.model;

public class Transaction {
    private int id;
    private User user;
    private Copy copy;
    private String checkout_date;
    private  String due_date;
    private String status;

    public Transaction(int id, User user, Copy copy, String checkout_date, String due_date, String status) {
        setId(id);
        setUser(user);
        setCopy(copy);
        setCheckoutDate(checkout_date);
        setDueDate(due_date);
        setStatus(status);
    }

    public int getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public Copy getCopy() {
        return this.copy;
    }

    public String getCheckoutDate() {
        return this.checkout_date;
    }

    public String getDueDate() {
        return this.due_date;
    }

    public String getStatus() {
        return this.status;
    }

    public void setId(int id) throws IllegalArgumentException {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("Transaction id must be a positive integer.");
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }

    public void setCheckoutDate(String checkout_date) {
        this.checkout_date = checkout_date;
    }

    public void setDueDate(String due_date) {
        this.due_date = due_date;
    }

    public void setStatus(String status) {
        if (status.equals("checked out") || status.equals("returned")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Status must be either \"checked out\" or \"returned\".");
        }
    }
}
