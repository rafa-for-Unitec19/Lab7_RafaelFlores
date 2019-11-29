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
public class adminEstudiante {
    private ArrayList<Estudiante> listaEstudiante = new ArrayList();
    private File archivo = null;  

    public adminEstudiante(String source) {
        this.archivo = new File(source);
    }

    public void setListaEstudiante(ArrayList<Estudiante> listaEstudiante) {
        this.listaEstudiante = listaEstudiante;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public ArrayList<Estudiante> getListaEstudiante() {
        return listaEstudiante;
    }

    public File getArchivo() {
        return archivo;
    }
    
    public void setEstudiante(Estudiante a){
        listaEstudiante.add(a);
    }
    
    public void cargarArchivo() {
        try {            
            listaEstudiante = new ArrayList();
            Estudiante temp;
            if (archivo.exists()) {
                FileInputStream entrada
                    = new FileInputStream(archivo);
                ObjectInputStream objeto
                    = new ObjectInputStream(entrada);
                try {
                    while ((temp = (Estudiante) objeto.readObject()) != null) {
                        listaEstudiante.add(temp);
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
            for (Estudiante t : listaEstudiante) {
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
