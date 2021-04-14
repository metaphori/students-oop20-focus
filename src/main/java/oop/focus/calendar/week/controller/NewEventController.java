package oop.focus.calendar.week.controller;

import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.Event;

public interface NewEventController {

    void addNewEvent(Event event);

    DataSource getDsi();

    boolean itIsValid(Event event);

    View getView();

    //void updateView();
}