/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a5
 */
public abstract class DBAbstractModel {
    private static String dbHost="";
    private static String dbUser="";
    private static String dbPass="";
    protected String dbName="";
    protected String query;
    protected ResultSet rows;
    private Connection con;
    public String mensaje="Hecho";
    abstract protected void get();
    abstract protected void set();
    abstract protected void edit();
    abstract protected void delete();
    private void openConnection(){
        
    }
    private void closeConnection(){
        try {
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAbstractModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en el cierre de la conexión db -" +ex.toString());
        }
    }
    protected void executeSingleQuery(){
        this.openConnection();
        //
        this.closeConnection();
    }
    protected ResultSet gerResultsFromQuery(){
        this.openConnection();
        
        
        this.closeConnection();
        return rows;
    }
}
