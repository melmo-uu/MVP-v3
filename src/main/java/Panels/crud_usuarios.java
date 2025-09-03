/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Panels;

import DAO.LogicaUsuarioDAO;
import Modelo.Usuario;
import Vista.Vista;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author marce
 */
public class crud_usuarios extends javax.swing.JPanel {

    /**
     * Creates new form crud_usuarios
     */
    
    public TableRowSorter<DefaultTableModel> sorter;

    Vista v = new Vista();
    crear c = new crear();
    
    public crud_usuarios() {
        initComponents();
        

        scrollTabla.setVisible(false);
        txtBuscador.setVisible(false);
        btnActualizar.setVisible(false);
        btnEliminar.setVisible(false);
        txtID.setVisible(false);
        
       
    }

    
    public void inicializarTabla() {
        String[] etiquetas = {"Nombre", "ApellidoP", "ApellidoM", "Nombre completo", "Correo electronico", "Rol", "Fecha de registro", "ID"};
 
        // Modelo de tabla no editable
        DefaultTableModel model = new DefaultTableModel(etiquetas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Ninguna celda editable
            }
        };
        
        
        // Asignar modelo a la tabla
        tablaEmpleados.setModel(model);

        // Configurar sorter para búsquedas/filtros
        sorter = new TableRowSorter<>(model);
        tablaEmpleados.setRowSorter(sorter);

        // Evitar mover celdas de lugar
        tablaEmpleados.getTableHeader().setReorderingAllowed(false);

        // Configuración de anchos de columnas
        ocultarColumna(tablaEmpleados, 0,1,2,6); // Ocultar Nombre y Apellidos}
        tablaEmpleados.getColumnModel().getColumn(3).setPreferredWidth(200);//NombreCompleto
        tablaEmpleados.getColumnModel().getColumn(4).setPreferredWidth(200); // Correo
        tablaEmpleados.getColumnModel().getColumn(5).setPreferredWidth(100);// Rol
        tablaEmpleados.getColumnModel().getColumn(0).setResizable(false);

        // Listener para seleccionar fila
        tablaEmpleados.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {
            int fila = tablaEmpleados.getSelectedRow();
            if (fila != -1) {
                String nombre = tablaEmpleados.getValueAt(fila, 0).toString();
                String apellidop = tablaEmpleados.getValueAt(fila, 1).toString();
                String apellidom = tablaEmpleados.getValueAt(fila, 2).toString(); 
                String correo = tablaEmpleados.getValueAt(fila, 4).toString();
                String rol = tablaEmpleados.getValueAt(fila, 5).toString();
                String ID = tablaEmpleados.getValueAt(fila, 7).toString();

                c.txtNombre.setText(nombre);
                c.txtApellidoP.setText(apellidop);
                c.txtApellidoM.setText(apellidom);
                c.txtEmail.setText(correo);
                c.comboRol.setSelectedItem(rol);
                txtID.setText(ID);

                // Mostrar botones
                btnActualizar.setVisible(true);
                btnEliminar.setVisible(true);
            } else {
                // Si no hay selección, ocultar botones
                btnActualizar.setVisible(false);
                btnEliminar.setVisible(false);
            }
        }
    });

        // Llenar tabla desde DAO
        model.setRowCount(0);
        LogicaUsuarioDAO dao = new LogicaUsuarioDAO();
        for (Usuario u : dao.listarTodos()) {
            model.addRow(new Object[]{
                    u.getNombre(),
                    u.getApellidoP(),
                    u.getApellidoM(),
                    u.getNombrecompleto(),
                    u.getEmail(),
                    u.isRol() ? "Administrador" : "Trabajador",
                    u.getFechaCreacionFormateada(),
                    u.getID()
                    
            });
        }
    }
    
    
    public void cargarDatos(List<Usuario> usuarios) {
    DefaultTableModel model = (DefaultTableModel) tablaEmpleados.getModel();
    model.setRowCount(0); // Limpiar datos actuales

        for (Usuario u : usuarios) {
            model.addRow(new Object[]{
                u.getNombre(),
                u.getApellidoP(),
                u.getApellidoM(),
                u.getNombrecompleto(),
                u.getEmail(),
                u.isRol() ? "Administrador" : "Usuario",
                u.getFechaCreacion(),  // Asegúrate que sea formateable
                u.getID()
            });
        }
    }

    
    
    

    // Método para ocultar columnas sin borrarlas del modelo
    private void ocultarColumna(JTable tabla, int index, int index1, int index2, int index3) {
        tabla.getColumnModel().getColumn(index).setMinWidth(0);
        tabla.getColumnModel().getColumn(index).setMaxWidth(0);
        tabla.getColumnModel().getColumn(index).setPreferredWidth(0);
        
        tabla.getColumnModel().getColumn(index1).setMinWidth(0);
        tabla.getColumnModel().getColumn(index1).setMaxWidth(0);
        tabla.getColumnModel().getColumn(index1).setPreferredWidth(0);
        
        tabla.getColumnModel().getColumn(index2).setMinWidth(0);
        tabla.getColumnModel().getColumn(index2).setMaxWidth(0);
        tabla.getColumnModel().getColumn(index2).setPreferredWidth(0);
        
        tabla.getColumnModel().getColumn(index3).setMinWidth(0);
        tabla.getColumnModel().getColumn(index3).setMaxWidth(0);
        tabla.getColumnModel().getColumn(index3).setPreferredWidth(0);
    }

    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        scrollTabla = new javax.swing.JScrollPane();
        tablaEmpleados = new javax.swing.JTable();
        txtBuscador = new javax.swing.JTextField();
        txtID = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        Contenido = new javax.swing.JPanel();

        tablaEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollTabla.setViewportView(tablaEmpleados);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(184, 184, 184)
                        .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));

        btnAgregar.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregar.setText("Agregar usuario");

        btnLimpiar.setForeground(new java.awt.Color(0, 0, 0));
        btnLimpiar.setText("Limpiar");

        btnActualizar.setForeground(new java.awt.Color(0, 0, 0));
        btnActualizar.setText("Modificar usuario");

        btnEliminar.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminar.setText("Eliminar");

        btnSeleccionar.setForeground(new java.awt.Color(0, 0, 0));
        btnSeleccionar.setText("Seleccionar usuario");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSeleccionar, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ContenidoLayout = new javax.swing.GroupLayout(Contenido);
        Contenido.setLayout(ContenidoLayout);
        ContenidoLayout.setHorizontalGroup(
            ContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ContenidoLayout.setVerticalGroup(
            ContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Contenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(Contenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 608, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel Contenido;
    private javax.swing.JButton btnActualizar;
    public javax.swing.JButton btnAgregar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnSeleccionar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    public javax.swing.JScrollPane scrollTabla;
    public javax.swing.JTable tablaEmpleados;
    public javax.swing.JTextField txtBuscador;
    public javax.swing.JTextField txtID;
    // End of variables declaration//GEN-END:variables
}
