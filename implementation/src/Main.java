import Models.WestminsterSkinConsultationManager;

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
                    
                    (PA) -> Add a new Patient
                    (PD) -> Delete a Patient
                    (PP) -> List of all Patients
                    (PS) -> Save to File
                    (PR) -> Read file
                    (PL) -> Load data from Files
                    
                    (G)  -> Open GUI
                    _____________________________
                    Answer: """);
            String answer = input.nextLine();
            if (answer.equalsIgnoreCase("Q")){
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
                westminsterSkinConsultationManager.addANewDoctor(name, surName, dateOfBirth,mobileNumber,medicalLicenceNumber,specialisation);
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
                westminsterSkinConsultationManager.addNewPatient(name, surName, dateOfBirth,mobileNumber,patentId);
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

            // GUI
            else if (answer.equalsIgnoreCase("G")) {
                westminsterSkinConsultationManager.runGUI();
            }
        }
    }
}
