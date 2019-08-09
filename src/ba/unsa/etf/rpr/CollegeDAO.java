package ba.unsa.etf.rpr;

import javax.xml.transform.Result;
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

    private PreparedStatement studentQuery, getStudentsQuery, setStudentIdQuery, addStudentQuery, getSubjectsQuery, proba, getStudentsWithSubjectId,
            getGradeForStudent, getStudentWithId, getSubjectReportModel, getNumberOfStudentsOnSubject, getNumberOfPassedStudentsOnSubject,
            getStudentReportModel, getMaxIdProfessor, getUsernamesFromProfessor, addProfessorQuery, setIdProfessor, getNamesProfessor,
            getIdProfessorFromNameAndLastname, addSubjectQuery, getMaxIdSubject, getDataForListOfProfessors, deleteProfessorQuery, addActiveUser,
            getDataFromActive, getDataFromLogin, deleteAllFromActive, setLabelForActiveUser, addUserIntoLogin, updateLogin, updateProfessor,
            getAllSubjects, deleteSubjectQuery;

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
            setStudentIdQuery = conn.prepareStatement("SELECT MAX(index_number)+1 FROM student");
            addStudentQuery = conn.prepareStatement("INSERT INTO student VALUES(?,?,?,?,?,?,?,?,?,?)");
            addProfessorQuery = conn.prepareStatement("INSERT INTO professor VALUES(?,?,?,?,?,?,?)");
            addUserIntoLogin = conn.prepareStatement("INSERT INTO login VALUES(?,?)");
            addSubjectQuery = conn.prepareStatement("INSERT INTO subject VALUES(?,?,?,?)");
            getSubjectsQuery = conn.prepareStatement("SELECT name FROM subject WHERE semester = ?");
            getStudentsWithSubjectId = conn.prepareStatement("SELECT student FROM grade WHERE subject = ?");
            getGradeForStudent = conn.prepareStatement("SELECT grade FROM grade WHERE student = ? AND subject = ?");
            getStudentWithId = conn.prepareStatement("SELECT * FROM student WHERE index_number = ?");
            getSubjectReportModel = conn.prepareStatement("SELECT student.name, student.lastname, grade.id, grade.grade FROM student, grade WHERE student.index_number = grade.student AND grade.subject = ?");
            getNumberOfStudentsOnSubject = conn.prepareStatement("SELECT COUNT(grade.id) FROM grade, subject WHERE grade.subject = ? AND grade.subject = subject.id");
            getNumberOfPassedStudentsOnSubject = conn.prepareStatement("SELECT COUNT(grade.id) FROM grade, subject WHERE grade.grade > 5 AND grade.subject = ? AND subject.id = grade.subject");
            getStudentReportModel = conn.prepareStatement("SELECT subject.name, grade.grade, grade.date, professor.name || ' ' || professor.lastname FROM student, subject, grade, professor WHERE student.index_number = ? AND student.index_number = grade.student AND grade.subject = subject.id AND subject.professor = professor.id");
            getMaxIdProfessor = conn.prepareStatement("SELECT MAX(id)+1 FROM professor");
            getUsernamesFromProfessor = conn.prepareStatement("SELECT username FROM professor");
            getNamesProfessor = conn.prepareStatement("SELECT name || ' ' || lastname FROM professor");
            getIdProfessorFromNameAndLastname = conn.prepareStatement("SELECT id FROM professor WHERE name || ' ' || lastname = ?");
            getMaxIdSubject = conn.prepareStatement("SELECT MAX(id)+1 FROM subject");
            getDataForListOfProfessors = conn.prepareStatement("SELECT * FROM professor");
            deleteProfessorQuery = conn.prepareStatement("DELETE FROM professor WHERE name = ? AND lastname = ? AND birthday = ? AND employment_date = ?");
            deleteSubjectQuery = conn.prepareStatement("DELETE FROM subject WHERE id = ?");
            addActiveUser = conn.prepareStatement("INSERT  INTO active VALUES(?,?)");
            getDataFromActive = conn.prepareStatement("SELECT username FROM active");
            getDataFromLogin = conn.prepareStatement("SELECT username, password FROM login WHERE username = ? AND password = ?");
            deleteAllFromActive = conn.prepareStatement("DELETE FROM active");
            setLabelForActiveUser = conn.prepareStatement("SELECT name || ' ' || lastname FROM professor WHERE username = ?");
            updateLogin = conn.prepareStatement("UPDATE login SET username = ?, password = ? WHERE username = ?");
            updateProfessor = conn.prepareStatement("UPDATE professor SET username = ?, password = ? WHERE username = ?");
            getAllSubjects = conn.prepareStatement("SELECT * FROM subject");



            proba = conn.prepareStatement("SELECT * FROM subject WHERE semester = ?");

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


    private Student getStudentFromResultSet (ResultSet rs) throws SQLException {
        Student student = new Student(rs.getString(1), rs.getString(2), rs.getInt(3), LocalDate.parse(rs.getString(4), formatter), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), LocalDate.parse(rs.getString(10), formatter));
        return student;
    }

    private Subject getSubjectFromResultSet (ResultSet rs) throws SQLException {
        Subject subject = new Subject(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
        return subject;
    }

    private Professor getProfessorFromResultSet (ResultSet rs) throws SQLException {
        Professor professor = new Professor(rs.getInt(1), rs.getString(2), rs.getString(3), LocalDate.parse(rs.getString(4), formatter), rs.getString(5), rs.getString(6),LocalDate.parse(rs.getString(7), formatter));
        return professor;
    }

    private Grade getGradeFromResultSet (ResultSet rs) throws SQLException {
        Grade grade = new Grade(rs.getInt(1), rs.getInt(2), LocalDate.parse(rs.getString(3), formatter), rs.getInt(4), rs.getInt(5));
        return grade;
    }

    private SubjectReportModel getSubjectReportModelFromResultSet (ResultSet rs) throws SQLException {
        SubjectReportModel model = new SubjectReportModel(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
        return model;
    }

    private StudentsModel getStudentModelFromResultSet (ResultSet rs) throws SQLException {
        StudentsModel model = new StudentsModel(rs.getString(1), rs.getInt(2), LocalDate.parse(rs.getString(3), formatter), rs.getString(4));
        return model;
    }

    public ArrayList<Student> students (int studyLevel, int studyYear) {
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

    public ArrayList<Subject> subjects (int semester) {
        ArrayList<Subject> subjects = new ArrayList<>();

        try {
            proba.setInt(1, semester);
            ResultSet resultSet = proba.executeQuery();
            while (resultSet.next()) {
                Subject subject = getSubjectFromResultSet(resultSet);
                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    public ArrayList<Professor> getAllProfessors () {
        ArrayList<Professor> list = new ArrayList<>();

        try {

            ResultSet resultSet = getDataForListOfProfessors.executeQuery();
            while (resultSet.next()) {
                Professor professor = getProfessorFromResultSet(resultSet);
                list.add(professor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Subject> getAllSubjects () {
        ArrayList<Subject> list = new ArrayList<>();

        try {

            ResultSet resultSet = getAllSubjects.executeQuery();
            while (resultSet.next()) {
                Subject subject = getSubjectFromResultSet(resultSet);
                list.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
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
            addStudentQuery.setString(10, formatter.format(student.getEnrolmentDate()));
            addStudentQuery.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProfessor (Professor professor) {
        try {
                ResultSet resultSet = getMaxIdProfessor.executeQuery();
                int id = 1;

                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                addProfessorQuery.setInt(1, id);
                addProfessorQuery.setString(2, professor.getName());
                addProfessorQuery.setString(3, professor.getLastname());
                addProfessorQuery.setString(4, formatter.format(professor.getBirthday()));
                addProfessorQuery.setString(5, professor.getUsername());
                addProfessorQuery.setString(6, professor.getPassword());
                addProfessorQuery.setString(7, formatter.format(professor.getEmploymentDay()));

                addProfessorQuery.executeUpdate();

                addUserIntoLogin.setString(1, professor.getUsername());
                addUserIntoLogin.setString(2, professor.getPassword());

                addUserIntoLogin.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSubject (Subject subject) {
        try {
            ResultSet resultSet = getMaxIdSubject.executeQuery();
            int id = 1;

            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }

            addSubjectQuery.setInt(1, id);
            addSubjectQuery.setString(2, subject.getName());
            addSubjectQuery.setInt(3, subject.getProfessor());
            addSubjectQuery.setInt(4, subject.getSemester());

            addSubjectQuery.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addActiveUser (String username, String password) {
        try {
            addActiveUser.setString(1, username);
            addActiveUser.setString(2, password);
            addActiveUser.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getMaxId () {
        int id = 1;
        try {
            ResultSet resultSet = setStudentIdQuery.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public int getMaxIdProfessor () {
        int id = 1;
        try {
            ResultSet resultSet = getMaxIdProfessor.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public int getMaxIdSubject () {
        int id = 1;
        try {
            ResultSet resultSet = getMaxIdSubject.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public ArrayList<Grade> getGradeWithSubjectId (int id) {
        ArrayList<Grade> grades = new ArrayList<>();

        try {
            getStudentsWithSubjectId.setInt(1, id);
            ResultSet resultSet = getStudentsWithSubjectId.executeQuery();

            while (resultSet.next()) {
                Grade grade = getGradeFromResultSet(resultSet);
                grades.add(grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grades;
    }

    public ArrayList<SubjectReportModel> getSubjectReportModel (int id) {
        ArrayList<SubjectReportModel> models = new ArrayList<>();
        try {
            getSubjectReportModel.setInt(1, id);
            ResultSet resultSet = getSubjectReportModel.executeQuery();

            while (resultSet.next()) {
                SubjectReportModel model = getSubjectReportModelFromResultSet(resultSet);
                models.add(model);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return models;
    }

    public ArrayList<StudentsModel> getStudentModel (int id) {
        ArrayList<StudentsModel> models = new ArrayList<>();
        try {
            getStudentReportModel.setInt(1, id);
            ResultSet resultSet = getStudentReportModel.executeQuery();

            while (resultSet.next()) {
                StudentsModel model = getStudentModelFromResultSet(resultSet);
                models.add(model);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return models;
    }

    public int getNumberOfStudentsOnSubject (int id) {
        int number = 0;

        try {
            getNumberOfStudentsOnSubject.setInt(1, id);
            ResultSet resultSet = getNumberOfStudentsOnSubject.executeQuery();
            while (resultSet.next()) {
                number++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return number;
    }

    public int getNumberOfPassedStudentsOnSubject (int id) {
        int number = 0;

        try {
            getNumberOfPassedStudentsOnSubject.setInt(1, id);
            ResultSet resultSet = getNumberOfPassedStudentsOnSubject.executeQuery();
            while (resultSet.next()) {
                number++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return number;
    }

    public ArrayList<String> getUsernameFromProfessor () {
        ArrayList<String> list = new ArrayList<>();
        try {
            ResultSet resultSet = getUsernamesFromProfessor.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }

    public ArrayList<String> getNamesProfessor () {
        ArrayList<String> list = new ArrayList<>();
        try {
            ResultSet resultSet = getNamesProfessor.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getIdProfessorFromNameAndLastname (String name) {
        int id = 0;
        try {
            getIdProfessorFromNameAndLastname.setString(1, name);
            ResultSet resultSet = getIdProfessorFromNameAndLastname.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void deleteProfessor (String name, String lastname, LocalDate birthday, LocalDate employmentDay) {
        try {
            deleteProfessorQuery.setString(1, name);
            deleteProfessorQuery.setString(2, lastname);
            deleteProfessorQuery.setString(3, formatter.format(birthday));
            deleteProfessorQuery.setString(4, formatter.format(employmentDay));
            deleteProfessorQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSubject (int id) {
        try {
            deleteSubjectQuery.setInt(1, id);
            deleteSubjectQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkLogin (String username, String password) {
        boolean check = false;
        try {
            getDataFromLogin.setString(1, username);
            getDataFromLogin.setString(2, password);
            ResultSet resultSet = getDataFromLogin.executeQuery();
            if (resultSet.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return check;
    }

    public void deleteAllFromActive () {
        try {
            deleteAllFromActive.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getActiveUser (String username) {
        String result = null;
        try {
            setLabelForActiveUser.setString(1, username);
            ResultSet resultSet = setLabelForActiveUser.executeQuery();
            result = resultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getUsernameFromActive () {
        String string = null;
        try {
            ResultSet resultSet =  getDataFromActive.executeQuery();
            string = resultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return string;
    }

    public void updateLogin (String username, String password, String oldUsername) {
        try {
            updateLogin.setString(1, username);
            updateLogin.setString(2, password);
            updateLogin.setString(3, oldUsername);

            updateLogin.executeUpdate();

            updateProfessor.setString(1, username);
            updateProfessor.setString(2, password);
            updateProfessor.setString(3, oldUsername);

            updateProfessor.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
