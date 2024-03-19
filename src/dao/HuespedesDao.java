package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import factory.ConnectionFactory;
import modelo.Huesped;

public class HuespedesDao {

    
    //Listar Huespeds
    
    @SuppressWarnings("finally")
    public static Vector<Huesped> buscar(String busqueda) {
        final Connection con = new ConnectionFactory().startConnection();
        Vector<Huesped> huesped = new Vector<Huesped>();

        try {
            if (busqueda == null || busqueda == ""){
                busqueda = " ";
            }
            final PreparedStatement statement = con.prepareStatement("SELECT * FROM huespedes WHERE " +
                                                                    " INSTR(id, ?) or "+
                                                                    " INSTR(nombre, ?) or "+ 
                                                                    " INSTR(apellido, ?) or "+
                                                                    " INSTR(fecha_de_nacimiento, ?) or"+
                                                                    " INSTR(nacionalidad, ?) or "+
                                                                    " INSTR(telefono, ?) or "+
                                                                    " INSTR(id_reservas, ?)");
            for (int i = 1; i <= 7; i++){
                statement.setString(i, busqueda);
            }
            statement.execute();

            try {
                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {
                    while (resultSet.next()){
                        Huesped perfilHuesped = new Huesped(resultSet.getInt("id"),
                                                    resultSet.getString("nombre"),
                                                    resultSet.getString("apellido"),
                                                    resultSet.getDate("fecha_de_nacimiento"),
                                                    resultSet.getString("nacionalidad"),
                                                    resultSet.getString("telefono"),
                                                    resultSet.getInt("id_reservas"));
                        huesped.add(perfilHuesped);
                        // System.out.println(perfilHuesped.getNombre() +
                        //                     perfilHuesped.getApellido()+
                        //                     perfilHuesped.getFechaNacimiento() +
                        //                     perfilHuesped.getNacionalidad() +
                        //                     perfilHuesped.getNacionalidad() +
                        //                     perfilHuesped.getTelefono() +
                        //                     perfilHuesped.getIdReserva());
                        
                    }
                }
                return huesped;
            } finally {
                return huesped;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Eliminar de la base de datos el cliente y su reserva o solo la reserva
    public static void eliminar(int id, int idReserva) {
        final Connection con = new ConnectionFactory().startConnection();
        String borrarHuespedQuery = "DELETE FROM huespedes WHERE id = ?";
        String borrarReservaQuery = "DELETE FROM reservas WHERE id = ?";

        try {
            con.setAutoCommit(false);

            PreparedStatement borrarHuespedStatement = con.prepareStatement(borrarHuespedQuery);
                borrarHuespedStatement.setInt(1,  id);
            borrarHuespedStatement.executeUpdate();

            PreparedStatement borrarReservaStatement = con.prepareStatement(borrarReservaQuery);
                borrarReservaStatement.setInt(1, idReserva);
            borrarReservaStatement.executeUpdate();

            con.commit();
            con.close();
        }catch(SQLException e) {
            if (con != null) {
                try{
                    con.rollback();
                    con.close();
                }catch (SQLException e2){
                    throw new RuntimeException(e2);
                }
            }
            throw new RuntimeException(e);
        }
    }
}
