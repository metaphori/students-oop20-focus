package oop.focus.diary.model;

/**
 * The interface TimerListener can be used to manage the beginning of a timer(or of a Stopwatch) and the creation of
 * the relatives event when a counter ends.
 */
public interface TimerListener {
    /**
     * Used to create new event when a counter ends.
     */
    void createEvent();

    /**
     * @throws  IllegalStateException if the counter couldn't start now, because another event is taking place
     * @return  true if the counter can start
     * Used when a counter starts, in order to register that time.
     */
    boolean startCounter();

    }