
package cz.terner.lombokor.zasedacka.ui;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TamaraTableModel extends AbstractTableModel {
    
    private final List<TamaraItem> tamaraItems;
    private final String[] columns = {"ID", "START", "END", "OCC"};
    private final Class[] columnClasses = {Integer.class, String.class, String.class, TamaraCellType.class};

    public TamaraTableModel(List<TamaraItem> tamaraItems) {
        this.tamaraItems = tamaraItems;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }
    
    @Override
    public int getRowCount() {
        return tamaraItems.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TamaraItem ti = tamaraItems.get(rowIndex);
        if (columnIndex == 0) {
            return ti.getId();
        } else if (columnIndex == 1) {
            return ti.getTimeStart();
        } else if (columnIndex == 2) {
            return ti.getTimeEnd();
        } else if (columnIndex == 3) {
            return ti.getOcc();
        } else {
            return null;
        }
    }

}
