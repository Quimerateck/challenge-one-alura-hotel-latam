package utils;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VaciarTabla {

    public static void vaciar(JTable tabla) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0);
    }

    public static void vaciarFila(JTable tabla){
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.removeRow(tabla.getSelectedRow());
    }
}
