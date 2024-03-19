package utils;

import java.sql.Date;

import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class Calcular {
    public static String calcularValorReserva(JDateChooser fechaEntrada, JDateChooser fechaSalida) {
		Float precioPorDia = 12.5f;
		String fechaDeEntrada = ((JTextField) fechaEntrada.getDateEditor().getUiComponent()).getText();
		String fechaDeSalida = ((JTextField) fechaSalida.getDateEditor().getUiComponent()).getText();

		long diasDeEstadia = ((Date.valueOf(fechaDeSalida).getTime() - Date.valueOf(fechaDeEntrada).getTime()) / (1000 * 60 * 60)) / 24;
		String valorTotal = String.valueOf(precioPorDia * diasDeEstadia);
		
		return valorTotal;
	}
}
