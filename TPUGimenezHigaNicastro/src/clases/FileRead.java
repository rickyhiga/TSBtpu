/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Access;

/**
 *
 * @author user
 */
public class FileRead {

    private String tablaArch = "Archivo";
    private String pkArch = "id_archivo";
    private String tablaP = "Palabra";
    private String pkP = "id_palabra";
    private Access a;
    private TempStore t;
    private String nombreFile;

    public FileRead(String file) {
        this.nombreFile = file;
        this.a = new Access("tpuVocabulario.db");
        this.t = new TempStore(6000);
    }

    public FileRead(String dbFile, String file) {
        this.nombreFile = file;
        this.a = new Access(dbFile);
        this.t = new TempStore(6000);
    }

    public FileRead(String dbFile, String file, int sizeTemp) {
        this.nombreFile = file;
        this.a = new Access(dbFile);
        this.t = new TempStore(sizeTemp);
    }

    public void readFile() {
        try {
            Pattern pattern = Pattern.compile("[ñÑA-Za-záÁéÉíÍóÓúÚ][ñÑa-zA-ZáÁéÉíÍóÓúÚ]+");
            File f = new File(nombreFile);
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                Matcher matcher = pattern.matcher(sc.nextLine());
                while (matcher.find()) {
                    t.addCount(matcher.group().toLowerCase());
                }
            }
            System.out.println(this.nombreFile + " - Cantidad de Palabras=" + t.getCantClaves());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileRead.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se pudo leer el archivo");
        }
    }

    public void saveCount() {
        int idArch = 0;
        this.a.openConnection();

        idArch = this.saveCountArch();

        this.saveCountWord(idArch);
        this.a.closeConnection();
    }

    private int saveCountArch() {
        //ARCHIVO
        int idAr = 0, idRes;

        idRes = a.getId(this.tablaArch, this.pkArch, "nombre=('" + nombreFile + "')");
        if (idRes != -1) {
            //Existe
            System.out.println("Ese archivo ya fue cargado");
            System.exit(0);
        } else {
            a.insertPoA(this.tablaArch, nombreFile);
            idRes = a.getMaxId(this.tablaArch, this.pkArch);
            if (idRes != -1) {
                idAr = idRes;
            } else {
                System.out.println("No se insertó el Archivo");
                System.exit(0);
            }
        }
        return idAr;
    }

    private void saveCountWord(int idAr) {
        Iterator<String> it = t.getIterator();
        int idPa = tempIdWord();
        int idOld=-1, idP;
        while (it.hasNext()) {
            String clave = it.next();
            int cant = t.getCount(clave);
            idOld=a.getId(tablaP, this.pkP, "nombre=('" + clave + "')") ;
            if (idOld== -1) {
                a.insertPoA(this.tablaP, clave);
                idPa++;
                idP=idPa;
            }else{
                idP=idOld;
            }
            
            a.insertPxA(idP, idAr, cant);
        }
        System.out.println("ID last word=" + idPa);
    }

    private int tempIdWord() {
        int i = a.getMaxId(this.tablaP, this.pkP);
        if (i == -1) {
            return 0;
        }
        return i;
    }

}
