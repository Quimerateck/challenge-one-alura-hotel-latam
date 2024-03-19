package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import factory.ConnectionFactory;
import modelo.Usuario;

public class test {
    
    public static void main(String[] args) throws Exception {
        Connection con = new ConnectionFactory().startConnection();
        try{
			final PreparedStatement statement = con.prepareStatement(
					"SELECT * FROM login");
					statement.execute();
			
			try (statement) {
				final ResultSet resultSet = statement.getResultSet();
				
				try(resultSet){
                
                    List<Usuario> userData = new ArrayList<>();

					while (resultSet.next()){
						String nombre = resultSet.getString("usuario");
						String password = resultSet.getString("contraseña");	
						if ("quimera".equals(nombre)){
                            if ("1234".equals(password)){
                                System.out.println("inicio de sesion exitoso");
                            } else {
                                System.out.println("Error en la contraseña");
                            }

                        } else {
                            System.out.println("Error de nombre de usuario");
                        }
					}
				}
			}
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
    }
}
