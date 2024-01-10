package main.java.org.papz20.services;

import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.Order;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class LendBookService {
    private Database database;

    public LendBookService() { this.database = new Database(); }

    public boolean lendBook(int order_id){
        try{
            Order target_order = database.fetchOrder(order_id);
            int user_id = target_order.getUserId();
            int copy_id = target_order.getCopyId();

            int book_id = database.fetchCopy(copy_id).getBookId();
            boolean can_proceed = database.copyAvailableBook(book_id);
            if (!can_proceed) return false;

            String checkout_date = target_order.getDate();
            int days_count = target_order.getPeriod();
            LocalDate checkout_date_parsed = LocalDate.parse(checkout_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate due_date_parsed = checkout_date_parsed.plusDays(days_count);
            String due_date = due_date_parsed.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            String status = "checked out";

            database.addTransaction(order_id, user_id, copy_id, checkout_date, due_date, status);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
