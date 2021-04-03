package oop.focus.homepage.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.stage.Stage;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import oop.focus.finance.model.Repetition;
import oop.focus.homepage.controller.HomePageController;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;

public class NewEventView implements Initializable, View {

    @FXML
    private Pane paneNewEvent;

    @FXML
    private Label newEvent, newEventName, startHour, endHour, repetition;

    @FXML
    private ComboBox<String> startHourChoice, repetitionChoice, endHourChoice, startMinuteChoice, endMinuteChoice;

    @FXML
    private Button back, saveSelection, deleteSelection;

    private Event event;
    private Parent root;
    private Repetition lastRep;
    private HomePageController controller;

    public NewEventView(final HomePageController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layouts/homepage/addNewEvent.fxml"));
        loader.setController(this);

        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        final ComboBoxFiller filler = new ComboBoxFiller();

        this.endHourChoice = filler.getHourAndMinute(Constants.HOUR_PER_DAY);
        this.startHourChoice = filler.getHourAndMinute(Constants.HOUR_PER_DAY);
        this.startMinuteChoice = filler.getHourAndMinute(Constants.MINUTE_PER_HOUR);
        this.endMinuteChoice = filler.getHourAndMinute(Constants.MINUTE_PER_HOUR);
        this.repetitionChoice = filler.getRepetition();

        this.lastRep = null;
    }

    @FXML
    public final void backButtonPress(final ActionEvent event) throws IOException {
        Stage stage = (Stage) this.paneNewEvent.getScene().getWindow();
        stage.close();
    }

    @FXML
    public final void deleteSelection(final ActionEvent event) {
        this.repetitionChoice.getSelectionModel().clearSelection();
        this.startHourChoice.getSelectionModel().clearSelection();
        this.endHourChoice.getSelectionModel().clearSelection();
        this.startMinuteChoice.getSelectionModel().clearSelection();
        this.endMinuteChoice.getSelectionModel().clearSelection();
    }

    @Override
    public final Parent getRoot() {
        return this.root;
    }

    @FXML
    public final void saveSelection(final ActionEvent event) throws IOException {
        if (!this.startHourChoice.getSelectionModel().isEmpty() && !this.endHourChoice.getSelectionModel().isEmpty()
                && !this.startMinuteChoice.getSelectionModel().isEmpty() && !this.endMinuteChoice.getSelectionModel().isEmpty()
                && !this.repetitionChoice.getSelectionModel().isEmpty()) {

            final LocalDate date = LocalDate.now();
            final LocalTime start = new LocalTime(Integer.valueOf(this.startHourChoice.getSelectionModel().getSelectedItem()), Integer.valueOf(this.startMinuteChoice.getSelectionModel().getSelectedItem()));
            final LocalTime end = new LocalTime(Integer.valueOf(this.endHourChoice.getSelectionModel().getSelectedItem()), Integer.valueOf(this.endMinuteChoice.getSelectionModel().getSelectedItem()));
            final Repetition repetition = Repetition.valueOf(this.repetitionChoice.getSelectionModel().getSelectedItem());

            this.controller.saveEvent(new EventImpl(this.newEventName.getText(), date.toLocalDateTime(start), date.toLocalDateTime(end), repetition));
            this.backButtonPress(event);
        } else {
            this.controller.showAllert();
        }
    }

    public final void setText(final String eventName) {
        newEventName.setText(eventName);
    }

    private class Constants {
         public static final int HOUR_PER_DAY = 24;
         public static final int MINUTE_PER_HOUR = 60;
    }

}
