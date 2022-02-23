package bll;


import be.Lecture;
import be.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

public class PersonManager {
    private final HashMap<Integer, Student> mapStudent;
    private ObservableList<String> topAbsentStudents ;

    public List<Lecture> getLectureList() {
        return lectureList;
    }

    List<Lecture> lectureList;

    public PersonManager() {
        LocalDate now = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();

        /**
         * Creating mock data
         *
         * */
        lectureList = new ArrayList<>();

        Lecture lecture1 = new Lecture(1, "SCO2", now.with(fieldISO, 1));
        Lecture lecture2 = new Lecture(2, "SDE2", now.with(fieldISO, 2));
        Lecture lecture3 = new Lecture(3, "ITO2", now.with(fieldISO, 3));
        Lecture lecture4 = new Lecture(4, "DBOS2", now.with(fieldISO, 4));
        Lecture lecture5 = new Lecture(5, "Football", now.with(fieldISO, 5));
        LocalDate nextWeek = now.plusDays(7);
        Lecture lecture6 = new Lecture(6, "SDE2", nextWeek.with(fieldISO, 1));
        Lecture lecture7 = new Lecture(7, "ITO2", nextWeek.with(fieldISO, 2));
        Lecture lecture8 = new Lecture(8, "DBOS2", nextWeek.with(fieldISO, 3));
        Lecture lecture9 = new Lecture(9, "SCO2", nextWeek.with(fieldISO, 4));
        Lecture lecture10 = new Lecture(10, "BasketBall", nextWeek.with(fieldISO, 5));
        LocalDate previousWeek = now.minusDays(7);
        Lecture lecture11 = new Lecture(11, "SDE2", previousWeek.with(fieldISO, 1));
        Lecture lecture12 = new Lecture(12, "ITO2", previousWeek.with(fieldISO, 2));
        Lecture lecture13 = new Lecture(13, "DBOS2", previousWeek.with(fieldISO, 3));
        Lecture lecture14 = new Lecture(14, "SCO2", previousWeek.with(fieldISO, 4));
        Lecture lecture15 = new Lecture(15, "BasketBall", previousWeek.with(fieldISO, 5));
        lectureList.add(lecture1);
        lectureList.add(lecture2);
        lectureList.add(lecture3);
        lectureList.add(lecture4);
        lectureList.add(lecture5);
        lectureList.add(lecture6);
        lectureList.add(lecture7);
        lectureList.add(lecture8);
        lectureList.add(lecture9);
        lectureList.add(lecture10);
        lectureList.add(lecture11);
        lectureList.add(lecture12);
        lectureList.add(lecture13);
        lectureList.add(lecture14);
        lectureList.add(lecture15);


        mapStudent = new HashMap<>();

        Student student1 = new Student(1, "Tawfik Azza", "tawf0021@easv.dk", "test");


        student1.setImageURL("Taoufik.jpg");
        Student student2 = new Student(2, "Amin Aouina", "amin@easv.dk", "test");
        student2.setImageURL("images/Avatar.jpg");
        Student student3 = new Student(3, "Mads Madsen", "madsmadsen@easv.dk", "test");
        Student student4 = new Student(4, "Marcus Iversen", "marcusi@easv.dk", "test");
        Student student5 = new Student(5, "Milos Josek", "milosjosek@easv.dk", "test");
        Student student6 = new Student(6, "Matej Kuka", "matejkuka@easv.dk", "test");
        Student student7 = new Student(7, "Renars Madnieks", "RenarsMadnieks@easv.dk", "test");
        student7.setImageURL("images/Renars.png");
        Student student8 = new Student(8, "Mareike Steffens", "mareikesteffens@easv.dk", "test");
        Student student9 = new Student(9, "Chantal Brockmeyer", "chantalbrock@easv.dk", "test");
        Student student10 = new Student(10, "Adam Lörincz", "adamlorincz@easv.dk", "test");

        Student student11 = new Student(11, "Mustafa Ahmad Alwaro", "must2765@easv365.dk", "test");
        Student student12 = new Student(12, "Mikkel Bie", "mikk674g@easv365.dk", "test");
        Student student13 = new Student(13, "Diogo Da Costa Queijo", "diog0044@easv365.dk", "test");
        Student student14 = new Student(14, "Þorvaldur Máni Danivalsson", "thor9734@easv365.dk", "test");
        Student student15 = new Student(15, "Tunay Erkush", "tuna0043@easv365.dk", "test");
        Student student16 = new Student(16, "Zahra Gharahchomaghloo", "zahr1185@easv365.dk", "test");
        Student student17 = new Student(17, "Daniel Gislason", "dani65h7@easv365.dk", "test");
        student7.setImageURL("images/Renars.png");
        Student student18 = new Student(18, "Filip Kobialka", "fili5668@easv365.dk", "test");
        Student student19 = new Student(19, "Roman Masár", "roma0966@easv365.dk", "test");
        Student student20 = new Student(20, "Sergio Moreno Martinez", "serg0971@easv365.dk", "test");

        mapStudent.put(student1.getId(), student1);
        mapStudent.put(student2.getId(), student2);
        mapStudent.put(student3.getId(), student3);
        mapStudent.put(student4.getId(), student4);
        mapStudent.put(student5.getId(), student5);
        mapStudent.put(student6.getId(), student6);
        mapStudent.put(student7.getId(), student7);
        mapStudent.put(student8.getId(), student8);
        mapStudent.put(student9.getId(), student9);
        mapStudent.put(student10.getId(), student10);
        mapStudent.put(student11.getId(), student11);
        mapStudent.put(student12.getId(), student12);
        mapStudent.put(student13.getId(), student13);
        mapStudent.put(student14.getId(), student14);
        mapStudent.put(student15.getId(), student15);
        mapStudent.put(student16.getId(), student16);
        mapStudent.put(student17.getId(), student17);
        mapStudent.put(student18.getId(), student18);
        mapStudent.put(student19.getId(), student19);
        mapStudent.put(student20.getId(), student20);


        /**
         * Assigning the lectures to the students and validating participation
         * */

        for (Student student : mapStudent.values()) {
            student.setLectureList(lectureList);
            student.setEducation("Computer Science");
        }


        /**
         * Validating lectures so the data has some things to look for in the future form (FXML)
         * **/

        student1.validateParticipation(lecture1);
        //student1.validateParticipation(lecture3);
        student2.validateParticipation(lecture2);
        student2.validateParticipation(lecture3);
        student3.validateParticipation(lecture1);
        student4.validateParticipation(lecture1);
        student5.validateParticipation(lecture1);
        student6.validateParticipation(lecture1);
        student7.validateParticipation(lecture1);
        student8.validateParticipation(lecture1);
        student9.validateParticipation(lecture1);
        student10.validateParticipation(lecture5);

        /**
         * Validating presence for the week before for all the students (data test for Amin)
         * */
        student1.validateParticipation(lecture11);
        student1.validateParticipation(lecture12);
        student1.validateParticipation(lecture13);
        student1.validateParticipation(lecture14);
        student1.validateParticipation(lecture15);
        student2.validateParticipation(lecture11);
        student2.validateParticipation(lecture12);
        student2.validateParticipation(lecture13);
        student2.validateParticipation(lecture14);
        student2.validateParticipation(lecture15);
        student3.validateParticipation(lecture11);
        student3.validateParticipation(lecture12);
        student3.validateParticipation(lecture13);
        student3.validateParticipation(lecture14);
        student3.validateParticipation(lecture15);
        student4.validateParticipation(lecture11);
        student4.validateParticipation(lecture12);
        student4.validateParticipation(lecture13);
        student4.validateParticipation(lecture14);
        student4.validateParticipation(lecture15);
        student5.validateParticipation(lecture11);
        student5.validateParticipation(lecture12);
        student5.validateParticipation(lecture13);
        student5.validateParticipation(lecture14);
        student5.validateParticipation(lecture15);
        student6.validateParticipation(lecture11);
        student6.validateParticipation(lecture12);
        student6.validateParticipation(lecture13);
        student6.validateParticipation(lecture14);
        student6.validateParticipation(lecture15);
        student7.validateParticipation(lecture11);
        student7.validateParticipation(lecture12);
        student7.validateParticipation(lecture13);
        student7.validateParticipation(lecture14);
        student7.validateParticipation(lecture15);
        student8.validateParticipation(lecture11);
        student8.validateParticipation(lecture12);
        student8.validateParticipation(lecture13);
        student8.validateParticipation(lecture14);
        student8.validateParticipation(lecture15);
        student9.validateParticipation(lecture11);
        student9.validateParticipation(lecture12);
        student9.validateParticipation(lecture13);
        student9.validateParticipation(lecture14);
        student9.validateParticipation(lecture15);
        student10.validateParticipation(lecture12);
        student10.validateParticipation(lecture13);
        student10.validateParticipation(lecture14);
        student10.validateParticipation(lecture15);

        student11.validateParticipation(lecture11);
        student11.validateParticipation(lecture12);
        student11.validateParticipation(lecture13);
        student11.validateParticipation(lecture14);
        student11.validateParticipation(lecture15);
        student12.validateParticipation(lecture11);
        student12.validateParticipation(lecture12);
        student12.validateParticipation(lecture13);
        student12.validateParticipation(lecture14);
        student12.validateParticipation(lecture15);
        student13.validateParticipation(lecture11);
        student13.validateParticipation(lecture12);
        student13.validateParticipation(lecture13);
        student13.validateParticipation(lecture14);
        student13.validateParticipation(lecture15);
        student14.validateParticipation(lecture11);
        student14.validateParticipation(lecture12);
        student14.validateParticipation(lecture13);
        student14.validateParticipation(lecture14);
        student14.validateParticipation(lecture15);
        student15.validateParticipation(lecture11);
        student15.validateParticipation(lecture12);
        student15.validateParticipation(lecture13);
        student15.validateParticipation(lecture14);
        student15.validateParticipation(lecture15);
        student16.validateParticipation(lecture11);
        student16.validateParticipation(lecture12);
        student16.validateParticipation(lecture13);
        student16.validateParticipation(lecture14);
        student16.validateParticipation(lecture15);
        student17.validateParticipation(lecture11);
        student17.validateParticipation(lecture12);
        student17.validateParticipation(lecture13);
        student17.validateParticipation(lecture14);
        student17.validateParticipation(lecture15);
        student18.validateParticipation(lecture11);
        student18.validateParticipation(lecture12);
        student18.validateParticipation(lecture13);
        student18.validateParticipation(lecture14);
        student18.validateParticipation(lecture15);
        student19.validateParticipation(lecture11);
        student19.validateParticipation(lecture12);
        student19.validateParticipation(lecture13);
        student19.validateParticipation(lecture14);
        student19.validateParticipation(lecture15);
        student20.validateParticipation(lecture11);
        student20.validateParticipation(lecture12);
        student20.validateParticipation(lecture13);
        student20.validateParticipation(lecture14);
        student20.validateParticipation(lecture15);

    }

    public Student getStudent(int id) {
        return mapStudent.get(id);
    }

    public List<Student> getAllStudent() {
        return new ArrayList<>(mapStudent.values());
    }

    public ObservableList getTopAbsentStudents() {
        topAbsentStudents = FXCollections.observableArrayList();
        List<Student>allStudents = getAllStudent();
        allStudents.sort(Comparator.comparing(Student::getAttendance));
        for (int i=1; i<=10;i ++){
            topAbsentStudents.add(allStudents.get(i).getName()+" : "+allStudents.get(i).getAttendance()+"%");
        }
        return topAbsentStudents;
    }
}
