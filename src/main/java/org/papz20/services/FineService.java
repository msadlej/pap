package main.java.org.papz20.services;

import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.Fine;
import java.util.List;

public class FineService {
    private final Database database;

    public FineService() { this.database = Database.getInstance(); }

    public void addFine(int transaction_id, int amount) {
        database.addFine(transaction_id, amount, "unpaid");
    }

    public void setPaid(Fine fine) throws IllegalArgumentException{
        int fine_id = fine.getId();

        if (database.fetchFine(fine_id) != null) {
            database.setFineStatus(fine_id, "paid");
        } else {
            throw new IllegalArgumentException("Fine does not exist.");
        }
    }

    public List<Fine> viewFines(int user_id) {
        return database.viewFines(user_id);
    }

    public List<Fine> getAllFines(){
        return database.getAllFines();
    }
}
