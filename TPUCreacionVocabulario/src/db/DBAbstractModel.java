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

    private static final StringBuilder dbType = new StringBuilder("org.sqlite.JDBC");
    protected StringBuilder dbPath = new StringBuilder("jdbc:sqlite:");
    protected StringBuilder query = new StringBuilder("");
    protected ResultSet rows;
    private Connection con;
    private Statement stmt;
    public StringBuilder mensaje = new StringBuilder("Hecho");
//    abstract protected void get();
//    abstract protected void set();
//    abstract protected void edit();
//    abstract protected void delete();

    public void crearTablas() {
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
        this.setQuery(st);
        this.executeSingleQuery();
    }
//NO DEBERIA ESTAR ACA ESTO

    public DBAbstractModel(String direccion) {
        dbPath.append(direccion);
        this.crearTablas();
    }

    public void setQuery(String sql) {
        query.setLength(0);
        query.append(sql);
    }

    private void openConnection() {
        try {
            Class.forName(dbType.toString());
            //System.out.println(dbPath.toString());
            con = DriverManager.getConnection(dbPath.toString());
            con.setAutoCommit(false);
            //System.out.println("DB abierta con éxito");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al abrir DB - " + e.toString());
            System.exit(0);
        }
    }

    public void closeConnection() {
        try {
            this.stmt.close();
            this.con.commit();
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAbstractModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en el cierre de la conexión db -" + ex.toString());
            System.exit(0);
        }
    }

    //Deberian ser protected
    
    public void executeSingleQuery() {
        this.openConnection();
        System.out.println(query.toString());
        try {
            this.stmt = this.con.createStatement();
            this.stmt.executeUpdate(query.toString());
            System.out.println("SingleQuery ejecutada con éxito");
        } catch (SQLException ex) {

            System.out.println("Error al ejecutar SingleQuery - " + ex.toString());
            System.exit(0);
        }

        this.closeConnection();

    }

    //protected
    public ResultSet getResultsFromQuery() {
        this.openConnection();
        System.out.println(query.toString());
        rows = null;
        try {
            stmt = this.con.createStatement();
            rows = stmt.executeQuery(query.toString());
            System.out.println("Consulta ejecutada con éxito");
        } catch (SQLException ex) {
            System.exit(0);
            System.out.println("Error al ejecutar consulta - " + ex.toString());
        }
       // this.closeConnection();

        return rows;
    }

         
}
