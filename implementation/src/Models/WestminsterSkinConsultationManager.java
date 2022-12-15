package Models;

import GUI.MainFrame;
import Interfaces.SkinConsultationManager;

import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {


    public static ArrayList<Person> doctorArrayList = new ArrayList<>();
    private ArrayList<Person> patientArrayList = new ArrayList<>();
    private ArrayList<Sessions> sessionsArrayList = new ArrayList<>();
    File doctorFile = new File("doctorList.txt");
    File patientFile = new File("patientList.txt");

    public WestminsterSkinConsultationManager() {
    }

    public static ArrayList<Person> getDoctorArrayList() {
        return doctorArrayList;
    }

    public ArrayList<Person> getPatientArrayList() {
        return patientArrayList;
    }

    public ArrayList<Sessions> getSessionsArrayList() {
        return sessionsArrayList;
    }

    /**
     * Get All details of a Doctor
     * Get date of birth as a String parameter and cast it in to Date object using strToDate method.
     * Pass all parameters to Doctor object.
     * Add Doctor object to doctorArrayList
     *
     * @param name String name
     * @param surName String Surname
     * @param stringDateOfBirth String date of birth
     * @param mobileNumber String Mobile Number
     * @param medicalLicenceNumber String Medical Licence Number
     * @param specialisation String Specialisation
     *
     * @Catch ParseException if the date of birth is not in correct format.
     * correct format Example 15-MAR-2000
     */
    @Override
    public void addANewDoctor(String name, String surName, String stringDateOfBirth, String mobileNumber, String medicalLicenceNumber, String specialisation) {
        Date dateOfBirth = strToDate(stringDateOfBirth);
        Person doctor = new Doctor(name, surName, dateOfBirth,mobileNumber,medicalLicenceNumber,specialisation, "Available");
        doctorArrayList.add(doctor);
    }

    /**
     * @param medicalLicenceNumber Medical Licence Number of the doctor we want ro delete.
     * Traversal through doctorArrayList ArrayList and matching (i)th Doctor objects medicalLicenceNumber with parameter.
     * If they matched removed matched (i)th element from the ArrayList.
     * @Variable boolean "found" to keep track item found or not. It will update it in to true if (i)th element's medicalLicenceNumber matched with the parameter
     */
    @Override
    public void deleteADoctor(String medicalLicenceNumber) {
        boolean found = false;
        for (int i = 0; i < doctorArrayList.size(); i++){
            Doctor doctor = (Doctor) doctorArrayList.get(i);
            if (medicalLicenceNumber.equals(doctor.getMedicalLicenceNumber())){
                System.out.println("Doctor "+ doctor.getName()+" "+ doctor.getSurName()+" deleted successfully.");
                found = true;
                doctorArrayList.remove(i);
                break;
            }
        }
        if (!found){
            System.out.println("Doctor not found by Medical Licence Number: "+medicalLicenceNumber);
        }
    }

    /**
     * Sort doctorArrayList by calling to the sort method.
     * Traversal through doctorArrayList and call (i)th Doctor's print method.
     */
    @Override
    public void printAllDoctors() {
        sort(doctorArrayList);
        for (int i = 0; i < doctorArrayList.size(); i++){
            Doctor doctor = (Doctor) doctorArrayList.get(i);
            System.out.println("Doctor "+ (i+1)+": "+doctor.print());
        }
    }

    /**
     * Save all Doctor objects in doctorArrayList to a text file.
     * Used buffer to improve performance.
     * 1st argument for fileWriter object pass the text file instance to the fileWriter. 2nd argument tells FileWriter to append any given input to the file instead of overwriting it.
     */
    @Override
    public void saveDoctorsToFile() {
        saveToFile(doctorFile,"Doctor");
    }


    @Override
    public void readDoctorFile() {
        readFile(doctorFile);
    }

    /**
     * Traversal through the doctorList text file and create Doctor object from each line.
     * To avoid exception if condition has been used. if only corresponding file exists read the file.
     * @Variable line - A line from text file
     * @ArrayList dataItems - An ArrayList for hold each field regarding a doctor from a line temporally.
     * @ArrayList tempItem - An ArrayList for hold each letter for each field regarding a doctor from a line temporally.
     *Traveled through a line and add each character to tempItem, if found a " " blank String, tempItem will add to dataItems and clear tempItem ArrayList.
     *  @Variable name, surName, dateOfBirth, mobileNumber, medicalLicenceNumber,specialisation - For hold values to pass addANewDoctor as arguments.
     * After finishing a line create a new doctor by calling addANewDoctor method.
     */
    @Override
    public void loadDoctorsFromFile() {
        if (doctorFile.exists()) {
            try {
                Scanner myReader = new Scanner(doctorFile);
                loadFromFile(myReader,"Doctor");
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

    /**
     * Get All details of a Patient.
     * Get date of birth as a String parameter and cast it in to Date object using strToDate method.
     * Pass all parameters to Patient object.
     * Add Patient object to patientArrayList
     * @param name Name of the patient.
     * @param surName Surname of the patient.
     * @param stringDateOfBirth String birthday of the patient.
     * @param mobileNumber Mobile number of the patient.
     * @param patentId Unique ID for the patient.
     *
     * @Catch ParseException if the date of birth is not in correct format.
     * correct format Example 15-MAR-2000
     */
    @Override
    public void addNewPatient(String name, String surName, String stringDateOfBirth, String mobileNumber, String patentId) {
        Date dateOfBirth = strToDate(stringDateOfBirth);
        Patient patient = new Patient(name,surName,dateOfBirth,mobileNumber,patentId);
        patientArrayList.add(patient);
    }

    @Override
    public void deleteAPatient(String patentId) {
        boolean found = false;
        for (int i = 0; i < patientArrayList.size(); i++){
            Patient patient = (Patient) patientArrayList.get(i);
            if (patentId.equals(patient.getPatientId())){
                System.out.println("Doctor "+ patient.getName()+" "+ patient.getSurName()+" deleted successfully.");
                found = true;
                patientArrayList.remove(i);
                break;
            }
        }
        if (!found){
            System.out.println("Patient not found by Medical Licence Number: "+patientArrayList);
        }
    }

    /**
     * Traversal through patientArrayList and call (i)th Patient's print method.
     */
    @Override
    public void printAllPatients() {
        sort(patientArrayList);
        for (int i = 0; i < patientArrayList.size(); i++){
            Patient patient = (Patient) patientArrayList.get(i);
            System.out.println("Patient "+ (i+1)+": "+patient.print());
        }
    }

    /**
     * Save all Patient objects in patientArrayList to a text file.
     * Used buffer to improve performance.
     * 1st argument for fileWriter object pass the text file instance to the fileWriter. 2nd argument tells FileWriter to append any given input to the file instead of overwriting it.
     */
    @Override
    public void savePatientsToFile() {
        saveToFile(patientFile,"Patient");
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
     * @param strDate String date.
     * @return A date typed Date.
     * @Catch: ParseException if the date of birth is not in correct format.
     * correct format Example 15-MAR-2000
     */
    public static Date strToDate(String strDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Date dateDate = null;
        try {
            dateDate = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateDate;
    }


    /**
     * Read a text file and add each line to a ArrayList.
     * If text file is doctor file, create a doctor object by calling addANewDoctor method.
     * If text file is patient file, create a patient object by calling addNewPatient method.
     * @param reader Scanner object to read a text file.
     * @param type Type of the file. Doctor or Patient.
     */
    public void loadFromFile(Scanner reader,String type){
        if (type.equals("Doctor")){
            while (reader.hasNextLine()) {
                ArrayList<String> dataItems = lineReader(reader.nextLine());
                String name = dataItems.get(0);
                String surName = dataItems.get(1);
                String dateOfBirth = dataItems.get(4) + "-" + dataItems.get(3) + "-" + dataItems.get(7);
                String mobileNumber = dataItems.get(8);
                String medicalLicenceNumber = dataItems.get(9);
                String specialisation = dataItems.get(10);

                addANewDoctor(name, surName, dateOfBirth, mobileNumber, medicalLicenceNumber, specialisation);
            }
        }else if (type.equals("Patient")){
            while (reader.hasNextLine()) {
                ArrayList<String> dataItems = lineReader(reader.nextLine());
                String name = dataItems.get(0);
                String surName = dataItems.get(1);
                String dateOfBirth = dataItems.get(4) + "-" + dataItems.get(3) + "-" + dataItems.get(7);
                String mobileNumber = dataItems.get(8);
                String patentId = dataItems.get(9);

                addNewPatient(name, surName, dateOfBirth, mobileNumber, patentId);
            }
        }
    }

    /**
     * Read data in an array list and Store that daa in to a text file.
     * @param file A file that data should save.
     * @param type The type of ArrayList.
     * If Doctor ArrayList it has additional attribute MedicalLicenceNumber & Specialisation.
     * If Patient ArrayList it has additional attribute PatientId.
     */
    public void saveToFile(File file, String type){
        try{
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            if (type.equals("Doctor")){
                for (Person doctor : doctorArrayList) {
                    Doctor doctorType = (Doctor) doctor;
                    bufferedWriter.write(doctorType.getName() + " " + doctorType.getSurName() + " " + doctorType.getDateOfBirth() + " " + doctorType.getMobileNumber() + " " + doctorType.getMedicalLicenceNumber() + " " + doctorType.getSpecialisation() + "\n");
                }
            }else if (type.equals("Patient")){
                for (Person patient : patientArrayList) {
                    Patient patientType = (Patient) patient;
                    bufferedWriter.write(patientType.getName() + " " + patientType.getSurName() + " " + patientType.getDateOfBirth() + " " + patientType.getMobileNumber() + " " + patientType.getPatientId() + "\n");
                }
            }
            bufferedWriter.close();

        } catch(IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Get a line as a String parameter and split it by space.
     * @param line A line of a text file.
     * @return An ArrayList of String.
     */
    public ArrayList<String> lineReader(String line){
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
     * Sort a given ArrayList using a bubble sort algorithm
     * @param arrayList ArrayList to sort.
     */
    public void sort(ArrayList<Person> arrayList){
        Person temp;
        for (int i = 0; i < arrayList.size(); i++){
            for (int j = 1; j < (arrayList.size()-1); j++){
                int comparisonReturn = (arrayList.get(j-1).getSurName()).compareTo(arrayList.get(j).getSurName());
                if (comparisonReturn > 0){
                    temp = arrayList.get(j-1);
                    arrayList.set(j-1,arrayList.get(j));
                    arrayList.set(j,temp);
                }
            }
        }
    }

    // Run GUI
    public void runGUI(){
        MainFrame mainFrame = new MainFrame();
        mainFrame.setTitle("Hospital Management System");
        mainFrame.setSize(1300, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
