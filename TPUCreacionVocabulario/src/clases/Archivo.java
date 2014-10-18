/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import db.DBAbstractModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import testear.*;

/**
 *
 * @author user
 */
public class Archivo {

    DBAbstractModel db = new DBAbstractModel("C:\\Users\\user\\Documents\\RickyFacu\\2014\\TSB\\TSBtpu\\TPUCreacionVocabulario\\prueba.db");
    File f;
    Contador c;
    String nombre;
    int id = 0;
    //public static final String EXAMPLE_TEST = "This is my, 489 sma4ll   . '0example string which? I'm going to /use for pattern_matching. \n Nueva linea continuando ejemplo.";

    public Archivo(String nombre) {
        this.nombre = nombre;
        f = new File(nombre);

        c = new Contador();
    }

    public void leer() {
        try {
            /*
             a=10
             b9
             c8
             resto abecedario=1
             ab5
             bc4
             ded3
             di4
             dif2
             */

            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String clave = sc.nextLine();
                c.sumarCantA(clave);
                c.sumarCantB(clave);
                c.sumarCantC(clave);
            }
            try {
                this.cargaArchivo();
                c.cargaDB(nombre, id);
            } catch (SQLException ex) {
                Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error en la carga en DB: " + ex.toString());

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //NO IRIA
    private void cargaArchivo() throws SQLException {
        ResultSet r, res;
        String sql = "";
        sql = "SELECT id_archivo AS id FROM Archivo WHERE nombre=('" + nombre + "');";

        this.db.setQuery(sql);

        r = db.getResultsFromQuery();
        //System.out.println("ID="+r.getInt("id"));
        try {
            
            if (r.next()) {
                System.out.println("Existe archivo "+nombre);
                
                //while (r.next()) {
                    id = r.getInt("id");
               // }
                System.out.println("Archivo existe ID=" + id);
            } else {
                System.out.println("No existe el archivo " + nombre);
                db.closeConnection();
                this.db.setQuery("INSERT INTO Archivo(nombre) VALUES('" + nombre + "');");

                db.executeSingleQuery();
                db.setQuery("SELECT MAX(id_archivo) AS id FROM Archivo;");
                res = db.getResultsFromQuery();
                while (res.next()) {
                    this.id = res.getInt("id");
                }
                System.out.println("Archivo ID="+id);
            }
        } catch (SQLException ex) {

            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnection();
        }

    }

    public String toString() {
        return c.toStringA();
    }

}
