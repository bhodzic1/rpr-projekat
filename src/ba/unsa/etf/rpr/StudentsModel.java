package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class StudentsModel {
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleIntegerProperty grade = new SimpleIntegerProperty();
    private LocalDate date;
    private SimpleStringProperty professor = new SimpleStringProperty();

    public StudentsModel() {
    }

    public StudentsModel(String name, int grade, LocalDate date, String professor) {
        this.name = new SimpleStringProperty(name);
        this.grade = new SimpleIntegerProperty(grade);
        this.date = date;
        this.professor = new SimpleStringProperty(professor);
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

    public int getGrade() {
        return grade.get();
    }

    public SimpleIntegerProperty gradeProperty() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade.set(grade);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getProfessor() {
        return professor.get();
    }

    public SimpleStringProperty professorProperty() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor.set(professor);
    }
}
