package bll;


import be.Lecture;
import be.Student;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class PersonManager {
    HashMap<Integer,Student> mapStudent;
    List<Lecture> lectureList;
    public PersonManager() {
        LocalDate now = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
        //System.out.println("first day is : "+now.with(fieldISO, 1));
        /**
         * Creating the lectures mock datas
         *
         * */
        lectureList = new ArrayList<>();
    // filling the dates of the lecture with the current dates of the week (for the test)
        Lecture lecture1 = new Lecture(1,"SCO2", now.with(fieldISO, 1));
        Lecture lecture2 = new Lecture(2,"SDE2", now.with(fieldISO, 2));
        Lecture lecture3 = new Lecture(3,"ITO2", now.with(fieldISO, 3));
        Lecture lecture4 = new Lecture(4,"DBOS2", now.with(fieldISO, 4));
        Lecture lecture5 = new Lecture(5,"SCO2/SDE2", now.with(fieldISO, 5));

        lectureList.add(lecture1);
        lectureList.add(lecture2);
        lectureList.add(lecture3);
        lectureList.add(lecture4);
        lectureList.add(lecture5);

        mapStudent = new HashMap<>();

        /**
         * Creating the Student Mock Datas and adding them in the HashMap for easy search through future methods
         * */
        Student student1 = new Student(1,"Tawfik Azza","tawf0021@easv.dk","test");
        Student student2 = new Student(2,"Amin Aouina","amin@easv.dk","test");
        Student student3 = new Student(3,"Mads Madsen","madsmadsen@easv.dk","test");
        Student student4 = new Student(4,"Marcus Iversen","marcusi@easv.dk","test");
        Student student5 = new Student(5,"Milos Josek","milosjosek@easv.dk","test");
        Student student6 = new Student(6,"Matej Kuka","matejkuka@easv.dk","test");
        Student student7 = new Student(7,"Mikkel Bie","mikkelbie@easv.dk","test");
        Student student8 = new Student(8,"Mareike Steffens","mareikesteffens@easv.dk","test");
        Student student9 = new Student(9,"Chantal Brockmeyer","chantalbrock@easv.dk","test");
        Student student10 = new Student(10,"Adam Lörincz","adamlorincz@easv.dk","test");
        mapStudent.put(student1.getId(),student1);
        mapStudent.put(student2.getId(),student2);
        mapStudent.put(student3.getId(),student3);
        mapStudent.put(student4.getId(),student4);
        mapStudent.put(student5.getId(),student5);
        mapStudent.put(student6.getId(),student6);
        mapStudent.put(student7.getId(),student7);
        mapStudent.put(student8.getId(),student8);
        mapStudent.put(student9.getId(),student9);
        mapStudent.put(student10.getId(),student10);

        /**
         * Assigning the lectures to the students.
         * */
        student1.setLectureList(lectureList);
        student2.setLectureList(lectureList);
        student3.setLectureList(lectureList);
        student4.setLectureList(lectureList);
        student5.setLectureList(lectureList);
        student6.setLectureList(lectureList);
        student7.setLectureList(lectureList);
        student8.setLectureList(lectureList);
        student9.setLectureList(lectureList);
        student10.setLectureList(lectureList);

        /**
         * Validating lectures so the data has some things to look for in the future form (FXML)
         * **/

        student1.validateParticipation(lecture1);
        student1.validateParticipation(lecture3);
        student2.validateParticipation(lecture1);
        student2.validateParticipation(lecture3);
        student3.validateParticipation(lecture1);
        student4.validateParticipation(lecture1);
        student5.validateParticipation(lecture1);
        student6.validateParticipation(lecture1);
        student7.validateParticipation(lecture1);
        student8.validateParticipation(lecture1);
        student9.validateParticipation(lecture1);
        student10.validateParticipation(lecture5);


    }
    public Student getStudent(int id) {
        return mapStudent.get(id);
    }
    public List<Student> getAllStudent(){
        List<Student> studentList= new ArrayList<>(mapStudent.values());
        return studentList;
    }
}
