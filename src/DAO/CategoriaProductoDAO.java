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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CategoriaProducto;
import model.DBUtils;

/**
 *
 * @author Enso
 */
public class CategoriaProductoDAO {
    private DBUtils dbUtils;
    private ResultSet rs;
    private PreparedStatement pst;
    private Connection cnx;
    private CategoriaProducto model;
    
    public ArrayList<CategoriaProducto> listarCategoria() throws SQLException {
        ArrayList<CategoriaProducto> list = new ArrayList<>();
        dbUtils = new DBUtils();
        String sql = "select * from categoria_producto";
        
        try {
            cnx = dbUtils.getConnection();
            pst = cnx.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {                
                model = new CategoriaProducto(rs.getInt("id_categoria"), rs.getString("nombre_categoria"));
                list.add(model);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbUtils.closeConnection(cnx, pst, rs);
        }
        return list;
    } 
    
    public CategoriaProducto consultarIdCategoria(String nombre) throws SQLException {
        String sql = "select * from categoria_producto where nombre_categoria = ?";
        dbUtils = new DBUtils();
        model = new CategoriaProducto();
        try {
            cnx = dbUtils.getConnection();
            pst = cnx.prepareStatement(sql);
            pst.setString(1, nombre);
            rs = pst.executeQuery();
            if (rs.next()) {
                model.setIdCategoria(rs.getInt("id_categoria"));
                model.setNombre_categoria(rs.getString("nombre_categoria"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbUtils.closeConnection(cnx, pst, rs);
        }
        return model;
    }
}
