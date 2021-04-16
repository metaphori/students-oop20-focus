package oop.focus.diary.view;

import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import oop.focus.common.View;
import oop.focus.diary.controller.DiaryPages;
import oop.focus.diary.controller.FXMLPaths;
import oop.focus.diary.controller.RemovePageController;
import oop.focus.diary.model.DiaryImpl;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static oop.focus.diary.view.OpenWindow.openWindow;

public class DiaryView implements View, Initializable {
    private static final Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getBounds();
    private static final double PAGES_HEIGHT = 0.2;
    private static final double DIARY_LABEL_HEIGHT = 0.1;
    private static final double H_BOX_SPACING = 0.04;
    private static final double BUTTON_WIDTH = 0.3;
    private static final double BUTTON_HEIGHT = 0.08;
    private static final double V_BOX_WIDTH = 0.5;
    private static final double CONTAINER_DIARY_HEIGHT = 0.7;
    @FXML
    private VBox vBox;

    @FXML
    private Label diaryLabel;

    @FXML
    private ScrollPane containerDiary;

    @FXML
    private Button addButton;

    @FXML
    private Button removeButton;
    @FXML
    private HBox hBox;

    private final DiaryPages controller;
    private Accordion pages;
    public DiaryView(final DiaryPages controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.DIARY_SCHEME.getPath()));
        loader.setController(this);
        try {
            Parent root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.pages = new Accordion();
        this.insertPages();
        this.controller.getObservableSet().addListener((SetChangeListener<DiaryImpl>) change -> {
            if (change.wasAdded()) {
                this.updateView(change.getElementAdded().getName());
            } else if (change.wasRemoved()) {
                this.pages.getPanes().clear();
                this.insertPages();
            }
        });
        this.vBox = (VBox) new CreateBoxFactoryImpl().createVBox(List.of(this.diaryLabel, this.containerDiary, this.hBox)).getRoot();
        this.diaryLabel.setText("Diario");
        this.addButton.setText("Aggiungi");
        this.addButton.setOnMouseClicked(event -> openWindow((Parent) new WindowCreateNewPage(this.controller).getRoot()));
        this.removeButton.setText("Rimuovi");
        this.removeButton.setOnMouseClicked(event -> openWindow((Parent) new RemovePageController(this.controller).getView().getRoot()));
        this.hBox.spacingProperty().bind(this.vBox.widthProperty().multiply(H_BOX_SPACING));
        List.of(this.addButton, this.removeButton).forEach(s -> s.prefHeightProperty().bind(this.vBox.heightProperty().multiply(BUTTON_HEIGHT)));
        List.of(this.addButton, this.removeButton).forEach(s -> s.prefWidthProperty().bind(this.vBox.widthProperty().multiply(BUTTON_WIDTH)));
        this.containerDiary.prefHeightProperty().bind(this.vBox.heightProperty().multiply(CONTAINER_DIARY_HEIGHT));
        this.diaryLabel.prefHeightProperty().bind(this.containerDiary.heightProperty().multiply(DIARY_LABEL_HEIGHT));
        this.hBox.setAlignment(Pos.CENTER);
        this.vBox.prefWidthProperty().set(SCREEN_BOUNDS.getWidth() * V_BOX_WIDTH);
        this.diaryLabel.prefWidthProperty().bind(this.containerDiary.widthProperty());
        this.diaryLabel.setAlignment(Pos.CENTER);
        this.containerDiary.setContent(this.pages);
        this.pages.prefWidthProperty().bind(this.containerDiary.widthProperty());
        this.pages.prefHeightProperty().bind(this.containerDiary.heightProperty().multiply(PAGES_HEIGHT));
    }
    private void updateView(final String s) {
        this.pages.getPanes().add(new SingleTitledPaneDiaryImpl(this.controller).createTitledPane(s));
    }

    /**
     * The method can be used to add all pages' saved to the accordion.
     */
    private void insertPages() {
        this.controller.getFileName().forEach(this::updateView);
    }
    @Override
    public final Node getRoot() {
        return this.vBox;
    }
}