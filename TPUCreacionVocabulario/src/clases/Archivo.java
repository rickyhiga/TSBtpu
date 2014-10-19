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
        Pattern pattern = Pattern.compile("[ñÑA-Za-záÁéÉíÍóÓúÚ][ñÑa-zA-ZáÁéÉíÍóÓúÚ]+");
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
//                String clave = sc.nextLine();
                Matcher matcher = pattern.matcher(sc.nextLine());
                while (matcher.find()) {
//                    System.out.print("Start index: " + matcher.start());
//                    System.out.print(" End index: " + matcher.end() + " ");
                    c.sumarCantA(matcher.group().toLowerCase());
                  //  System.out.println(matcher.group());
                }
//                c.sumarCantA(clave);
//                c.sumarCantB(clave);
//                c.sumarCantC(clave);
            }
            System.out.println("Cantidad de palabras="+c.cant);
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
        int idRes=0;
        String sql = "";
        sql = "SELECT id_archivo AS id FROM Archivo WHERE nombre=('" + nombre + "');";

        this.db.setQuery(sql);
        this.db.openConnection();
        idRes = db.getIntFromQuery();
        if (idRes!=-1) {
            System.out.println("Existe archivo " + nombre);
            
            //while (r.next()) {
            id = idRes;
            // }
            System.out.println("Archivo existe ID=" + id);
        } else {
            System.out.println("No existe el archivo " + nombre);
//                db.closeConnection();
            this.db.setQuery("INSERT INTO Archivo(nombre) VALUES('" + nombre + "');");
            
            db.executeSingleQuery();
            db.setQuery("SELECT MAX(id_archivo) AS id FROM Archivo LIMIT 1;");
            idRes = db.getIntFromQuery();
            if (idRes!=-1) {
                this.id = idRes;
            }else{
                idRes=0;
                this.id=idRes;
            }
            System.out.println("Archivo ID=" + id);
        }    
        this.db.closeConnection();
        

    }

    public String toString() {
        return c.toStringA();
    }

}
