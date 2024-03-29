package GUI.Main_Frames.SubFrames;

import GUI.MainFrame;
import GUI.Main_Frames.DoctorsPanel;
import GUI.GUIModels.DatePicker;
import Models.Doctor;
import Models.Person;
import Models.WestminsterSkinConsultationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static GUI.MainFrame.addSpace;
import static GUI.MainFrame.scaleImage;

public class EditDoctor extends JDialog implements ActionListener {
    String doctorId;
    Person doctor;
    final JFrame mainFrame;
    JPanel mainPanel;
    JPanel[] panels = new JPanel[6];
    JPanel[] mainBorderLayouts = new JPanel[3];
    JLabel[] labels = new JLabel[6];
    JTextField fNameField, lNameField, mobileNumField, medicalLicenceNumField;
    JComboBox specialisationDropDown, availabilityDropDown;
    final JTextField dateOfBirthField = new JTextField();
    JTextField[] textFields = new JTextField[4];
    JButton editDoctor, cancel;

    public EditDoctor(String doctorId) {
        this.doctorId = doctorId;
        for (int i = 0; i < WestminsterSkinConsultationManager.doctorArray.length; i++) {
            if (WestminsterSkinConsultationManager.doctorArray[i] != null) {
                Doctor doc = (Doctor) WestminsterSkinConsultationManager.doctorArray[i];
                if (doc.getMedicalLicenceNumber().equals(doctorId)) {
                    doctor = WestminsterSkinConsultationManager.doctorArray[i];
                }
            }
        }
        Doctor doc = (Doctor) doctor;

        // Main Frame
        mainFrame = new JFrame();
        mainFrame.setVisible(true);
        mainFrame.setSize(520, 450);
        mainFrame.setLocationRelativeTo(MainFrame.getFrames()[0]);
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
        labels[0].setText("First Name");
        labels[1].setText("Last Name");
        labels[2].setText("Date of Birth");
        labels[3].setText("Mobile Number");
        labels[4].setText("Medical Licence Number");
        labels[5].setText("Specialisation");

        // Initialise text fields
        fNameField = new JTextField();
        fNameField.setText(doctor.getName());
        lNameField = new JTextField();
        lNameField.setText(doctor.getSurName());
        mobileNumField = new JTextField();
        mobileNumField.setText(doctor.getMobileNumber());
        medicalLicenceNumField = new JTextField();
        medicalLicenceNumField.setText(doc.getMedicalLicenceNumber());

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
        dateOfBirthField.setText(doctor.getStringDateOfBirth());

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
        String[] specialisations = {"Cosmetic Dermatology", "Medical Dermatology", "Paediatric Dermatology", "Surgical Dermatology"};


        specialisationDropDown = new JComboBox(specialisations);
        specialisationDropDown.setPreferredSize(new Dimension(220, 30));
        specialisationDropDown.setFont(new Font("Arial", Font.PLAIN, 16));
        specialisationDropDown.setBorder(null);
        specialisationDropDown.setSelectedItem(doc.getSpecialisation());
        panels[5].add(specialisationDropDown);

        // Add availability drop down menu
        JPanel availabilityPanel = new JPanel(new FlowLayout());
        availabilityPanel.setPreferredSize(new Dimension(220, 55));
        JLabel availabilityLabel = new JLabel("Availability");
        availabilityLabel.setPreferredSize(new Dimension(200, 20));
        availabilityLabel.setOpaque(false);
        availabilityLabel.setFont(new Font("Arial", Font.PLAIN, 16));


        String[] availabilities = {"Available", "Unavailable"};
        availabilityDropDown = new JComboBox(availabilities);
        availabilityDropDown.setPreferredSize(new Dimension(220, 30));
        availabilityDropDown.setFont(new Font("Arial", Font.PLAIN, 16));
        availabilityDropDown.setBorder(null);
        availabilityDropDown.setSelectedItem(doc.getAvailability());

        availabilityPanel.add(availabilityLabel);
        availabilityPanel.add(availabilityDropDown);


        // Update buttons
        JPanel updateDoctorBtnContainer = new JPanel();
        updateDoctorBtnContainer.setPreferredSize(new Dimension(500, 50));
        editDoctor = new JButton("Update");
        editDoctor.setPreferredSize(new Dimension(220, 40));
        editDoctor.setFont(new Font("Arial", Font.PLAIN, 16));
        editDoctor.setBorder(BorderFactory.createLineBorder(new Color(164, 92, 255), 2));
        editDoctor.setBackground(new Color(0, 0, 0, 0));
        editDoctor.setForeground(new Color(164, 92, 255));
        editDoctor.addActionListener(this);
        editDoctor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                editDoctor.setOpaque(true);
                editDoctor.setBackground(new Color(164, 92, 255));
                editDoctor.setForeground(new Color(255, 255, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                editDoctor.setOpaque(false);
                editDoctor.setForeground(new Color(164, 92, 255));
            }
        });
        updateDoctorBtnContainer.add(editDoctor);

        // Cancel button
        JPanel addAndCancelButtons = new JPanel(new FlowLayout());
        JLabel cancelDescription = new JLabel("No need Edit? ");
        cancelDescription.setFont(new Font("Arial", Font.PLAIN, 16));
        cancelDescription.setForeground(new Color(164, 92, 255));

        cancel = new JButton("Cancel");
        cancel.setFont(new Font("Arial", Font.BOLD, 16));
        cancel.setOpaque(false);
        cancel.setBorder(null);
        cancel.setForeground(new Color(164, 92, 255));
        cancel.addActionListener(this);
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cancel.setForeground(new Color(51, 0, 115));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cancel.setForeground(new Color(164, 92, 255));
            }
        });
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
        mainPanel.add(availabilityPanel);
        mainPanel.add(addSpace(520, 30));
        mainPanel.add(updateDoctorBtnContainer);
        mainPanel.add(addAndCancelButtons);
        mainPanel.add(addSpace(520, 50));
        mainFrame.add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editDoctor) {
            if (textFields[0].getText().equals("") || textFields[1].getText().equals("") || textFields[2].getText().equals("") || textFields[3].getText().equals("") || dateOfBirthField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill in all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
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
                            // Change doctor details
                            String firstName = textFields[0].getText();
                            String lastName = textFields[1].getText();
                            String dateOfBirth = dateOfBirthField.getText();
                            String mobileNumber = textFields[2].getText();
                            String medicalLicenceNumber = textFields[3].getText();
                            String specialisation = (String) specialisationDropDown.getSelectedItem();
                            String availability = (String) availabilityDropDown.getSelectedItem();

                            doctor.setName(firstName);
                            doctor.setSurName(lastName);
                            doctor.setDateOfBirth(WestminsterSkinConsultationManager.strToDate(dateOfBirth));
                            doctor.setMobileNumber(mobileNumber);
                            Doctor doc = (Doctor) doctor;
                            doc.setMedicalLicenceNumber(medicalLicenceNumber);
                            doc.setSpecialisation(specialisation);
                            doc.setAvailability(availability);

                            JOptionPane.showMessageDialog(null, "Doctor Edited Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                            FindDoctor.tableReRender(WestminsterSkinConsultationManager.getDoctorArray());
                            DoctorsPanel.tableReRender(WestminsterSkinConsultationManager.getDoctorArray());
                            mainFrame.dispose();
                        }
                    }
                }
            }
        }
        if (e.getSource() == cancel) {
            mainFrame.dispose();
        }
    }
}
