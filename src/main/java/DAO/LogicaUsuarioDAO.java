/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import Modelo.*;
import Vista.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author marce
 */
public class LogicaUsuarioDAO implements UsuarioDAO {

    private Connection con;

    public LogicaUsuarioDAO(){
        con = Conexion.getInstancia().getConnection();
    }

    @Override
    public void agregar(Usuario usuario){
        String query = "INSERT INTO USUARIOS (NOMBRE, APELLIDOP, APELLIDOM, CORREO, CONTRASEÑA, ROL, FECHACREACION, ACTIVO) "
                + "VALUES (?,?,?,?,?,?,NOW(),1)";
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, usuario.getNombre());
            st.setString(2, usuario.getApellidoP());
            st.setString(3, usuario.getApellidoM());
            st.setString(4, usuario.getEmail());
            st.setString(5, usuario.getContrasena());
            st.setInt(6, usuario.isRol() ?  0 : 1 );
           
         int filas = st.executeUpdate();
         
         if(filas > 0){
            System.out.println("Usuario agregado con éxito");         
         }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actualizar(){
        
    }
    
    public void eliminar(int ID){
        
        String query = "DELETE FROM USUARIOS "
                + "WHERE ID_USUARIO = ?";
        
        try (PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1, ID);
            
            int filas = ps.executeUpdate();
            
            if(filas > 0){
                System.out.println("Usuario eliminado con exito");
            }
            
        }catch(SQLException e ){
            System.out.println("Error: " + e.getMessage());
        }
        
    }
    
    public Usuario validarUsuario(String correo, String contraseña) {
        String query = "SELECT * FROM USUARIOS WHERE CORREO = ? AND CONTRASEÑA = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, correo);
            ps.setString(2, contraseña);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("ID_USUARIO");
                int rol = rs.getInt("ROL");
                String nombre = rs.getString("NOMBRE");
                String apellidoP = rs.getString("APELLIDOP");
                String apellidoM = rs.getString("APELLIDOM");
                if(rol == 1){
                    return new Usuario(id, nombre, apellidoP,apellidoM, correo, contraseña, true, null, null, true, null);
                     
                }else{
                     return new Usuario(id, nombre, apellidoP,apellidoM, correo, contraseña,false, null, null, true, null);
                }    
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error con la base de datos");
        }
        return null;
    }

    public Usuario obtenerPorID(){
        
        Usuario usuario = null;
        
        
        
        return usuario;
    }
    
    public List<Usuario> listarTodos(){
        List<Usuario> lista = new ArrayList<>();
        String query = "SELECT * , CONCAT(NOMBRE, ' ' , APELLIDOP, ' ' , APELLIDOM) AS NOMBRECOMPLETO FROM USUARIOS ";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String nombre = rs.getString("NOMBRE");
                String apellidop = rs.getString("APELLIDOP");   
                String apellidom = rs.getString("APELLIDOM");
                String correo = rs.getString("CORREO");
                String password = rs.getString("CONTRASEÑA");
                String nombreCompleto = rs.getString("NOMBRECOMPLETO");
                int rol = rs.getInt("ROL");
                Timestamp ts = rs.getTimestamp("FECHACREACION");
                int id = rs.getInt("ID_USUARIO");
                LocalDateTime fecha = null;
                
                if(ts != null){
                    fecha = ts.toLocalDateTime();
                }
                

                Usuario usuario = new Usuario(id, nombre, apellidop, apellidom, correo, password, rol == 1, nombreCompleto, fecha , true, null );
                lista.add(usuario);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar usuarios: " + e.getMessage());
        }
        return lista;
    }
    

}
