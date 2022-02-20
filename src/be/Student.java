package be;


import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends Person{

    private List<Lecture> lectureList;
    private HashMap<Integer,Attendance> attendanceList;
    private String password;
    private String imageURL;

    public Student (int id, String name, String email, String password) {
        super(id, name, email, password);
        attendanceList = new HashMap<>();
        this.password=password;
    }

    public void validateParticipation(Lecture lecture) {

        if(checkParticipation(lecture)) {
            attendanceList.get(lecture.getId()).setPresence(true);
        }

    }
    public void removeParticipation(Lecture lecture) {
        if(checkParticipation(lecture)) {
            attendanceList.get(lecture.getId()).setPresence(false);
        }
    }

    /**
     * Will return true if the Lecture is associated to the student,
     * false otherwise.
     * */
    private boolean checkParticipation(Lecture lecture) {

        for(Map.Entry entry:attendanceList.entrySet()) {
            Attendance attendance = (Attendance) entry.getValue();

            if(attendance.getLecture().getId() == lecture.getId()) {
                return true;
            }
        }
        return false;
    }
    public List<Lecture> getLectureList() {
        return lectureList;
    }

    public void setLectureList(List<Lecture> lectureList) {
        this.lectureList = lectureList;
        for (Lecture lecture:lectureList) {
            attendanceList.put(lecture.getId(), new Attendance(false,lecture));
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
        double total= 100-((totalMissedClasses/lectureList.size())*100);
        return String.valueOf(total);
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {

        this.imageURL = imageURL;
    }
}
