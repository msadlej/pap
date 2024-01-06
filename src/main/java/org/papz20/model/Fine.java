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
}
