
package cz.terner.lombokor.zasedacka.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

public class TamaraTableRenderer extends JLabel implements TableCellRenderer {
    
    private final List<TamaraItem> tamaraItems;
    
    public TamaraTableRenderer(List<TamaraItem> tamaraItems) {
        super.setOpaque(true);
        this.tamaraItems = tamaraItems;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        TamaraCellType tct = (TamaraCellType) value;
        switch (tct) {
            case OCCUPIED:
                super.setBackground(Color.RED);
                super.setText(tamaraItems.get(row).getOcc().name());
                break;
            case FREE:
                super.setBackground(Color.GREEN);
                super.setText(tamaraItems.get(row).getOcc().name());
        }
        Border b;
        Color white = Color.WHITE;
        b = BorderFactory.createCompoundBorder();
            b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(2,0,0,0,white));
            b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0,2,0,0,white));
            b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0,0,2,0,white));
            b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0,0,0,2,white));
        super.setBorder(b);
        super.setFont(new Font("Verdana", Font.PLAIN, 11));
        super.setHorizontalAlignment(CENTER);
        return this;
    }

}
