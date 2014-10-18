/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a5
 */
//DEBERIA SER AVSTRACT
public class DBAbstractModel {
    private static final StringBuilder dbType=new StringBuilder("org.sqlite.JDBC");
    protected StringBuilder dbPath=new StringBuilder("jdbc:sqlite:");
    protected StringBuilder query=new StringBuilder("");
    protected ResultSet rows;
    private Connection con;
    private Statement stmt;
    public StringBuilder mensaje= new StringBuilder("Hecho");
//    abstract protected void get();
//    abstract protected void set();
//    abstract protected void edit();
//    abstract protected void delete();
    

//NO DEBERIA ESTAR ACA ESTO
    public DBAbstractModel(String direccion){
        dbPath.append(direccion);
    }
    public void setQuery(String sql){
        query.setLength(0);
        query.append(sql);
    }
    private void openConnection(){
        try {
            Class.forName(dbType.toString());
            con=DriverManager.getConnection(dbPath.toString());
            con.setAutoCommit(false);
            System.out.println("DB abierta con éxito");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al abrir DB - " +e.toString());
        }
    }
    private void closeConnection(){
        try {
            this.stmt.close();
            this.con.commit();
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAbstractModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en el cierre de la conexión db -" +ex.toString());
        }
    }
    //Deberian ser protected
    public void executeSingleQuery(){
        this.openConnection();
        
        try {
            this.stmt=this.con.createStatement();
            this.stmt.executeUpdate(query.toString());
        } catch (SQLException ex) {
            Logger.getLogger(DBAbstractModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al ejecutar SingleQuery - " + ex.toString());
        }
        
        this.closeConnection();
        System.out.println("SingleQuery ejecutada con éxito");
    }
    
    //protected
    public ResultSet getResultsFromQuery(){
        this.openConnection();
        rows=null;
        try {
            stmt=this.con.createStatement();
            rows=stmt.executeQuery(query.toString());
        } catch (SQLException ex) {
            Logger.getLogger(DBAbstractModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al ejecutar consulta - "+ex.toString());
        }
        this.closeConnection();
        System.out.println("Consulta ejecutada con éxito");
        return rows;
    }
   
         
}
