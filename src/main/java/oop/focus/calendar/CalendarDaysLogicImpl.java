package oop.focus.calendar;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import oop.focus.calendar.HoursViewImpl.Format;


public class CalendarDaysLogicImpl implements CalendarDaysLogic {



    private final HoursViewImpl hoursbox = new HoursViewImpl();
    private final EventViewImpl eventbox = new EventViewImpl(hoursbox);
    private final DayImpl day;
    private ScrollPane scroller = new ScrollPane();
    private final int width;
    private final int height;
    private final int spacing;

    /**
     * 
     * @param day    date of the day that we want build
     * @param width  max width of the day view.
     * @param height  max height of the day view.
     * @param spacing space between two hours
     */
    public CalendarDaysLogicImpl(final DayImpl day, final int width, final int height, final int spacing) {
        this.day = day; 
        this.width = width;
        this.height = height;
        this.spacing = spacing;
    }


    private void configureDay(final HBox myhbox) {

        hoursbox.setFormat(Format.NORMAL);
        hoursbox.setSpacing(spacing);

        hoursbox.buildVBox();
        eventbox.buildVBox();

        hoursbox.getVBox().prefWidthProperty().bind(myhbox.widthProperty().divide(2));
        eventbox.getVBox().prefWidthProperty().bind(myhbox.widthProperty());

        myhbox.getChildren().add(hoursbox.getVBox());
        myhbox.getChildren().add(eventbox.getVBox());
        myhbox.setAlignment(Pos.CENTER);
    }



    private void configureDailyEvent(final VBox container, final Label daily) {
        daily.prefWidthProperty().bind(container.widthProperty());
        daily.setAlignment(Pos.CENTER);
        daily.setTextAlignment(TextAlignment.CENTER);
        daily.setText("Attività giornaliere: \nLavoro\nAllenamento");
        daily.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        container.getChildren().add(daily);
    }

    /**
     * Used for create Day for Calendar.
     */
    public void buildDay() {

        final VBox container = new VBox();
        final HBox myhbox = new HBox();

        configureDay(myhbox);


        final Label nameDay = new Label(day.getName());
        final Label numberDay = new Label(" " + day.getNumber() + " ");
        nameDay.setAlignment(Pos.CENTER);
        numberDay.setAlignment(Pos.CENTER);
        container.getChildren().add(nameDay);
        container.getChildren().add(numberDay);

        final Label daily = new Label();
        configureDailyEvent(container, daily);

        container.getChildren().add(myhbox);
        container.setBorder(new Border(new BorderStroke(Color.PURPLE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        container.setAlignment(Pos.CENTER);


        final ScrollPane scroller = new ScrollPane(container);
        scroller.prefHeight(height);
        scroller.prefWidth(width);
        setScroller(scroller);
        scroller.setFitToWidth(true);
    }

    private void setScroller(final ScrollPane scroller) {
        this.scroller = scroller;
    }

    /**
     * Get the scroller with all the object of the day.
     * @return scrollable view of the day
     */
    public ScrollPane getScroller() {
        return this.scroller;
    }

    /**
     * Get the width of the scroller.
     * @return width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Get the height of the scroller.
     * @return height
     */
    public int getHeight() {
        return this.height;
    }


}
