package ba.unsa.etf.rpr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class CollegeDAO {
    private static CollegeDAO instance;
    private Connection conn;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MM. yyyy");

    private PreparedStatement studentQuery, getStudentsQuery, setStudentIdQuery, addStudentQuery;

    public static CollegeDAO getInstance() {
        if (instance == null) instance = new CollegeDAO();
        return instance;
    }

    private CollegeDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            studentQuery = conn.prepareStatement("SELECT student.name FROM student WHERE student.index_number = 1");
        } catch (SQLException e) {
            regenerateBase();
            try {
                studentQuery = conn.prepareStatement("SELECT student.name FROM student WHERE student.index_number = 1");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }
        try {
            getStudentsQuery = conn.prepareStatement("SELECT * FROM student WHERE study_level = ? AND study_year = ?");
            setStudentIdQuery = conn.prepareStatement("SELECT MAX(id)+1 FROM student");
            addStudentQuery = conn.prepareStatement("INSERT INTO student VALUES(?,?,?,?,?,?,?,?,?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeInstance() {
        if (instance == null) return;
        instance.close();
        instance = null;
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void regenerateBase() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream("baza.db.sql"));
            String sqlUpit = "";
            while (scanner.hasNext()) {
                sqlUpit += scanner.nextLine();
                if ( sqlUpit.charAt( sqlUpit.length()-1 ) == ';') {
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void ispisi(){
        try {
            ResultSet resultSet = studentQuery.executeQuery();
            String string = resultSet.getString(1);
            System.out.println(string);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private Student getStudentFromResultSet(ResultSet rs) throws SQLException {
        Student student = new Student(rs.getString(1), rs.getString(2), rs.getInt(3), LocalDate.parse(rs.getString(4), formatter), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9));
        return student;
    }

    public ArrayList<Student> students(int studyLevel, int studyYear) {
        ArrayList<Student> result = new ArrayList<>();

        try {
            getStudentsQuery.setInt(1, studyLevel);
            getStudentsQuery.setInt(2, studyYear);
            ResultSet resultSet = getStudentsQuery.executeQuery();
            while (resultSet.next()) {
                Student student = getStudentFromResultSet(resultSet);
                result.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void addStudent (Student student) {
        try {
            ResultSet resultSet = setStudentIdQuery.executeQuery();
            int id = 1;

            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }

            addStudentQuery.setString(1, student.getName());
            addStudentQuery.setString(2, student.getLastname());
            addStudentQuery.setInt(3, id);
            addStudentQuery.setString(4, formatter.format(student.getBirthday()));
            addStudentQuery.setString(5, student.getIdentificationNumber());
            addStudentQuery.setInt(6, student.getStudyLevel());
            addStudentQuery.setInt(7, student.getStudyYear());
            addStudentQuery.setString(8, student.getAddress());
            addStudentQuery.setString(9, student.getEmail());
            addStudentQuery.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
