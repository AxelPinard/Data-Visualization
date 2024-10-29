import java.awt.event.ActionListener;
import java.util.ArrayList;

public interface DataSubject {

    default Object[] getDataForAnalysis(){
        return null;
    }
    default Object[] getTableDataForAnalysis(int column){
        return null;
    }
    void addActionListener(ActionListener listener);
    void removeActionListener(ActionListener listener);
    ArrayList<ActionListener> getActionListeners();
    void removeAllActionListeners();
}
