package Models;

import java.util.Date;

public class Patient extends Person{
    private static int numberOfConsultations;
    static int numberOfPatients;
    private String patientId;

    public Patient(String name, String surName, Date dateOfBirth, String mobileNumber, String patientId) {
        super(name, surName, dateOfBirth, mobileNumber);
        this.patientId = patientId;
        numberOfPatients ++;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }


    @Override
    public String print() {
        return "Name: "+super.getName()+ " Surname: "+super.getSurName()+" Age: "+ super.getAge()+" Mobile Number: " + super.getMobileNumber()+ " Patient ID: "+ patientId;
    }
}

