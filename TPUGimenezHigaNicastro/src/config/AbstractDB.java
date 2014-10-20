
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
 * @author ricky
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
    
   //SI NO FUERA SQLITE SERIA PRIVATE Y CADA CONSULTA ABRE Y CIERRA CONEXIÓN
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
     protected void crearTablas() {
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
        this.executeTable();
    }
    private void executeTable() {
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
    protected void closeResultSet() throws SQLException{
        this.stmt.close();
    }


}
