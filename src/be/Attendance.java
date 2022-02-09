package be;
/***
 * This class has been created in order to store the couple presence/lecture for a student.
 * each student object will possess a HashMap<Integer,Attendance> which will contain the lecture ID as key.
 * this will allow each student to have a lecture as well as a boolean associated to register his/her presence
 * to said lectures.
 *
 * */

public class Attendance {
    private boolean presence;
    private Lecture lecture;
    public Attendance(boolean presence,Lecture lecture){
        this.presence = presence;
        this.lecture = lecture;
    }

    public boolean isPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }
}
