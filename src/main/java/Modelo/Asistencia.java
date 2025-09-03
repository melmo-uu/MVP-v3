/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.time.LocalDateTime;

/**
 *
 * @author marce
 */
public class Asistencia {
        private int ID;
        private LocalDateTime HoraEntrada;
        private LocalDateTime HoraSalida;

    public Asistencia(int ID, LocalDateTime HoraEntrada, LocalDateTime HoraSalida) {
        this.ID = ID;
        this.HoraEntrada = HoraEntrada;
        this.HoraSalida = HoraSalida;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LocalDateTime getHoraEntrada() {
        return HoraEntrada;
    }

    public void setHoraEntrada(LocalDateTime HoraEntrada) {
        this.HoraEntrada = HoraEntrada;
    }

    public LocalDateTime getHoraSalida() {
        return HoraSalida;
    }

    public void setHoraSalida(LocalDateTime HoraSalida) {
        this.HoraSalida = HoraSalida;
    }
        
        
}
