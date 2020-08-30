/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import InterfaceDAO.SalidaProductoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DBUtils;
import model.SalidaProducto;

/**
 *
 * @author Enso
 */
public class SalidaProductoImpl implements SalidaProductoDAO{
    private PreparedStatement pst = null;
    private DBUtils dbutils = null;
    private Connection cnx = null;
    private ResultSet rs = null;
    
    @Override
    public List<SalidaProducto> listAll() {
        return null;
    }

    @Override
    public SalidaProducto searchId(int id) {
        return null;
    }

    @Override
    public void register(SalidaProducto model) {
        dbutils = new DBUtils();
        String sql = "insert into salida_producto values(?,?,?)";
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            pst.setInt(1, model.getIdSalida());
            pst.setInt(2, model.getCantidadSalida());
            pst.setInt(3, model.getIdProducto());
            int result = pst.executeUpdate();
            if (result == 1) {
                System.out.println("La salida del producto se registro correctamente.");
            } else {
                System.out.println("¡REGISTRO FALLIDO!");
            }           
        } catch (SQLException ex) {
            dbutils.procesarExcepcion(ex);
        } finally {
            try {
                dbutils.closeConnection(cnx, pst);
            } catch (SQLException ex) {
                Logger.getLogger(SalidaProductoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(SalidaProducto model) {
        
    }

    @Override
    public void delete(int id) {
    }
    
}
