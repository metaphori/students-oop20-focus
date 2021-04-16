package oop.focus.application.controller;
import oop.focus.application.view.ButtonsView;
import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.statistics.controller.UpdatableController;

/**
 * ButtonsController is the controller that manages the section's buttons.
 */
public class ButtonsController implements Controller {
    private final View buttonsView;
    public ButtonsController(final UpdatableController<Controller> controller) {
        this.buttonsView = new ButtonsView(controller);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.buttonsView;
    }
}