package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class Subject {
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private SimpleStringProperty professorName = new SimpleStringProperty("");

    public Subject(String name, int id, String professorName, int semester) {
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleIntegerProperty(id);
        this.professorName = new SimpleStringProperty(professorName);
        this.semester = new SimpleIntegerProperty(semester);
    }

    private SimpleIntegerProperty semester = new SimpleIntegerProperty(0);
    private ArrayList<Student> students = new ArrayList<>();

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

    public String getProfessorName() {
        return professorName.get();
    }

    public SimpleStringProperty professorNameProperty() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName.set(professorName);
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

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) { //Register more students to subject
        this.students = students;
    }

    public void setStudent(Student student) { //Register one student to subject
        this.students.add(student);
    }
}
