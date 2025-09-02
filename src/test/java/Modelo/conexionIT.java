/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marce
 */
public class conexionIT {
    
    public conexionIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstancia method, of class conexion.
     */
    @Test
    public void testGetInstancia() {
        System.out.println("getInstancia");
        Conexion expResult = null;
        Conexion result = Conexion.getInstancia();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConnection method, of class conexion.
     */
    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
        Conexion instance = null;
        Connection expResult = null;
        Connection result = instance.getConnection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testCon method, of class conexion.
     */
    @Test
    public void testTestCon() {
        System.out.println("testCon");
        Conexion instance = null;
        boolean expResult = true;
        boolean result = instance.testCon();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of cerrarConexion method, of class conexion.
     */
    @Test
    public void testCerrarConexion() {
        System.out.println("cerrarConexion");
        Conexion instance = null;
        instance.cerrarConexion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
