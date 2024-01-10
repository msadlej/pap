package main.java.org.papz20.services;

import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.Order;

public class LendBookService {
    private Database database;

    public LendBookService() { this.database = new Database(); }

    public boolean lendBook(int order_id){
        try{
            Order target_order = database.fetchOrder(order_id);
            int user_id = target_order.getUserId();
            int copy_id = target_order.getCopyId();
            String checkout_date;
            String due_date;
            /// do dates' strings
            String status = "checked out";

            int book_id = database.fetchCopy(copy_id).getBookId();
            boolean can_proceed = database.copyAvailableBook(book_id);
            if (!can_proceed) return false;

            database.addTransaction(order_id, user_id, copy_id, checkout_date, due_date, status);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
