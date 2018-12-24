package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentsModel {
    private ObservableList<Student> students = FXCollections.observableArrayList();

    public StudentsModel () {};

    public ObservableList<Student> getStudents() {
        return students;
    }

    public void setStudents(ObservableList<Student> students) {
        this.students = students;
    }
}
