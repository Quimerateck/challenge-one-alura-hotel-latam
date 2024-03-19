package utils;

public class Guardar {
    static Object datosGuardados;

    public static void setDatos(Object datos){
        datosGuardados = datos;
    };

    public static Object getDatos(){
        return datosGuardados;
    }
}
