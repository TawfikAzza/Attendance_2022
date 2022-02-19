package bll;


import be.Course;
import be.Student;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

public class PersonManager {
    HashMap<Integer,Student> mapStudent;
    List<Course> courseList;
    public PersonManager() {
        LocalDate now = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();

        /**
         * Creating mock data
         *
         * */
        courseList = new ArrayList<>();

        Course course1 = new Course(1,"SCO");
        HashMap<Integer,ArrayList<String>>allLecturesCourse1=new HashMap<>();
        allLecturesCourse1.put(1,new ArrayList<>(Arrays.asList("12:00","14:30")));
        allLecturesCourse1.put(3,new ArrayList<>(Arrays.asList("09:00","11:30")));
        allLecturesCourse1.put(5,new ArrayList<>(Arrays.asList("09:00","11:30")));
        course1.setAllLectures(allLecturesCourse1);
        courseList.add(course1);



        Course course2 = new Course(2,"SDE");
        HashMap<Integer,ArrayList<String>>allLecturesCourse2=new HashMap<>();

        allLecturesCourse2.put(2,new ArrayList<>(Arrays.asList("09:00","13:30")));
        course2.setAllLectures(allLecturesCourse2);
        courseList.add(course2);

        Course course3 = new Course(3,"ITO");
        HashMap<Integer,ArrayList<String>>allLecturesCourse3=new HashMap<>();

        allLecturesCourse3.put(4,new ArrayList<>(Arrays.asList("12:00","15:15")));
        course3.setAllLectures(allLecturesCourse3);
        courseList.add(course3);

        Course course4 = new Course(4,"DBOS");
        HashMap<Integer,ArrayList<String>>allLecturesCourse4=new HashMap<>();

        allLecturesCourse4.put(3,new ArrayList<>(Arrays.asList("08:15","11:30")));
        courseList.add(course4);
        course4.setAllLectures(allLecturesCourse4);



        mapStudent = new HashMap<>();

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
         * Assigning the lectures to the students and validating participation
         * */

        for (Student student:mapStudent.values()){
            student.setLectureList(courseList);
        }


        /**
         * Validating lectures so the data has some things to look for in the future form (FXML)
         * **/

        student1.validateParticipation(course1);
        student1.validateParticipation(course3);
        student2.validateParticipation(course2);
        student2.validateParticipation(course3);
        student3.validateParticipation(course1);
        student4.validateParticipation(course1);
        student5.validateParticipation(course1);
        student6.validateParticipation(course1);
        student7.validateParticipation(course1);
        student8.validateParticipation(course1);
        student9.validateParticipation(course1);


    }
    public Student getStudent(int id) {
        return mapStudent.get(id);
    }
    public List<Student> getAllStudent(){
        List<Student> studentList= new ArrayList<>(mapStudent.values());
        return studentList;
    }
}
