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

}
