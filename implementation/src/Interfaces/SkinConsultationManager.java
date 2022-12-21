package Interfaces;

import Models.Person;
import Models.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public interface SkinConsultationManager {

    // Doctor Methods
    void addANewDoctor(String name, String surName, String dateOfBirth, String mobileNumber, String medicalLicenceNumber, String specialisation, String availability);

    void deleteADoctor(String medicalLicenceNumber);

    void printAllDoctors();

    void saveDoctorsToFile();

    void readDoctorFile();

    void loadDoctorsFromFile();


    //methods for patients
    void addNewPatient(String name, String surName, String stringDateOfBirth, String mobileNumber, String patentId, String gender);

    void deleteAPatient(String patentId);

    void printAllPatients();

    void savePatientsToFile();

    void readPatientFile();

    void loadPatientsFromFile();


    //methods for sessions
    void addNewSession(String sessionID, String doctorID, String date, String time, int maxPatients, String sessionStatus);

    void deleteASession(String sessionID);

    void printAllSessions();

    void saveSessionsToFile();

    void readSessionFile();

    void loadSessionsFromFile();


    // Consultation Methods
    void addNewConsultation(String consultationId, Session session, Person doctor, Person patient, double hours, String notes, ArrayList<String> imagePath) throws IOException;

    void deleteAConsultation(String consultationID);

    void printAllConsultations();

    void saveConsultationsToFile();

    void readConsultationFile();

    void loadConsultationsFromFile();
}

