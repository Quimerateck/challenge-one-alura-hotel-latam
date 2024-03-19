package controller;

import dao.LoginDao;

public class LoginController {

    public static boolean checkUsername(String username){
        return LoginDao.verificarUser(username);
    }

    public static boolean checkPassword(String username, String password){
        return LoginDao.validarCredenciales(username, password);
    }
}
