package controller;

import java.util.Vector;

import dao.ReservasDao;
import modelo.Huesped;
import modelo.Reserva;

public class ReservasController {

    public static void guardar(Reserva nuevaReserva, Huesped nuevoHuesped) {
        new ReservasDao().guardar(nuevaReserva, nuevoHuesped);
    }

    public static Vector<Reserva> buscar(String busqueda) {
        return ReservasDao.buscar(busqueda);
    }

    public static String ultimoID() {
        return ReservasDao.ultimoID();
    }

    public static void editar(int rowId, String nombreTabla, int colIndex, Object value) {
        ReservasDao.editar(rowId, nombreTabla, colIndex, value);
    }
    
}
