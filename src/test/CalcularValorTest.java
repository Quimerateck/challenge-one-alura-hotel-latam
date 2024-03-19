package test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.GregorianCalendar;

public class CalcularValorTest {
    
    public static void main(String[] args) {
        final String fechaEntrada = "2023-01-01";
        final String fechaSalida = "2023-02-01";
        
        final Date entrada = Date.valueOf( fechaEntrada ); // conversion from String
        final Date salida = Date.valueOf( fechaSalida ); // conversion from String
    
        long diferencia = ((salida.getTime() - entrada.getTime()) / (1000 * 60 * 60)) / 24 ;
        
        System.out.println(diferencia);
    }
}
