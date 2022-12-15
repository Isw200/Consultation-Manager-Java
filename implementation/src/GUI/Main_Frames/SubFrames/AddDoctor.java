package GUI.Main_Frames.SubFrames;

import GUI.Other_components.DatePicker;
import Models.Doctor;
import Models.WestminsterSkinConsultationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import static GUI.MainFrame.addSpace;
import static GUI.MainFrame.scaleImage;

public class AddDoctor extends JDialog implements ActionListener {
    final JFrame mainFrame;
    JPanel mainPanel;
    JPanel[] panels = new JPanel[6];
    JPanel[] mainBorderLayouts = new JPanel[3];
    JLabel[] labels = new JLabel[6];
    JTextField fNameField, lNameField, mobileNumField, medicalLicenceNumField;
    JComboBox specialisationDropDown;
    final JTextField dateOfBirthField = new JTextField();
    JTextField[] textFields = new JTextField[4];
    JButton addDoctor, cancel;
    public AddDoctor() {
        // Main Frame
        mainFrame = new JFrame();
        mainFrame.setVisible(true);
        mainFrame.setSize(520, 450);
        mainPanel = new JPanel(new FlowLayout());

        // Initialise 3 border panels
        for (int i = 0; i < mainBorderLayouts.length; i++) {
            mainBorderLayouts[i] = new JPanel(new BorderLayout());
            mainBorderLayouts[i].setPreferredSize(new Dimension(500, 70));
            mainBorderLayouts[i].setOpaque(false);
        }

        // Initialise 6 panels
        for (int i = 0; i < 6; i++) {
            panels[i] = new JPanel(new FlowLayout());
            panels[i].setPreferredSize(new Dimension(220, 200));
            panels[i].setOpaque(false);
        }

        // Initialise labels
        for (int i = 0; i < 6; i++) {
            labels[i] = new JLabel();
            labels[i].setPreferredSize(new Dimension(200, 20));
            labels[i].setOpaque(false);
            labels[i].setFont(new Font("Arial", Font.PLAIN, 16));
        }
        labels[0].setText("First Name:");
        labels[1].setText("Last Name:");
        labels[2].setText("Date of Birth:");
        labels[3].setText("Mobile Number:");
        labels[4].setText("Medical Licence Number:");
        labels[5].setText("Specialisation:");

        // Initialise text fields
        fNameField = new JTextField();
        lNameField = new JTextField();
        mobileNumField = new JTextField();
        medicalLicenceNumField = new JTextField();

        textFields[0] = fNameField;
        textFields[1] = lNameField;
        textFields[2] = mobileNumField;
        textFields[3] = medicalLicenceNumField;

        // Text field settings
        for (int i = 0; i < 4; i++) {
            textFields[i].setPreferredSize(new Dimension(220, 30));
            textFields[i].setOpaque(true);
            textFields[i].setFont(new Font("Arial", Font.PLAIN, 16));
        }

        // Add labels to panels
        for (int i = 0; i < 6; i++) {
            panels[i].add(labels[i]);
        }
        // Add text fields to panels
        panels[0].add(textFields[0]);
        panels[1].add(textFields[1]);
        panels[3].add(textFields[2]);
        panels[4].add(textFields[3]);

        // Date picker
        dateOfBirthField.setPreferredSize(new Dimension(190, 35));
        dateOfBirthField.setFont(new Font("Arial", Font.PLAIN, 16));
        dateOfBirthField.setFocusable(false);
        ImageIcon icon = new ImageIcon("src/GUI/Assets/calendar.png");
        icon = scaleImage(icon, 20, 20);

        JButton dateSelectButton = new JButton(icon);
        dateSelectButton.setPreferredSize(new Dimension(30, 30));
        JPanel selectDatePanel = new JPanel(new BorderLayout());
        selectDatePanel.setOpaque(true);
        selectDatePanel.setSize(new Dimension(220, 30));
        selectDatePanel.add(dateOfBirthField, BorderLayout.CENTER);
        selectDatePanel.add(dateSelectButton, BorderLayout.EAST);
        panels[2].add(selectDatePanel);

        dateSelectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dateOfBirthField.setText(new DatePicker(mainFrame).setPickedDate());
            }
        });

        // Add specialisation drop down menu
        String[] specialisations = {"General Practitioner", "Cardiologist", "Dentist", "Dermatologist", "Endocrinologist", "Gastroenterologist", "Geriatrician", "Gynecologist", "Neurologist", "Oncologist", "Ophthalmologist", "Orthopedist", "Pediatrician", "Psychiatrist", "Rheumatologist", "Surgeon"};

        specialisationDropDown = new JComboBox(specialisations);
        specialisationDropDown.setPreferredSize(new Dimension(220, 30));
        specialisationDropDown.setFont(new Font("Arial", Font.PLAIN, 16));
        specialisationDropDown.setBorder(null);
        panels[5].add(specialisationDropDown);

        // Add buttons
        JPanel addDoctorBtnContainer = new JPanel();
        addDoctorBtnContainer.setPreferredSize(new Dimension(500,50));
        addDoctor = new JButton("Add Doctor");
        addDoctor.setPreferredSize(new Dimension(220, 40));
        addDoctor.setFont(new Font("Arial", Font.PLAIN, 16));
        addDoctor.setBorder(BorderFactory.createLineBorder(new Color(164, 92, 255), 2));
        addDoctor.setBackground(new Color(0, 0, 0, 0));
        addDoctor.setForeground(new Color(164, 92, 255));
        addDoctor.addActionListener(this);
        addDoctorBtnContainer.add(addDoctor);

        // Cancel button
        JPanel addAndCancelButtons = new JPanel(new FlowLayout());
        JLabel cancelDescription = new JLabel("No need add? ");
        cancelDescription.setFont(new Font("Arial", Font.PLAIN, 16));
        cancelDescription.setForeground(new Color(164, 92, 255));

        cancel = new JButton("Cancel");
        cancel.setFont(new Font("Arial", Font.BOLD, 16));
        cancel.setOpaque(false);
        cancel.setBorder(null);
        cancel.setForeground(new Color(164, 92, 255));
        cancel.addActionListener(this);
        addAndCancelButtons.add(cancelDescription);
        addAndCancelButtons.add(cancel);

        // Add panels to border panels
        mainBorderLayouts[0].add(panels[0], BorderLayout.WEST);
        mainBorderLayouts[0].add(panels[1], BorderLayout.EAST);

        mainBorderLayouts[1].add(panels[2], BorderLayout.WEST);
        mainBorderLayouts[1].add(panels[3], BorderLayout.EAST);

        mainBorderLayouts[2].add(panels[4], BorderLayout.WEST);
        mainBorderLayouts[2].add(panels[5], BorderLayout.EAST);

        // Add panels to main frame
        mainPanel.add(mainBorderLayouts[0]);
        mainPanel.add(mainBorderLayouts[1]);
        mainPanel.add(mainBorderLayouts[2]);
        mainPanel.add(addSpace(520,30));
        mainPanel.add(addDoctorBtnContainer);
        mainPanel.add(addAndCancelButtons);
        mainPanel.add(addSpace(520,50));
        mainFrame.add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addDoctor) {
            // Check if all fields are filled
            if (fNameField.getText().equals("") || lNameField.getText().equals("") || dateOfBirthField.getText().equals("") || mobileNumField.getText().equals("") || medicalLicenceNumField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Check if mobile number is valid
                if (mobileNumField.getText().length() != 10) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid mobile number", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Check if medical licence number is valid
                    if (medicalLicenceNumField.getText().length() != 8) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid medical licence number: EX- DOC12345", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Add doctor to array list
                        Date dateOfBirth = WestminsterSkinConsultationManager.strToDate(dateOfBirthField.getText());
                        Doctor doctor = new Doctor(fNameField.getText(), lNameField.getText(), dateOfBirth, mobileNumField.getText(), medicalLicenceNumField.getText(), specialisationDropDown.getSelectedItem().toString(), "Available");
                        WestminsterSkinConsultationManager.doctorArrayList.add(doctor);
                        JOptionPane.showMessageDialog(null, "Doctor added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        mainFrame.dispose();
                    }
                }
            }
        }
        if (e.getSource() == cancel) {
            mainFrame.dispose();
        }
    }
}
