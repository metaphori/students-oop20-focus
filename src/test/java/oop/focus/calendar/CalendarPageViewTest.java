package oop.focus.calendar;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import oop.focus.calendar.controller.CalendarController;
import oop.focus.calendar.controller.CalendarControllerImpl;
import oop.focus.db.DataSourceImpl;




public class CalendarPageViewTest extends Application {

    private static final double WIDTH = 700;
    private static final double HEIGHT = 500;

    public final void start(final Stage primaryStage) {

    	//final HBox test = new HBox();
    	final DataSourceImpl datasource = new DataSourceImpl();
        final CalendarController pagecontroller = new CalendarControllerImpl(datasource);
        //test.getChildren().add(pagecontroller.getCalendarPage());
        primaryStage.setScene(new Scene((Parent) pagecontroller.getView().getRoot(), WIDTH, HEIGHT));
        primaryStage.show();
    }

    public static final void main(final String... args) {
        launch(args);
    }
}

