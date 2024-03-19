package utils;

import java.sql.Date;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import modelo.Reserva;

public class Reservar {

    public static void guardarReserva(JDateChooser fechaEntrada, JDateChooser fechaSalida, JComboBox<String> formaPago, JTextField valor){
		String fechaDeEntrada = ((JTextField) fechaEntrada.getDateEditor().getUiComponent()).getText();
		String fechaDeSalida = ((JTextField) fechaSalida.getDateEditor().getUiComponent()).getText();

		Reserva nuevaReserva = new Reserva(
			Date.valueOf(fechaDeEntrada), 
			Date.valueOf(fechaDeSalida), 
			Float.valueOf(valor.getText()), 
			formaPago.getSelectedItem().toString());
		//TODO: resolver esto, lograr que se guarde la reserva junto con el huesped para luego Commit
        utils.Guardar.setDatos(nuevaReserva);
		//ReservasController.guardar(nuevaReserva);
	}
}
