package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class Subject {
    private SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleIntegerProperty semester = new SimpleIntegerProperty(0);
    private SimpleIntegerProperty professor = new SimpleIntegerProperty();

    public Subject(int id, String name, int semester, int professor) {
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleIntegerProperty(id);
        this.semester = new SimpleIntegerProperty(semester);
        this.professor = new SimpleIntegerProperty(professor);
    }



    public Subject () {};


    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

    public int getSemester() {
        return semester.get();
    }

    public SimpleIntegerProperty semesterProperty() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester.set(semester);
    }


    @Override
    public String toString () {
        return this.name.get();
    }

    public int getProfessor() {
        return professor.get();
    }

    public SimpleIntegerProperty professorProperty() {
        return professor;
    }

    public void setProfessor(int professor) {
        this.professor.set(professor);
    }
}
