package main.java.org.papz20.services;

import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.Fine;
import java.util.List;

public class FineService {
    private Database database;

    public FineService() { this.database = new Database(); }

    public void addFine(Fine fine) {
        database.addFine(fine);
    }

    public void addFine(int fine_id, int transaction_id, int amount, String status) {
        database.addFine(fine_id, transaction_id, amount, status);
    }

    public void addFine(int transaction_id, int amount, String status) {
        database.addFine(transaction_id, amount, status);
    }

    public void setFineStatus(int fine_id, String new_status) throws IllegalArgumentException{
        if (database.fetchFine(fine_id) != null) {
            database.setFineStatus(fine_id, new_status);
        } else {
            throw new IllegalArgumentException("Fine does not exist.");
        }
    }

    public void setFineStatus(Fine target_fine, String new_status){
        setFineStatus(target_fine.getId(), new_status);
    }

    public List<Fine> viewFines(int user_id) {
        return database.viewFines(user_id);
    }

    public List<Fine> getAllFines(){
        return database.getAllFines();
    }
}
