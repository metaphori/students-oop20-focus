package oop.focus.calendar.persons.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oop.focus.calendar.persons.view.AddNewPersonView;
import oop.focus.calendar.persons.view.RelationshipsView;
import oop.focus.calendar.persons.view.RelationshipsViewImpl;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.RelationshipsManager;
import oop.focus.homepage.model.RelationshipsManagerImpl;

import java.util.List;
import java.util.stream.Collectors;

public class RelationshipsControllerImpl implements RelationshipsController {

    private final DataSource dsi;
    private final RelationshipsManager relationships;
    private final RelationshipsView view;

    public RelationshipsControllerImpl(final DataSource dsi, final AddNewPersonView personView) {
        this.dsi = dsi;
        this.relationships = new RelationshipsManagerImpl(this.dsi);
        this.view = new RelationshipsViewImpl(this, personView);
    }

    public final void addRelationship(final String relationship) {
        this.relationships.add(relationship);
    }

    public final void deleteRelationship(final String relationship)  {
        try {
            this.relationships.remove(relationship);
        } catch (IllegalStateException e) {
            throw  new IllegalStateException();
        }
    }

    public final ObservableList<String> getDegree() {
        final ObservableList<String> list = FXCollections.observableArrayList();
        final List<String> arrayList = this.relationships.getAll().stream().sorted().collect(Collectors.toList());
        arrayList.stream().forEach(p -> list.add(p));
        return list.sorted();
    }

    public final DataSource getDsi() {
        return this.dsi;
    }

    public final View getView() {
        return this.view;
    }
}
