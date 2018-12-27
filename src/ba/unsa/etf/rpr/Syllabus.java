package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Syllabus {
    private ArrayList<Student> firstYearOfBachelorStudents = new ArrayList<>();

    private Map<Subject, ArrayList<Student>> mapa = new HashMap<>();

    public Syllabus () {
        Subject computerScience = new Subject("Osnove računarstva", 1001, "Vedran Ljubović", 1);
        Subject physics = new Subject("Fizika", 1002, "Hasna Šamić", 1);
        Subject mathematics = new Subject("Matematika", 1003, "Naida Mujić", 1);
        Subject algebra = new Subject("Linearna algebra", 1004, "Almasa Odžak", 1);
        Subject electricalEngineering = new Subject("Osnove elektrotehnike", 1005, "Narcis Behlilović", 1);

        mapa.put(computerScience, firstYearOfBachelorStudents);
        mapa.put(physics, firstYearOfBachelorStudents);
        mapa.put(mathematics, firstYearOfBachelorStudents);
        mapa.put(algebra, firstYearOfBachelorStudents);
        mapa.put(electricalEngineering, firstYearOfBachelorStudents);
    };

    public ArrayList<Student> getFirstYearOfBachelor() {
        return firstYearOfBachelorStudents;
    }


    public void setFirstYearOfBachelor(ArrayList<Student> firstYearOfBachelor) {
        this.firstYearOfBachelorStudents = firstYearOfBachelor;
    }
}
