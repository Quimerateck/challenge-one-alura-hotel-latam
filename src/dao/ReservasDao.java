package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import factory.ConnectionFactory;
import modelo.Huesped;
import modelo.Reserva;

public class ReservasDao {

    

    //Guardar
    public void guardar(Reserva datosReserva, Huesped datosHuesped ) {
        final Connection con = new ConnectionFactory().startConnection();
        Reserva reserva = datosReserva;
        Huesped huesped = datosHuesped;

        String reservaQuery = "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_de_pago) \n" + //
                        "\tVALUES (?,?,?,?);";
                        
        String huespedQuery = "INSERT INTO huespedes " + 
                "(nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reservas)" +
                "VALUES ( ?, ?, ?, ?, ?, last_insert_id());";

        try {

            con.setAutoCommit(false);

            PreparedStatement guardarReserva = con.prepareStatement(reservaQuery);
                guardarReserva.setDate(1, reserva.getFechaEntrada());
                guardarReserva.setDate(2, reserva.getFechaSalida());
                guardarReserva.setFloat(3, reserva.getValor());
                guardarReserva.setString(4, reserva.getFormaDePago());
            guardarReserva.executeUpdate();
            // solo para query del tipo INSERT, DELETE y UPDATE

            PreparedStatement guardarHuesped = con.prepareStatement(huespedQuery);
                guardarHuesped.setString(1, huesped.getNombre());
                guardarHuesped.setString(2, huesped.getApellido());
                guardarHuesped.setDate(3, huesped.getFechaNacimiento());
                guardarHuesped.setString(4, huesped.getNacionalidad());
                guardarHuesped.setString(5, huesped.getTelefono());

            guardarHuesped.executeUpdate();
            
            con.commit();
            con.close();
            
        } catch (SQLException e) {
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

    @SuppressWarnings("finally")
    public static Vector<Reserva> buscar(String busqueda) {
        final Connection con = new ConnectionFactory().startConnection();

        Vector<Reserva> reserva = new Vector<Reserva>();

        try {
            if (busqueda == null || busqueda == ""){
                busqueda = " ";
            }
            final PreparedStatement statement = con.prepareStatement("SELECT * FROM reservas WHERE " +
                                                                    " INSTR(id, ?) or "+
                                                                    " INSTR(fecha_entrada, ?) or "+ 
                                                                    " INSTR(fecha_salida, ?) or "+
                                                                    " INSTR(valor, ?) or "+
                                                                    " INSTR(forma_de_pago, ?)");
            for (int i = 1; i <= 5; i++){
                statement.setString(i, busqueda);
            }
            statement.execute();

            try {
                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {
                    while (resultSet.next()){
                        Reserva perfilReserva= new Reserva(resultSet.getInt("id"),
                                                            resultSet.getDate("fecha_entrada"),
                                                            resultSet.getDate("fecha_salida"),
                                                            resultSet.getFloat("valor"),
                                                            resultSet.getString("forma_de_pago"));
                        reserva.add(perfilReserva);
                        
                    }
                }
                return reserva;
            } finally {
                return reserva;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String ultimoID() {
        final Connection con = new ConnectionFactory().startConnection();
        int id = 0;
        try(con){

            final PreparedStatement statement = con.prepareStatement("SELECT Max(id) FROM reservas");

            try(statement){

                statement.execute();
                
                final ResultSet resultSet = statement.getResultSet();

                try(resultSet){
                    if(resultSet.next()){
                        id = resultSet.getInt(1);
                    }
                }
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return Integer.toString(id);
    }

    public static void editar(int id, String nombreTabla, int colIndex, Object value) {
        final Connection con = new ConnectionFactory().startConnection();
        
        String updateQuery = "UPDATE tabla SET columna = ? WHERE id = ?";
        updateQuery = updateQuery.replace("tabla", nombreTabla.toLowerCase());
        updateQuery = updateQuery.replace("columna", identificarColumna(colIndex, nombreTabla));

        try(con){

            final PreparedStatement statement = con.prepareStatement(updateQuery);
                diferenciarValor(1, value, statement);
                statement.setInt(2, id);
                

                try(statement){
                    
                    // System.out.println("+==================================================+");
                    // System.out.println(statement);
                    // System.out.println("+==================================================+");
                    statement.executeUpdate();
                }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    private static String identificarColumna(int index, String table){

        final ArrayList<String> colReservas = new ArrayList<>();
            colReservas.add("id");
            colReservas.add("fecha_entrada");
            colReservas.add("fecha_salida");
            colReservas.add("valor");
            colReservas.add("forma_de_pago");

        final ArrayList<String> colHuespedes = new ArrayList<>();
            colHuespedes.add("id");
            colHuespedes.add("nombre");
            colHuespedes.add("apellido");
            colHuespedes.add("fecha_de_nacimiento");
            colHuespedes.add("nacionalidad");
            colHuespedes.add("telefono");
            colHuespedes.add("id_reserva");

        switch (table.toLowerCase()) {
            case "reservas":
                return colReservas.get(index);
            case "huespedes":
                return colHuespedes.get(index);
            default:
                return null;
                
        }
    }

    private static void diferenciarValor(Integer index, Object value, PreparedStatement statement) throws SQLException{

        if (value == Float.class) {
            statement.setFloat(index, Float.valueOf((String)value));
        }
        else if(value == Date.class){
            statement.setDate(index, Date.valueOf(String.valueOf(value)));
        }
        else{
            statement.setString(index, (String)value);
        }
    }

}
