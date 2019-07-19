package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class StudentsModel {
    private ObservableList<Student> studentsOfFirstYear = FXCollections.observableArrayList();
    private ObservableList<Student> studentsOfSecondYear = FXCollections.observableArrayList();
    private ObservableList<Student> studentsOfThirdYear = FXCollections.observableArrayList();
    private ObservableList<Student> studentsOfFourthYear = FXCollections.observableArrayList();
    private ObservableList<Student> studentsOfFifthYear = FXCollections.observableArrayList();
    private ObservableList<Student> students = FXCollections.observableArrayList();

    private CollegeDAO dao = CollegeDAO.getInstance();

    public StudentsModel () {};

    public void set () {
        studentsOfFirstYear = FXCollections.observableArrayList(dao.students(1, 1));
        studentsOfSecondYear = FXCollections.observableArrayList(dao.students(1, 2));
        studentsOfThirdYear = FXCollections.observableArrayList(dao.students(1, 3));
        studentsOfFourthYear = FXCollections.observableArrayList(dao.students(2, 1));
        studentsOfFifthYear = FXCollections.observableArrayList(dao.students(2, 2));
    }

    public void addStudent (Student student, int level) {
        if (level == 1) {
            studentsOfFirstYear.add(student);
        } else if (level == 2) {
            studentsOfSecondYear.add(student);
        } else if (level == 3) {
            studentsOfThirdYear.add(student);
        } else if (level == 4) {
            studentsOfFourthYear.add(student);
        } else studentsOfFifthYear.add(student);
    }

    public ObservableList<Student> getStudentsOfFirstYear() {
        return studentsOfFirstYear;
    }

    public void setStudentsOfFirstYear(ObservableList<Student> students) {
        this.studentsOfFirstYear = students;
    }

    public ObservableList<Student> getStudentsOfSecondYear() {
        return studentsOfSecondYear;
    }

    public void setStudentsOfSecondYear(ObservableList<Student> studentsOfSecondYear) {
        this.studentsOfSecondYear = studentsOfSecondYear;
    }

    public ObservableList<Student> getStudentsOfThirdYear() {
        return studentsOfThirdYear;
    }

    public void setStudentsOfThirdYear(ObservableList<Student> studentsOfThirdYear) {
        this.studentsOfThirdYear = studentsOfThirdYear;
    }

    public ObservableList<Student> getStudentsOfFourthYear() {
        return studentsOfFourthYear;
    }

    public void setStudentsOfFourthYear(ObservableList<Student> studentsOfFourthYear) {
        this.studentsOfFourthYear = studentsOfFourthYear;
    }

    public ObservableList<Student> getStudentsOfFifthYear() {
        return studentsOfFifthYear;
    }

    public void setStudentsOfFifthYear(ObservableList<Student> studentsOfFifthYear) {
        this.studentsOfFifthYear = studentsOfFifthYear;
    }

    @Override
    public String toString() {
        String temp = "";
        for(Student s : students){
            temp += s.toString();
        }
        return temp;
    }
}
