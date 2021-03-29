package oop.focus.calendar.view;

import javafx.scene.layout.VBox;

public interface VBoxManager {

    /**
     * @param i  Index
     * @return position of the object in the VBox
     */
    double getY(int i);

    /**
     * @return the vbox.
     */
    VBox getVBox();

    /**
     * build the vbox.
     */
    void buildVBox();

}