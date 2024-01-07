package main.java.org.papz20.model;

public class Order {
    int id;
    int userID;
    int copyID;
    String date;
    int period;
    String status;

    public Order(int id, int userID, int copyID, String date, int period, String status) {
        setId(id);
        setUserID(userID);
        setCopyID(copyID);
        setDate(date);
        setPeriod(period);
        setStatus(status);
    }

    public int getId() {
        return this.id;
    }

    public int getUserID() {
        return this.userID;
    }

    public int getCopyID() {
        return this.copyID;
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

    public void setUserID(int userID) throws IllegalArgumentException {
        if (userID > 0) {
            this.userID = userID;
        } else {
            throw new IllegalArgumentException("User id must be a positive integer.");
        }
    }

    public void setCopyID(int copyID) throws IllegalArgumentException {
        if (copyID > 0) {
            this.copyID = copyID;
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
        if (status.equals("pending") || status.equals("approved") || status.equals("rejected")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Status must be either \"pending\", \"approved\", or \"rejected\".");
        }
    }
}
