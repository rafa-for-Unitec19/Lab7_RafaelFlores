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
public class adminAutobus {
    private ArrayList<Autobus> listaAutobus = new ArrayList();
    private File archivo = null; 

    public adminAutobus(String source) {
        this.archivo = new File(source);
    }

    public void setListaAutobus(ArrayList<Autobus> listaAutobus) {
        this.listaAutobus = listaAutobus;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public ArrayList<Autobus> getListaAutobus() {
        return listaAutobus;
    }

    public File getArchivo() {
        return archivo;
    }
    
    public void setAutobus(Autobus a){
        listaAutobus.add(a);
    }
    
    public void cargarArchivo() {
        try {            
            listaAutobus = new ArrayList();
            Autobus temp;
            if (archivo.exists()) {
                FileInputStream entrada
                    = new FileInputStream(archivo);
                ObjectInputStream objeto
                    = new ObjectInputStream(entrada);
                try {
                    while ((temp = (Autobus) objeto.readObject()) != null) {
                        listaAutobus.add(temp);
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
            for (Autobus t : listaAutobus) {
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
