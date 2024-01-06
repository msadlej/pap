package main.java.org.papz20.model;

public class Order {
    int id;
    User user;
    Copy copy;
    String date;
    int period;
    String status;

    public Order(int id, User user, Copy copy, String date, int period, String status) {
        setId(id);
        setUser(user);
        setCopy(copy);
        setDate(date);
        setPeriod(period);
        setStatus(status);
    }
}
