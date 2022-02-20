package gui.Model;





import be.Lecture;
import be.Student;
import bll.DateUtil;
import bll.PersonManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class StudentModel {
    private List<Lecture> lectureList;
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
    public List <Lecture>getAllLectures(){
        return personManager.getLectureList();
    };
}
