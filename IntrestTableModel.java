import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class IntrestTableModel implements TableModel {
    int rowCount;
    int columnCount;
    final String[] columnNames = {"Country Name", "Series Name", "2015",
                                    "2016", "2017", "2018", "2019", "2020", "2021",
                                    "2022", "2023"};
    final int[] viewColumns = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    ArrayList<TableModelListener> listeners;
    Object[][] data;

    public IntrestTableModel(List<IntrestData> data2){
        columnCount = columnNames.length;
        listeners = new ArrayList<>();

        data = new Object[data2.size()][10];

        for(int i = 0; i <data2.size(); i++){
                data[i][0] = data2.get(i).getCountryName();
                data[i][1] = data2.get(i).getSeriesName();
                for (int y = 0; y < data2.get(i).getDataYear().size()-1; y++) {
                    data[i][y + 2] = data2.get(i).getDataYear().get(y);
                }
        }
        rowCount = data.length;

    }

    @Override
    public int getRowCount() { return rowCount;}
    @Override
    public int getColumnCount() {return columnCount;}
    @Override
    public String getColumnName(int column) {return columnNames[column];}
    @Override
    public Class<?> getColumnClass(int column) {return getValueAt(0, column).getClass();}
    @Override
    public boolean isCellEditable(int row, int column) {return false;}
    @Override
    public Object getValueAt(int row, int column) {
        if (column > 9){ column = 9;}
        if(data[row][column] != null) {
            return data[row][column];
        }
            return data[0][0];
    }
    @Override
    public void setValueAt(Object value, int row, int column) {}
    @Override
    public void addTableModelListener(TableModelListener l) {listeners.add(l);}
    @Override
    public void removeTableModelListener(TableModelListener l) {listeners.remove(l);}

}
