package oop.focus.homepage.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.focus.homepage.controller.HomePageController;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyImpl;
import oop.focus.homepage.model.HotKeyType;

public class HotKeyMenuView implements Initializable, View {

    @FXML
    private Pane paneHotKeyView;

    @FXML
    private VBox vboxHotKeyList;

    @FXML
    private Button addHotKeyButton, deleteElement, goBackButton;

    @FXML
    private TableView<HotKey> tableHotKeyList;

    @FXML
    private TableColumn<HotKey, String> nome, tipo;

    private ObservableList<HotKey> hotKeyList;
    private final HomePageController controller;
    private Parent root;

    public HotKeyMenuView(final HomePageController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layouts/homepage/choiceMenu.fxml"));
        loader.setController(this);

        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {

        nome.setCellValueFactory(new PropertyValueFactory<HotKey, String>("name"));
        nome.setCellFactory(TextFieldTableCell.forTableColumn());
        nome.setOnEditCommit(new EventHandler<CellEditEvent<HotKey, String>>() {
            @Override
            public void handle(final CellEditEvent<HotKey, String> event) {
                final HotKey hotKey = event.getRowValue();
                hotKey.setName(event.getNewValue());
            }
        });

        tipo.setCellValueFactory(new PropertyValueFactory<HotKey, String>("typeRepresentation"));
        tipo.setCellFactory(TextFieldTableCell.forTableColumn());
        tipo.setOnEditCommit(new EventHandler<CellEditEvent<HotKey, String>>() {
            @Override
            public void handle(final CellEditEvent<HotKey, String> event) {
                final HotKey hotKey = event.getRowValue();
                hotKey.setType(event.getNewValue());
            }
        });

        tableHotKeyList.setEditable(true);
        tableHotKeyList.setItems(this.controller.getHotKey());
    }

    @FXML
    public final void addNewHotKey(final ActionEvent event) throws IOException {
        final NewHotKeyView newHotKey = new NewHotKeyView(this.controller);
        Stage stage = new Stage();
        stage.setScene(new Scene(newHotKey.getRoot()));
        stage.show();
    }

    @FXML
    public final void deletSelectedRowItem(final ActionEvent event) {
        this.controller.deleteHotKey(tableHotKeyList.getSelectionModel().getSelectedItem());
        this.tableHotKeyList.getItems().removeAll(tableHotKeyList.getSelectionModel().getSelectedItems());
    }

    public final ObservableList<HotKey> getElem() {
        return this.hotKeyList;
    }

    @FXML
    public final void goBack(final ActionEvent event) throws IOException {
        final HomePageBaseView base = new HomePageBaseView(this.controller);
        this.paneHotKeyView.getChildren().clear();
        this.paneHotKeyView.getChildren().add(base.getRoot());
    }

    @FXML
    public final void refreshList() {
        this.hotKeyList.add(new HotKeyImpl("Shopping", HotKeyType.EVENT));
        this.hotKeyList.add(new HotKeyImpl("Shopping", HotKeyType.EVENT));
        /*this.hotKeyList.add(hotKey);*/
        tableHotKeyList.setItems(hotKeyList);
    }

    public final Parent getRoot() {
        return this.root;
    }
}
