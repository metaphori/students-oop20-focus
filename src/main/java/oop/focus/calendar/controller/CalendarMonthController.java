package oop.focus.calendar.controller;


import java.util.List;

import oop.focus.calendar.model.CalendarLogic;
import oop.focus.calendar.model.Day;
import oop.focus.calendar.model.Format;
import oop.focus.common.Controller;
import oop.focus.db.DataSource;




/**
 * Interface that models a Month Controller.
 * Is used for set and get the format and the the spacing or for update the month view.
 */
public interface CalendarMonthController extends  Controller {

    /**
     * Used for set the Format and the Spacing of the hoursbox of the day.
     * @param dayController
     */
    void configureday(CalendarDayController dayController);

    /**
     * Used for set the List of the days (Month).
     */
    void setMonth();

    /**
     * 
     * Used to get the list with the days of the month.
     * @return List<Day> : month
     */
    List<Day> getMonth();

    /**
     * Used for get the logic of the calendar.
     * @return CalendarLogic
     */
     CalendarLogic getCalendarLogic();

    /**
     * Used for set the font size of the texts.
     * @param fontSize : double
     */
    void setFontSize(double fontSize);

    /**
     * Used for get the font size of texts.
     * @return double
     */
    double getFontSize();

    /**
     * Used for set the Format of the hoursbox.
     * @param format
     */
    void setFormat(Format format);

    /**
     * Used for set the Spacing of the hoursbox.
     * @param spacing
     */
    void setSpacing(double spacing);

    /**
     * Used for update the view of the month.
     */
    void updateView();


    /**
     * Used for get the DataSource.
     * @return DataSource
     */
    DataSource getDataSource();

}
