import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;

public class TablePanel extends JPanel {
    JScrollPane scrollPane;
    JTable table;
    JPanel filterPanel;
    JCheckBox[] filters;

    public TablePanel(TableModel data){
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(1000, 500));

        filterPanel = new JPanel();
        filterPanel.setBackground(Color.BLUE);
        filterPanel.setPreferredSize(new Dimension(800, 50));

        table = new JTable();
        table.setModel(data);

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 50));
        table.setFillsViewportHeight(true);

        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.sizeWidthToFit();
        }
        add(filterPanel);
        add(scrollPane);
    }
}
