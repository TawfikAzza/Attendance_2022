package be;

import java.util.List;

public class Teacher extends Person{
    private List<Student> studentList;

    public Teacher(int id, String name, String email, String password) {
        super(id, name, email, password);
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}

