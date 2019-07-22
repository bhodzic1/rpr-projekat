package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SubjectReportModel {
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty lastname = new SimpleStringProperty();
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleIntegerProperty grade = new SimpleIntegerProperty();

    public SubjectReportModel() {

    };

    public SubjectReportModel(String name, String lastname, int id, int grade) {
        this.name = new SimpleStringProperty(name);
        this.lastname = new SimpleStringProperty(lastname);
        this.id = new SimpleIntegerProperty(id);
        this.grade = new SimpleIntegerProperty(grade);
    }

    public String getName() {
        return name.get();
    }


    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getLastname() {
        return lastname.get();
    }

    public SimpleStringProperty lastnameProperty() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getGrade() {
        return grade.get();
    }

    public SimpleIntegerProperty gradeProperty() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade.set(grade);
    }
}
