package oop.focus.calendar;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox; 



public class HoursViewImpl implements HoursView {

    public enum Format {

        /**
         * Normal format day.
         */
        NORMAL(24),

        /**
         * Extended format day.
         */
        EXTENDED(48);

        private int number;

        Format(final int i) {
            number = i;
        }

        int getNumber() {
            return number;
        }


    }

    private int hoursformat;
    private VBox myvbox;
    private boolean flag = true;

    public HoursViewImpl() {
        this.hoursformat = Format.NORMAL.getNumber();
    }


    /**
     * @param format of the time hours or half
     * 
     */
    public void setSpacing(final Format format) {
        hoursformat = format.getNumber();
    }

    /**
     * @return  the position of the label
     * @param hour qualcosa part 2
     */
    public double getY(final int hour) {
        return this.myvbox.getChildren().get(hour).getLayoutY();
    }

    /**
     *@param vbox set VBox hours.
     */
    public void setVBox(final VBox vbox) {
        this.myvbox = vbox;
    }

     /**
     * @return get the hours box.
     */
    public VBox getVBox() {
        if (this.myvbox == null) {
            buildVBox();
        }
        return this.myvbox;
    }

    private void buildVBox() {
        final VBox vbox = new VBox();

        if (this.hoursformat == Format.NORMAL.getNumber()) {
            for (int i = 0; i <= hoursformat; i++) { 
                    final Label label = new Label(i + ":00");
                    label.setLayoutY(i);
                    vbox.getChildren().add(label);
            }
        } else {
            for (int i = 0; i <= hoursformat; i++) { 
                if (flag) {
                    final Label label = new Label(i / 2 + ":00");
                    flag = false;
                    vbox.getChildren().add(label);
                } else {
                    final Label label = new Label(i / 2 + ":30");
                    flag = true;
                    vbox.getChildren().add(label);
                }
            } 
        }
        setVBox(vbox);
    }


}