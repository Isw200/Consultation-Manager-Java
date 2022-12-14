package Models;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Consultation {
    private Person doctor;
    private Sessions session;
    private Patient patient;
    private double price;
    private Date date;
    private Time time;

    private double hours;

    public Consultation(String doctorName, Patient patient, ArrayList<Sessions> sessions, double hours) {
        boolean isFirstConsultation = true;
        for (int i = 0; i < sessions.size(); i++) {
            if (sessions.get(i).getDoctor().getName().equals(doctorName)) {
                this.doctor = sessions.get(i).getDoctor();
                this.session = sessions.get(i);
                this.patient = patient;
                this.date = sessions.get(i).getDate();
                this.time = sessions.get(i).getTime();
            }
            for (int j = 0; j < sessions.get(i).getConsultations().length; j++) {
                if (sessions.get(i).getConsultations()[j] != null) {
                    if (sessions.get(i).getConsultations()[j].getPatient().getName().equals(patient.getName())) {
                        isFirstConsultation = false;
                    }
                }
            }
        }
        if (isFirstConsultation) {
            this.price = 15 * hours;
        } else {
            this.price = 25 * hours;
        }
        this.patient = patient;
    }

    public Person getDoctor() {
        return doctor;
    }

    public Sessions getSession() {
        return session;
    }

    public Patient getPatient() {
        return patient;
    }

    public double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }
}
