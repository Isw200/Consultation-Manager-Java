package Models;

import GUI.MainFrame;
import Interfaces.SkinConsultationManager;

import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {
    public static Person[] doctorArray = new Doctor[10];
    public static ArrayList<Person> patientArrayList = new ArrayList<>();
    public static ArrayList<Session> sessionArrayList = new ArrayList<>();
    public static ArrayList<Consultation> consultationArrayList = new ArrayList<>();
    File doctorFile = new File("doctorList.txt");
    File patientFile = new File("patientList.txt");
    File sessionsFile = new File("sessionsList.txt");
    File consultationsFile = new File("consultationsList.txt");

    public WestminsterSkinConsultationManager() {
    }

    public static Person[] getDoctorArray() {
        return doctorArray;
    }

    public static ArrayList<Person> getPatientArrayList() {
        return patientArrayList;
    }

    public static ArrayList<Session> getSessionsArrayList() {
        return sessionArrayList;
    }

    public static ArrayList<Consultation> getConsultationsArrayList() {
        return consultationArrayList;
    }

    /**
     * Get All details of a Doctor
     * Get date of birth as a String parameter and cast it in to Date object using strToDate method.
     * Pass all parameters to Doctor object.
     * Add Doctor object to doctorArrayList
     *
     * @param name                 String name
     * @param surName              String Surname
     * @param stringDateOfBirth    String date of birth
     * @param mobileNumber         String Mobile Number
     * @param medicalLicenceNumber String Medical Licence Number
     * @param specialisation       String Specialisation
     * @Catch ParseException if the date of birth is not in correct format.
     * correct format Example 15-MAR-2000
     */
    @Override
    public void addANewDoctor(String name, String surName, String stringDateOfBirth, String mobileNumber, String medicalLicenceNumber, String specialisation, String availability) {
        if (isArrayFull(doctorArray)) {
            JOptionPane.showMessageDialog(null, "Doctor Array is Full");
        } else {
            Date dateOfBirth = strToDate(stringDateOfBirth);
            Person doctor = new Doctor(name, surName, dateOfBirth, mobileNumber, medicalLicenceNumber, specialisation, availability);
            for (int i = 0; i < doctorArray.length; i++) {
                if (doctorArray[i] == null) {
                    doctorArray[i] = doctor;
                    break;
                }
            }
        }
    }

    /**
     * @param medicalLicenceNumber Medical Licence Number of the doctor we want ro delete.
     *                             Traversal through doctorArrayList ArrayList and matching (i)th Doctor objects medicalLicenceNumber with parameter.
     *                             If they matched removed matched (i)th element from the ArrayList.
     * @Variable boolean "found" to keep track item found or not. It will update it in to true if (i)th element's medicalLicenceNumber matched with the parameter
     */
    @Override
    public void deleteADoctor(String medicalLicenceNumber) {
        boolean found = false;
        for (int i = 0; i < doctorArray.length; i++) {
            Doctor doctor = (Doctor) doctorArray[i];
            if (medicalLicenceNumber.equals(doctor.getMedicalLicenceNumber())) {
                System.out.println("Doctor " + doctor.getName() + " " + doctor.getSurName() + " deleted successfully.");
                found = true;
                doctorArray[i] = null;
                break;
            }
        }
        if (!found) {
            System.out.println("Doctor not found by Medical Licence Number: " + medicalLicenceNumber);
        }
    }

    /**
     * Sort doctorArrayList by calling to the sort method.
     * Traversal through doctorArrayList and call (i)th Doctor's print method.
     */
    @Override
    public void printAllDoctors() {
        sortDoctor(doctorArray);
        for (int i = 0; i < doctorArray.length; i++) {
            Doctor doctor = (Doctor) doctorArray[i];
            System.out.println("Doctor " + (i + 1) + ": " + doctor.print());
        }
    }

    /**
     * Save all Doctor objects in doctorArrayList to a text file.
     * Used buffer to improve performance.
     * 1st argument for fileWriter object pass the text file instance to the fileWriter. 2nd argument tells FileWriter to append any given input to the file instead of overwriting it.
     */
    @Override
    public void saveDoctorsToFile() {
        if (doctorFile.exists()) {
            doctorFile.delete();
        }
        saveToFile(doctorFile, "Doctor");
    }


    @Override
    public void readDoctorFile() {
        readFile(doctorFile);
    }

    /**
     * Traversal through the doctorList text file and create Doctor object from each line.
     * To avoid exception if condition has been used. if only corresponding file exists read the file.
     *
     * @Variable line - A line from text file
     * @ArrayList dataItems - An ArrayList for hold each field regarding a doctor from a line temporally.
     * @ArrayList tempItem - An ArrayList for hold each letter for each field regarding a doctor from a line temporally.
     * Traveled through a line and add each character to tempItem, if found a " " blank String, tempItem will add to dataItems and clear tempItem ArrayList.
     * @Variable name, surName, dateOfBirth, mobileNumber, medicalLicenceNumber,specialisation - For hold values to pass addANewDoctor as arguments.
     * After finishing a line create a new doctor by calling addANewDoctor method.
     */
    @Override
    public void loadDoctorsFromFile() {
        if (doctorFile.exists()) {
            try {
                Scanner myReader = new Scanner(doctorFile);
                loadFromFile(myReader, "Doctor");
                doctorFile.delete();
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            System.out.println("File not exists!");
        }
    }

    public static int getNumberOfDoctors(Person[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * Get All details of a Patient.
     * Get date of birth as a String parameter and cast it in to Date object using strToDate method.
     * Pass all parameters to Patient object.
     * Add Patient object to patientArrayList
     *
     * @param name              Name of the patient.
     * @param surName           Surname of the patient.
     * @param stringDateOfBirth String birthday of the patient.
     * @param mobileNumber      Mobile number of the patient.
     * @param patentId          Unique ID for the patient.
     * @Catch ParseException if the date of birth is not in correct format.
     * correct format Example 15-MAR-2000
     */
    @Override
    public void addNewPatient(String name, String surName, String stringDateOfBirth, String mobileNumber, String patentId, String gender) {
        Date dateOfBirth = strToDate(stringDateOfBirth);
        Patient patient = new Patient(name, surName, dateOfBirth, mobileNumber, patentId, gender);
        patientArrayList.add(patient);
    }

    @Override
    public void deleteAPatient(String patentId) {
        boolean found = false;
        for (int i = 0; i < patientArrayList.size(); i++) {
            Patient patient = (Patient) patientArrayList.get(i);
            if (patentId.equals(patient.getPatientId())) {
                System.out.println("Doctor " + patient.getName() + " " + patient.getSurName() + " deleted successfully.");
                found = true;
                patientArrayList.remove(i);
                break;
            }
        }
        if (!found) {
            System.out.println("Patient not found by Medical Licence Number: " + patientArrayList);
        }
    }

    /**
     * Traversal through patientArrayList and call (i)th Patient's print method.
     */
    @Override
    public void printAllPatients() {
        sort(patientArrayList);
        for (int i = 0; i < patientArrayList.size(); i++) {
            Patient patient = (Patient) patientArrayList.get(i);
            System.out.println("Patient " + (i + 1) + ": " + patient.print());
        }
    }

    /**
     * Save all Patient objects in patientArrayList to a text file.
     * Used buffer to improve performance.
     * 1st argument for fileWriter object pass the text file instance to the fileWriter. 2nd argument tells FileWriter to append any given input to the file instead of overwriting it.
     */
    @Override
    public void savePatientsToFile() {
        if (patientFile.exists()) {
            patientFile.delete();
        }
        saveToFile(patientFile, "Patient");
    }

    @Override
    public void readPatientFile() {
        readFile(doctorFile);
    }

    @Override
    public void loadPatientsFromFile() {
        if (patientFile.exists()) {
            try {
                Scanner reader = new Scanner(patientFile);
                loadFromFile(reader, "Patient");
                patientFile.delete();
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            System.out.println("File not exists!");
        }
    }


    // Sessions methods
    @Override
    public void addNewSession(String sessionID, String doctorID, String date, String time, int maxPatients, String sessionStatus) {
        Date sessionDate = strToDate(date);
        Doctor doctor = null;
        for (int i = 0; i < doctorArray.length; i++) {
            Doctor tempDoctor = (Doctor) doctorArray[i];
            if (doctorID.equals(tempDoctor.getMedicalLicenceNumber())) {
                doctor = tempDoctor;
                break;
            }
        }
        Date sessionTime = strToTime(time);
        if (doctor != null) {
            Session session = new Session(sessionID, doctor, sessionDate, sessionTime, maxPatients, sessionStatus);
            sessionArrayList.add(session);
        } else {
            System.out.println("Doctor not found by Medical Licence Number: " + doctorID);
        }
    }

    @Override
    public void deleteASession(String sessionID) {
        boolean found = false;
        for (int i = 0; i < sessionArrayList.size(); i++) {
            Session session = (Session) sessionArrayList.get(i);
            if (sessionID.equals(session.getSessionId())) {
                System.out.println("Session " + session.getSessionId() + " deleted successfully.");
                found = true;
                sessionArrayList.remove(i);
                break;
            }
        }
        if (!found) {
            System.out.println("Session not found by Session ID: " + sessionID);
        }
    }

    @Override
    public void printAllSessions() {
        for (int i = 0; i < sessionArrayList.size(); i++) {
            Session session = (Session) sessionArrayList.get(i);
            System.out.println("Session " + (i + 1) + ": " + session.print());
        }
    }

    @Override
    public void saveSessionsToFile() {
        if (sessionsFile.exists()) {
            sessionsFile.delete();
        }
        saveToFile(sessionsFile, "Session");
    }

    @Override
    public void readSessionFile() {
        readFile(sessionsFile);
    }

    @Override
    public void loadSessionsFromFile() {
        if (sessionsFile.exists()) {
            try {
                Scanner reader = new Scanner(sessionsFile);
                loadFromFile(reader, "Session");
                sessionsFile.delete();
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            System.out.println("File not exists!");
        }
    }

    // Consultation methods
    @Override
    public void addNewConsultation(String consultationId, Session session, Person doctor, Person patient, double hours, String notes, String imagePath) throws IOException {
        Consultation consultation = new Consultation(consultationId, session, doctor, patient, hours, notes, imagePath);
        consultationArrayList.add(consultation);
    }

    @Override
    public void deleteAConsultation(String consultationID) {
        boolean found = false;
        for (int i = 0; i < consultationArrayList.size(); i++) {
            Consultation consultation = consultationArrayList.get(i);
            if (consultationID.equals(consultation.getConsultationId())) {
                System.out.println("Consultation " + consultation.getConsultationId() + " deleted successfully.");
                found = true;
                consultationArrayList.remove(i);
                break;
            }
        }
        if (!found) {
            System.out.println("Consultation not found by Consultation ID: " + consultationID);
        }
    }

    @Override
    public void printAllConsultations() {
        for (int i = 0; i < consultationArrayList.size(); i++) {
            Consultation consultation = (Consultation) consultationArrayList.get(i);
            System.out.println("Consultation " + (i + 1) + ": " + consultation.print());
        }
    }

    @Override
    public void saveConsultationsToFile() {
        if (consultationsFile.exists()) {
            consultationsFile.delete();
        }
        saveToFile(consultationsFile, "Consultation");
    }

    @Override
    public void readConsultationFile() {
        readFile(consultationsFile);
    }

    @Override
    public void loadConsultationsFromFile() {
        if (consultationsFile.exists()) {
            try {
                Scanner reader = new Scanner(consultationsFile);
                loadFromFile(reader, "Consultation");
                consultationsFile.delete();
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            System.out.println("File not exists!");
        }
    }





    /**
     * Traversal through a text file and print each line. To avoid exception if condition has been used. if only corresponding file exists read the file.
     */
    public void readFile(File file) {
        if (file.exists()) {
            try {
                Scanner myReader = new Scanner(doctorFile);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            System.out.println("File not exists!");
        }
    }

    /**
     * Get date of birth as a String parameter and cast it in to Date object using @Class SimpleDateFormat
     *
     * @param strDate String date.
     * @return A date typed Date.
     * @Catch: ParseException if the date of birth is not in correct format.
     * correct format Example 15-MAR-2000
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Date dateDate = null;
        try {
            dateDate = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateDate;
    }

    public static Date strToTime(String strTime) {
       DateFormat dateFormat = new SimpleDateFormat("hh:mm");
       Date date = null;
       try {
           date = dateFormat.parse(strTime);
       } catch (ParseException e) {
           e.printStackTrace();
       }
       return date;
    }


    /**
     * Read a text file and add each line to a ArrayList.
     * If text file is doctor file, create a doctor object by calling addANewDoctor method.
     * If text file is patient file, create a patient object by calling addNewPatient method.
     *
     * @param reader Scanner object to read a text file.
     * @param type   Type of the file. Doctor or Patient.
     */
    public void loadFromFile(Scanner reader, String type) {
        if (type.equals("Doctor")) {
            while (reader.hasNextLine()) {
                ArrayList<String> dataItems = lineReader(reader.nextLine());
                String name = dataItems.get(0);
                String surName = dataItems.get(1);
                String dateOfBirth = dataItems.get(4) + "-" + dataItems.get(3) + "-" + dataItems.get(7);
                String mobileNumber = dataItems.get(8);
                String medicalLicenceNumber = dataItems.get(9);
                String specialisation = dataItems.get(10);
                String availability = dataItems.get(11);

                addANewDoctor(name, surName, dateOfBirth, mobileNumber, medicalLicenceNumber, specialisation, availability);
            }
        } else if (type.equals("Patient")) {
            while (reader.hasNextLine()) {
                ArrayList<String> dataItems = lineReader(reader.nextLine());
                String name = dataItems.get(0);
                String surName = dataItems.get(1);
                String dateOfBirth = dataItems.get(4) + "-" + dataItems.get(3) + "-" + dataItems.get(7);
                String mobileNumber = dataItems.get(8);
                String patentId = dataItems.get(9);
                String gender = dataItems.get(10);

                addNewPatient(name, surName, dateOfBirth, mobileNumber, patentId, gender);
            }
        } else if (type.equals("Session")) {
            while (reader.hasNextLine()) {
                ArrayList<String> dataItems = lineReader(reader.nextLine());
                String sessionID = dataItems.get(0);
                String doctorID = dataItems.get(1);
                String date = dataItems.get(4) + "-" + dataItems.get(3) + "-" + dataItems.get(7);
                String time = dataItems.get(11);
                int maxPatients = Integer.parseInt(dataItems.get(14));
                String sessionStatus = dataItems.get(15);

                addNewSession(sessionID, doctorID, date, time, maxPatients, sessionStatus);
            }
        } else if (type.equals("Consultation")) {
            while (reader.hasNextLine()) {
                ArrayList<String> dataItems = lineReader(reader.nextLine());
                String consultationID = dataItems.get(0);
                String sessionID = dataItems.get(1);
                String patientID = dataItems.get(2);
                String date = dataItems.get(4) + "-" + dataItems.get(3) + "-" + dataItems.get(7);
                String time = dataItems.get(11);
                String consultationStatus = dataItems.get(15);

//                addNewConsultation(consultationID, sessionID, patientID, date, time, consultationStatus);
            }

        }

    }

    /**
     * Read data in an array list and Store that daa in to a text file.
     *
     * @param file A file that data should save.
     * @param type The type of ArrayList.
     *             If Doctor ArrayList it has additional attribute MedicalLicenceNumber & Specialisation.
     *             If Patient ArrayList it has additional attribute PatientId.
     */
    public void saveToFile(File file, String type) {
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            if (type.equals("Doctor")) {
                for (Person doctor : doctorArray) {
                    Doctor doctorType = (Doctor) doctor;
                    bufferedWriter.write(doctorType.getName() + " " + doctorType.getSurName() + " " + doctorType.getDateOfBirth() + " " + doctorType.getMobileNumber() + " " + doctorType.getMedicalLicenceNumber() + " " + doctorType.getSpecialisation() + " " + doctorType.getAvailability() + "\n");
                }
            } else if (type.equals("Patient")) {
                for (Person patient : patientArrayList) {
                    Patient patientType = (Patient) patient;
                    bufferedWriter.write(patientType.getName() + " " + patientType.getSurName() + " " + patientType.getDateOfBirth() + " " + patientType.getMobileNumber() + " " + patientType.getPatientId() + " " + patientType.getGender() + "\n");
                }
            } else if (type.equals("Session")) {
                for (Session session : sessionArrayList) {
                    Doctor doctorType = (Doctor) session.getDoctor();
                    bufferedWriter.write(session.getSessionId() + " " + doctorType.getMedicalLicenceNumber() + " " + session.getDate() + " " + session.getTime() + " " + session.getMaxPatients() + " " + session.getSessionStatus() + "\n");
                }
            } else if (type.equals("Consultation")) {
                for (Consultation consultation : consultationArrayList) {
                    Patient patientType = (Patient) consultation.getPatient();
                    Doctor doctorType = (Doctor) consultation.getDoctor();

                    bufferedWriter.write(consultation.getConsultationId() + " " + consultation.getSession().getSessionId() + " " + doctorType.getMedicalLicenceNumber()+ " " + patientType.getPatientId() + consultation.getHours() + " " + consultation.getNotes() + " " + consultation.getImagePath() + "\n");
                }
            }
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Get a line as a String parameter and split it by space.
     *
     * @param line A line of a text file.
     * @return An ArrayList of String.
     */
    public ArrayList<String> lineReader(String line) {
        ArrayList<String> dataItems = new ArrayList<>();
        ArrayList<String> tempItem = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            if (!String.valueOf(line.charAt(i)).equals(" ")) {
                tempItem.add(String.valueOf(line.charAt(i)));
            } else {
                dataItems.add(String.join("", tempItem));
                tempItem.clear();
            }
            if (i == line.length() - 1) {
                dataItems.add(String.join("", tempItem));
                tempItem.clear();
            }
        }
        return dataItems;
    }

    /**
     * Sort a given ArrayList using a bubble sort algorithm.
     * @param arrayList ArrayList to sort.
     */
    public void sort(ArrayList<Person> arrayList) {
        Person temp;
        for (int i = 0; i < arrayList.size(); i++) {
            for (int j = 1; j < (arrayList.size()); j++) {
                int comparisonReturn = (arrayList.get(j - 1).getSurName()).compareTo(arrayList.get(j).getSurName());
                if (comparisonReturn > 0) {
                    temp = arrayList.get(j - 1);
                    arrayList.set(j - 1, arrayList.get(j));
                    arrayList.set(j, temp);
                }
            }
        }
    }
    public void sortDoctor(Person[] doctorArray){
        Person temp;
        for (int i = 0; i < doctorArray.length; i++) {
            for (int j = 1; j < (doctorArray.length); j++) {
                int comparisonReturn = (doctorArray[j - 1].getSurName()).compareTo(doctorArray[j].getSurName());
                if (comparisonReturn > 0) {
                    temp = doctorArray[j - 1];
                    doctorArray[j - 1] = doctorArray[j];
                    doctorArray[j] = temp;
                }
            }
        }
    }
    public void sortSession(ArrayList<Session> arrayList) {
        Session temp;
        for (int i = 0; i < arrayList.size(); i++) {
            for (int j = 1; j < (arrayList.size()); j++) {
                int comparisonReturn = (arrayList.get(j - 1).getDate()).compareTo(arrayList.get(j).getDate());
                if (comparisonReturn > 0) {
                    temp = arrayList.get(j - 1);
                    arrayList.set(j - 1, arrayList.get(j));
                    arrayList.set(j, temp);
                }
            }
        }
    }

    // Run GUI
    public void runGUI() {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setTitle("Hospital Management System");
        mainFrame.setSize(1300, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
    public boolean isArrayFull(Person[] array){
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null){
                return false;
            }
        }
        return true;
    }
}
