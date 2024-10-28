import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.io.FileReader;
import java.util.ArrayList;

public class IntrestTableModel implements TableModel {
    int rowCount;
    int columnCount;
    final String[] columnNames = {"Country Name", "Series Name", "2015",
                                    "2016", "2017", "2018", "2019", "2020", "2021",
                                    "2022", "2023"};
    final int[] viewColumns = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    ArrayList<TableModelListener> listeners;
    Object[][] data;

    public IntrestTableModel(IntrestData data){
        columnCount = columnNames.length;
        listeners = new ArrayList<>();
        try{
            data = F
        } catch (IDException e){
            System.out.println("Error reading file");
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
        return data[row][viewColumns[column]];}
    @Override
    public void setValueAt(Object value, int row, int column) {}
    @Override
    public void addTableModelListener(TableModelListener l) {listeners.add(l);}
    @Override
    public void removeTableModelListener(TableModelListener l) {listeners.remove(l);}

}
