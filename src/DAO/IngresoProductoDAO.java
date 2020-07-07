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
import model.IngresoProducto;

/**
 *
 * @author Enso
 */
public class IngresoProductoDAO {
    private ResultSet rs;
    private PreparedStatement pst;
    private DBUtils dbutils;
    private Connection cnx;
    private IngresoProducto model;
    
    public void insertarIngresoProducto(IngresoProducto model){
        String sql = "insert into ingreso_producto values(?,?,?,?,?,?,?)";
        dbutils = new DBUtils();
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            pst.setInt(1, model.getId_ingreso());
            pst.setString(2, model.getFecha_ingreso());
            pst.setString(3, model.getHora_ingreso());
            pst.setInt(4, model.getStock_ingreso());
            pst.setString(5, model.getEstado());
            pst.setInt(6, model.getId_usuario());
            pst.setInt(7, model.getId_producto());
            int result = pst.executeUpdate();
            if (result == 1) {
                System.out.println("Se inserto la fila correctamente de Ingreso Producto");
            } else {
                System.out.println("Hubo un problema al insertar la fila de Ingreso Producto");
            }
        } catch (SQLException ex) {
            dbutils.procesarExcepcion(ex);
        } finally {
            try {
                dbutils.closeConnection(cnx, pst);
            } catch (SQLException ex) {
                Logger.getLogger(IngresoProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    //método para eliminar ingreso de producto
    public void eliminarIngresoProducto(IngresoProducto ip) {
        dbutils = new DBUtils();
        String sqlIngresoProducto = "Delete from ingreso_producto where id_producto = ?";
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sqlIngresoProducto);
            pst.setInt(1, ip.getId_producto());
            int result = pst.executeUpdate();
            if (result == 1) {
                System.out.println("Los productos ingresados a través del tiempo han sido eliminados");
            }
        } catch (SQLException ex) {
            dbutils.procesarExcepcion(ex);
        } finally {
            try {
                dbutils.closeConnection(cnx, pst);
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
