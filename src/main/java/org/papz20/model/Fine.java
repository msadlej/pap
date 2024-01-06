package main.java.org.papz20.model;

public class Fine {
    private int id;
    private Transaction transaction;
    private int amount;
    private String status;

    public Fine(int id, Transaction transaction, int amount, String status) {
        setId(id);
        setTransaction(transaction);
        setAmount(amount);
        setStatus(status);
    }

    public int getId() {
        return this.id;
    }

    public Transaction getTransaction() {
        return this.transaction;
    }

    public int getAmount() {
        return this.amount;
    }

    public String getStatus() {
        return this.status;
    }

    public void setId(int id) throws IllegalArgumentException {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("Fine id must be a positive integer.");
        }
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public void setAmount(int amount) throws IllegalArgumentException {
        if (amount > 0) {
            this.amount = amount;
        } else {
            throw new IllegalArgumentException("Fine amount must be a positive integer.");
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
