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
   
}
