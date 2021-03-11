package oop.focus.finance;

import oop.focus.homepage.model.Person;

import java.util.List;

/**
 * Interface that models a group transaction manager,
 * taking into account the debts and credits of the users.
 */
public interface GroupManager {

    /**
     * @param person added to the group
     */
    void addPerson(Person person);

    /**
     * @param person removed to the group
     */
    void removePerson(Person person);

    /**
     * @param groupTransaction added to group transactions
     */
    void addTransaction(GroupTransaction groupTransaction);

    /**
     * @param groupTransaction removed from group transactions
     */
    void removeTransaction(GroupTransaction groupTransaction);

    /**
     * @return the list of people in the group
     */
    List<Person> getGroup();

    /**
     * @return the list of group transactions
    */
    List<GroupTransaction> getTransactions();

    /**
     *
     * @param person whose we want to know credit amount
     * @return the credit amount
     */
    int getCredit(Person person);
}