/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Panels.asistencia;
import Vista.login;
import Panels.crud_usuarios;
import Panels.control_asist;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DAO.*;
import Modelo.Usuario;
import Panels.informes;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;

/**
 *
 * @author marce
 */
public class Controlador implements ActionListener{
    
    // Declaramos las vistas y la logica de negocio que está en LogicaUsuarioDAO
    // Vistas
    private login l;
    private control_asist asist;
    private Vista v; 
    private crud_usuarios crud;
    private asistencia as;
    private informes i;

    // DAO y lógica de negocio
    private UsuarioDAO dao;
    private AsistenciaDAO asisDao;
    
    // Modelo
    private Usuario u;

    public Controlador( Vista v, login l, control_asist asist, crud_usuarios crud, asistencia as, informes i) {
        this.asist = asist;
        this.i = i;
        this.crud = crud;
        this.asisDao = new LogicaAsistenciaDAO();
        this.as = as;
        this.dao = new LogicaUsuarioDAO();
        this.l = l;
        this.v = v;

        asignarEventos();
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e){
       
        if(e.getSource() ==  l.btnIngresar){  //v.boton = Obtiene el boton desde la vista 
            ValidarUsuario(); // aqui van los metodos que se le asignan al botón
        }
        
        if(e.getSource() == asist.btnCrud){
            v.mostrarContenido1(crud, asist);
        }
        
        if(e.getSource() == asist.btnInformes){
            v.mostrarContenido2(i, asist);
        }
        
        if(e.getSource() == crud.btnSeleccionar){
            mostrarocultar();
        }
        
        if(e.getSource() == asist.btnCerrarSesion){
            cerrarSesion();
        }
        
        if(e.getSource() == crud.btnAgregar){
            agregarUsuario();
        }
        
        if(e.getSource() == as.btnIngreso){
            MarcarIngreso(u);
        }
        
        if(e.getSource() == as.btnSalida){
            MarcarSalida(u);
        }
        if(e.getSource() == crud.btnLimpiar){
            limpiarCampos();
        }
        
    }
    
    
    //Login}
    
    private void ValidarUsuario() {
        String correo = l.txtCorreo.getText();
        String contraseña = l.txtPass.getText();
 
        if (!validarCampos(correo, contraseña)) return;
        if (!validarFormatoCorreo(correo)) return;

        Usuario usuario = dao.validarUsuario(correo, contraseña);
        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "Correo/Contraseña incorrectos");
            return;
        }

        procesarUsuario(usuario);
    }

    private boolean validarCampos(String correo, String contraseña) {
        if (correo.isBlank() || contraseña.isBlank()) {
            JOptionPane.showMessageDialog(null, "Debes llenar todos los campos", "¡Advertencia!", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean validarFormatoCorreo(String correo) {
        if (!List.of("@", ".com").stream().allMatch(correo::contains)) {
            JOptionPane.showMessageDialog(null, "Correo inválido.\n Inténtalo nuevamente");
            return false;
        }
        return true;
    }

    private void procesarUsuario(Usuario usuario) {
        this.u = usuario;
        v.setVisible(true);
        v.mostrarCtrlAsist(asist);

        asist.lblNombre.setText(u.getNombre() + " " + u.getApellidoP() + " " + u.getApellidoM());

        if (!usuario.isRol()) { // usuario normal
            v.mostrarAsistencia(as, asist);
            asist.btnCrud.setVisible(false);
            asist.btnInformes.setVisible(false);
        }
    }
    
    //Registro de ingreso
    
    private void MarcarIngreso(Usuario usuario){
        this.u = usuario;

        
        if(u == null){
            System.out.println("Error al marcar asistencia");
        }else{
            asisDao.marcarEntrada(usuario.getID());
        }
    }
    
    private void MarcarSalida(Usuario usuario){
        this.u = usuario;
        
        if(u == null){
            System.out.println("Error al marcar asistencia");
        }else{
            asisDao.marcarSalida(usuario.getID());
        }
    }
    
    
    //Registro de usuarios

    
    private void agregarUsuario(){
        String nombre = crud.txtNombre.getText();
        String apellidop = crud.txtApellidoP.getText();
        String apellidom = crud.txtApellidoM.getText();
        String pass = crud.txtPass.getText();
        String email = crud.txtEmail.getText();
        String rol = crud.comboRol.getSelectedItem().toString();
        
        if(!validarCamposVacios(pass, email, nombre, apellidop, apellidom, rol)) return;
        if(!validarFormatoCorreo(email)) return;
        
        Usuario usuario = new Usuario(0, nombre, apellidop, apellidom, email, pass, true, null, null, true, null);
        dao.agregar(usuario);

        if(usuario != null){
            JOptionPane.showMessageDialog(null, "Usuario agregado con éxito");
            limpiarCampos();
        }
        else{
            JOptionPane.showMessageDialog(null, "Error al agregar usuario");
        }
        
    }
    
    public boolean validarCamposVacios(String pass, String email, String nombre, String apellidop, String apellidom, String rol){
        if(email.isBlank() ||  pass.isBlank() ||  nombre.isBlank() ||  apellidop.isBlank() || apellidom.isBlank() || rol.equals("Seleccione una opción")){
            JOptionPane.showMessageDialog(null, "No dejes campos sin rellenar");
            return false;
        }
        return true;
    }
    
    private void mostrarocultar (){
        if (crud.scrollTabla.isVisible()) {
            crud.scrollTabla.setVisible(false);
            crud.txtBuscador.setVisible(false);
        } else {
            crud.inicializarTabla();
            crud.scrollTabla.setVisible(true);
            crud.txtBuscador.setVisible(true);
        }
        crud.scrollTabla.getParent().revalidate();
        crud.scrollTabla.getParent().repaint();
    }
  
    private void cerrarSesion() {
        
        int opcion = JOptionPane.showConfirmDialog(
            null,
            "¿Estás seguro de que deseas cerrar sesión?",
            "Confirmación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (opcion == JOptionPane.YES_OPTION) {
            // Acción si el usuario confirma
            v.mostrarLogin(l);
            l.txtCorreo.setText("");
            l.txtPass.setText("");
            this.u = null;
        } else {
            // Acción si cancela o elige NO
            System.out.println("Acción cancelada");
        }
    }
    
    
    
    private void limpiarCampos(){
        crud.txtNombre.setText("");
        crud.txtApellidoP.setText("");
        crud.txtEmail.setText("");
        crud.txtPass.setText("");
        crud.txtApellidoM.setText("");
        crud.comboRol.setSelectedIndex(0);
        
    }
    
    public void filtrar(String txt){
        if(crud.sorter != null){
            if(txt.trim().length() == 0){
                crud.sorter.setRowFilter(null);
            }else{
                crud.sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txt));
            }
        }
    }
    
    // Aqui se le va a asignar evento a los botones de la interfaz
    private void asignarEventos() {
        l.btnIngresar.addActionListener(this);
        asist.btnCrud.addActionListener(this);
        asist.btnInformes.addActionListener(this);
        crud.btnSeleccionar.addActionListener(this);
        asist.btnCerrarSesion.addActionListener(this);
        crud.btnAgregar.addActionListener(this);
        as.btnSalida.addActionListener(this);
        as.btnIngreso.addActionListener(this);
        crud.btnLimpiar.addActionListener(this);
        crud.txtBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
        @Override
        public void keyReleased(java.awt.event.KeyEvent e) {   // Evento que filtra datos en la tabla para buscar coincidencias
            String texto = crud.txtBuscador.getText();
            crud.tablaEmpleados.clearSelection();
            filtrar(texto);
        }
    });
    }
    
    public void retornar(){
        v.mostrarLogin(l);
        l.txtCorreo.setText("");
        l.txtPass.setText("");
        this.u = null;
    }
}
    
    
    
    

