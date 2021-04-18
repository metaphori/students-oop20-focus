package oop.focus.finance.view.launchers;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.db.DataSourceImpl;
import oop.focus.finance.controller.ChangeViewController;
import oop.focus.finance.controller.ChangeViewControllerImpl;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.FinanceManagerImpl;

public class FinanceLauncher extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final FinanceManager manager = new FinanceManagerImpl(new DataSourceImpl());
        final ChangeViewController controller = new ChangeViewControllerImpl(manager);
        primaryStage.setScene(new Scene((Parent) controller.getView().getRoot()));
        primaryStage.show();
    }

    public static void main(final String... args) {
        launch(args);
    }
}
