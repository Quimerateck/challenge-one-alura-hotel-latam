package utils;

import java.util.ArrayList;

import javax.swing.JTable;

public class RetornarID {

    public static int conseguirID(JTable tabla){
        int seleccionado = tabla.getSelectedRow();
        int id = (int) tabla.getModel().getValueAt(seleccionado, 0);
        return id;
    }

    public static ArrayList<Integer> conseguirListaID(JTable tabla){
        ArrayList<Integer> idList = new ArrayList<>();
        int seleccionado = tabla.getSelectedRow();
        idList.add((int) tabla.getModel().getValueAt(seleccionado, 0));
        idList.add((int) tabla.getModel().getValueAt(seleccionado, 6));
        
        return idList;
    }
}
