package GUI.Main_Frames;

import GUI.GUILibs.StatusColumnCellRenderer;
import GUI.MainFrame;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

import static GUI.MainFrame.addSpace;

public class DashBoardPanel extends JPanel{
    JButton doctorPanel, patientPanel, sessionsPanel;
    JPanel cardPanel;

    JPanel tablePanel, upPanel, downPanel;
    JButton[] cardPanels = new JButton[3];
    String[] iconPaths = new String[3];
    String[] cardTitles = new String[3];
    String[] cardDescriptions = new String[3];
    String[] cardCounts = new String[3];

    JTable sessionsTable;
    JScrollPane sessionsTableScroll;

    public DashBoardPanel(){

        // Table
        String[] sessionsTableColumns = {"Session ID", "Doctor Name", "Date", "Time", "Maximum Patients", "Status"};
        Object[][] data = {
                {001,"John Doe", "2020-01-01", "10:00", 10, "Available"},
                {002,"John Doe", "2020-01-01", "10:00", 10, "Available"},
                {003,"John Doe", "2020-01-01", "10:00", 10, "Full"},
                {004,"John Doe", "2020-01-01", "10:00", 10, "Available"},
                {005,"John Doe", "2020-01-01", "10:00", 10, "Full"},
                {006,"John Doe", "2020-01-01", "10:00", 10, "Available"},
                {007,"John Doe", "2020-01-01", "10:00", 10, "Full"},
                {108,"John Doe", "2020-01-01", "10:00", 10, "Available"},
                {109,"John Doe", "2020-01-01", "10:00", 10, "Full"},
                {010,"John Doe", "2020-01-01", "10:00", 10, "Available"},
                {011,"John Doe", "2020-01-01", "10:00", 10, "Full"},
                {012,"John Doe", "2020-01-01", "10:00", 10, "Full"},
                {013,"John Doe", "2020-01-01", "10:00", 10, "Available"},
                {014,"John Doe", "2020-01-01", "10:00", 10, "Full"},
                {015,"John Doe", "2020-01-01", "10:00", 10, "Full"},
                {016,"John Doe", "2020-01-01", "10:00", 10, "Available"},
                {017,"John Doe", "2020-01-01", "10:00", 10, "Full"},
                {102,"John Doe", "2020-01-01", "10:00", 10, "Full"},
                {101,"John Doe", "2020-01-01", "10:00", 10, "Available"},
                {010,"John Doe", "2020-01-01", "10:00", 10, "Full"},
                {011,"John Doe", "2020-01-01", "10:00", 10, "Available"},
                {012,"John Doe", "2020-01-01", "10:00", 10, "Available"},
                {013,"John Doe", "2020-01-01", "10:00", 10, "Full"},
                {014,"John Doe", "2020-01-01", "10:00", 10, "Available"},
                {015,"John Doe", "2020-01-01", "10:00", 10, "Full"},
                {016,"John Doe", "2020-01-01", "10:00", 10, "Available"},
                {017,"John Doe", "2020-01-01", "10:00", 10, "Full"},
                {102,"John Doe", "2020-01-01", "10:00", 10, "Available"},
                {101,"John Doe", "2020-01-01", "10:00", 10, "Full"},
                {010,"John Doe", "2020-01-01", "10:00", 10, "Available"},
                {011,"John Doe", "2020-01-01", "10:00", 10, "Available"},
                {012,"John Doe", "2020-01-01", "10:00", 10, "Available"},
                {013,"John Doe", "2020-01-01", "10:00", 10, "Full"},
                {014,"John Doe", "2020-01-01", "10:00", 10, "Full"},
                {015,"John Doe", "2020-01-01", "10:00", 10, "Available"},
                {016,"John Doe", "2020-01-01", "10:00", 10, "Available"},
                {017,"John Doe", "2020-01-01", "10:00", 10, "Available"},
                {102,"John Doe", "2020-01-01", "10:00", 10, "Full"},
                {101,"John Doe", "2020-01-01", "10:00", 10, "Full"}
        };

        setSize(1300, 800);
        setLayout(new BorderLayout());

        sessionsTable = new JTable();
        sessionsTable.setModel(new DefaultTableModel(data, sessionsTableColumns));
        sessionsTable.setFont(new Font("Arial", Font.PLAIN, 15));
        sessionsTable.setRowHeight(40);

        JTableHeader header = sessionsTable.getTableHeader();
        header.setFont(new Font("Arial", Font.PLAIN, 15));
        header.setBackground(new Color(20, 0, 54));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(100,50));
        header.setOpaque(false);
        ((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < sessionsTable.getColumnCount(); i++) {
            sessionsTable.getColumnModel().getColumn(i).setCellRenderer(new StatusColumnCellRenderer());
        }

        sessionsTableScroll = new JScrollPane(sessionsTable);

        tablePanel = new JPanel();
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Upcoming Sessions");
        titledBorder.setTitleFont(new Font("Arial", Font.PLAIN, 30));
        tablePanel.setBorder(titledBorder);
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(sessionsTableScroll, BorderLayout.CENTER);

        // Cards
        iconPaths[0] = "src/GUI/Assets/doctorsGrad.png";
        iconPaths[1] = "src/GUI/Assets/patientGrad.png";
        iconPaths[2] = "src/GUI/Assets/sessionsGrad.png";

        cardTitles[0] = "Doctors";
        cardTitles[1] = "Patients";
        cardTitles[2] = "Sessions";

        cardDescriptions[0] = "Total Doctors";
        cardDescriptions[1] = "Total Patients";
        cardDescriptions[2] = "Total Sessions";

        cardCounts[0] = "10";
        cardCounts[1] = "1000";
        cardCounts[2] = "10";



        cardPanel = new JPanel(new FlowLayout());
        cardPanel.setOpaque(true);

        doctorPanel = new JButton(){
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D g2d) {
                    Paint p =
                            new GradientPaint(1.0f, 1.0f, new Color(132, 0, 197),
                                    getWidth(), getHeight(), new Color(224, 63, 255), true);
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    super.paintComponent(g);
                }
            }
        };
        patientPanel = new JButton(){
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D g2d) {
                    Paint p =
                            new GradientPaint(0.0f, 0.0f, new Color(48, 0, 178),
                                    getWidth(), getHeight(), new Color(0, 154, 218), true);
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    super.paintComponent(g);
                }
            }
        };
        sessionsPanel = new JButton(){
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D g2d) {
                    Paint p =
                            new GradientPaint(0.0f, 0.0f, new Color(190, 65, 0),
                                    getWidth(), getHeight(), new Color(227, 122, 0), true);
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    super.paintComponent(g);
                }
            }
        };
        cardPanels[0] = doctorPanel;
        cardPanels[1] = patientPanel;
        cardPanels[2] = sessionsPanel;

        for(int i = 0; i < 3; i++){

            cardPanels[i].setPreferredSize(new Dimension(240, 150));
            // Card texts
            JPanel cardEastPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            cardEastPanel.setPreferredSize(new Dimension(90, 100));

            cardEastPanel.add(addSpace(100, 20));
            cardEastPanel.setOpaque(false);
            JLabel cardTitle = new JLabel(cardTitles[i]);
            cardTitle.setForeground(Color.WHITE);
            cardTitle.setPreferredSize(new Dimension(200, 20));
            cardTitle.setFont(new Font("Arial", Font.BOLD, 20));
            cardEastPanel.add(cardTitle);

            JLabel cardDescription = new JLabel(cardDescriptions[i]);
            cardDescription.setPreferredSize(new Dimension(200, 20));
            cardDescription.setFont(new Font("Arial", Font.PLAIN, 15));
            cardDescription.setForeground(new Color(180, 180, 180, 200));
            cardEastPanel.add(cardDescription);

            JLabel cardCount = new JLabel(cardCounts[i]);
            cardCount.setFont(new Font("Arial", Font.BOLD, 30));
            cardCount.setForeground(Color.WHITE);
            cardEastPanel.add(cardCount);

            try {
                ImageIcon cardIcon = new ImageIcon(iconPaths[i]);
                cardIcon = MainFrame.scaleImage(cardIcon, 100, 100);
                JLabel cardIconLabel = new JLabel(cardIcon);

                cardPanels[i].add(cardIconLabel, BorderLayout.WEST);
                cardPanels[i].add(cardEastPanel,BorderLayout.CENTER);
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
        cardPanel.add(cardPanels[0]);
        cardPanel.add(addSpace(20, 20));
        cardPanel.add(cardPanels[1]);
        cardPanel.add(addSpace(20, 20));
        cardPanel.add(cardPanels[2]);

        upPanel = new JPanel(new FlowLayout());
        upPanel.setPreferredSize(new Dimension(1200, 220));
        upPanel.add(addSpace(1200, 20));
        upPanel.add(cardPanel);


        add(upPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(addSpace(1200, 20), BorderLayout.SOUTH);
    }
}
