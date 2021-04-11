package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import oop.focus.finance.controller.FXMLPaths;

import oop.focus.finance.controller.TransactionsController;


public class NewAccountViewImpl extends GenericWindow<TransactionsController> {

    private static final int RGB_MAX_VALUE = 255;

    @FXML
    private Label titleLabel, nameLabel, amountLabel, colorLabel;
    @FXML
    private TextField nameTextfield, amountTextfield;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Button cancelButton, saveButton;

    public NewAccountViewImpl(final TransactionsController transactionsController) {
        super(transactionsController, FXMLPaths.NEWACCOUNT);
    }

    @Override
    public final void populate() {
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
    }

    @Override
    public final void save() {
        if (this.nameTextfield.getText().isEmpty() || isNotNumeric(this.amountTextfield.getText())) {
            super.allert("I campi non sono stati compilati correttamente.");
        } else {
            super.getX().newAccount(this.nameTextfield.getText(), toRGBCode(this.colorPicker.getValue()),
                        Double.parseDouble(this.amountTextfield.getText()));
            this.close();
        }
    }

    public static String toRGBCode(final Color color) {
        return String.format("%02X%02X%02X", (int) (color.getRed() * RGB_MAX_VALUE),
                (int) (color.getGreen() * RGB_MAX_VALUE), (int) (color.getBlue() * RGB_MAX_VALUE));
    }
}
