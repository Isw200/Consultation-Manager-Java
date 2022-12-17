package Interfaces;

import java.util.Date;

public interface SkinConsultationManager {
    void addANewDoctor(String name, String surName, String dateOfBirth, String mobileNumber, String medicalLicenceNumber, String specialisation);

    void deleteADoctor(String medicalLicenceNumber);

    void printAllDoctors();

    void saveDoctorsToFile();

    void readDoctorFile();

    void loadDoctorsFromFile();

    //methods for patients
    void addNewPatient(String name, String surName,  String stringDateOfBirth, String mobileNumber, String patentId, String gender);

    void deleteAPatient(String patentId);

    void printAllPatients();

    void savePatientsToFile();

    void readPatientFile();

    void loadPatientsFromFile();
}

