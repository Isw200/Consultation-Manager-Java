package GUI.Main_Frames.SubFrames;

import GUI.MainFrame;
import Models.Doctor;
import Models.Person;
import Models.WestminsterSkinConsultationManager;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FindDoctor extends JFrame implements ActionListener, MouseListener, DocumentListener {
    String fName, lName, medicalLicenceNum;
    JFrame mainFrame;
    JLabel fNameLabel, lNameLabel, medicalLicenceNumLabel;
    JTextField fNameField, lNameField, medicalLicenceNumField;
    JPanel topPanel, topPanelUp, topPanelDown, bottomPanel;
    JButton findDoctor;
    JTable doctorTable;
    JScrollPane scrollPane;
    JButton edit, delete;
    public FindDoctor() {
        fName = "";
        lName = "";
        medicalLicenceNum = "";

        mainFrame = new JFrame();
        mainFrame.setTitle("Find Doctor");
        mainFrame.setSize(800, 500);
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(MainFrame.getFrames()[0]);


        // Top Panel
        topPanel = new JPanel(new FlowLayout());
        topPanel.setPreferredSize(new Dimension(800, 100));
        topPanel.setOpaque(true);

        // Top Panel Up
        fNameLabel = new JLabel("First Name");
        fNameLabel.setPreferredSize(new Dimension(150, 20));
        lNameLabel = new JLabel("Last Name");
        lNameLabel.setPreferredSize(new Dimension(150, 20));
        medicalLicenceNumLabel = new JLabel("Medical Licence Number");
        medicalLicenceNumLabel.setPreferredSize(new Dimension(250, 20));

        topPanelUp = new JPanel(new FlowLayout());
        topPanelUp.setPreferredSize(new Dimension(800, 20));
        topPanelUp.add(fNameLabel);
        topPanelUp.add(lNameLabel);
        topPanelUp.add(medicalLicenceNumLabel);
        topPanel.add(topPanelUp);

        // Top Panel Down
        topPanelDown = new JPanel(new FlowLayout());
        topPanelDown.setPreferredSize(new Dimension(800, 35));
        fNameField = new JTextField();
        fNameField.setPreferredSize(new Dimension(150, 30));
        fNameField.getDocument().addDocumentListener(this);

        lNameField = new JTextField();
        lNameField.setPreferredSize(new Dimension(150, 30));
        lNameField.getDocument().addDocumentListener(this);

        medicalLicenceNumField = new JTextField();
        medicalLicenceNumField.setPreferredSize(new Dimension(250, 30));
        medicalLicenceNumField.getDocument().addDocumentListener(this);

        topPanelDown.add(fNameField);
        topPanelDown.add(lNameField);
        topPanelDown.add(medicalLicenceNumField);
        topPanel.add(topPanelDown);

        // Find Doctor Button
        findDoctor = new JButton("Find Doctor");
        findDoctor.setPreferredSize(new Dimension(150, 30));
        findDoctor.addActionListener(this);
        findDoctor.addMouseListener(this);
        findDoctor.setForeground(new Color(164, 92, 255));
        findDoctor.setFont(new Font("Arial", Font.PLAIN, 14));
        findDoctor.setBorder(BorderFactory.createLineBorder(new Color(164, 92, 255), 2));
        topPanel.add(findDoctor);
        mainFrame.add(topPanel);


        // Bottom Panel
        bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setPreferredSize(new Dimension(800, 400));

        String[] doctorsTableColumns = {"Doctor ID", "First Name", "Last Name", "Phone Number", "Speciality", "Availability"};
        ArrayList<Person> doctors = WestminsterSkinConsultationManager.getDoctorArrayList();
        String[][] doctorData = new String[doctors.size()][6];
        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = (Doctor) WestminsterSkinConsultationManager.getDoctorArrayList().get(i);
            doctorData[i][0] = doctor.getMedicalLicenceNumber();
            doctorData[i][1] = doctor.getName();
            doctorData[i][2] = doctor.getSurName();
            doctorData[i][3] = doctor.getMobileNumber();
            doctorData[i][4] = doctor.getSpecialisation();
            doctorData[i][5] = doctor.getAvailability();
        }

        doctorTable = new JTable();
        doctorTable.setPreferredScrollableViewportSize(new Dimension(750, 400));
        doctorTable.setModel(new DefaultTableModel(doctorData, doctorsTableColumns));
        doctorTable.setRowHeight(30);
        doctorTable.setFont(new Font("Arial", Font.PLAIN, 14));
        doctorTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        doctorTable.setShowGrid(true);
        doctorTable.setGridColor(new Color(234, 214, 255));
        doctorTable.setModel(new DefaultTableModel(doctorData, doctorsTableColumns));
        JTableHeader header = doctorTable.getTableHeader();
        header.setBackground(new Color(30, 0, 70));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.PLAIN, 14));
        header.setPreferredSize(new Dimension(750, 30));

        /**
         * This is to get values from selected row from the table
         */
        doctorTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int viewRow = doctorTable.getSelectedRow();

                if (!e.getValueIsAdjusting() && viewRow != -1) {
                    int columnIndex = 0;
                    int modelRow = doctorTable.convertRowIndexToModel(viewRow);
                    Object doctorId = doctorTable.getModel().getValueAt(modelRow, columnIndex);
                    JDialog dialog = new JDialog(mainFrame, "Doctor Details", true);
                    dialog.setSize(400, 100);
                    dialog.setLayout(new FlowLayout());
                    dialog.setResizable(false);
                    dialog.setLocationRelativeTo(FindDoctor.getFrames()[0]);

                    // Edit and Delete Buttons
                    edit = new JButton("Edit");
                    edit.setPreferredSize(new Dimension(150, 50));
                    edit.setForeground(new Color(0, 122, 31));
                    edit.setOpaque(true);
                    edit.setFont(new Font("Arial", Font.PLAIN, 16));
                    edit.setBorder(BorderFactory.createLineBorder(new Color(0, 122, 31), 2));
                    edit.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            edit.setForeground(Color.WHITE);
                            edit.setBackground(new Color(0, 122, 31));
                            edit.setOpaque(true);
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                            edit.setForeground(new Color(0, 122, 31));
                            edit.setBackground(Color.WHITE);
                            edit.setOpaque(true);
                        }
                    });
                    edit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dialog.dispose();
                            EditDoctor editDoctor = new EditDoctor(doctorId.toString());
                        }
                    });

                    delete = new JButton("Delete");
                    delete.setPreferredSize(new Dimension(150, 50));
                    delete.setForeground(Color.RED);
                    delete.setOpaque(true);
                    delete.setFont(new Font("Arial", Font.PLAIN, 16));
                    delete.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 2));
                    delete.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            delete.setForeground(Color.WHITE);
                            delete.setBackground(Color.RED);
                            delete.setOpaque(true);
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                            delete.setForeground(Color.RED);
                            delete.setBackground(Color.WHITE);
                            delete.setOpaque(true);
                        }
                    });
                    delete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Person doctor = null;
                            for (int i = 0; i < WestminsterSkinConsultationManager.doctorArrayList.size(); i++) {
                                Doctor doc = (Doctor) WestminsterSkinConsultationManager.doctorArrayList.get(i);
                                if (doc.getMedicalLicenceNumber().equals(doctorId)) {
                                    doctor = WestminsterSkinConsultationManager.doctorArrayList.get(i);
                                }
                            }
                            int dialogButton = JOptionPane.YES_NO_OPTION;
                            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete Doctor "+ doctor.getName()+" "+doctor.getSurName()+ " ?", "Warning", dialogButton);

                            if (dialogResult == JOptionPane.YES_OPTION) {
                                WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
                                manager.deleteADoctor(doctorId.toString());
                                dialog.dispose();
                                mainFrame.dispose();
                                new FindDoctor();
                            }
                        }
                    });
                    dialog.add(edit);
                    dialog.add(delete);
                    dialog.setVisible(true);
                }
            }
        });

        scrollPane = new JScrollPane(doctorTable);

        bottomPanel.add(scrollPane);
        mainFrame.add(bottomPanel);

        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == findDoctor) {
            ArrayList<Person> doctors = WestminsterSkinConsultationManager.getDoctorArrayList();
            ArrayList<Person> filteredDoctors = new ArrayList<>();

            for (Person doctor : doctors) {
                System.out.println(fName);
                Doctor doc = (Doctor) doctor;
                if (doctor.getName().equalsIgnoreCase(fName) && doctor.getSurName().equalsIgnoreCase(lName) && doc.getMedicalLicenceNumber().equalsIgnoreCase(medicalLicenceNum)) {
                    filteredDoctors.add(doctor);
                }
            }
            tableReRender(filteredDoctors);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == findDoctor) {
            findDoctor.setForeground(new Color(164, 92, 255));
            findDoctor.setBackground(Color.WHITE);
            findDoctor.setOpaque(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == findDoctor) {
            findDoctor.setForeground(Color.WHITE);
            findDoctor.setBackground(new Color(164, 92, 255));
            findDoctor.setOpaque(true);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == findDoctor) {
            findDoctor.setForeground(Color.WHITE);
            findDoctor.setBackground(new Color(164, 92, 255));
            findDoctor.setOpaque(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == findDoctor) {
            findDoctor.setForeground(new Color(164, 92, 255));
            findDoctor.setBackground(Color.WHITE);
            findDoctor.setOpaque(true);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        if (e.getDocument() == fNameField.getDocument()) {
            fName = fNameField.getText();
            updateTable(fName, lName, medicalLicenceNum);
        } else if (e.getDocument() == lNameField.getDocument()) {
            lName = lNameField.getText();
            updateTable(fName, lName, medicalLicenceNum);
        } else if (e.getDocument() == medicalLicenceNumField.getDocument()) {
            medicalLicenceNum = medicalLicenceNumField.getText();
            updateTable(fName, lName, medicalLicenceNum);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if (e.getDocument() == fNameField.getDocument()) {
            fName = fNameField.getText();
            updateTable(fName, lName, medicalLicenceNum);
        } else if (e.getDocument() == lNameField.getDocument()) {
            lName = lNameField.getText();
            updateTable(fName, lName, medicalLicenceNum);
        } else if (e.getDocument() == medicalLicenceNumField.getDocument()) {
            medicalLicenceNum = medicalLicenceNumField.getText();
            updateTable(fName, lName, medicalLicenceNum);
        }
    }

    public void updateTable(String fName, String lName, String doctorId) {
        ArrayList<Person> doctors = WestminsterSkinConsultationManager.getDoctorArrayList();
        ArrayList<Person> filteredDoctors = new ArrayList<>();

        for (Person doctor : doctors) {
            Doctor doc = (Doctor) doctor;
            if (doctor.getName().equalsIgnoreCase(fName) && doctor.getSurName().equalsIgnoreCase(lName) || doc.getMedicalLicenceNumber().equalsIgnoreCase(doctorId)) {
                filteredDoctors.add(doctor);
            }
        }
        tableReRender(filteredDoctors);
    }

    public void tableReRender(ArrayList<Person> filteredDoctors){
        String[][] filteredDoctorsTableData = new String[filteredDoctors.size()][6];
        for (int i = 0; i < filteredDoctors.size(); i++) {
            Doctor doc = (Doctor) filteredDoctors.get(i);
            filteredDoctorsTableData[i][0] = doc.getMedicalLicenceNumber();
            filteredDoctorsTableData[i][1] = doc.getName();
            filteredDoctorsTableData[i][2] = doc.getSurName();
            filteredDoctorsTableData[i][3] = doc.getMobileNumber();
            filteredDoctorsTableData[i][4] = doc.getSpecialisation();
            filteredDoctorsTableData[i][5] = doc.getAvailability();
        }
        String[] doctorsTableColumns = {"Doctor ID", "First Name", "Last Name", "Phone Number", "Speciality", "Availability"};
        doctorTable.setModel(new DefaultTableModel(filteredDoctorsTableData, doctorsTableColumns));
    }

}
