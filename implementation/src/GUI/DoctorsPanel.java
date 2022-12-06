package GUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import static GUI.MainFrame.addSpace;
import static GUI.MainFrame.scaleImage;

public class DoctorsPanel extends JPanel implements ActionListener {
    JPanel mainPanel1, mainPanel2, mainPanel3;
    JPanel panel1Top, panel1Bottom;
    JPanel panel1BottomWest, panel1BottomEast;
    JPanel searchPanel;
    JTextField searchField;
    ImageIcon searchIcon;
    JLabel panelTitle, allDoctors;
    JButton addDoctor, editDoctor, deleteDoctor,importData;
    JButton[] purpleButtons = new JButton[4];
    JButton refreshButton;
    JTable doctorsTable;
    JPanel tablePanel;
    JScrollPane doctorsTableScroll;
    JPanel panel3West, panel3East;
    JButton saveDataButton, sortDataButton;
    JButton[] panel3Buttons = new JButton[2];
    String[] panel3ButtonIconPaths = new String[2];
    public DoctorsPanel() {
        setSize(1300, 800);
        setLayout(new BorderLayout());

        // Main Panel 1
        mainPanel1 = new JPanel();
        mainPanel1.setOpaque(true);
        mainPanel1.setOpaque(false);
        mainPanel1.setPreferredSize(new Dimension(1000, 180));

        // Main Panel 3
        mainPanel2 = new JPanel();
        mainPanel2.setOpaque(true);
        mainPanel2.setPreferredSize(new Dimension(1300, 600));

        // Main Panel 3
        mainPanel3 = new JPanel(new BorderLayout());
        mainPanel3.setOpaque(false);
        mainPanel3.setPreferredSize(new Dimension(1300, 100));

        // Adding Main Panels to Main Panel
        add(mainPanel1, BorderLayout.NORTH);
        add(mainPanel2, BorderLayout.CENTER);
        add(mainPanel3, BorderLayout.SOUTH);


        // Panel 1 Components
        // Panel 1 Top
        panel1Top = new JPanel(new BorderLayout());
        panel1Top.setOpaque(false);
        panel1Top.setPreferredSize(new Dimension(1000, 50));
        panelTitle = new JLabel("All Doctors");
        panelTitle.setForeground(new Color(91, 91, 91, 200));
        panelTitle.setFont(new Font("Arial", Font.BOLD, 40));

        searchPanel = new JPanel(new BorderLayout());
        searchPanel.setPreferredSize(new Dimension(300, 50));
        searchPanel.setOpaque(true);
        searchPanel.setBackground(new Color(225, 225, 225));
        searchField = new JTextField("Search Doctors...");
        searchField.setOpaque(false);
        searchField.setBorder(null);
        searchField.setFont(new Font("Arial", Font.PLAIN, 20));
        searchField.setForeground(new Color(124, 124, 124, 200));
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search Doctors...")) {
                    searchField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().equals("")) {
                    searchField.setText("Search Doctors...");
                }
            }
        });
        searchField.setPreferredSize(new Dimension(320, 30));
        searchPanel.add(addSpace(30, 10), BorderLayout.WEST);
        searchPanel.add(searchField,BorderLayout.CENTER);

        searchIcon = new ImageIcon("src/GUI/Assets/search.png");
        try {
            searchIcon = new ImageIcon("src/GUI/Assets/search.png");
            searchIcon = scaleImage(searchIcon, 40, 40);
            JLabel searchLabel = new JLabel(searchIcon);
            searchPanel.add(searchLabel, BorderLayout.EAST);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        panel1Top.add(panelTitle, BorderLayout.WEST);
        panel1Top.add(searchPanel, BorderLayout.EAST);

        mainPanel1.add(addSpace(1000, 20));
        mainPanel1.add(panel1Top);

        // Panel 1 Bottom
        addDoctor = new JButton("Add Doctor");
        editDoctor = new JButton("Edit Doctor");
        deleteDoctor = new JButton("Delete Doctor");
        importData = new JButton("Import Data");

        purpleButtons[0] = addDoctor;
        purpleButtons[1] = editDoctor;
        purpleButtons[2] = deleteDoctor;
        purpleButtons[3] = importData;

        for (int i = 0; i < 4; i++) {
            purpleButtons[i].setPreferredSize(new Dimension(150, 50));
            purpleButtons[i].setForeground(new Color(164, 92, 255));
            purpleButtons[i].setFont(new Font("Arial", Font.PLAIN, 14));
//            purpleButtons[i].setBorder(new RoundedBorder(20));
            purpleButtons[i].setBorder(BorderFactory.createLineBorder(new Color(164, 92, 255), 2));
            purpleButtons[i].addActionListener(this);
        }

        panel1Bottom = new JPanel(new BorderLayout());
        panel1Bottom.setOpaque(false);
        panel1Bottom.setPreferredSize(new Dimension(1000, 100));
        // Panel 1 Bottom West
        panel1BottomWest = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel1BottomWest.setOpaque(false);
        panel1BottomWest.setPreferredSize(new Dimension(700, 600));
        panel1BottomWest.add(addDoctor);
        panel1BottomWest.add(editDoctor);
        panel1BottomWest.add(deleteDoctor);

        panel1Bottom.add(panel1BottomWest, BorderLayout.WEST);

        mainPanel1.add(addSpace(1000, 10));
        mainPanel1.add(panel1Bottom);

        // Panel 1 Bottom East
        panel1BottomEast = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel1BottomEast.setOpaque(false);
        panel1BottomEast.setPreferredSize(new Dimension(300, 50));
        panel1BottomEast.add(importData);

        ImageIcon buttonIcon = new ImageIcon("src/GUI/Assets/reload.png");
        buttonIcon = scaleImage(buttonIcon, 50, 50);

        ImageIcon finalButtonIcon = buttonIcon;

        refreshButton = new JButton("A"){
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D g2d) {
                    Paint p =
                            new GradientPaint(0.0f, 0.0f, new Color(198, 139, 255, 255),
                                    getWidth(), getHeight(), new Color(100, 143, 255, 255), true);
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    super.paintComponent(g);
                }
                g.drawImage(finalButtonIcon.getImage(), 0, 0, null);
            }
        };

        refreshButton.setPreferredSize(new Dimension(50, 50));
        panel1BottomEast.add(refreshButton);

        panel1Bottom.add(panel1BottomEast, BorderLayout.EAST);


        // Main Panel 2 Components
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setOpaque(false);
        tablePanel.setPreferredSize(new Dimension(1000, 600));

        // Table
        String[] doctorsTableColumns = {"Doctor ID", "First Name", "Last Name", "Phone Number", "Email", "Speciality", "Actions"};
        String[][] doctorData = {
                {"D0001","John", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0002","Jane", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0001","John", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0002","Jane", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0001","John", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0002","Jane", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0001","John", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0002","Jane", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0001","John", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0002","Jane", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0001","John", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0002","Jane", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0001","John", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0002","Jane", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0001","John", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0002","Jane", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0001","John", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0002","Jane", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0001","John", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0002","Jane", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0001","John", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0002","Jane", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0001","John", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0002","Jane", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0001","John", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0002","Jane", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0001","John", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0002","Jane", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0001","John", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0002","Jane", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0001","John", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0002","Jane", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0001","John", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"},
                {"D0002","Jane", "Doe", "+9470234882","doc1002@hotmail.com","Heart","Action"}
        };
        doctorsTable = new JTable();
        doctorsTable.setRowHeight(40);
        doctorsTable.setModel(new DefaultTableModel(doctorData, doctorsTableColumns));
        doctorsTable.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        JTableHeader header = doctorsTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        header.setBackground(new Color(51, 0, 101, 255));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(100, 40));
        ((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        doctorsTableScroll = new JScrollPane(doctorsTable);

        tablePanel.setOpaque(false);
        tablePanel.setPreferredSize(new Dimension(1000, 490));


        TitledBorder tablePanelBorder = new TitledBorder("Doctors");
        tablePanelBorder.setTitleFont(new Font("Segoe UI", Font.PLAIN, 20));
        tablePanel.setBorder(tablePanelBorder);

        tablePanel.add(doctorsTableScroll, BorderLayout.CENTER);
        mainPanel2.add(tablePanel, BorderLayout.CENTER);

        // Main Panel 3 Components
        panel3West = new JPanel(new FlowLayout());
        panel3West.setOpaque(false);
        panel3West.setPreferredSize(new Dimension(200, 50));

        panel3East = new JPanel(new FlowLayout());
        panel3East.setOpaque(false);
        panel3East.setPreferredSize(new Dimension(340, 50));


        // Panel 3 East Components
        saveDataButton = new JButton("Save Data");
        sortDataButton = new JButton("Sort Data");

        panel3Buttons[0] = saveDataButton;
        panel3Buttons[1] = sortDataButton;

        panel3ButtonIconPaths[0] = "src/GUI/Assets/save.png";
        panel3ButtonIconPaths[1] = "src/GUI/Assets/sort.png";

        for (int i = 0; i<2; i++){
            try {
                ImageIcon panel3buttonIcon = new ImageIcon(panel3ButtonIconPaths[i]);
                panel3buttonIcon = scaleImage(panel3buttonIcon, 20, 20);
                panel3Buttons[i].setIcon(panel3buttonIcon);
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
            panel3Buttons[i].setFont(new Font("Segoe UI", Font.PLAIN, 20));
            panel3Buttons[i].setPreferredSize(new Dimension(150, 50));
            panel3Buttons[i].setOpaque(true);
            panel3Buttons[i].setBackground(new Color(215, 215, 215));
            panel3Buttons[i].setForeground(new Color(107, 107, 107));
            panel3Buttons[i].setBorderPainted(false);
            panel3Buttons[i].setFocusPainted(false);
            panel3Buttons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        panel3East.add(panel3Buttons[0]);
        panel3East.add(panel3Buttons[1]);

        // Panel 3 West Components
        // set all doctor count ************************************************************
        allDoctors = new JLabel("All Doctors"+" 22");
        allDoctors.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        allDoctors.setForeground(Color.GRAY);
        allDoctors.setOpaque(false);

        panel3West.add(allDoctors);

        mainPanel3.add(addSpace(0, 20), BorderLayout.NORTH);
        mainPanel3.add(panel3West, BorderLayout.WEST);
        mainPanel3.add(panel3East, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addDoctor){
            System.out.println("Add doctor");
            AddDoctor addDoctor = new AddDoctor();
            addDoctor.setVisible(true);
        }
        if (e.getSource() == deleteDoctor){
            System.out.println("delete doctor");
        }
        if (e.getSource() == editDoctor){
            System.out.println("edit doctor");
        }
    }
}
