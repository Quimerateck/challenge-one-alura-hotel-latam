package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import factory.ConnectionFactory;

public class LoginDao{

    //verifica si el usuario y contraseña son correctos
    

    public static boolean verificarUser(String username) {

        Connection conUser = new ConnectionFactory().startConnection();

        try {
            PreparedStatement statement = conUser.prepareStatement(
                "SELECT usuario FROM login WHERE usuario = ?");
                statement.setString(1, username);

            if (statement.execute()){
                try {
                    ResultSet resultSet = statement.getResultSet();

                    resultSet.next(); 
                    return username.equals(resultSet.getString(1));
                    
                } catch (Exception e) {
                    return false;
                }
                
            }
            System.out.println("False user");
            return false;
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        

    }

    public static boolean validarCredenciales(String username, String password){

        Connection conPass = new ConnectionFactory().startConnection();
        
        try {

            PreparedStatement statement = conPass.prepareStatement(
                "SELECT ? FROM login WHERE usuario = ?");
                statement.setString(1, password);
                statement.setString(2, username);

                if (statement.execute()){

                    try {
                        ResultSet resultset = statement.getResultSet();
    
                        if (resultset.next()){
    
                            if(resultset.getString(1).equals(password)){
                                return true;
                            }
                        }
                        
                    } catch (Exception e) {
                        return false;
                    }
                }
            return false;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
