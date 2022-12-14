package Models;

import java.sql.Time;
import java.util.Date;

public class Sessions {
    private String sessionId;
    private Person doctor;
    private Date date;
    private Time time;
    private int maxPatients;
    private String sessionStatus;
    private int currentPatients;
    private Consultation[] consultations;

    public Sessions(String sessionId, Person doctor, Date date, Time time, int maxPatients, String sessionStatus, int currentPatients) {
        this.sessionId = sessionId;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.maxPatients = maxPatients;
        this.sessionStatus = sessionStatus;
        this.currentPatients = currentPatients;
        consultations = new Consultation[maxPatients];
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Person getDoctor() {
        return doctor;
    }

    public void setDoctor(Person doctor) {
        this.doctor = doctor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getMaxPatients() {
        return maxPatients;
    }

    public void setMaxPatients(int maxPatients) {
        this.maxPatients = maxPatients;
    }

    public String getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public int getCurrentPatients() {
        return currentPatients;
    }

    public void setCurrentPatients(int currentPatients) {
        this.currentPatients = currentPatients;
    }

    public Consultation[] getConsultations() {
        return consultations;
    }

    public void setConsultations(Consultation[] consultations) {
        this.consultations = consultations;
    }

    public void addConsultation(Consultation consultation){
        if (currentPatients < maxPatients){
            consultations[currentPatients] = consultation;
            currentPatients ++;
        }
    }
}
