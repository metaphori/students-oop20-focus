package oop.focus.finance.view.bases;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.FinanceHomePageController;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.tiles.GenericTileView;
import oop.focus.finance.view.tiles.GenericTileViewImpl;
import oop.focus.finance.view.windows.FinanceWindow;
import oop.focus.finance.view.windows.NewQuickTransactionViewImpl;
import oop.focus.finance.view.windows.NewTransactionViewImpl;

import java.util.ArrayList;
import java.util.List;

public class FinanceHomePageViewImpl extends GenericView<FinanceHomePageController> implements FinanceHomePageView {

    @FXML
    private VBox accountsVBox, movementsVBox, financeHotKeyVBox;
    @FXML
    private Label amountLabel;
    @FXML
    private Button newMovememntButton, newQuickTransactionButton;

    public FinanceHomePageViewImpl(final FinanceHomePageController controller) {
        super(controller, FXMLPaths.HOMEPAGE);
    }

    @Override
    public final void populate() {
        this.populateAccounts();
        this.populateRecentTransactions();
        this.populateQuickTransactions();
        this.newMovememntButton.setOnAction(event -> this.show(new NewTransactionViewImpl(super.getX())));
    }

    @Override
    public final void populateAccounts() {
        this.amountLabel.setText(super.getX().getTotalAmount());
        this.accountsVBox.getChildren().clear();
        final List<GenericTileView<Account>> fastAccountTiles = new ArrayList<>();
        super.getX().getSortedAccounts().forEach(a -> fastAccountTiles.add(
                new GenericTileViewImpl<>(a, a.getName(), super.getX().getAmount(a))));
        fastAccountTiles.forEach(t -> this.accountsVBox.getChildren().add(t.getRoot()));
    }

    @Override
    public final void populateRecentTransactions() {
        this.movementsVBox.getChildren().clear();
        final List<GenericTileView<Transaction>> fastTransactionTiles = new ArrayList<>();
        super.getX().getSortedTodayTransactions().forEach(t -> fastTransactionTiles.add(
                new GenericTileViewImpl<>(t, t.getDescription(), super.getX().format(t.getAmount()))));
        fastTransactionTiles.forEach(t -> this.movementsVBox.getChildren().add(t.getRoot()));
    }

    @Override
    public final void populateQuickTransactions() {
        this.financeHotKeyVBox.getChildren().clear();
        final List<FinanceMenuButton<FinanceHomePageController>> financeHotKeyButtons = new ArrayList<>();
        super.getX().getSortedQuickTransactions().forEach(qt -> financeHotKeyButtons.add(
                new FinanceMenuButtonImpl<>(qt.getDescription(), c -> c.doQuickTransaction(qt))));
        financeHotKeyButtons.forEach(b -> this.financeHotKeyVBox.getChildren().add(b.getButton()));
        financeHotKeyButtons.forEach(b -> b.getButton().setOnAction(event -> b.getAction(super.getX())));
        this.newQuickTransactionButton.setOnAction(event -> this.show(new NewQuickTransactionViewImpl(super.getX())));
    }

    private void show(final FinanceWindow view) {
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) view.getRoot()));
        stage.show();
    }
}