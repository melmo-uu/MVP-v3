/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Asistencia;
import Modelo.Usuario;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Controlador.Controlador;

/**
 *
 * @author marce
 */
public class LogicaAsistenciaDAO implements AsistenciaDAO {
    
    private Controlador c;
    private Connection con;
    private Usuario u;
    public LogicaAsistenciaDAO(){
        con = Conexion.getInstancia().getConnection();
    }
    
    public void marcarEntrada(int ID) {
    String countQuery = "SELECT COUNT(*) FROM ASISTENCIA WHERE ID_USUARIO = ? AND HORASALIDA IS NULL"; //Query para validar que no existan más ingresos con el mismo usuario
    String validateQuery = "SELECT COUNT(*) FROM ASISTENCIA where FECHA = CURDATE() AND ID_USUARIO = ?"; //Query para validar si no se ha registrado la asistencia mas de una vez en el dia
    String insertQuery = "INSERT INTO ASISTENCIA (FECHA, HORAENTRADA, ID_USUARIO) VALUES (CURDATE(), CURTIME(), ?)"; //Insert de la entrada

        // Validar si ya registró asistencia el dia actual
        try (PreparedStatement psValidate = con.prepareStatement(validateQuery)) {
            psValidate.setInt(1, ID);
            var rs = psValidate.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "El usuario ya tiene registrada la asistencia de hoy.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Error en validación de asistencia diaria: " + e.getMessage());
            return;
        }

        // Validar si marcó entrada pero no salida
        try (PreparedStatement psCount = con.prepareStatement(countQuery)) {
            psCount.setInt(1, ID);
            var rs = psCount.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "El usuario ya está ingresado y no ha marcado salida.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Error en validación de asistencia abierta: " + e.getMessage());
            return;
        }

        // Insertar entrada si no hubo inconvenientes
        try (PreparedStatement psInsert = con.prepareStatement(insertQuery)) {
            psInsert.setInt(1, ID);
            int filas = psInsert.executeUpdate();

            if (filas > 0) {
                System.out.println("Ingreso registrado con éxito");
            }
        } catch (Exception e) {
            System.out.println("Error al registrar entrada: " + e.getMessage());
        }
    }


    public  void marcarSalida(int ID){
        String query = "UPDATE ASISTENCIA SET HORASALIDA = CURTIME() WHERE ID_USUARIO = ? AND HORASALIDA IS NULL"; //Update para marcar la salida que tenga pendiente el trabajador 

        //Ingresa la hora de salida del usuario
        try (PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setInt(1, ID);
            
            int filas = ps.executeUpdate();
            
             if (filas > 0) {
            System.out.println("Salida registrada con éxito");
            } else {
                 System.out.println("No hay asistencia pendiente para este usuario.");
                 JOptionPane.showMessageDialog(null, "No hay asistencia pendiente para este usuario.");
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public  List<Asistencia> ListarAsist(){
        List< Asistencia> lista = new ArrayList<>();
       
        
       
       return lista;
   }
    
   
}
