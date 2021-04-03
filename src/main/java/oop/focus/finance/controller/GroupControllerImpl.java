package oop.focus.finance.controller;

import javafx.scene.Parent;
import oop.focus.db.DataSource;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.FinanceManagerImpl;
import oop.focus.finance.view.GroupView;

public class GroupControllerImpl implements GroupController {

    private final GroupView view;
    private final FinanceManager manager;

    public GroupControllerImpl(final DataSource db) {
        this.view = new GroupView(this);
        this.manager = new FinanceManagerImpl(db);
    }

    @Override
    public final Parent getView() {
        return this.view.getRoot();
    }
}
