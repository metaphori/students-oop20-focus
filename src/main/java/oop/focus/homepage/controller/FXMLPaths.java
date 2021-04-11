package oop.focus.homepage.controller;

public enum FXMLPaths {

    /**
     * 
     */
    HOMEPAGEBASE(Constants.HOMEPAGE + "base.fxml"),
    /**
     * 
     */
    HOTKEYMENU(Constants.HOMEPAGE + "choiceMenu.fxml"),
    /**
     * 
     */
    ADDNEWEVENT(Constants.HOMEPAGE + "addNewEvent.fxml"),
    /**
     * 
     */
    ADDNEWHOTKEY(Constants.HOMEPAGE + "addNewHotKey.fxml");

    /**
     *
     */
    private String path;

    FXMLPaths(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    private static class Constants {
        public static final String HOMEPAGE = "/layouts/homepage/";
    }
}