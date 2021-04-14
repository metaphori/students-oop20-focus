package oop.focus.homepage.view;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oop.focus.db.exceptions.DaoAccessException;
import oop.focus.homepage.controller.FXMLPaths;
import oop.focus.homepage.controller.HotKeyController;
import oop.focus.homepage.model.HotKeyImpl;
import oop.focus.homepage.model.HotKeyType;

public class NewHotKeyViewImpl implements GenericAddView {

    @FXML
    private Pane paneNewHotKey;

    @FXML
    private Label newHotKeyName, newHotKeyCategory, labelAddNew, labelHotKey;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button saveHotKeyButton, deleteButton, goBackButton;

    @FXML
    private ComboBox<String> categoryComboBox;

    private final HotKeyController controller;
    private final HotKeyMenuView menuView;
    private Node root;

    public NewHotKeyViewImpl(final HotKeyController controller, final HotKeyMenuView hotKeyMenuView) {
        this.controller = controller;
        this.menuView = hotKeyMenuView;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.ADDNEWHOTKEY.getPath()));
        loader.setController(this);

        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setProperty();
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.setButtonOnAction();
        final ComboBoxFiller comboBox = new ComboBoxFiller();
        this.categoryComboBox.setItems(comboBox.getHotKey());
    }

    private void setProperty() {
        this.labelAddNew.prefWidthProperty().bind(this.paneNewHotKey.widthProperty().multiply(0.5));
        this.labelAddNew.prefHeightProperty().bind(this.paneNewHotKey.heightProperty().multiply(0.05));
        this.labelAddNew.setAlignment(Pos.CENTER);

        this.labelHotKey.prefWidthProperty().bind(this.paneNewHotKey.widthProperty().multiply(0.5));
        this.labelHotKey.prefHeightProperty().bind(this.paneNewHotKey.heightProperty().multiply(0.05));
        this.labelHotKey.setAlignment(Pos.CENTER);

        this.categoryComboBox.prefWidthProperty().bind(this.paneNewHotKey.widthProperty().multiply(0.3));
        this.nameTextField.prefWidthProperty().bind(this.paneNewHotKey.widthProperty().multiply(0.3));
    }

    @FXML
    public final void delete(final ActionEvent event) {
        this.nameTextField.setText(" ");
        this.categoryComboBox.getSelectionModel().clearSelection();
    }

    public final Node getRoot() {
        return this.root;
    }

    @FXML
    public final void goBack(final ActionEvent event) throws IOException {
        final Stage stage = (Stage) this.paneNewHotKey.getScene().getWindow();
        stage.close();
    }

    @FXML
    public final void save(final ActionEvent event) throws IOException {
        final String name = nameTextField.getText();
        if (categoryComboBox.getSelectionModel().isEmpty() || name.isEmpty()) {
            final AllertGenerator allert = new AllertGenerator();
            allert.checkFieldsFilled();
            allert.showAllert();
        } else {
            try {
                this.controller.saveHotKey(new HotKeyImpl(name, HotKeyType.getTypeFrom(categoryComboBox.getSelectionModel().getSelectedItem())));
                this.menuView.populateTableView();
                this.goBack(event);
            } catch (IllegalStateException e) {
                final Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Errore");
                alert.setContentText("Il tasto inserito è già presente!");

                final Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
                    alert.close();
                }
            }
        }
    }


    public final void setButtonOnAction() {
        this.goBackButton.setOnAction(event -> {
            try {
                this.goBack(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.deleteButton.setOnAction(event -> this.delete(event));

        this.saveHotKeyButton.setOnAction(event -> {
            try {
                this.save(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
