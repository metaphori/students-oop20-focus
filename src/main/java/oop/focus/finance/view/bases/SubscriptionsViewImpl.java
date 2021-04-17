package oop.focus.finance.view.bases;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import oop.focus.common.View;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.SubscriptionsController;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.tiles.GenericTileView;
import oop.focus.finance.view.tiles.GenericTileViewImpl;
import oop.focus.finance.view.windows.SubscriptionDetailsWindowImpl;
import oop.focus.statistics.view.ViewFactoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubscriptionsViewImpl extends GenericView<SubscriptionsController> implements SubscriptionsView {

    @FXML
    private BorderPane mainPane;
    @FXML
    private ScrollPane subscriptionsScroll;
    @FXML
    private Label monthlyLabel, annualLabel, monthlyTransactionLabel, annualTransactionLabel;

    public SubscriptionsViewImpl(final SubscriptionsController controller) {
        super(controller, FXMLPaths.SUBS);
    }

    @Override
    public final void populate() {
        this.annualTransactionLabel.setText(this.format(super.getX().getYearlyExpense()));
        this.monthlyTransactionLabel.setText(this.format(super.getX().getMonthlyExpense()));
        this.setPref();
    }

    private void setPref() {
        this.monthlyLabel.prefWidthProperty().bind(this.mainPane.prefWidthProperty());
        this.annualLabel.prefWidthProperty().bind(this.mainPane.prefWidthProperty());
        this.monthlyTransactionLabel.prefWidthProperty().bind(this.mainPane.prefWidthProperty());
        this.annualTransactionLabel.prefWidthProperty().bind(this.mainPane.prefWidthProperty());
        this.subscriptionsScroll.setFitToWidth(true);
    }

    @Override
    public final void showSubscriptions(final List<Transaction> subscriptions) {
        var viewFactory = new ViewFactoryImpl();
        final List<GenericTileView<Transaction>> subscriptionsTiles = new ArrayList<>();
        subscriptions.forEach(t -> subscriptionsTiles.add(
                new GenericTileViewImpl<>(t, t.getCategory().getColor(), t.getDescription(),
                        t.getRepetition().getName(), super.getX().getTransactionAmount(t))));
        var vbox = viewFactory.createVerticalAutoResizingWithNodes(subscriptionsTiles.stream()
                .map(View::getRoot).collect(Collectors.toList()));
        subscriptionsTiles.forEach(t -> t.getRoot()
                .addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.showDetails(t.getElement())));
        this.subscriptionsScroll.setContent(vbox.getRoot());
    }

    private void showDetails(final Transaction subscription) {
        final View details = new SubscriptionDetailsWindowImpl(super.getX(), subscription);
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) details.getRoot()));
        stage.show();
    }
}
