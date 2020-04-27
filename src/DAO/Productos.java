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
public class Productos{
    private ResultSet rs;
    private PreparedStatement st = null;
    Connection cnx = null;
    public void consultarProducto(Producto producto) throws SQLException {
        
        DBUtils dbutils = new DBUtils();
        String var2 = "1";
        cnx = dbutils.getConnection();
        String sql = "select nombre_producto, precio_venta from producto where id_producto =" + var2;
        st = cnx.prepareStatement(sql);
        rs = st.executeQuery();
        
        while(rs.next()) {
            producto.setNombre_Producto(rs.getString("nombre_producto"));
            producto.setPrecio_venta(rs.getDouble("precio_venta"));
        }
        
        System.out.println("El nombre del producto es: " + producto.getNombre_Producto());
        cnx.close();
    }
}
