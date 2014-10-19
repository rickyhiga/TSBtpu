/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import config.AbstractDB;

/**
 *
 * @author user
 */
public class Access extends AbstractDB {
    public Access(String dbFile){
        super.dbPath.append(dbFile);
        this.crearTablas();
    }
    
    private void crearTablas() {
        String st = "CREATE TABLE IF NOT EXISTS [Archivo] ( "
                + "[id_archivo] INTEGER  NOT NULL PRIMARY KEY, "
                + "[nombre] VARCHAR(45)  UNIQUE NULL "
                + "); "
                + " "
                + "CREATE TABLE IF NOT EXISTS [Palabra] ( "
                + "[id_palabra] INTEGER  PRIMARY KEY NOT NULL, "
                + "[nombre] VARCHAR(45)  UNIQUE NOT NULL "
                + "); "
                + " "
                + "CREATE TABLE IF NOT EXISTS [PalabraXArchivo] ( "
                + "[id] INTEGER  PRIMARY KEY NOT NULL, "
                + "[id_palabra] INTEGER  NOT NULL, "
                + "[id_archivo] INTEGER  NOT NULL, "
                + "[apariciones] INTEGER  NULL "
                + ");";
        super.setQuery(st);
        super.executeTable();
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
   
   
}
