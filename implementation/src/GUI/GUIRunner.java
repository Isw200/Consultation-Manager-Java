package GUI;

import GUI.Main_Frames.SubFrames.AddPatients;
import GUI.Main_Frames.SubFrames.FindDoctor;
import Models.WestminsterSkinConsultationManager;

import javax.swing.*;

public class GUIRunner {
//    public static void main(String[] args) {
//        MainFrame mainFrame = new MainFrame();
//        mainFrame.setTitle("Hospital Management System");
//        mainFrame.setSize(1300, 800);
//        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        mainFrame.setVisible(true);
//    }

    public static void main(String[] args) {
        WestminsterSkinConsultationManager westminsterSkinConsultationManager = new WestminsterSkinConsultationManager();
        westminsterSkinConsultationManager.loadDoctorsFromFile();
        westminsterSkinConsultationManager.saveDoctorsToFile();
        westminsterSkinConsultationManager.loadPatientsFromFile();
        westminsterSkinConsultationManager.savePatientsToFile();
        westminsterSkinConsultationManager.runGUI();
//        AddPatients addPatients = new AddPatients();
    }
}
