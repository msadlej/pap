package main.java.org.papz20.model;

public class Fine {
    private int id;
    private int transaction_id;
    private int amount;
    private String status;

    public Fine(int id, int transaction_id, int amount, String status) {
        setId(id);
        setTransactionId(transaction_id);
        setAmount(amount);
        setStatus(status);
    }

    public int getId() {
        return this.id;
    }

    public int getTransactionId() {
        return this.transaction_id;
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

    public void setTransactionId(int transaction_id) throws IllegalArgumentException {
        if (transaction_id > 0) {
            this.transaction_id = transaction_id;
        } else {
            throw new IllegalArgumentException("Transaction id must be a positive integer.");
        }
    }

    public void setAmount(int amount) throws IllegalArgumentException {
        if (amount > 0) {
            this.amount = amount;
        } else {
            throw new IllegalArgumentException("Fine amount must be a positive integer.");
        }
    }

    public void setStatus(String status) {
        if (status.equals("unpaid") || status.equals("paid")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Status must be either \"unpaid\" or \"paid\".");
        }
    }
}
