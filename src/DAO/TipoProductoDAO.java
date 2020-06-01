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
import model.DBUtils;
import model.TipoProducto;

/**
 *
 * @author Enso
 */
public class TipoProductoDAO {
    private ResultSet rs;
    private PreparedStatement pst;
    private DBUtils dbutils;
    private Connection cnx;
    private TipoProducto model;
    
    public ArrayList<TipoProducto> consultarTipoProducto() throws SQLException {
        ArrayList<TipoProducto> list = new ArrayList<>();
        dbutils = new DBUtils();
        String sql = "select * from tipo_producto";
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {                
                model = new TipoProducto(rs.getInt("id_tipo"), rs.getString("nombre_tipo"));
                list.add(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbutils.closeConnection(cnx, pst, rs);
        }
        return list;
    }
    
    public TipoProducto consultarIdTipo(String nombre) throws SQLException {
        String sql = "select * from tipo_producto where nombre_tipo = ?";
        model = new TipoProducto();
        dbutils = new DBUtils();
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            pst.setString(1, nombre);
            rs = pst.executeQuery();
            if (rs.next()) {
                model = new TipoProducto(rs.getInt("id_tipo"), rs.getString("nombre_tipo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbutils.closeConnection(cnx, pst, rs);
        }
        return model;
    }
}
