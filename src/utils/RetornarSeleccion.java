package utils;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JViewport;

public class RetornarSeleccion {

    public static JTable retornarTabla(JTabbedPane pane){
        JScrollPane scrollPane = (JScrollPane) (pane.getSelectedComponent()); 
		JViewport viewport = scrollPane.getViewport();
		JTable table = (JTable)viewport.getView(); 
        return table;
    }

    public static String retornarNombreTab(JTabbedPane pane){
        return pane.getTitleAt(pane.getSelectedIndex());
    }

    public static Object retornarValorNuevo(JTable table){
        Object valor = table.getModel().getValueAt(
            table.getSelectedRow(),
            table.getSelectedColumn()
        );
        return valor;
    }
}
