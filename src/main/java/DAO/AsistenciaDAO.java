/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Modelo.*;
import java.util.List;

/**
 *
 * @author marce
 */
public interface AsistenciaDAO {
    void marcarEntrada(int ID);
    void marcarSalida(int ID);
    List<Asistencia> ListarAsist();
}
