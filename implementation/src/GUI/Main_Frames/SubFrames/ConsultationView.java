package GUI.Main_Frames.SubFrames;

import GUI.GUILibs.DeletingDone;
import GUI.MainFrame;
import GUI.Main_Frames.ConsultationsPanel;
import GUI.Main_Frames.DoctorsPanel;
import GUI.Main_Frames.SessionsPanel;
import Models.Consultation;
import Models.Patient;
import Models.SubModels.EncryptAndDecrypt;
import Models.WestminsterSkinConsultationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ConsultationView extends JFrame implements ActionListener {
    String encryptedNote;
    static Consultation consultation;
    int decryptKey = 5;
    static JFrame mainFrame = null;
    String[] labelStrings = {"Patient Name", "Patient Age", "Patient Gender", "Doctor Name", "Session Date", "Session Start Time", "Patient's Time Slot", "Patient's Token Number", "Cost"};
    JLabel[] labels = new JLabel[labelStrings.length];
    JTextField[] textFields = new JTextField[labelStrings.length];
    JPanel[] containers = new JPanel[labelStrings.length];
    JPanel[] rows = new JPanel[3];

    JPanel panelTop;
    JPanel consultationIDPanel;
    JLabel consultationIDLabel;
    JLabel consultationID;
    JButton deleteBtn;
    JPanel deletePanel;

    JPanel notesPanel, imagePanel;
    JPanel imagePane;
    JTextArea notesTextArea;
    JButton unlockNotesBtn, unlockImageBtn, doneBtn, cancelBtn;

    public ConsultationView(Consultation consultation) {
        ConsultationView.consultation = consultation;
        // Main Frame
        mainFrame = new JFrame();
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setSize(700, 900);
        mainFrame.setLocationRelativeTo(MainFrame.getFrames()[0]);
        mainFrame.setResizable(false);
        mainFrame.setTitle("Consultation Details");

        // Labels and Text Fields into Containers
        for (int i = 0; i < labelStrings.length; i++) {
            // Labels
            labels[i] = new JLabel(labelStrings[i]);
            labels[i].setFont(new Font("Arial", Font.PLAIN, 16));
            labels[i].setPreferredSize(new Dimension(200, 20));
            labels[i].setOpaque(false);
            // Text Fields
            textFields[i] = new JTextField();
            textFields[i].setEditable(false);
            textFields[i].setFont(new Font("Arial", Font.PLAIN, 16));
            textFields[i].setPreferredSize(new Dimension(200, 40));
            // Containers
            containers[i] = new JPanel();
            containers[i].setLayout(new FlowLayout());
            containers[i].setPreferredSize(new Dimension(200, 80));
            // Add Labels and Text Fields to Containers
            containers[i].add(labels[i]);
            containers[i].add(textFields[i]);
        }
        Patient patient = (Patient) consultation.getPatient();
        textFields[0].setText(consultation.getPatient().getName());
        textFields[1].setText(String.valueOf(consultation.getPatient().getAge()));
        textFields[2].setText(patient.getGender());
        textFields[3].setText(consultation.getDoctor().getName());
        textFields[4].setText(consultation.getSession().getStringDate());
        textFields[5].setText(consultation.getSession().getStringTime());
        textFields[6].setText(consultation.getStringTime());
        textFields[7].setText(String.valueOf(consultation.getTokenNumber()));
        textFields[8].setText(String.valueOf(consultation.getPrice()));


        for (int i = 0; i < rows.length; i++) {
            rows[i] = new JPanel();
            rows[i].setLayout(new FlowLayout());
            rows[i].setPreferredSize(new Dimension(670, 80));
            rows[i].setOpaque(false);
        }

        rows[0].add(containers[0]);
        rows[0].add(containers[1]);
        rows[0].add(containers[2]);
        rows[1].add(containers[3]);
        rows[1].add(containers[4]);
        rows[1].add(containers[5]);
        rows[2].add(containers[6]);
        rows[2].add(containers[7]);
        rows[2].add(containers[8]);


        // Top Panel
        panelTop = new JPanel();
        panelTop.setLayout(new BorderLayout());
        panelTop.setPreferredSize(new Dimension(800, 100));
        panelTop.setOpaque(false);
        // Top Panel - Consultation ID
        consultationIDPanel = new JPanel();
        consultationIDPanel.setLayout(new FlowLayout());
        consultationIDPanel.setPreferredSize(new Dimension(370, 40));
        consultationIDPanel.setOpaque(false);
        consultationIDLabel = new JLabel("   Consultation ID");

        consultationIDLabel.setForeground(new Color(166, 166, 166));
        consultationIDLabel.setFont(new Font("Arial", Font.BOLD, 16));
        consultationIDLabel.setPreferredSize(new Dimension(200, 26));
        consultationIDLabel.setOpaque(false);
        consultationID = new JLabel(consultation.getConsultationId());
        consultationID.setFont(new Font("Arial", Font.BOLD, 40));
        consultationID.setForeground(new Color(119, 119, 119));
        consultationID.setOpaque(false);
        consultationIDPanel.add(consultationIDLabel);
        consultationIDPanel.add(consultationID);

        // Top Panel - Edit and Delete Buttons
        deletePanel = new JPanel();
        deletePanel.setLayout(new FlowLayout());
        deletePanel.setPreferredSize(new Dimension(300, 100));
        deletePanel.setOpaque(false);


        deleteBtn = new JButton("Delete");
        deleteBtn.setPreferredSize(new Dimension(100, 40));
        deleteBtn.setFont(new Font("Arial", Font.BOLD, 16));
        deleteBtn.addActionListener(this);
        deleteBtn.setBorder(BorderFactory.createLineBorder(new Color(164, 92, 255), 2));
        deleteBtn.setBackground(new Color(0, 0, 0, 0));
        deleteBtn.setForeground(new Color(164, 92, 255));
        deleteBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                deleteBtn.setOpaque(true);
                deleteBtn.setBackground(new Color(164, 92, 255));
                deleteBtn.setForeground(new Color(255, 255, 255));
            }

            public void mouseExited(MouseEvent evt) {
                deleteBtn.setOpaque(false);
                deleteBtn.setBackground(new Color(0, 0, 0, 0));
                deleteBtn.setForeground(new Color(164, 92, 255));
            }
        });

        deletePanel.add(MainFrame.addSpace(300, 25));
        deletePanel.add(deleteBtn);

        panelTop.add(consultationIDPanel, BorderLayout.WEST);
        panelTop.add(deletePanel, BorderLayout.EAST);


        // Note Panel
        notesPanel = new JPanel(new FlowLayout());
        notesPanel.setPreferredSize(new Dimension(670, 140));
        notesPanel.setOpaque(false);

        JLabel notesLabel = new JLabel("Patient's Notes");
        notesLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        notesLabel.setForeground(new Color(166, 166, 166));
        notesLabel.setPreferredSize(new Dimension(200, 26));

        unlockNotesBtn = new JButton("Decrypt");
        unlockNotesBtn.setPreferredSize(new Dimension(100, 40));
        unlockNotesBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        unlockNotesBtn.addActionListener(this);
        unlockNotesBtn.setBorder(BorderFactory.createLineBorder(new Color(164, 92, 255), 2));
        unlockNotesBtn.setBackground(new Color(0, 0, 0, 0));
        unlockNotesBtn.setForeground(new Color(164, 92, 255));
        unlockNotesBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                unlockNotesBtn.setOpaque(true);
                unlockNotesBtn.setBackground(new Color(164, 92, 255));
                unlockNotesBtn.setForeground(new Color(255, 255, 255));
            }

            public void mouseExited(MouseEvent evt) {
                unlockNotesBtn.setOpaque(false);
                unlockNotesBtn.setBackground(new Color(0, 0, 0, 0));
                unlockNotesBtn.setForeground(new Color(164, 92, 255));
            }
        });

        JPanel notesTopPanel = new JPanel(new BorderLayout());
        notesTopPanel.setPreferredSize(new Dimension(600, 40));
        notesTopPanel.setOpaque(false);
        notesTopPanel.add(notesLabel, BorderLayout.WEST);
        notesTopPanel.add(unlockNotesBtn, BorderLayout.EAST);

        notesTextArea = new JTextArea();
        notesTextArea.setPreferredSize(new Dimension(600, 80));
        notesTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        notesTextArea.setLineWrap(true);
        notesTextArea.setWrapStyleWord(true);
        notesTextArea.setEditable(false);

        encryptedNote = consultation.getNotes();
        notesTextArea.setText(consultation.getNotes());

        notesPanel.add(notesTopPanel);
        notesPanel.add(notesTextArea);

        // Image Panel
        imagePanel = new JPanel(new FlowLayout());
        imagePanel.setPreferredSize(new Dimension(600, 270));
        imagePanel.setOpaque(false);

        JLabel imageLabel = new JLabel("Patient's Image");
        imageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        imageLabel.setForeground(new Color(166, 166, 166));
        imageLabel.setPreferredSize(new Dimension(200, 26));

        unlockImageBtn = new JButton("Decrypt");
        unlockImageBtn.setPreferredSize(new Dimension(100, 40));
        unlockImageBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        unlockImageBtn.addActionListener(this);
        unlockImageBtn.setBorder(BorderFactory.createLineBorder(new Color(164, 92, 255), 2));
        unlockImageBtn.setBackground(new Color(0, 0, 0, 0));
        unlockImageBtn.setForeground(new Color(164, 92, 255));
        unlockImageBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                unlockImageBtn.setOpaque(true);
                unlockImageBtn.setBackground(new Color(164, 92, 255));
                unlockImageBtn.setForeground(new Color(255, 255, 255));
            }

            public void mouseExited(MouseEvent evt) {
                unlockImageBtn.setOpaque(false);
                unlockImageBtn.setBackground(new Color(0, 0, 0, 0));
                unlockImageBtn.setForeground(new Color(164, 92, 255));
            }
        });

        JPanel imageTopPanel = new JPanel(new BorderLayout());
        imageTopPanel.setPreferredSize(new Dimension(600, 40));
        imageTopPanel.setOpaque(false);
        imageTopPanel.add(imageLabel, BorderLayout.WEST);
        imageTopPanel.add(unlockImageBtn, BorderLayout.EAST);

        imagePane = new JPanel();
        imagePane.setLayout(new FlowLayout());
        imagePane.setPreferredSize(new Dimension(600, 220));
        imagePane.setOpaque(true);
        imagePane.setBackground(new Color(255, 255, 255));

        imagePanel.add(imageTopPanel);
        imagePanel.add(imagePane);

        // Done and Cancel Buttons
        JPanel doneContainer = new JPanel();
        doneContainer.setPreferredSize(new Dimension(700, 50));
        doneBtn = new JButton("Done");
        doneBtn.setPreferredSize(new Dimension(220, 40));
        doneBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        doneBtn.setBorder(BorderFactory.createLineBorder(new Color(164, 92, 255), 2));
        doneBtn.setBackground(new Color(0, 0, 0, 0));
        doneBtn.setForeground(new Color(164, 92, 255));
        doneBtn.addActionListener(this);
        doneBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                doneBtn.setOpaque(true);
                doneBtn.setBackground(new Color(164, 92, 255));
                doneBtn.setForeground(new Color(255, 255, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                doneBtn.setOpaque(false);
                doneBtn.setForeground(new Color(164, 92, 255));
            }
        });
        doneContainer.add(doneBtn);

        mainFrame.add(panelTop);
        mainFrame.add(rows[0]);
        mainFrame.add(rows[1]);
        mainFrame.add(rows[2]);
        mainFrame.add(notesPanel);
        mainFrame.add(imagePanel);
        mainFrame.add(MainFrame.addSpace(700, 10));
        mainFrame.add(doneContainer);
//        mainFrame.add(cancelBtnContainer);
        mainFrame.setVisible(true);
        ConsultationsPanel.tableReRender(WestminsterSkinConsultationManager.getConsultationsArrayList());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == unlockNotesBtn) {
            JDialog passwordDialog = new JDialog();
            passwordDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            passwordDialog.setSize(400, 200);
            passwordDialog.setLocationRelativeTo(ConsultationView.getFrames()[0]);
            passwordDialog.setResizable(false);
            passwordDialog.setLayout(new FlowLayout());
            passwordDialog.setModal(true);


            JLabel passwordLabel = new JLabel("Enter password to decrypt notes");
            passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            passwordLabel.setForeground(new Color(91, 91, 91));
            passwordLabel.setPreferredSize(new Dimension(400, 26));
            passwordLabel.setHorizontalAlignment(JLabel.CENTER);

            JPasswordField passwordField = new JPasswordField();
            passwordField.setPreferredSize(new Dimension(300, 40));
            passwordField.setFont(new Font("Arial", Font.PLAIN, 30));
            passwordField.setBorder(BorderFactory.createLineBorder(new Color(131, 131, 131), 2));
            passwordField.setBackground(new Color(0, 0, 0, 0));
            passwordField.setForeground(new Color(164, 92, 255));
            passwordField.setCaretColor(Color.DARK_GRAY);
            passwordField.setEchoChar('*');


            JButton unlockBtn = new JButton("Unlock");
            unlockBtn.setPreferredSize(new Dimension(300, 40));
            unlockBtn.setFont(new Font("Arial", Font.PLAIN, 16));
            unlockBtn.setBorder(BorderFactory.createLineBorder(new Color(164, 92, 255), 2));
            unlockBtn.setBackground(new Color(0, 0, 0, 0));
            unlockBtn.setForeground(new Color(164, 92, 255));
            unlockBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String password = new String(passwordField.getPassword());
                    if (password.equals("5")) {
                        String decryptedText = EncryptAndDecrypt.decryptText(encryptedNote, decryptKey);
                        notesTextArea.setText(decryptedText);
                        unlockNotesBtn.setVisible(false);
                        passwordDialog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(ConsultationView.getFrames()[0], "Wrong password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            unlockBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    unlockBtn.setOpaque(true);
                    unlockBtn.setBackground(new Color(164, 92, 255));
                    unlockBtn.setForeground(new Color(255, 255, 255));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    unlockBtn.setOpaque(false);
                    unlockBtn.setForeground(new Color(164, 92, 255));
                }
            });

            passwordDialog.add(passwordLabel);
            passwordDialog.add(passwordField);
            passwordDialog.add(unlockBtn);
            passwordDialog.setVisible(true);
        }
        if (e.getSource() == unlockImageBtn) {
            JDialog passwordDialog = new JDialog();
            passwordDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            passwordDialog.setSize(400, 200);
            passwordDialog.setLocationRelativeTo(ConsultationView.getFrames()[0]);
            passwordDialog.setResizable(false);
            passwordDialog.setLayout(new FlowLayout());
            passwordDialog.setModal(true);


            JLabel passwordLabel = new JLabel("Enter password to decrypt notes");
            passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            passwordLabel.setForeground(new Color(91, 91, 91));
            passwordLabel.setPreferredSize(new Dimension(400, 26));
            passwordLabel.setHorizontalAlignment(JLabel.CENTER);

            JPasswordField passwordField = new JPasswordField();
            passwordField.setPreferredSize(new Dimension(300, 40));
            passwordField.setFont(new Font("Arial", Font.PLAIN, 30));
            passwordField.setBorder(BorderFactory.createLineBorder(new Color(131, 131, 131), 2));
            passwordField.setBackground(new Color(0, 0, 0, 0));
            passwordField.setForeground(new Color(164, 92, 255));
            passwordField.setCaretColor(Color.DARK_GRAY);
            passwordField.setEchoChar('*');


            JButton unlockBtn = new JButton("Unlock");
            unlockBtn.setPreferredSize(new Dimension(300, 40));
            unlockBtn.setFont(new Font("Arial", Font.PLAIN, 16));
            unlockBtn.setBorder(BorderFactory.createLineBorder(new Color(164, 92, 255), 2));
            unlockBtn.setBackground(new Color(0, 0, 0, 0));
            unlockBtn.setForeground(new Color(164, 92, 255));
            unlockBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String password = new String(passwordField.getPassword());
                    if (password.equals("5")) {
                        ArrayList<String> imagePaths = consultation.getImagesPaths();
                        for (String imagePath : imagePaths) {
                            String decryptedImagePath = null;
                            try {
                                EncryptAndDecrypt.decryptImage(imagePath, decryptKey);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            ImageIcon imageIcon = new ImageIcon(imagePath);
                            Image image = imageIcon.getImage();
                            Image newImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                            ImageIcon newImageIcon = new ImageIcon(newImage);
                            JLabel imageLabel = new JLabel(newImageIcon);
                            imageLabel.setPreferredSize(new Dimension(150, 150));
                            imageLabel.setBorder(BorderFactory.createLineBorder(new Color(164, 92, 255), 2));
                            imagePane.add(imageLabel);
                        }
                        openImage(imagePane, consultation);
                        unlockImageBtn.setVisible(false);
                        passwordDialog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(ConsultationView.getFrames()[0], "Wrong password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            unlockBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    unlockBtn.setOpaque(true);
                    unlockBtn.setBackground(new Color(164, 92, 255));
                    unlockBtn.setForeground(new Color(255, 255, 255));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    unlockBtn.setOpaque(false);
                    unlockBtn.setForeground(new Color(164, 92, 255));
                }
            });

            passwordDialog.add(passwordLabel);
            passwordDialog.add(passwordField);
            passwordDialog.add(unlockBtn);
            passwordDialog.setVisible(true);
        }
        if (e.getSource() == doneBtn) {
            ArrayList<String> imagePaths = consultation.getImagesPaths();
            // encrypt again
            // TODO: Encrypt only if they are in decrypted format
            for (String imagePath : imagePaths) {
                try {
                    EncryptAndDecrypt.encryptImage(imagePath, decryptKey);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            mainFrame.dispose();
            ConsultationsPanel.tableReRender(WestminsterSkinConsultationManager.getConsultationsArrayList());
        }
        if (e.getSource() == deleteBtn) {
            deleteConsultation();
        }
    }

    public static void deleteConsultation() {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete Consultation ?", "Warning", dialogButton);

        if (dialogResult == JOptionPane.YES_OPTION) {
            WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();

            ArrayList<String> imagePaths = consultation.getImagesPaths();
            Boolean isDeleted = true;
            for (String imagePath : imagePaths) {
                File file = new File(imagePath);
                if (file.delete()) {
                } else {
                    isDeleted = false;
                }
            }
            if (isDeleted) {
                new DeletingDone();
                WestminsterSkinConsultationManager mgr = new WestminsterSkinConsultationManager();
                mgr.saveConsultationsToFile();
            } else {
                JOptionPane.showMessageDialog(ConsultationView.getFrames()[0], "Error deleting images", "Error", JOptionPane.ERROR_MESSAGE);
            }

            manager.deleteAConsultation(consultation.getConsultationId());

            SessionsPanel.tableReRender(WestminsterSkinConsultationManager.getSessionsArrayList());
            ConsultationsPanel.tableReRender(WestminsterSkinConsultationManager.getConsultationsArrayList());
            mainFrame.dispose();
        }
    }

    private void openImage(JPanel panel, Consultation consultation) {
        Component[] component = panel.getComponents();

        String imagePath1 = consultation.getImagesPaths().get(0);
        String imagePath2 = consultation.getImagesPaths().get(1);

        ImageIcon imageIcon1 = new ImageIcon(imagePath1);
        Image image1 = imageIcon1.getImage();
        Image newImage1 = image1.getScaledInstance(1000, 1000, Image.SCALE_SMOOTH);
        ImageIcon newImageIcon1 = new ImageIcon(newImage1);
        JLabel imageLabel1 = new JLabel(newImageIcon1);
        imageLabel1.setPreferredSize(new Dimension(1000, 1000));


        component[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JDialog imageDialog = new JDialog();
                imageDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                imageDialog.setSize(1000, 1000);
                imageDialog.setLocationRelativeTo(ConsultationView.getFrames()[0]);
                imageDialog.setResizable(false);
                imageDialog.setLayout(new FlowLayout());
                imageDialog.setModal(true);
                imageDialog.add(imageLabel1);
                imageDialog.setVisible(true);
            }
        });

        ImageIcon imageIcon2 = new ImageIcon(imagePath2);
        Image image2 = imageIcon2.getImage();
        Image newImage2 = image2.getScaledInstance(1000, 1000, Image.SCALE_SMOOTH);
        ImageIcon newImageIcon2 = new ImageIcon(newImage2);
        JLabel imageLabel2 = new JLabel(newImageIcon2);
        imageLabel2.setPreferredSize(new Dimension(1000, 1000));

        component[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JDialog imageDialog = new JDialog();
                imageDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                imageDialog.setSize(1000, 1000);
                imageDialog.setLocationRelativeTo(ConsultationView.getFrames()[0]);
                imageDialog.setResizable(false);
                imageDialog.setLayout(new FlowLayout());
                imageDialog.setModal(true);
                imageDialog.add(imageLabel2);
                imageDialog.setVisible(true);
            }
        });
    }

}
