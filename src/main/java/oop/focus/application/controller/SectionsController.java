package oop.focus.application.controller;

import oop.focus.application.view.SectionsView;
import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.statistics.controller.UpdatableController;

/**
 * SectionsController manages and updates the view of different sections.
 */
public class SectionsController implements UpdatableController<Controller> {
    private final SectionsView view;
    public SectionsController() {
        this.view = new SectionsView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void updateInput(final Controller input) {
        this.view.update(input.getView());
    }
}