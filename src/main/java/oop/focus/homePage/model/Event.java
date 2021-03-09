package oop.focus.homePage.model;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * This interface model an event.
 * An event has a name a start hour , an end hour , a start day , an end day.
 * Also, an event can be repeated every day, every week, every month or every year , and can be carried out in the company of one or more people.
 */
public interface Event {

    /**
     * This method is use for get the event name.
     * @return a String.
     */
    String getName();

    /**
     * This method is use for get the event start hour.
     * @return a LocalDateTime.
     */
    LocalTime getStartHour();

    /**
     * This method is use for get the event end hour.
     *  @return a LocalDateTime.
     */
    LocalTime getEndHour();

    /**
     * This method is use for get the event start day.
     *  @return a LocalDate.
     */
    LocalDate getStartDate();

    /**
     * This method is use for get the event end day.
     *  @return a LocalDate.
     */
    LocalDate getEndDate();

    /**
     * This method is used to know if an event repeats itself or not, and if it recurs, to know how often.
     *  @return a member of the Ripetitions enum.
     *  @see Ripetitions enum.
     */
    Ripetitions getRipetition();

    /**
     * This method is used to add a new person who will attend the event.
     * @param p is the person to be added.
     */
    void addPerson(Person p);
}