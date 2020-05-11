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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
    private PcCliente model = null;
    
    public ArrayList<PcCliente> consultar() throws SQLException {
        ArrayList<PcCliente> pcList = new ArrayList<>();
        dbutils = new DBUtils();
        String sql = "select * from pc_cliente";
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {                
                model = new PcCliente(rs.getInt("id_pc"), rs.getString("nombre_pc"));
                pcList.add(model);
            }
            System.out.println("se listo las pc cliente");
        } catch (SQLException e) {
            dbutils.procesarExcepcion(e);
        } finally {
            dbutils.closeConnection(cnx, pst, rs);
        }
        return pcList;
    }
}
