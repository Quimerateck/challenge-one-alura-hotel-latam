package controller;

import java.util.ArrayList;
import java.util.Vector;

import dao.HuespedesDao;
import dao.ReservasDao;
import modelo.Huesped;
import modelo.Reserva;

public class HuespedesController {
    //TODO:Controles para manejar los huespedes
    public static void guardar(Huesped huesped){
        Reserva reserva = (Reserva) utils.Guardar.getDatos();
        ReservasDao reservar = new ReservasDao();
        reservar.guardar(reserva, huesped);
    }

    public static void eliminar(ArrayList<Integer> ids){
        HuespedesDao.eliminar(ids.get(0), ids.get(1));
    }

    public static void editar(){
        //TODO:Crear funcion para editar el campo seleccionado o actualizar toda la info de una
    }

    public static Vector<Huesped> buscar(String busqueda){
        return HuespedesDao.buscar(busqueda);
        
    }
}
