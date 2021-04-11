package oop.focus.finance.model;

import javafx.collections.ObservableSet;

/**
 * Interface that models an account manager,
 * working on an account list and managing database operations.
 */
public interface AccountManager {

    /**
     * Adds an account and saves it in the database.
     * If the color of account doesn't exist, saves it in the database too.
     * 
     * @param account that is saved
     */
    void add(Account account);

    /**
     * Removes an account and deletes it from the database.
     * 
     * @param account being deleted
     */
    void remove(Account account);

    /**
     * @return the list of all accounts.
     */
    ObservableSet<Account> getAccounts();
}
