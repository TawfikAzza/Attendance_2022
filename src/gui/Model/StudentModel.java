package gui.Model;





import be.Course;
import be.Student;
import bll.PersonManager;

import java.util.HashMap;
import java.util.List;


public class StudentModel {
    private List<Course> courseList;
    private HashMap<Integer, Student> studentHashMap;
    PersonManager personManager;
    public StudentModel() {
        personManager = new PersonManager();
    }
    public Student getStudent(int idStudent) {
        return personManager.getStudent(idStudent);
    }

    public List<Student> getAllStudent() {
        return personManager.getAllStudent();
    }
}
