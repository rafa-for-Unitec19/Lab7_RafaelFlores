/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7_rafaelflores;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Faith
 */
public class adminParada {
     private ArrayList<Parada> listaParada = new ArrayList();
    private File archivo = null; 

    public adminParada(String source) {
        this.archivo = new File(source);
    }

    public void setListaParada(ArrayList<Parada> listaAutobus) {
        this.listaParada = listaAutobus;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public ArrayList<Parada> getListaParada() {
        return listaParada;
    }

    public File getArchivo() {
        return archivo;
    }
    
    public void setParada(Parada a){
        listaParada.add(a);
    }
    
    public void cargarArchivo() {
        try {            
            listaParada = new ArrayList();
            Parada temp;
            if (archivo.exists()) {
                FileInputStream entrada
                    = new FileInputStream(archivo);
                ObjectInputStream objeto
                    = new ObjectInputStream(entrada);
                try {
                    while ((temp = (Parada) objeto.readObject()) != null) {
                        listaParada.add(temp);
                    }
                } catch (EOFException e) {
                    //encontro el final del archivo
                }
                objeto.close();
                entrada.close();
            }            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void escribirArchivo() {
        FileOutputStream fw = null;
        ObjectOutputStream bw = null;
        try {
            fw = new FileOutputStream(archivo);
            bw = new ObjectOutputStream(fw);
            for (Parada t : listaParada) {
                bw.writeObject(t);
            }
            bw.flush();
        } catch (Exception ex) {
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (Exception ex) {
            }
        }
    }
}
