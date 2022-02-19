package be;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends Person{

    private List<Course> courseList;
    private HashMap<Integer,Attendance> attendanceList;
    private String password;

    public Student (int id, String name, String email, String password) {
        super(id, name, email, password);
        attendanceList = new HashMap<>();
        this.password=password;
    }

    public void validateParticipation(Course course) {

        if(checkParticipation(course)) {
            attendanceList.get(course.getId()).setPresence(true);
        }

    }
    public void removeParticipation(Course course) {
        if(checkParticipation(course)) {
            attendanceList.get(course.getId()).setPresence(false);
        }
    }

    /**
     * Will return true if the Lecture is associated to the student,
     * false otherwise.
     * */
    private boolean checkParticipation(Course course) {

        for(Map.Entry entry:attendanceList.entrySet()) {
            Attendance attendance = (Attendance) entry.getValue();

            if(attendance.getLecture().getId() == course.getId()) {
                return true;
            }
        }
        return false;
    }
    public List<Course> getLectureList() {
        return courseList;
    }

    public void setLectureList(List<Course> courseList) {
        this.courseList = courseList;
        for (Course course : courseList) {
            attendanceList.put(course.getId(), new Attendance(false, course));
        }
    }

    public HashMap<Integer, Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(HashMap<Integer, Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public String getAttendance() {
        double totalMissedClasses=0;
        for (Attendance attendance: attendanceList.values()) {
            if(!attendance.isPresence()) {
                totalMissedClasses+=1;
            }
        }
        double total= 100-((totalMissedClasses/ courseList.size())*100);
        return total +" %";
    }
}
