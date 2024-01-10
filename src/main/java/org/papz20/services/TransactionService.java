package main.java.org.papz20.services;

import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.Order;
import main.java.org.papz20.model.Transaction;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransactionService {
    private Database database;

    public TransactionService() { this.database = new Database(); }

    public Transaction createTransactionFromOrder(int order_id){
        Transaction this_transaction = null;

        try{
            int transaction_id = database.getNextId("transactions");
            Order this_order = database.fetchOrder(order_id);
            int user_id = this_order.getUserId();
            int copy_id = this_order.getCopyId();
            String checkout_date = this_order.getDate();
            int days_count = this_order.getPeriod();
            LocalDate parsed_checkout_date = LocalDate.parse(checkout_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate parsed_due_date = parsed_checkout_date.plusDays(days_count);
            String due_date = parsed_due_date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            this_transaction = new Transaction(transaction_id, order_id, user_id, copy_id, checkout_date, due_date, "checked out");
            database.addTransaction(this_transaction);
            database.setOrderStatus(order_id, "approved");
        }catch (Exception e){
            e.printStackTrace();
        }

        return this_transaction;
    }

}
