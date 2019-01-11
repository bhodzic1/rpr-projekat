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

    public StudentsModel () {};

    public void set () {
        studentsOfFirstYear.add(new Student("Meho", "Mehić", 10000, LocalDate.of(1995, 9, 17), "1709995122157", 1, "1"));
        studentsOfFirstYear.add(new Student("Mirza", "Delibašić", 10001, LocalDate.of(1995, 4, 15), "1504995136452", 1, "1"));
        studentsOfFirstYear.add(new Student("Dragoslav", "Šekularac", 10002, LocalDate.of(1995, 2, 20), "2002995331967", 1, "1"));
        studentsOfSecondYear.add(new Student("Sergej", "Barbarez", 10003, LocalDate.of(1994, 6, 19), "1906994989231", 1, "2"));
        studentsOfSecondYear.add(new Student("Asim", "Ferhatović", 10004, LocalDate.of(1993, 9, 12), "1209993243221", 1, "2"));
        studentsOfSecondYear.add(new Student("Zvjezdan", "Misimović", 10005, LocalDate.of(1994, 1, 14), "1401994334289", 1, "2"));

        students = studentsOfFirstYear;
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
