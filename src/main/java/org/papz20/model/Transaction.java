package main.java.org.papz20.model;

public class Transaction {
    private int id;
    private int user_id;
    private int copy_id;
    private String checkout_date;
    private  String due_date;
    private String status;

    public Transaction(int id, int user_id, int copy_id, String checkout_date, String due_date, String status) {
        setId(id);
        setUserId(user_id);
        setCopyId(copy_id);
        setCheckoutDate(checkout_date);
        setDueDate(due_date);
        setStatus(status);
    }

    public int getId() {
        return this.id;
    }

    public int getUserId() {
        return this.user_id;
    }

    public int getCopyId() {
        return this.copy_id;
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

    public void setUserId(int user_id) throws IllegalArgumentException {
        if (user_id > 0) {
            this.user_id = user_id;
        } else {
            throw new IllegalArgumentException("User id must be a positive integer.");
        }
    }

    public void setCopyId(int copy_id) throws IllegalArgumentException {
        if (copy_id > 0) {
            this.copy_id = copy_id;
        } else {
            throw new IllegalArgumentException("Copy id must be a positive integer.");
        }
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
