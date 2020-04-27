/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DBUtils;
import model.PcCliente;
/**
 *
 * @author Enso
 */
public class pcClienteDao {
    private PreparedStatement pst = null;
    private DBUtils dbutils = null;
    private Connection cnx = null;
    private ResultSet rs = null;
    public int totalFilas;
    public void consultarPcs(PcCliente pc) throws SQLException{
        dbutils = new DBUtils();
        String sql = "select id_pc, nombre_pc from pc_cliente" ;
        
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            
            rs = pst.executeQuery();
            
            while (rs.next()) {
                pc.setId_pc(rs.getInt("id_pc"));
                pc.setNombre_pc(rs.getString("nombre_pc"));   
            }
             
            System.out.println("Total de pcs es: " + totalFilas);
        } catch (SQLException ex) {
            dbutils.procesarExcepcion(ex);
        } finally {   
            cnx.close();
            pst.close();
            rs.close();
        }

        
        
    }
}
