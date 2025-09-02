/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;
import Modelo.Usuario;
import java.util.List;
/**
 *
 * @author marce
 */
public interface UsuarioDAO {
    void agregar(Usuario usuario);
    void actualizar();
    void eliminar();
    Usuario validarUsuario(String correo, String contraseña);
    Usuario obtenerPorID();
    List<Usuario> listarTodos();
    
}
