package main.java.org.papz20.model;

public class Order {
    private int id;
    private int user_id;
    private int copy_id;
    private String date;
    private int period;
    private String status;

    public Order(int id, int user_id, int copy_id, String date, int period, String status) {
        setId(id);
        setUserId(user_id);
        setCopyId(copy_id);
        setDate(date);
        setPeriod(period);
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
        if (status.equals("pending") || status.equals("approved") || status.equals("rejected") || status.equals("hidden")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Status must be either \"pending\", \"approved\", \"rejected\", or \"hidden\".");
        }
    }
}
