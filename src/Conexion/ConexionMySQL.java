package Conexion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;


public class ConexionMySQL {

    // Datos de conexión
    static String URL = "jdbc:mysql://154.49.247.103:3306";
    static String dbNombre= "u115629394_tienda";
    static String usuario = "u115629394_tendero";
    static String contraseña = "T13nd1t4%";

    // Metodo de Conexión
    public static Connection conectar() {
        Connection conn = null;

        try {

            conn = DriverManager.getConnection(URL + "/" + dbNombre, usuario, contraseña);
            System.out.println("Conexion establecida con MySql");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectarse a la DB:" + e.toString());
        }
        return conn;

    }

}
