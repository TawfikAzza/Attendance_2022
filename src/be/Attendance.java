package be;

/***
 * This class was created in order to store both presence/lecture infos for a student.
 * each student object will hold a HashMap<Integer,Attendance> which will contain the lecture ID as key.
 * this will allow each student to have a lecture as well as a boolean associated to register his/her presence
 * to said lectures.
 *
 * */

public class Attendance {
    private boolean presence;
    private Course course;
    public Attendance(boolean presence, Course course){
        this.presence = presence;
        this.course = course;
    }

    public boolean isPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }

    public Course getLecture() {
        return course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setLecture(Course course) {
        this.course = course;
    }

}
