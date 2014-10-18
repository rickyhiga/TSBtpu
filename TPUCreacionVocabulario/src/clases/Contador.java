/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import db.DBAbstractModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import testear.*;

/**
 *
 * @author user
 */
public class Contador {

    private HashMap<String, Integer> hma, hmb, hmc;
    DBAbstractModel db = new DBAbstractModel("C:\\Users\\user\\Documents\\RickyFacu\\2014\\TSB\\TSBtpu\\TPUCreacionVocabulario\\tpu.s3db");

    public Contador() {
        /*INFORMACIÓN EXTRAIDA DE http://docs.oracle.com/javase/7/docs/api/java/util/HashMap.html 
         The HashMap class is roughly equivalent to Hashtable, except that it is unsynchronized and permits nulls.
         Load factor: Higher values decrease the space overhead but increase the lookup cost*/
        hma = new HashMap(); //default initial capacity (16) and the default load factor (0.75)
        hmb = new HashMap(100); //specified initial capacity and the default load factor (0.75)
        hmc = new HashMap(100, (float) 0.5); //specified initial capacity and load factor 
    }

    public void sumarCantA(String clave) {
        if (!hma.containsKey(clave)) {
            hma.put(clave, 1);
            return;
        }
        int old = this.getCantA(clave);
        hma.replace(clave, old + 1);
    }

    public void sumarCantB(String clave) {
        if (!hmb.containsKey(clave)) {
            hmb.put(clave, 1);
            return;
        }
        hmb.replace(clave, this.getCantB(clave) + 1);
    }

    public void sumarCantC(String clave) {
        if (!hmc.containsKey(clave)) {
            hmc.put(clave, 1);
            return;
        }
        hmc.replace(clave, this.getCantC(clave) + 1);
    }

    public int getCantA(String clave) {
        return hma.get(clave);
    }

    public int getCantB(String clave) {
        return hmb.get(clave);
    }

    public int getCantC(String clave) {
        return hmc.get(clave);
    }

    public String toStringA() {
        Iterator<String> it = hma.keySet().iterator();
        StringBuilder st = new StringBuilder("HashMap A\n");
        while (it.hasNext()) {
            String clave = it.next();
            st.append("[");
            st.append(clave);
            st.append("]=");
            st.append(this.getCantA(clave));
            st.append("\n");
        }
        return st.toString();
    }

    public String toStringB() {
        Iterator<String> it = hma.keySet().iterator();
        StringBuilder st = new StringBuilder("HashMap B\n");
        while (it.hasNext()) {
            String clave = it.next();
            st.append("[");
            st.append(clave);
            st.append("]=");
            st.append(this.getCantA(clave));
            st.append("\n");
        }
        return st.toString();
    }

    public String toStringC() {
        Iterator<String> it = hma.keySet().iterator();
        StringBuilder st = new StringBuilder("HashMap C\n");
        while (it.hasNext()) {
            String clave = it.next();
            st.append("[");
            st.append(clave);
            st.append("]=");
            st.append(this.getCantA(clave));
            st.append("\n");
        }
        return st.toString();
    }

    /* INSERT INTO Archivo(nombre) VALUES("file.txt");
     INSERT INTO Palabra(nombre) VALUES(UPPER('Palabra'));
     SELECT id_palabra AS id FROM Palabra WHERE nombre=UPPER('otra');
     INSERT INTO PalabraXArchivo(id_palabra, id_archivo, apariciones) VALUES(1, 1, 5);
     */
    public void cargaDB(String archivo, int idArch) throws SQLException {
        ResultSet r, rs, res;
        Iterator<String> it = hma.keySet().iterator();
        while (it.hasNext()) {
            String clave = it.next();
            int idPalabra = 0;
            int cant = this.getCantA(clave);
            db.setQuery("SELECT id_palabra AS id FROM Palabra WHERE nombre=UPPER('" + clave + "');");
            r = db.getResultsFromQuery();
            if (!r.next()) {
                System.out.println("Palabra no existe, carga nueva");
                db.setQuery("INSERT INTO Palabra(nombre) VALUES(UPPER('" + clave + "'));");
                db.executeSingleQuery();
                db.setQuery("SELECT MAX(id_palabra) AS id FROM Palabra;");
                res = db.getResultsFromQuery();
                while (res.next()) {
                    idPalabra = res.getInt("id");
                }
            } else {
                while (r.next()) {
                    idPalabra = r.getInt("id");
                }
            }
            db.setQuery("SELECT pa.id AS id "
                    + "FROM PalabraXArchivo pa "
                    + "INNER JOIN Archivo a ON pa.id_archivo=a.id_archivo "
                    + "INNER JOIN Palabra p ON pa.id_palabra=p.id_palabra "
                    + "WHERE a.nombre='" + archivo + "' AND p.nombre=UPPER('" + clave + "');");
            rs = db.getResultsFromQuery();
            if (!rs.next()) {
                db.setQuery("INSERT INTO PalabraXArchivo(id_palabra, id_archivo, apariciones) VALUES(" + idPalabra + ", " + idArch + ", " + cant + ");");
                db.executeSingleQuery();
            } else {
                int idRel=0;
                while(rs.next()){
                    idRel=rs.getInt("id");
                }
                db.setQuery("UPDATE  PalabraXArchivo "
                        + "SET apariciones="+cant
                        + " WHERE id="+idRel+";");
                db.executeSingleQuery();

            }
        }
        this.db.setQuery("");
    }

}
