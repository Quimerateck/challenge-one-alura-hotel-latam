package utils;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Huesped;
import modelo.Reserva;

public class RellenarTabla {

    public static void llenarTablaHuespedes(JTable tabla, Vector<Huesped> vector){

        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        
        for (int i = 0; i < vector.size(); i++ ){
            Huesped huesped = vector.get(i);
            model.addRow(new Object[]{
                huesped.getId(),
                huesped.getNombre(),
                huesped.getApellido(),
                huesped.getFechaNacimiento(),
                huesped.getNacionalidad(),
                huesped.getTelefono(),
                huesped.getIdReserva()});    
        }
    }

    public static void llenarTablaReservas(JTable tabla, Vector<Reserva> vector){

        DefaultTableModel model = (DefaultTableModel) tabla.getModel();

        for (int i = 0; i < vector.size(); i++ ){
            Reserva reserva = vector.get(i);
            model.addRow(new Object[]{
                reserva.getId(),
                reserva.getFechaEntrada(),
                reserva.getFechaSalida(),
                reserva.getValor(),
                reserva.getFormaDePago()
            } );
        }
    }
}
