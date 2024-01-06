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
}
