/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7_rafaelflores;

import java.util.ArrayList;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Faith
 */
public class adminViaje extends Thread{
    private JProgressBar pgrViaje;
    private JTable tabla;
    private DefaultTableModel modelo;
    private ArrayList<Estudiante> pasajeros = new ArrayList();
    private final int  tiempo = 1000;
    private float distancia, distanciaPorKm = 0;
    private boolean avanzar;
    private boolean vive;
    private Parada paradaActual;
    private Autobus bus;
    private int cordX, cordY;

    public adminViaje(JProgressBar pgrViaje, JTable tabla, DefaultTableModel modelo, Autobus bus, int cordX, int cordY) {
        this.pgrViaje = pgrViaje;
        this.tabla = tabla;
        this.modelo = modelo;
        this.bus = bus;
        this.cordX = cordX;
        this.cordY = cordY;
        this.avanzar = true;
        this.vive = true;
    }
    
    public void setTabla(JTable tabla) {
        this.tabla = tabla;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public void setPasajeros(ArrayList<Estudiante> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    public JTable getTabla() {
        return tabla;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public ArrayList<Estudiante> getPasajeros() {
        return pasajeros;
    }

    public int getTiempo() {
        return tiempo;
    }

    public float getDistancia() {
        return distancia;
    }

    
    
    public void setPgrViaje(JProgressBar pgrViaje) {
        this.pgrViaje = pgrViaje;
    }

    public void setAvanzar(boolean avanzar) {
        this.avanzar = avanzar;
    }

    public void setVive(boolean vive) {
        this.vive = vive;
    }

    public void setVelocidad(float velocidad) {
        this.distancia = velocidad;
    }
    
    public JProgressBar getPgrViaje() {
        return pgrViaje;
    }

    public boolean isAvanzar() {
        return avanzar;
    }

    public boolean isVive() {
        return vive;
    }

    public float getVelocidad() {
        return distancia;
    }
    
    public double menorDistancia(){
        double menor = pasajeros.get(0).getParada().getDistancia();
        for (int i = 1; i < pasajeros.size(); i++) {
            if (menor > pasajeros.get(i).getParada().getDistanciacordenada(cordX, cordY)) {
                menor = pasajeros.get(i).getParada().getDistanciacordenada(cordX, cordY);
                paradaActual = pasajeros.get(i).getParada();
            }
        }
        
        return menor;
    }
    
    private void reset() {
        modelo = (DefaultTableModel) this.tabla.getModel();
        String alumnos = "";
        for (int i = 0; i < pasajeros.size(); i++) {
            alumnos = pasajeros.get(i).getNombre() + ",";
        }
        Object row[] = {
            this.paradaActual.toString(),
            tiempoTotal(),
            alumnos
        };
        modelo.addRow(row);
        this.tabla.setModel(modelo);
        for (int i = 0; i < pasajeros.size(); i++) {
            if (pasajeros.get(i).getParada().toString().equals(paradaActual.toString())) {
                pasajeros.remove(pasajeros.get(i));
            }
        }
        distancia = (float) menorDistancia();
        this.cordX = (int) paradaActual.getCordX();
        this.cordY = (int) paradaActual.getCordY();
    }
    
    public double tiempoTotal(){
        return this.distancia / this.bus.getVelocidad();
    }
    
    @Override
    public void run(){
        if (this.pgrViaje.getValue() == this.pgrViaje.getMaximum()) {
            reset();
        }
        while (vive) {            
            if (avanzar) {
                this.distanciaPorKm += (int) Math.round(distancia / bus.getVelocidad());
                this.pgrViaje.setValue((int) this.distanciaPorKm);
                this.pgrViaje.setString(
                        Integer.toString(
                                this.pgrViaje.getValue()
                        )
                        + "Kilometros"
                );
            }//Fin If
            try {
                Thread.sleep(tiempo);
            } catch (Exception e) {
            }
        }
    }
    
}
