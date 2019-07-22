package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleIntegerProperty;

import java.time.LocalDate;

public class Grade {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleIntegerProperty grade = new SimpleIntegerProperty();
    private LocalDate date;
    private SimpleIntegerProperty student = new SimpleIntegerProperty();
    private SimpleIntegerProperty subject = new SimpleIntegerProperty();

    public Grade () {}


    public Grade(int id, int grade, LocalDate date, int student, int subject) {
        this.id = new SimpleIntegerProperty(id);
        this.grade = new SimpleIntegerProperty(grade);
        this.date = date;
        this.student = new SimpleIntegerProperty(student);
        this.subject = new SimpleIntegerProperty(subject);
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getStudent() {
        return student.get();
    }

    public SimpleIntegerProperty studentProperty() {
        return student;
    }

    public void setStudent(int student) {
        this.student.set(student);
    }

    public int getSubject() {
        return subject.get();
    }

    public SimpleIntegerProperty subjectProperty() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject.set(subject);
    }
}
