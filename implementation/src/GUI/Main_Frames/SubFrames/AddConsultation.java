package GUI.Main_Frames.SubFrames;

import GUI.MainFrame;
import GUI.Other_components.DatePicker;
import Models.Doctor;
import Models.Person;
import Models.WestminsterSkinConsultationManager;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import static GUI.MainFrame.scaleImage;

public class AddConsultation implements ActionListener {
    // Variables
    private String name;
    // Main Frame
    private JFrame mainFrame;

    // Top Panel
    JPanel panelTop, panelBottom;
    JPanel fromExistingPatient, fromNewPatient;
    JButton fromExistingPatientButton, fromNewPatientButton;

    // Bottom Panel
    JLabel patientNameLabel, selectPatientLabel, patientGenderLabel, patientAgeLabel, patientDOBLabel, patientMobileNumberLabel, doctorLabel, dateLabel, hoursLabel, availableBtnLabel, timeLabel, numberLabel;
    JLabel[] jLabelsStyleArray = new JLabel[12];
    // ContainerPanels
    JPanel[] containerPanelArray = new JPanel[12];

    // TextFields
    JTextField patientNameTextField, patientGenderTextField, patientAgeTextField, patientDOBTextField, patientMobileNumberTextField, doctorTextField, dateTextField, availableBtnTextField, timeTextField, numberTextField;
    JButton checkAvailabilityButton;
    JComboBox patientNameComboBox, doctorComboBox, hoursComboBox;
    final JTextField sessionDate = new JTextField();
    JTextArea notesTextArea;
    JButton attachImageButton;
    JPanel imageAttachmentPanel;
    ArrayList<File> imageFiles = new ArrayList<>();
    JPanel imagePreviewPanel;
    JPanel patientDetails, doctorDetails, consultationDetails;
    JPanel patientDetailsTop, patientDetailsBottom;
    JPanel doctorDetailsTop, doctorDetailsBottom;
    JButton addConsultationButton, cancelButton;

    public AddConsultation() {
        // Main Frame
        mainFrame = new JFrame("Add Consultation");
        mainFrame.setSize(700, 1000);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setLocationRelativeTo(MainFrame.getFrames()[0]);

        // Labels
        patientNameLabel = new JLabel("  Patient Name");
        selectPatientLabel = new JLabel("  Select Patient");
        patientGenderLabel = new JLabel("  Gender");
        patientAgeLabel = new JLabel("  Age");
        patientDOBLabel = new JLabel("  Date of Birth");
        patientMobileNumberLabel = new JLabel("  Mobile Number");
        doctorLabel = new JLabel("  Doctor");
        dateLabel = new JLabel("  Date");
        hoursLabel = new JLabel("  Hours");
        availableBtnLabel = new JLabel("");
        timeLabel = new JLabel("  Patient's Time Slot");
        numberLabel = new JLabel("  Patient's Token Number");

        jLabelsStyleArray[0] = patientNameLabel;
        jLabelsStyleArray[1] = selectPatientLabel;
        jLabelsStyleArray[2] = patientGenderLabel;
        jLabelsStyleArray[3] = patientAgeLabel;
        jLabelsStyleArray[4] = patientDOBLabel;
        jLabelsStyleArray[5] = patientMobileNumberLabel;
        jLabelsStyleArray[6] = doctorLabel;
        jLabelsStyleArray[7] = dateLabel;
        jLabelsStyleArray[8] = hoursLabel;
        jLabelsStyleArray[9] = availableBtnLabel;
        jLabelsStyleArray[10] = timeLabel;
        jLabelsStyleArray[11] = numberLabel;

        for (JLabel jLabel : jLabelsStyleArray) {
            jLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            jLabel.setPreferredSize(new Dimension(200, 17));
        }

        for (int i = 0; i < containerPanelArray.length; i++) {
            containerPanelArray[i] = new JPanel();
            containerPanelArray[i].setLayout(new FlowLayout());
            containerPanelArray[i].setPreferredSize(new Dimension(200, 80));
        }
        // Panels
        // Top Panel
        panelTop = new JPanel();
        panelTop.setLayout(new BorderLayout());
        panelTop.setPreferredSize(new Dimension(700, 50));
        panelTop.setOpaque(false);

        fromExistingPatientButton = new JButton("From Existing Patients");
        fromExistingPatientButton.setPreferredSize(new Dimension(350, 50));
        fromExistingPatientButton.setBorder(null);
        fromExistingPatientButton.setOpaque(false);
        fromExistingPatientButton.setFont(new Font("Arial", Font.BOLD, 14));

        fromNewPatientButton = new JButton("New Patient");
        fromNewPatientButton.setPreferredSize(new Dimension(350, 50));
        fromNewPatientButton.setOpaque(true);
        fromNewPatientButton.setBorderPainted(false);
        fromNewPatientButton.setBorder(null);
        fromNewPatientButton.setBackground(new Color(194, 194, 194, 255));
        fromNewPatientButton.setForeground(new Color(138, 138, 138, 255));

        panelTop.add(fromExistingPatientButton, BorderLayout.WEST);
        panelTop.add(fromNewPatientButton, BorderLayout.EAST);

        // Bottom Panel
        panelBottom = new JPanel();
        panelBottom.setLayout(new FlowLayout());
        panelBottom.setPreferredSize(new Dimension(700, 550));
        panelBottom.setOpaque(false);

        // Patient Details Panel
        patientDetails = new JPanel();
        patientDetails.setLayout(new FlowLayout());
        patientDetails.setPreferredSize(new Dimension(700, 220));
        patientDetails.setOpaque(true);

        patientDetailsTop = new JPanel();
        patientDetailsTop.setLayout(new FlowLayout());
        patientDetailsTop.setPreferredSize(new Dimension(700, 80));
        patientDetailsTop.setOpaque(false);

        patientDetailsBottom = new JPanel();
        patientDetailsBottom.setLayout(new FlowLayout());
        patientDetailsBottom.setPreferredSize(new Dimension(700, 80));
        patientDetailsBottom.setOpaque(false);

        patientNameTextField = new JTextField();
        patientNameTextField.setPreferredSize(new Dimension(200, 40));
        patientNameTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        patientNameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                name = patientNameTextField.getText();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                name = patientNameTextField.getText();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                name = patientNameTextField.getText();
            }
        });

        patientGenderTextField = new JTextField();
        patientGenderTextField.setPreferredSize(new Dimension(200, 40));
        patientGenderTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        patientGenderTextField.setEditable(false);

        patientAgeTextField = new JTextField();
        patientAgeTextField.setPreferredSize(new Dimension(200, 40));
        patientAgeTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        patientAgeTextField.setEditable(false);

        patientDOBTextField = new JTextField();
        patientDOBTextField.setPreferredSize(new Dimension(200, 40));
        patientDOBTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        patientDOBTextField.setEditable(false);

        patientMobileNumberTextField = new JTextField();
        patientMobileNumberTextField.setPreferredSize(new Dimension(200, 40));
        patientMobileNumberTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        patientMobileNumberTextField.setEditable(false);

        String[] patientNames = new String[WestminsterSkinConsultationManager.getPatientArrayList().size()];
        for (int i = 0; i < WestminsterSkinConsultationManager.getPatientArrayList().size(); i++) {
            patientNames[i] = WestminsterSkinConsultationManager.getPatientArrayList().get(i).getName() + " " + WestminsterSkinConsultationManager.getPatientArrayList().get(i).getSurName();
        }

        if (patientNameTextField.getText() != null) {
            ArrayList<String> filteredPatientNames = new ArrayList<>();
            for (Person patient : WestminsterSkinConsultationManager.getPatientArrayList()) {
                if (patient.getName().toLowerCase().contains(patientNameTextField.getText().toLowerCase())) {
                    filteredPatientNames.add(patient.getName() + " " + patient.getSurName());
                }
            }
            patientNames = new String[filteredPatientNames.size()];
            for (int i = 0; i < filteredPatientNames.size(); i++) {
                patientNames[i] = filteredPatientNames.get(i);
            }
        }
        patientNameComboBox = new JComboBox(patientNames);
        patientNameComboBox.setPreferredSize(new Dimension(200, 40));

        containerPanelArray[0].add(patientNameLabel);
        containerPanelArray[0].add(patientNameTextField);

        containerPanelArray[1].add(selectPatientLabel);
        containerPanelArray[1].add(patientNameComboBox);

        containerPanelArray[2].add(patientGenderLabel);
        containerPanelArray[2].add(patientGenderTextField);

        containerPanelArray[3].add(patientAgeLabel);
        containerPanelArray[3].add(patientAgeTextField);

        containerPanelArray[4].add(patientDOBLabel);
        containerPanelArray[4].add(patientDOBTextField);

        containerPanelArray[5].add(patientMobileNumberLabel);
        containerPanelArray[5].add(patientMobileNumberTextField);

        patientDetailsTop.add(containerPanelArray[0]);
        patientDetailsTop.add(containerPanelArray[1]);
        patientDetailsTop.add(containerPanelArray[2]);

        patientDetailsBottom.add(containerPanelArray[3]);
        patientDetailsBottom.add(containerPanelArray[4]);
        patientDetailsBottom.add(containerPanelArray[5]);

        patientDetails.add(patientDetailsTop);
        patientDetails.add(patientDetailsBottom);
        patientDetails.add(MainFrame.addSpace(700, 20));
        patientDetails.setBorder(BorderFactory.createTitledBorder("Patient Details"));

        panelBottom.add(patientDetails);

        // Doctor Details Panel
        doctorDetails = new JPanel();
        doctorDetails.setLayout(new FlowLayout());
        doctorDetails.setPreferredSize(new Dimension(700, 220));
        doctorDetails.setOpaque(true);
        doctorDetails.setBorder(BorderFactory.createTitledBorder("Patient Details"));

        doctorDetailsTop = new JPanel();
        doctorDetailsTop.setLayout(new FlowLayout());
        doctorDetailsTop.setPreferredSize(new Dimension(700, 80));
        doctorDetailsTop.setOpaque(false);

        doctorDetailsBottom = new JPanel();
        doctorDetailsBottom.setLayout(new FlowLayout());
        doctorDetailsBottom.setPreferredSize(new Dimension(700, 80));
        doctorDetailsBottom.setOpaque(false);

        String[] doctorNames = new String[WestminsterSkinConsultationManager.getNumberOfDoctors(WestminsterSkinConsultationManager.getDoctorArray())];
        int j = 0;
        for (Person doctor : WestminsterSkinConsultationManager.getDoctorArray()) {
            if (doctor != null) {
                doctorNames[j] = doctor.getName() + " " + doctor.getSurName();
                j++;
            }
        }
        // TODO: remove sample
        doctorNames = new String[]{"Dr. John Doe", "Dr. Jane Doe"};
        doctorComboBox = new JComboBox(doctorNames);
        doctorComboBox.setPreferredSize(new Dimension(200, 40));
        doctorComboBox.setBounds(200, 200, 200, 40);

        // Date picker
        sessionDate.setPreferredSize(new Dimension(170, 35));
        sessionDate.setFont(new Font("Arial", Font.PLAIN, 16));
        sessionDate.setFocusable(false);
        ImageIcon icon = new ImageIcon("src/GUI/Assets/calendar.png");
        icon = scaleImage(icon, 20, 20);

        JButton dateSelectButton = new JButton(icon);
        dateSelectButton.setPreferredSize(new Dimension(30, 30));
        JPanel selectDatePanel = new JPanel(new BorderLayout());
        selectDatePanel.setOpaque(true);
        selectDatePanel.setSize(new Dimension(200, 40));
        selectDatePanel.add(sessionDate, BorderLayout.CENTER);
        selectDatePanel.add(dateSelectButton, BorderLayout.EAST);

        dateSelectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                sessionDate.setText(new DatePicker(mainFrame).setPickedDate());
            }
        });

        // Check Availability Button
        checkAvailabilityButton = new JButton("Check Availability");
        checkAvailabilityButton.setPreferredSize(new Dimension(200, 40));
        checkAvailabilityButton.setFont(new Font("Arial", Font.PLAIN, 16));
        checkAvailabilityButton.setBorder(BorderFactory.createLineBorder(new Color(164, 92, 255), 2));
        checkAvailabilityButton.setBackground(new Color(0, 0, 0, 0));
        checkAvailabilityButton.setForeground(new Color(164, 92, 255));
        checkAvailabilityButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        checkAvailabilityButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                checkAvailabilityButton.setOpaque(true);
                checkAvailabilityButton.setBackground(new Color(164, 92, 255));
                checkAvailabilityButton.setForeground(new Color(255, 255, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                checkAvailabilityButton.setOpaque(false);
                checkAvailabilityButton.setForeground(new Color(164, 92, 255));
            }
        });

        String[] hours = {"0.25", "0.50", "1", "2"};
        hoursComboBox = new JComboBox(hours);
        hoursComboBox.setPreferredSize(new Dimension(200, 40));
        hoursComboBox.setFont(new Font("Arial", Font.PLAIN, 16));

        timeTextField = new JTextField();
        timeTextField.setPreferredSize(new Dimension(200, 40));
        timeTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        timeTextField.setEditable(false);

        numberTextField = new JTextField();
        numberTextField.setPreferredSize(new Dimension(200, 40));
        numberTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        numberTextField.setEditable(false);

        containerPanelArray[6].add(doctorLabel);
        containerPanelArray[6].add(doctorComboBox);

        containerPanelArray[7].add(dateLabel);
        containerPanelArray[7].add(selectDatePanel);

        containerPanelArray[8].add(MainFrame.addSpace(200, 25));
        containerPanelArray[8].add(checkAvailabilityButton);

        containerPanelArray[9].add(hoursLabel);
        containerPanelArray[9].add(hoursComboBox);

        containerPanelArray[10].add(timeLabel);
        containerPanelArray[10].add(timeTextField);

        containerPanelArray[11].add(numberLabel);
        containerPanelArray[11].add(numberTextField);

        doctorDetailsTop.add(containerPanelArray[6]);
        doctorDetailsTop.add(containerPanelArray[7]);
        doctorDetailsTop.add(containerPanelArray[8]);
        doctorDetailsBottom.add(containerPanelArray[9]);
        doctorDetailsBottom.add(containerPanelArray[10]);
        doctorDetailsBottom.add(containerPanelArray[11]);

        doctorDetails.add(doctorDetailsTop);
        doctorDetails.add(doctorDetailsBottom);


        // Consultation Details Panel
        consultationDetails = new JPanel();
        consultationDetails.setLayout(new FlowLayout());
        consultationDetails.setPreferredSize(new Dimension(700, 350));
        consultationDetails.setOpaque(false);
        consultationDetails.setBorder(BorderFactory.createTitledBorder("Consultation Details"));

        JLabel noteLabel = new JLabel("Notes");
        noteLabel.setPreferredSize(new Dimension(600, 17));
        noteLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        notesTextArea = new JTextArea();
        notesTextArea.setPreferredSize(new Dimension(600, 70));
        notesTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        notesTextArea.setLineWrap(true);
        notesTextArea.setWrapStyleWord(true);

        consultationDetails.add(noteLabel);
        consultationDetails.add(notesTextArea);

        // Image Attachment Panel
        imageAttachmentPanel = new JPanel(new BorderLayout());
        JPanel imageAttachmentTop = new JPanel(new BorderLayout());
        imageAttachmentTop.setPreferredSize(new Dimension(600, 40));
        JLabel imageLabel = new JLabel("Skin Images");
        imageLabel.setPreferredSize(new Dimension(300, 17));
        imageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        imageAttachmentTop.add(imageLabel, BorderLayout.WEST);

        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(300, 17));

        // Image Preview Panel
        imagePreviewPanel = new JPanel(new GridLayout(2, 3));
        imagePreviewPanel.setPreferredSize(new Dimension(600, 150));
        imagePreviewPanel.setOpaque(true);
        imagePreviewPanel.setBackground(new Color(255, 255, 255));


        attachImageButton = new JButton("Attach Image");
        attachImageButton.setPreferredSize(new Dimension(200, 40));
        attachImageButton.setFont(new Font("Arial", Font.PLAIN, 16));
        attachImageButton.setBorder(BorderFactory.createLineBorder(new Color(164, 92, 255), 2));
        attachImageButton.setOpaque(false);
        attachImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg"));
                fileChooser.setAcceptAllFileFilterUsed(false);

                int option = fileChooser.showOpenDialog(mainFrame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    imageFiles.add(file);
                    ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
                    Image image = imageIcon.getImage();
                    Image newImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(newImage);
                    imagePreviewPanel.add(new JLabel(imageIcon));
                    label.setText("Attached " + imageFiles.size() + " image(s)");
//                    try {
//                        copyFile(file, new File("src/GUI/SkinImages/" + "user1-" + file.getName()));
//                    } catch (IOException ex) {
//                        throw new RuntimeException(ex);
//                    }

                } else {
                    label.setText("Open command canceled");
                }
            }
        });
        imageAttachmentTop.add(attachImageButton, BorderLayout.EAST);
        imagePreviewPanel.add(label);

        imageAttachmentPanel.add(imageAttachmentTop, BorderLayout.NORTH);

        imageAttachmentPanel.add(imagePreviewPanel, BorderLayout.CENTER);

        consultationDetails.add(MainFrame.addSpace(600, 10));
        consultationDetails.add(imageAttachmentPanel);

        panelBottom.add(doctorDetails);
        panelBottom.add(consultationDetails);


        // Add Consultation Button
        addConsultationButton = new JButton("Add Consultation");
        addConsultationButton.setPreferredSize(new Dimension(220, 40));
        addConsultationButton.setFont(new Font("Arial", Font.PLAIN, 16));
        addConsultationButton.setBorder(BorderFactory.createLineBorder(new Color(164, 92, 255), 2));
        addConsultationButton.setBackground(new Color(0, 0, 0, 0));
        addConsultationButton.setForeground(new Color(164, 92, 255));
        addConsultationButton.addActionListener(this);
        addConsultationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                addConsultationButton.setOpaque(true);
                addConsultationButton.setBackground(new Color(164, 92, 255));
                addConsultationButton.setForeground(new Color(255, 255, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addConsultationButton.setOpaque(false);
                addConsultationButton.setForeground(new Color(164, 92, 255));
            }
        });

        // Cancel Consultation button
        JPanel addAndCancelButtons = new JPanel(new FlowLayout());
        JLabel cancelDescription = new JLabel("No need add? ");
        cancelDescription.setFont(new Font("Arial", Font.PLAIN, 16));
        cancelDescription.setForeground(new Color(164, 92, 255));

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelButton.setOpaque(false);
        cancelButton.setBorder(null);
        cancelButton.setForeground(new Color(164, 92, 255));
        cancelButton.addActionListener(this);
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cancelButton.setForeground(new Color(51, 0, 115));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cancelButton.setForeground(new Color(164, 92, 255));
            }
        });
        addAndCancelButtons.add(cancelDescription);
        addAndCancelButtons.add(cancelButton);

        JPanel addAndCancelPanel = new JPanel(new GridLayout(2, 1));
        JPanel addContainer = new JPanel(new FlowLayout());
        addContainer.setPreferredSize(new Dimension(600, 50));
        addContainer.add(addConsultationButton);
        addAndCancelPanel.add(addContainer);
        addAndCancelPanel.add(addAndCancelButtons);


        mainFrame.add(panelTop, BorderLayout.NORTH);
        mainFrame.add(panelBottom, BorderLayout.CENTER);
        mainFrame.add(addAndCancelPanel, BorderLayout.SOUTH);
        mainFrame.setVisible(true);
    }

    /**
     * Copies a given file to a given destination
     *
     * @param sourceFile the file to be copied
     * @param destFile   the destination of the file
     * @throws IOException if an I/O error occurs
     */
    private static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!sourceFile.exists()) {
            return;
        }
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        FileChannel source = null;
        FileChannel destination = null;
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        if (destination != null && source != null) {
            destination.transferFrom(source, 0, source.size());
        }
        if (source != null) {
            source.close();
        }
        if (destination != null) {
            destination.close();
        }
    }

    private void imagePreviewRerender() {
        for (int i = 0; i < imageFiles.size(); i++) {
            System.out.println(imageFiles.get(i).getName());
            ImageIcon imageIcon = new ImageIcon(imageFiles.get(i).getAbsolutePath());
            Image image = imageIcon.getImage();
            Image newImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newImage);
            imagePreviewPanel.add(new JLabel(imageIcon));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
