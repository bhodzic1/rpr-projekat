package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Syllabus {
    private ArrayList<Student> firstYearOfBachelorStudents = new ArrayList<>();
    private ObservableList<String> subjectsOfFirstYear1 = FXCollections.observableArrayList("Fizika", "Matematika 1", "Osnove računarstva", "Osnove elektrotehnike", "Linearna algebra");
    private ObservableList<String> subjectsOfFirstYear2 = FXCollections.observableArrayList("Operativni sistemi", "Matematika 2", "Tehnike programiranja", "Vjerovatnoća i statistika", "Matematička logika");
    private ObservableList<String> subjectsOfSecondYear1 = FXCollections.observableArrayList("ASP", "RPR", "Diskretna matematika", "Sistemsko programiranje", "OBP", "Logički dizajn");
    private ObservableList<String> subjectsOfSecondYear2 = FXCollections.observableArrayList("OOAD", "RMA", "Računarske arhitekture", "AFJ", "Ugradbeni sistemi", "ORM");


    public Syllabus () {
        /*Subject computerScience = new Subject("Osnove računarstva", 1001, "Vedran Ljubović", 1);
        Subject physics = new Subject("Fizika", 1002, "Hasna Šamić", 1);
        Subject mathematics = new Subject("Matematika", 1003, "Naida Mujić", 1);
        Subject algebra = new Subject("Linearna algebra", 1004, "Almasa Odžak", 1);
        Subject electricalEngineering = new Subject("Osnove elektrotehnike", 1005, "Narcis Behlilović", 1);

        mapa.put(computerScience, firstYearOfBachelorStudents);
        mapa.put(physics, firstYearOfBachelorStudents);
        mapa.put(mathematics, firstYearOfBachelorStudents);
        mapa.put(algebra, firstYearOfBachelorStudents);
        mapa.put(electricalEngineering, firstYearOfBachelorStudents);*/
    };

    public ArrayList<Student> getFirstYearOfBachelor() {
        return firstYearOfBachelorStudents;
    }


    public void setFirstYearOfBachelor(ArrayList<Student> firstYearOfBachelor) {
        this.firstYearOfBachelorStudents = firstYearOfBachelor;
    }


    public ObservableList<String> getSubjectsOfSecondYear1() {
        return subjectsOfSecondYear1;
    }

    public void setSubjectsOfSecondYear1(ObservableList<String> subjectsOfSecondYear1) {
        this.subjectsOfSecondYear1 = subjectsOfSecondYear1;
    }

    public ObservableList<String> getSubjectsOfFirstYear1() {
        return subjectsOfFirstYear1;
    }

    public void setSubjectsOfFirstYear1(ObservableList<String> subjectsOfFirstYear1) {
        this.subjectsOfFirstYear1 = subjectsOfFirstYear1;
    }

    public ObservableList<String> getSubjectsOfFirstYear2() {
        return subjectsOfFirstYear2;
    }

    public void setSubjectsOfFirstYear2(ObservableList<String> subjectsOfFirstYear2) {
        this.subjectsOfFirstYear2 = subjectsOfFirstYear2;
    }

    public ObservableList<String> getSubjectsOfSecondYear2() {
        return subjectsOfSecondYear2;
    }

    public void setSubjectsOfSecondYear2(ObservableList<String> subjectsOfSecondYear2) {
        this.subjectsOfSecondYear2 = subjectsOfSecondYear2;
    }
}
