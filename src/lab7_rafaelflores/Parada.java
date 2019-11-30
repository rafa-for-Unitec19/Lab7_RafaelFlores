/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7_rafaelflores;

import java.io.Serializable;

/**
 *
 * @author Faith
 */
public class Parada implements Serializable{
    private String nombre;
    private double distancia, angulo, cordX, cordY;

    public Parada() {
    }

    public Parada(String nombre, double distancia, double angulo) {
        this.nombre = nombre;
        this.distancia = distancia;
        this.angulo = angulo;
        this.cordX = distancia * Math.cos(angulo);
        this.cordY = distancia * Math.sin(angulo);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
        this.cordX = distancia * Math.cos(angulo);
        this.cordY = distancia * Math.sin(angulo);
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
        this.cordX = distancia * Math.cos(angulo);
        this.cordY = distancia * Math.sin(angulo);
    }

    public void setCordX(double cordX) {
        this.cordX = cordX;
    }

    public void setCordY(double cordY) {
        this.cordY = cordY;
    }

    public String getNombre() {
        return nombre;
    }

    public double getDistancia() {
        return distancia;
    }

    public double getAngulo() {
        return angulo;
    }

    public double getCordX() {
        return cordX;
    }

    public double getCordY() {
        return cordY;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    public double getDistanciacordenada(int cordX, int cordY){
        return Math.sqrt(    (   Math.pow((this.getCordX() - cordX), 2)  + Math.pow((this.getCordY() - cordY), 2)     )    );
    }
    
    
}
