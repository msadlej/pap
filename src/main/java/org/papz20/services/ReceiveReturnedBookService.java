package main.java.org.papz20.services;

import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.Transaction;

public class ReceiveReturnedBookService {
    private Database database;

    public ReceiveReturnedBookService() { this.database = new Database(); }

    public boolean receiveReturnedBook(int transaction_id){
        try{
            database.setTransactionStatus(transaction_id, "returned");

            Transaction this_transaction = database.fetchTransaction(transaction_id);
            int copy_id = this_transaction.getCopyId();

            database.setAvailableCopy(copy_id, true);

            FineService fine_service = new FineService();
            if(database.isLateTransaction(transaction_id)){
                fine_service.addFine(transaction_id, 100);
            }
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
