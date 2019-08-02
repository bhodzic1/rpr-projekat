package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Student {
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty lastname = new SimpleStringProperty("");
    private SimpleIntegerProperty indexNumber = new SimpleIntegerProperty();
    private LocalDate birthday;
    private SimpleStringProperty identificationNumber = new SimpleStringProperty("");
    private SimpleIntegerProperty studyLevel = new SimpleIntegerProperty(1);
    private SimpleIntegerProperty studyYear = new SimpleIntegerProperty(1);
    private SimpleStringProperty address = new SimpleStringProperty("");
    private SimpleStringProperty email = new SimpleStringProperty("");
    private LocalDate enrolmentDate;

    public Student(String name, String lastname, int indexNumber, LocalDate birthday, String identificationNumber, int studyLevel, int year, String address, String email, LocalDate enrolmentDate) {
        this.name = new SimpleStringProperty(name);
        this.lastname = new SimpleStringProperty(lastname);
        this.indexNumber = new SimpleIntegerProperty(indexNumber);
        this.birthday = birthday;
        this.identificationNumber = new SimpleStringProperty(identificationNumber);
        this.studyLevel = new SimpleIntegerProperty(studyLevel);
        this.studyYear = new SimpleIntegerProperty(Integer.valueOf(year));
        this.address = new SimpleStringProperty(address);
        this.email = new SimpleStringProperty(email);
        this.enrolmentDate = enrolmentDate;
    }

    public Student () {}

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

    public int getIndexNumber() {
        return indexNumber.get();
    }

    public SimpleIntegerProperty indexNumberProperty() {
        return indexNumber;
    }

    public void setIndexNumber(int indexNumber) {
        this.indexNumber.set(indexNumber);
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getIdentificationNumber() {
        return identificationNumber.get();
    }

    public SimpleStringProperty identificationNumberProperty() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber.set(identificationNumber);
    }

    public int getStudyLevel() {
        return studyLevel.get();
    }

    public SimpleIntegerProperty studyLevelProperty() {
        return studyLevel;
    }

    public void setStudyLevel(int studyLevel) {
        this.studyLevel.set(studyLevel);
    }

    public int getStudyYear() {
        return studyYear.get();
    }

    public SimpleIntegerProperty studyYearProperty() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear.set(studyYear);
    }

    @Override
    public String toString () {
        String string = name.get() + " " + lastname.get() + " " + indexNumber.get();
        return string;
    }


    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public LocalDate getEnrolmentDate() {
        return enrolmentDate;
    }

    public void setEnrolmentDate(LocalDate enrolmentDate) {
        this.enrolmentDate = enrolmentDate;
    }
}
