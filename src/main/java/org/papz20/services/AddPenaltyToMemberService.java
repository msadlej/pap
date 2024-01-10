package main.java.org.papz20.services;

import main.java.org.papz20.model.Database;

import java.sql.SQLException;

public class AddPenaltyToMemberService {
    private Database database;

    public AddPenaltyToMemberService() { this.database = new Database(); }

    public boolean addPenalty(int transaction_id, int fine_amount){
        String status = "unpaid";
        try{
            database.addFine(transaction_id, fine_amount, status);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean makePenaltyPaid(int fine_id){
        try{
            database.setFineStatus(fine_id, "paid");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
