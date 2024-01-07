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

    public int getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public Copy getCopy() {
        return this.copy;
    }

    public String getDate() {
        return this.date;
    }

    public int getPeriod() {
        return this.period;
    }

    public String getStatus() {
        return this.status;
    }

    public void setId(int id) throws IllegalArgumentException {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("Order id must be a positive integer.");
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPeriod(int period) throws IllegalArgumentException {
        if (period > 0) {
            this.period = period;
        } else {
            throw new IllegalArgumentException("Period must be a positive integer.");
        }
    }

    public void setStatus(String status) {
        if (status.equals("pending") || status.equals("approved") || status.equals("rejected")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Status must be either \"pending\", \"approved\", or \"rejected\".");
        }
    }
}
