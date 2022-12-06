package GUI;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;

public class AddDoctor extends JFrame {
    JPanel panel1, panel2, panel3, panel4;
    JLabel fName, sName, dob, mobileNum, medicalLicenceNum, specialisation;
    JButton addDoctor, cancel;
    JTextField fNameField, lNameField, mobileNumField, medicalLicenceNumField;
    JDatePicker dobField;
    public AddDoctor() {
        super("Add Doctor");
        setSize(420, 500);
        setLayout(new FlowLayout());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        JButton button = new JButton("Add Doctor");
        add(button);
    }
}
