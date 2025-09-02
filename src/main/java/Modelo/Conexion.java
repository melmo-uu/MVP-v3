/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author marce
 */
public class Conexion {
    
    private static Connection con;
    
    private static Conexion instancia;
    
    String ip = "localhost"; // Esta variable se rellena con la ip de la base de datos
    String db = "MVPA"; // Esta variable es la base de datos en si
    String url = "jdbc:mysql://" + ip + ":3306/" + db;
    String user = "root"; // Usuario de la base de datos que en mysql es root
    String pass = "M@sterzd124"; // Contraseña de la base de datos
    
    private Conexion(){
        //Bloque try catch para la conexión en la cual si tenemos un error nos va a saltar la excepcion 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass); 
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a base de datos\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Patron singleton para instanciar solamente una vez la conexión 
    public static Conexion getInstancia(){
        if(instancia == null){
            instancia = new Conexion();
        }
        return instancia;
    }
    
    public Connection getConnection(){
        return con;
    }

    //Test para la conexión a base de datos
    public boolean testCon(){
         try {
            if(con != null && con.isValid(2)){
                System.out.println("¡Conexión activa!");
                return true;
            }
            else{
                System.out.println("La conexión no es válida.");
                return false;
            }
        } catch (SQLException e) {
             System.out.println("Error al validar conexión\n" + e.getMessage());
            return false;
        }
    }
       
    //Metodo para cerrar la conexión cuando terminemos un proceso aunque no se si ocuparlo pero igual no ta de más
    public void cerrarConexion(){
        try {
            if(con != null && !con.isClosed()){
                con.close();
                con = null;
                instancia = null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión:\n" + e.getMessage());
        }
    }
}
