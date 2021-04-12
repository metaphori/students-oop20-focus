package oop.focus.calendar.view;


import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.model.Format;
import oop.focus.homepage.model.Event;




public class EventViewImpl implements VBoxManager {

    //Classes
    private final HoursViewImpl hours;

    //View
    private VBox myVBox;

    //Variables
    private double spacing;
    private double insertEventsDuration;

    //List
    private final List<Event> events;

    //Constants
    private static final double MINUTES_IN_HOURS = 60;


    public EventViewImpl(final HoursViewImpl hours, final DayImpl day) {
        events = new ArrayList<>(day.getEvents());
        this.hours = hours;
    }

    /**
     * Used for check if we are setting the right spacing according to the format.
     */
    private void checkSpacing() {
        this.spacing = hours.getSpacing();
        if (hours.getFormat() == Format.EXTENDED.getNumber()) {
            this.spacing = this.spacing * 2;
        }
    }


    public final double getY(final int i) {
        final double spaceforminute = this.spacing / MINUTES_IN_HOURS;
        final double minutestotalspace = spaceforminute * this.events.get(i).getStartHour().getMinuteOfHour();
        return hours.getY(this.events.get(i).getStartHour().getHourOfDay()) + minutestotalspace;
    }


    public final VBox getVBox() {
        if (this.myVBox == null) {
            buildVBox();
        }
        return this.myVBox;
    }

    /**
     * Used for build the events panel.
     * @param vbox is the box where the events will be
     * @param i index of the events
     */
    private void buildPanel(final VBox vbox, final int i) {
        final Pane panel = new Pane();
        final Label name = new Label(this.events.get(i).getName());

        panel.setBackground(new Background(
                new BackgroundFill(Color.GREENYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        panel.setBorder(new Border(
                new BorderStroke(Color.PURPLE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        panel.getChildren().add(name);

        if (i != 0) {
        panel.setTranslateY(this.getY(i) - insertEventsDuration);
        } else {
        panel.setTranslateY(this.getY(i));
        }

        final double durationeventinhours = this.events.get(i).getEndHour().getHourOfDay() - this.events.get(i).getStartHour().getHourOfDay();
        final double durationeventinminutes = (double) this.events.get(i).getEndHour().getMinuteOfHour() - (double) this.events.get(i).getStartHour().getMinuteOfHour();
        panel.setPrefHeight((this.spacing / MINUTES_IN_HOURS) * (durationeventinminutes + durationeventinhours * MINUTES_IN_HOURS));
        insertEventsDuration += (this.spacing / MINUTES_IN_HOURS) * (durationeventinminutes + durationeventinhours * MINUTES_IN_HOURS);

        vbox.getChildren().add(panel);
    }


    public final void buildVBox() {
        checkSpacing();
        final VBox vbox = new VBox();
        vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        for (int i = 0; i < this.events.size(); i++) {
            buildPanel(vbox, i);
        }
        this.myVBox = vbox;
    }



}
