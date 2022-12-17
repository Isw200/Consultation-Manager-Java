package Models;

import Interfaces.Printable;

import java.sql.Time;
import java.util.Date;

public class Sessions implements Printable {
    private String sessionId;
    private Person doctor;
    private Date date;
    private Date time;
    private int maxPatients;
    private String sessionStatus; // Active, Ongoing, On Hold;
    private int currentPatients;
    private static Consultation[] consultations;

    public Sessions(String sessionId, Person doctor, Date date, Date time, int maxPatients, String sessionStatus) {
        this.sessionId = sessionId;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.maxPatients = maxPatients;
        this.sessionStatus = sessionStatus;
        this.currentPatients = 0;

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

    public Date getTime() {
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

    public void addConsultation(Consultation consultation){
        if (currentPatients < maxPatients){
            consultations[currentPatients] = consultation;
            currentPatients ++;
        }
    }
    @Override
    public String print() {
        Doctor doc = (Doctor) this.doctor;
        return "Session ID: " + sessionId + " Doctor: " + doc.getMedicalLicenceNumber() + " Date: " + date + " Time: " + time + " Max Patients: " + maxPatients + " Session Status: " + sessionStatus;
    }
}
