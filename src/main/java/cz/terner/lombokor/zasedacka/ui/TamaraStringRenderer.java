package cz.terner.lombokor.zasedacka.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class TamaraStringRenderer extends JLabel implements TableCellRenderer {

    public TamaraStringRenderer() {
        super();
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        String val = (String) value;
        super.setFont(new Font("Verdana", Font.BOLD, 12));
        super.setText(val);
        super.setHorizontalAlignment(RIGHT);
        super.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        if (isSelected) {
            super.setBackground(Color.gray);
            super.setForeground(Color.white);
        } else {
            if (row % 2 != 0) {
                super.setBackground(Color.LIGHT_GRAY);
            } else {
                super.setBackground(Color.WHITE);
            }
            
            super.setForeground(Color.black);
            
        } 

        return this;
    }

}
