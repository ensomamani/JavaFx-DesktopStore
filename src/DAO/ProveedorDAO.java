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
import model.Proveedor;

/**
 *
 * @author Enso
 */
public class ProveedorDAO {
    private ResultSet rs;
    private PreparedStatement pst;
    private DBUtils dbutils;
    private Connection cnx;
    private Proveedor model;
    
    public ArrayList<Proveedor> listarProveedor() throws SQLException {
        ArrayList<Proveedor> list = new ArrayList<>();
        String sql = "select * from proveedor";
        dbutils = new DBUtils();
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {                
                model = new Proveedor(rs.getInt("id_proveedor"), rs.getString("nombre_proveedor"), rs.getString("telefono"), rs.getString("celular"));
                list.add(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbutils.closeConnection(cnx, pst, rs);
        }
        return list;
    }
    
    public Proveedor obtenerIdProveedor(String nombre) throws SQLException {
        dbutils = new DBUtils();
        String sql = "select * from proveedor where nombre_proveedor = ?";
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            pst.setString(1, nombre);
            rs = pst.executeQuery();
            if (rs.next()) {
                model = new Proveedor(rs.getInt("id_proveedor"), rs.getString("nombre_proveedor"), rs.getString("telefono"), rs.getString("celular"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbutils.closeConnection(cnx, pst, rs);
        }
        return model;
    }
}
