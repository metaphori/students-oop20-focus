package oop.focus.homepage.view;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oop.focus.homepage.controller.PersonsController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class RelationshipsView implements Initializable, View {
    @FXML
    private AnchorPane relationshipsPane;

    @FXML
    private TableView<String> relationshipsTable;

    @FXML
    private TableColumn<String, String> relationshipsColumn;

    @FXML
    private Button addRelationships, goBack, deleteRelationship;

    private final PersonsController controller;
    private Parent root;

    public RelationshipsView(final PersonsController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layouts/homepage/relationships.fxml"));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        relationshipsColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));

        this.relationshipsTable.setItems(this.controller.getDegree());

        goBack.setOnAction(event -> this.goBack());
        addRelationships.setOnAction(event -> this.addRelationships());
        deleteRelationship.setOnAction(event -> this.deleteRelationships());
    }

    public final void goBack() {
        final PersonsView persons = new PersonsView(this.controller);
        this.relationshipsPane.getChildren().clear();
        this.relationshipsPane.getChildren().add(persons.getRoot());
    }

    @FXML
    public final void addRelationships() {
        final View newDegree = new AddNewRelationship(this.controller);
        final Stage stage = new Stage();
        stage.setScene(new Scene(newDegree.getRoot()));
        stage.show();
    }

    @FXML
    public final void deleteRelationships() {
        this.controller.deleteRelationship(relationshipsTable.getSelectionModel().getSelectedItem());
        this.refreshTableView();
    }

    public final void refreshTableView() {
        this.relationshipsTable.getItems().removeAll(relationshipsTable.getSelectionModel().getSelectedItems());
    }

    @Override
    public final Parent getRoot() {
        return this.root;
    }
}
