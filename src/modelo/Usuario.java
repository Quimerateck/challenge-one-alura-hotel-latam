package modelo;

public class Usuario {
    
    private String user;
    private String password;

    public Usuario(){
    }

    public Usuario(String user){
        this.user = user;
    }

    public Usuario(String user, String password){
        this.user = user;
        this.password = password;
    }

    public String getUsername() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }
}
