/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public abstract class AbstractDB {

    protected static final StringBuilder dbType = new StringBuilder("org.sqlite.JDBC");
    protected StringBuilder dbPath = new StringBuilder("jdbc:sqlite:");
    protected StringBuilder query = new StringBuilder("");
    protected ResultSet rows;
    private Connection con;
    private Statement stmt;

    protected void setQuery(String sql) {
        query.setLength(0);
        query.append(sql);
    }

    public void openConnection() {
        try {
            Class.forName(dbType.toString());
          // System.out.println(dbPath.toString());
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
            this.con.commit();
            this.stmt.close();
            this.con.close();
        } catch (SQLException ex) {
            System.out.println("Error en el cierre de la conexión db -" + ex.toString());
            System.exit(0);
        }
    }

    protected void executeSingleQuery() {

       // System.out.println(query.toString());
        try {
            this.stmt = this.con.createStatement();
            this.stmt.executeUpdate(query.toString());
            //  System.out.println("SingleQuery ejecutada con éxito");
        } catch (SQLException ex) {

            System.out.println("Error al ejecutar SingleQuery - " + ex.toString());
            System.exit(0);
        }

    }

    protected ResultSet getResultsFromQuery() {
        //System.out.println(query.toString());
        ResultSet rows = null;
        try {
            stmt = this.con.createStatement();
            rows = stmt.executeQuery(query.toString());

        } catch (SQLException ex) {
            System.exit(0);
            System.out.println("Error al ejecutar consulta - " + ex.toString());
        }
        return rows;
    }

    protected void executeTable() {
        try {
            Class.forName(dbType.toString());
            //System.out.println(dbPath.toString());
            con = DriverManager.getConnection(dbPath.toString());
            con.setAutoCommit(true);
            //System.out.println("DB abierta con éxito");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al abrir DB - " + e.toString());
            System.exit(0);
        }
        //System.out.println(query.toString());
        try {
            this.stmt = this.con.createStatement();
            this.stmt.executeUpdate(query.toString());
            //  System.out.println("SingleQuery ejecutada con éxito");
        } catch (SQLException ex) {

            System.out.println("Error al ejecutar SingleQuery - " + ex.toString());
            System.exit(0);
        }

        try {
            this.stmt.close();

            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en el cierre de la conexión db -" + ex.toString());
            System.exit(0);
        }

    }
    protected int getIntFromQuery() {

      // System.out.println(query.toString());
        int i = -1;
        try {
            stmt = this.con.createStatement();
            rows = stmt.executeQuery(query.toString());
           // System.out.println("Consulta ejecutada con éxito");
        } catch (SQLException ex) {
            System.exit(0);
            
           // System.out.println("Error al ejecutar consulta - " + ex.toString());
        }
        try {
            if(rows.next()){
                i=rows.getInt(1);
            }
        } catch (SQLException ex) {
            
        }
        return i;
    }


}
