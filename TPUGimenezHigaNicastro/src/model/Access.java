/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import clases.Palabra;
import config.AbstractDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Access extends AbstractDB {
    public Access(String dbFile){
        super.dbPath.append(dbFile);
        super.crearTablas();
    }
       
    public int getMaxId(String tabla, String clave){
        StringBuilder st= new StringBuilder("");
        st.append("SELECT MAX(");
        st.append(clave);
        st.append(") AS id FROM ");
        st.append(tabla);
        st.append(" LIMIT 1;");
        super.setQuery(st.toString());
        return super.getIntFromQuery();
    }
    public int getId(String tabla, String clave, String condicion){
        int id=-1;
        StringBuilder st = new StringBuilder("SELECT ");
        st.append(clave);
        st.append(" AS id FROM ");
        st.append(tabla);
        st.append(" WHERE ");
        st.append(condicion);
        st.append(";");
        super.setQuery(st.toString());
        id=super.getIntFromQuery();
        //id_archivoArchivonombre=('" + nombre + "');";
        return id;
    }
    public void insertPoA(String tabla, String valor){
        StringBuilder st=new StringBuilder("");
        st.append("INSERT INTO ");
        st.append(tabla);
        st.append("(nombre) VALUES('");
        st.append(valor);
        st.append("');");
        super.setQuery(st.toString());
        super.executeSingleQuery();
    }
    public void insertPxA(int idP, int idA, int cant){
        StringBuilder st=new StringBuilder("");
        st.append("INSERT INTO PalabraXArchivo(id_palabra, id_archivo, apariciones) VALUES(");
        st.append(idP);
        st.append(", ");
        st.append(idA);
        st.append(", ");
        st.append(cant);
        st.append(");");
        super.setQuery(st.toString());
        super.executeSingleQuery();
    }  
    public ArrayList selectGrilla(){
        System.out.println("SelectGrilla");
        super.openConnection();
        ArrayList<Palabra> palabras=new ArrayList<>();
        try {
            ResultSet res=null;
            String sql = "SELECT P.id_palabra AS id, P.nombre AS palabra ,SUM(PXA.apariciones) AS apariciones, COUNT(P.nombre) AS cantArch FROM PalabraXArchivo PXA join Palabra P ON(PXA.id_palabra=P.id_palabra) GROUP BY id ORDER BY palabra; ";
            super.setQuery(sql);
            res=super.getResultsFromQuery();
            while (res.next()) {
                int id = res.getInt("id");
                String des = res.getString("palabra");
                int cant=res.getInt("apariciones");
                int cantArch=res.getInt("cantArch");
                Palabra pa=new Palabra(id, des, cant, cantArch);
                palabras.add(pa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Access.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en cargar grilla: "+ex.toString());
        }
        super.closeConnection();
        return palabras;
        
    }
   
}
