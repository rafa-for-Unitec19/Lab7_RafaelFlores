/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7_rafaelflores;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Faith
 */
public class Autobus implements Serializable{
    private int id;
    private String placa;
    private double velocidad;
    private Color color;
    ArrayList<Estudiante> pasajeros = new ArrayList<>();

    public Autobus() {
    }

    public Autobus(int id, String placa, double velocidad, Color color) {
        this.id = id;
        this.placa = placa;
        this.velocidad = velocidad;
        this.color = color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPasajeros(ArrayList<Estudiante> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public int getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public Color getColor() {
        return color;
    }

    public ArrayList<Estudiante> getPasajeros() {
        return pasajeros;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
    
    
}
