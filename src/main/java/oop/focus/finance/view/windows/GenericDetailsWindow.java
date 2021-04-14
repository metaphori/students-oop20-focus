package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import oop.focus.finance.controller.FXMLPaths;

public abstract class GenericDetailsWindow<X, Y> extends GenericWindow<Y> implements FinanceDetailsWindow<X> {

    @FXML
    private Button closeButton, deleteButton;

    private final X controller;

    public GenericDetailsWindow(final X controller, final Y element, final FXMLPaths path) {
        super(element, path);
        this.controller = controller;
    }

    @Override
    public final void populate() {
        this.populateStaticLabels();
        this.populateDynamicLabels();
        this.populateButtons();
    }

    /**
     * Populates static labels of fxml file.
     */
    @Override
    public void populateStaticLabels() { }

    /**
     * Populates buttons of fxml file.
     */
    @Override
    public void populateButtons() {
        this.deleteButton.setText("Elimina");
        this.closeButton.setText("Chiudi");
        this.deleteButton.setOnAction(event -> this.save());
        this.closeButton.setOnAction(event -> this.close());
    }

    @Override
    public final X getController() {
        return this.controller;
    }
}