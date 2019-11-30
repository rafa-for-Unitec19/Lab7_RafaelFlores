/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7_rafaelflores;

import java.util.ArrayList;
import javax.swing.JLabel;
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
    private JLabel label;
    private final int  tiempo = 1000;
    private float distancia, distanciaPorKm;
    private boolean avanzar;
    private boolean vive;
    private Parada paradaActual;
    private Autobus bus;
    private int cordX, cordY;

    public adminViaje(JProgressBar pgrViaje, JTable tabla, DefaultTableModel modelo, Autobus bus, int cordX, int cordY, JLabel label) {
        this.pgrViaje = pgrViaje;
        this.tabla = tabla;
        this.modelo = modelo;
        this.bus = bus;
        this.cordX = cordX;
        this.cordY = cordY;
        this.label = label;
        distanciaPorKm = 0;
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
    
    public void menorDistancia(){
        double menor = pasajeros.get(0).getParada().getDistancia();
        for (int i = 1; i < pasajeros.size(); i++) {
            if (menor > pasajeros.get(i).getParada().getDistanciacordenada(cordX, cordY)) {
                menor = pasajeros.get(i).getParada().getDistanciacordenada(cordX, cordY);
                paradaActual = pasajeros.get(i).getParada();
            }
        }
        if (pasajeros.isEmpty()) {
            vive = false;
        }else{
            distancia = (float) menor * 1000;
            this.label.setText(String.valueOf(distancia+" Metros"));
        }
        
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
        menorDistancia();
        this.cordX = (int) paradaActual.getCordX();
        this.cordY = (int) paradaActual.getCordY();
    }
    
    public double tiempoTotal(){
        return this.distancia / this.bus.getVelocidad();
    }
    
    @Override
    public void run(){
        while (vive) {            
            if (this.pgrViaje.getValue() == this.pgrViaje.getMaximum()) {
                reset();
            }
            if (avanzar) {
                System.out.println("Hola");
                this.distanciaPorKm += (float) distancia * (bus.getVelocidad()*1000);
                this.pgrViaje.setValue((int) this.distanciaPorKm);
                this.pgrViaje.setString(
                        Integer.toString(
                                this.pgrViaje.getValue()
                        )
                        + "Kilometros"
                );
                System.out.println(bus);
                System.out.println(bus.getVelocidad());
                System.out.println(distancia);
                System.out.println(pgrViaje.getValue());
            }//Fin If
            try {
                Thread.sleep(tiempo);
            } catch (Exception e) {
            }
        }
    }
    
}
