package GUI.GUILibs;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class StatusColumnCellRenderer extends DefaultTableCellRenderer {
    public StatusColumnCellRenderer() {
        super();
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value instanceof String) {
            String status = (String) value;
            if (status.equals("Available")) {
                cell.setBackground(new Color(0, 255, 0,50));
                cell.setForeground(Color.BLACK);
            } else if (status.equals("Full") || status.equals("Unavailable")) {
                cell.setBackground(new Color(255, 0, 0,50));
                cell.setForeground(Color.BLACK);
            } else {
                cell.setBackground(Color.WHITE);
                cell.setForeground(Color.BLACK);
            }
        }
        return cell;
    }
}
