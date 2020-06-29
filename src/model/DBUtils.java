/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Enso
 */
public class DBUtils {
    private final String DB = "nobi_tienda";
    private final String MYSQLDB = "jdbc:mysql://localhost:3306/" + DB;
    private final String User = "enso";
    private final String Pass = "123456";
    private Connection conec;
    /*Método para llamar a la conexion de la base de datos*/
    public Connection getConnection() throws SQLException {
        Connection conexion = null;
        
        try {
            conexion = DriverManager.getConnection(MYSQLDB, User, Pass);
            if (!conexion.isClosed()) {
                System.out.println("Ruben estas conectado a la base de datos por primera vez");
            }
        } catch (SQLException ex) {
            System.err.println("Exception: " + ex.getMessage());
        }
        return conexion;
    }
    
    public void closeConnection(Connection con, PreparedStatement pst, ResultSet rs) throws SQLException{
        con.close();
        pst.close();
        rs.close();
    }
    public void closeConnection(Connection con, PreparedStatement pst) throws SQLException{
        con.close();
        pst.close();

    }
    
    public void procesarExcepcion(SQLException ex) {
        System.err.println("Error: " + ex.getMessage());
        System.err.println("Code: " + ex.getErrorCode());
        System.err.println("SqlState: " + ex.getSQLState());
    }
}
