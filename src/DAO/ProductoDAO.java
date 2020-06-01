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
import java.sql.Statement;
import model.DBUtils;
import model.Producto;

/**
 *
 * @author Enso
 */
public class ProductoDAO{
    private ResultSet rs;
    private PreparedStatement pst = null;
    private DBUtils dbutils = new DBUtils();
    private Connection cnx = null;
    
    public void consultarProducto(Producto producto) throws SQLException {
        
        String var2 = "1";
        cnx = dbutils.getConnection();
        String sql = "select nombre_producto, precio_venta from producto where id_producto =" + var2;
        pst = cnx.prepareStatement(sql);
        rs = pst.executeQuery();
        
        while(rs.next()) {
            producto.setNombre_Producto(rs.getString("nombre_producto"));
            producto.setPrecio_venta(rs.getDouble("precio_venta"));
        }
        
        System.out.println("El nombre del producto es: " + producto.getNombre_Producto());
        cnx.close();
    }
    
    
    public void insertarProducto(Producto model) throws SQLException {
        try {
            String sql_Insert = "insert into producto values (?,?,?,?,?,?,?,?,?,?,?)";
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql_Insert);
            pst.setInt(1, model.getId_Producto());
            pst.setString(2, model.getNombre_Producto());
            pst.setString(3, model.getPeso());
            pst.setDouble(4, model.getPrecio_venta());
            pst.setInt(5, model.getStock());
            pst.setString(6, model.getFecha_vencimieto());
            pst.setBytes(7, model.getImagen());
            pst.setString(8, model.getEstado());
            pst.setInt(9, model.getId_categoria());
            pst.setInt(10, model.getId_tipo());
            pst.setInt(11, model.getId_proveedor());
            int result = pst.executeUpdate();
            if (result == 1) {
                System.out.println("Se inserto la fila correctamente");
            } else {
                System.out.println("Fallo inserccion del producto");
            }
            
        } catch (SQLException e) {
            dbutils.procesarExcepcion(e);
        } finally {
            cnx.close();
            pst.close();
        }   
    }
    
}
