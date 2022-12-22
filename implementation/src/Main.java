import Models.WestminsterSkinConsultationManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WestminsterSkinConsultationManager westminsterSkinConsultationManager = new WestminsterSkinConsultationManager();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("""
                    -------------------------------------------------------
                    ~~ Welcome to Westminster Skin Consultation Manager ~~  
                    Enter below your choice
                    _____________________________                
                    (DA) -> Add a new Doctor
                    (DD) -> Delete a Doctor
                    (DP) -> List of all Doctors
                    (DS) -> Save to File
                    (DR) -> Read file
                    (DL) -> Load data from Files
                    (DSO)-> Sort Doctors
                                        
                    (PA) -> Add a new Patient
                    (PD) -> Delete a Patient
                    (PP) -> List of all Patients
                    (PS) -> Save to File
                    (PR) -> Read file
                    (PL) -> Load data from Files
                                        
                    (SA) -> Add a new Session
                    (SD) -> Delete a Session
                    (SP) -> List of all Sessions
                    (SS) -> Save to File
                    (SR) -> Read file
                    (SL) -> Load data from Files
                                        
                    (GL)  -> Open GUI with all the data
                    (GN)  -> Open GUI with no data
                    _____________________________
                    Answer: """);
            String answer = input.nextLine();
            if (answer.equalsIgnoreCase("Q")) {
                break;
            } else if (answer.equalsIgnoreCase("DA")) {
                System.out.print("Enter Name: ");
                String name = input.nextLine();
                System.out.print("Enter Surname: ");
                String surName = input.nextLine();
                System.out.print("Enter Date of Birth [dd-MMM-yyyy]: ");
                String dateOfBirth = input.nextLine();
                System.out.print("Enter Mobile Number: ");
                String mobileNumber = input.nextLine();
                System.out.print("Enter Medical Licence Number: ");
                String medicalLicenceNumber = input.nextLine();
                System.out.print("Enter Specialisation: ");
                String specialisation = input.nextLine();
                System.out.print("Enter Availability: ");
                String availability = input.nextLine();
                westminsterSkinConsultationManager.addANewDoctor(name, surName, dateOfBirth, mobileNumber, medicalLicenceNumber, specialisation, availability);
            } else if (answer.equalsIgnoreCase("DD")) {
                System.out.print("Enter Medical Licence Number: ");
                String medicalLicenceNumber = input.nextLine();
                westminsterSkinConsultationManager.deleteADoctor(medicalLicenceNumber);
            } else if (answer.equalsIgnoreCase("DP")) {
                westminsterSkinConsultationManager.printAllDoctors();
            } else if (answer.equalsIgnoreCase("DS")) {
                westminsterSkinConsultationManager.saveDoctorsToFile();
            } else if (answer.equalsIgnoreCase("DR")) {
                westminsterSkinConsultationManager.readDoctorFile();
            } else if (answer.equalsIgnoreCase("DL")) {
                westminsterSkinConsultationManager.loadDoctorsFromFile();
            } else if (answer.equalsIgnoreCase("DSO")) {
                westminsterSkinConsultationManager.sortDoctor(WestminsterSkinConsultationManager.getDoctorArray());
            }
            // Patient
            else if (answer.equalsIgnoreCase("PA")) {
                System.out.print("Enter Patient Name: ");
                String name = input.nextLine();
                System.out.print("Enter Patient Surname: ");
                String surName = input.nextLine();
                System.out.print("Enter Date of Birth [dd-MMM-yyyy]: ");
                String dateOfBirth = input.nextLine();
                System.out.print("Enter Mobile Number: ");
                String mobileNumber = input.nextLine();
                System.out.print("Enter Patient ID: ");
                String patentId = input.nextLine();
                System.out.print("Enter Gender: ");
                String gender = input.nextLine();
                westminsterSkinConsultationManager.addNewPatient(name, surName, dateOfBirth, mobileNumber, patentId, gender);
            } else if (answer.equalsIgnoreCase("PD")) {
                System.out.print("Enter Patient ID: ");
                String patentId = input.nextLine();
                westminsterSkinConsultationManager.deleteAPatient(patentId);
            } else if (answer.equalsIgnoreCase("PP")) {
                westminsterSkinConsultationManager.printAllPatients();
            } else if (answer.equalsIgnoreCase("PS")) {
                westminsterSkinConsultationManager.savePatientsToFile();
            } else if (answer.equalsIgnoreCase("PR")) {
                westminsterSkinConsultationManager.readPatientFile();
            } else if (answer.equalsIgnoreCase("PL")) {
                westminsterSkinConsultationManager.loadPatientsFromFile();
            }

            // Session
            else if (answer.equalsIgnoreCase("SA")) {
                System.out.print("Enter Session ID: ");
                String sessionId = input.nextLine();
                System.out.print("Enter Doctor ID: ");
                String doctorId = input.nextLine();
                System.out.print("Enter Date: ");
                String date = input.nextLine();
                System.out.print("Enter Time: ");
                String time = input.nextLine();
                System.out.print("Enter Maximum Patients: ");
                int maxPatients = input.nextInt();
                westminsterSkinConsultationManager.addNewSession(sessionId, doctorId, date, time, maxPatients, "Active");
            } else if (answer.equalsIgnoreCase("SD")) {
                System.out.print("Enter Session ID: ");
                String sessionId = input.nextLine();
                westminsterSkinConsultationManager.deleteASession(sessionId);
            } else if (answer.equalsIgnoreCase("SP")) {
                westminsterSkinConsultationManager.printAllSessions();
            } else if (answer.equalsIgnoreCase("SS")) {
                westminsterSkinConsultationManager.saveSessionsToFile();
            } else if (answer.equalsIgnoreCase("SR")) {
                westminsterSkinConsultationManager.readSessionFile();
            } else if (answer.equalsIgnoreCase("SL")) {
                westminsterSkinConsultationManager.loadSessionsFromFile();
            }

            // GUI
            else if (answer.equalsIgnoreCase("GL")) {
                // Clean image folder
//                File folder = new File("src/GUI/SkinImages");
//                try {
//                    FileUtils.cleanDirectory(folder);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                // Load all data from files
                westminsterSkinConsultationManager.loadDoctorsFromFile();
                westminsterSkinConsultationManager.saveDoctorsToFile();
                westminsterSkinConsultationManager.loadPatientsFromFile();
                westminsterSkinConsultationManager.savePatientsToFile();
                westminsterSkinConsultationManager.loadSessionsFromFile();
                westminsterSkinConsultationManager.saveSessionsToFile();
                westminsterSkinConsultationManager.loadConsultationsFromFile();
                westminsterSkinConsultationManager.saveConsultationsToFile();
                westminsterSkinConsultationManager.runGUI();
            } else if (answer.equalsIgnoreCase("GN")) {
                westminsterSkinConsultationManager.runGUI();
            }
        }
    }
}
