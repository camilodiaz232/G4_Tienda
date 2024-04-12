package Mundo;

import Conexion.ConexionMySQL;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Tienda {


    //Metodo para actualizar el inventario
    public static Object [] actualizarInventario (String nombreColumna)  {
        Statement st; // variable para declarar la conexión
        ResultSet rs; // Varialble para almacenar el resultado de la conexión

        ArrayList <String> listaDatos = new ArrayList<>();  //ArrayList que almacena los datos de la conexión

        listaDatos.add(nombreColumna.toUpperCase());

        try {
            st= ConexionMySQL.conectar().createStatement();
            rs= st.executeQuery("select "+nombreColumna+" from vw_inventario"); // Consulta SQL

            // Almacenamiento de los datos del resultado en variable arraylist
            while (rs.next()){
                listaDatos.add(rs.getString(1));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        Object [] dataObjeto = listaDatos.toArray();
        return dataObjeto;
    }

    public static void ingresarMovimiento (String nombre, Integer movimiento, Integer cantidad, Float valor)  {

        PreparedStatement ps;  // Variable para declarar el conexión
        try {
            // Declaracion SQL y sus variables para insertar en la base de dato
            ps=ConexionMySQL.conectar().prepareStatement("INSERT INTO `tienda.movInventario` (producto, movimiento, cantidad, valor) VALUES(?, ?, ?, ?);");
            ps.setString(1,nombre);
            ps.setInt(2,movimiento);
            ps.setInt(3,cantidad);
            ps.setFloat(4,valor);
            int res = ps.executeUpdate();

            // Presentación de los resultados de la conexión
            if (res >0){
                JOptionPane.showMessageDialog(null, "Registro guardaro");
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar registro");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en SQL:" + e.toString());
        }

    }

    public static void eliminarProducto (String producto) {


        PreparedStatement ps;  // Variable para declarar el conexión
        try {
            // Declaracion SQL y sus variables para eliminar producto
            ps=ConexionMySQL.conectar().prepareStatement("delete from `tienda.movInventario` where producto = ?");
            ps.setString(1,producto);

            int res = ps.executeUpdate();

            // Presentación de los resultados de la conexión
            if (res >0){
                JOptionPane.showMessageDialog(null, "Registro eliminado");
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar registro");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en SQL:" + e.toString());
        }

    }

}
