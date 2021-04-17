package oop.focus.event.controller;

import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.event.view.EventInformationViewImpl;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;
import org.joda.time.LocalDate;

public class EventInformationControllerImpl implements EventInformationController {

    private final EventInformationViewImpl view;
    private final EventManager eventManager;
    private final Event event;

    public EventInformationControllerImpl(final DataSource dsi, final Event event) {
        this.eventManager = new EventManagerImpl(dsi);
        this.event = event;
        this.view = new EventInformationViewImpl(this);
    }

    public final Event getEvent() {
        return this.event;
    }

    public final void stopRepetition() {
        this.eventManager.generateRepeatedEvents(LocalDate.now());
        for (Event e : this.eventManager.getAll()){
            System.out.println(e.getName() + " "+ e.getStart().toString());
        }
    }

    @Override
    public final View getView() {
        return this.view;
    }

}
