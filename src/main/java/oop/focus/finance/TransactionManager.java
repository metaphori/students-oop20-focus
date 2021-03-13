package oop.focus.finance;

import org.joda.time.LocalDate;

import java.util.List;

/**
 * Interface that models a transaction manager,
 * working on a transaction list and managing database operations.
 */
public interface TransactionManager {

    /**
     * Adds a transaction and saves it in the database.
     * It is not possible to add a transaction at a future date.
     *
     * @param transaction that is saved
     */
    void add(Transaction transaction);

    /**
     * Removes a transaction and deletes it from the database.
     * 
     * @param transaction being deleted
     */
    void remove(Transaction transaction);

    /**
     * @return the list of all transactions.
     */
    List<Transaction> getTransactions();

    /**
     * @return positive transactions' list
     */
    List<Transaction> getIncomes();

    /**
     * @return negative transactions' list
     */
    List<Transaction> getOutings();

    /**
     * @return subscriptions' list
     */
    List<Transaction> getSubscriptions();

    /**
     * @return the total monthly expenditure due to subscriptions
     */
    int monthlyExpense();

    /**
     * @return the total yearly expenditure due to subscriptions
     */
    int yearlyExpense();

    /**
     * @param date until it is time to calculate
     * @return a list of transactions generated by their repetition
     */
    List<Transaction> getGeneratedTransactions(LocalDate date);
}
