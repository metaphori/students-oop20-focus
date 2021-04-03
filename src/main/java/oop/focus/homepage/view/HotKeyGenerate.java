package oop.focus.homepage.view;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import oop.focus.homepage.controller.HomePageController;
import oop.focus.homepage.model.HotKey;

public class HotKeyGenerate {

    private final HotKeyFactory factory;
    private final HomePageController controller;

    public HotKeyGenerate(final HomePageController controller) {
        this.factory = new HotKeyFactoryImpl();
        this.controller = controller;
    }

    public final Parent createButton(final HotKey hotKey) {
        switch (hotKey.getType()) {
        case EVENT :
            Button eventButton = this.factory.getEventButton(hotKey.getName(), this.controller);
            eventButton.setOnAction(event -> this.openNewEventView(event));
            return eventButton;
        case COUNTER :
            return this.factory.getCounterButton(hotKey.getName());
        default:
            return this.factory.getActivityButton(hotKey.getName());
        }
    }

    private void openNewEventView(ActionEvent event) {
        View newEvent = new NewEventView(controller);
        Stage stage = new Stage();
        stage.setScene(new Scene(newEvent.getRoot()));
        stage.show();
    }
}
