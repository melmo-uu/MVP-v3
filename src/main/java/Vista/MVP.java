/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Vista;

import Panels.asistencia;
import Panels.crud_usuarios;
import Vista.control_asist;
import Controlador.Controlador;
import DAO.LogicaUsuarioDAO;
import Modelo.Conexion;
import Panels.crear;
import Panels.informes;
import com.formdev.flatlaf.*;
import com.formdev.flatlaf.intellijthemes.*;
import com.formdev.flatlaf.themes.*;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author marce
 */
public class MVP {

    
    public static void main(String[] args) {
        
        FlatLightFlatIJTheme.setup(); //Pueden revisar los fondos con Flat, Ctrl + espacio para ver todos los temas disponibles
        
        Connection con = initConexion();
        
        if(con == null){
            mostrarError();
        }else{
            iniciarAPP();
        }  
    }
    
    private static Connection initConexion() {
        return Conexion.getInstancia().getConnection();
    }
    
    private static void mostrarError(){
        JOptionPane.showMessageDialog(null, """
                                            Error en la base de datos
                                             Contacte al técnico si el problema persiste""", "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private static void iniciarAPP(){
        Vista v = new Vista();
        login l = new login();
        informes i = new informes();
        crud_usuarios c = new crud_usuarios();
        control_asist a = new control_asist();
        asistencia as = new asistencia();
        crear cr = new crear();

        v.mostrarLogin(l);
        new Controlador(v, l, a, c, as, i, cr);
        v.setVisible(true);
    }
}
