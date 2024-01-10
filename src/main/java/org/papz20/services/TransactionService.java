package main.java.org.papz20.services;

import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.Order;
import main.java.org.papz20.model.Transaction;
import java.time.LocalDate;

public class TransactionService {
    private Database database;

    public TransactionService() { this.database = new Database(); }

    public Transaction createTransactionFromOrder(int order_id){
        Transaction this_transaction = null;
        int transaction_id = database.getNextId("transactions");
        Order this_order = database.fetchOrder(order_id);
        int user_id = this_order.getUserId();
        int copy_id = this_order.getCopyId();
        String checkout_date = this_order.getDate();
        int days_count = this_order.getPeriod();
        LocalDate parsedCheckoutDate = LocalDate.parse(checkout_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dueDate = parsedCheckoutDate.plusDays(daysToAdd);


        return this_transaction;
    }

}
