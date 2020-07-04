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
import model.Producto;
import model.ProductoCatTipProv;

/**
 *
 * @author Enso
 */
public class ProductoDAO {

    private ResultSet rs;
    private PreparedStatement pst = null;
    private DBUtils dbutils;
    private Connection cnx = null;
    private Producto modelProducto;
    private ProductoCatTipProv modelProductoCatTipProv;

    //lista de producto addItemVentanaTiendaController
    public void consultarProducto(Producto producto) throws SQLException {
        String var2 = "1";
        cnx = dbutils.getConnection();
        String sql = "select nombre_producto, precio_venta from producto where id_producto =" + var2;
        pst = cnx.prepareStatement(sql);
        rs = pst.executeQuery();

        while (rs.next()) {
            producto.setNombre_Producto(rs.getString("nombre_producto"));
            producto.setPrecio_venta(rs.getDouble("precio_venta"));
        }

        System.out.println("El nombre del producto es: " + producto.getNombre_Producto());
        cnx.close();
    }

    //lista de productos para tableView u otros
    public ArrayList<ProductoCatTipProv> listarProductos() throws SQLException {
        ArrayList<ProductoCatTipProv> listar = new ArrayList<>();
        String sql = "select p.Id_Producto, p.nombre_Producto, p.peso,p.precio_venta, p.stock, c.nombre_categoria, t.nombre_tipo, pro.nombre_proveedor, p.fecha_vencimiento, p.imagen from producto p\n"
                + "inner join nobi_tienda.categoria_producto c on p.Id_Categoria = c.Id_Categoria\n"
                + "inner join nobi_tienda.tipo_producto t on t.Id_Tipo = p.Id_Tipo\n"
                + "inner join nobi_tienda.proveedor pro on pro.Id_Proveedor = p.Id_Proveedor order by p.Id_Producto asc" ;
        dbutils = new DBUtils();
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                modelProductoCatTipProv = new ProductoCatTipProv(rs.getInt("p.id_producto"), rs.getString("p.nombre_producto"), rs.getString("p.peso"),
                        rs.getDouble("p.precio_venta"), rs.getInt("p.stock"), rs.getString("c.nombre_categoria"), rs.getString("t.nombre_tipo"),
                        rs.getString("pro.nombre_proveedor"), rs.getString("p.fecha_vencimiento"), rs.getBytes("p.imagen"));
                listar.add(modelProductoCatTipProv);
            }
        } catch (SQLException ex) {
            dbutils.procesarExcepcion(ex);
        } finally {
            dbutils.closeConnection(cnx, pst, rs);
        }
        return listar;
    }
    
    //ultimo codigo del prodcuto
    //genera el nuevo codigo producto
    public int getUltimoCodigo() throws SQLException {
        Producto p = new Producto();
        String sql = "select Id_Producto from producto order by Id_Producto desc";
        dbutils = new DBUtils();
        int codigo = 0;
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                codigo = rs.getInt("id_producto") + 1;
            }             
        } catch (SQLException ex) {
            dbutils.procesarExcepcion(ex);
        } finally {
            dbutils.closeConnection(cnx, pst, rs);
        }
        return codigo;
    }

    //metodo para registrar productos a la BD
    public void insertarProducto(Producto model) throws SQLException {
        dbutils = new DBUtils();
        try {
            String sql_Insert = "insert into producto values (?,?,?,?,?,?,?,?,?,?,?)";
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql_Insert);
            //pst 1 igual agrega por que el codigo de producto en la base de datos es autoincrementable, no es necesario
            // pasar datos en la interfaz grafica
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
            JOptionPane.showMessageDialog(null, "Ingresa el tipo de dato correcto o revisa el primary key");
            dbutils.procesarExcepcion(e);
        } finally {
            cnx.close();
            pst.close();
        }
    }

    //método para consultar producto existente
    public boolean existeProductoBool(String producto) {
        dbutils = new DBUtils();
        String sql = "select nombre_Producto from producto where nombre_Producto like ?";
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            pst.setString(1, "%" + producto + "%");
            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("Existe producto");
                return true;
            }
        } catch (SQLException ex) {
            dbutils.procesarExcepcion(ex);
        } finally {
            try {
                dbutils.closeConnection(cnx, pst, rs);
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
//verifica si el producto existe
    public Producto existeProducto(String producto) {
        dbutils = new DBUtils();
        String sql = "select id_Producto, nombre_Producto, precio_venta, stock from producto where nombre_Producto like ?";
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            pst.setString(1, "%" + producto + "%");
            rs = pst.executeQuery();
            if (rs.next()) {
                modelProducto = new Producto(rs.getInt("id_producto"), rs.getString("nombre_Producto"), rs.getInt("stock"));
            }

        } catch (SQLException ex) {
            dbutils.procesarExcepcion(ex);

        }
        return modelProducto;
    }

    //método para actualizar productos a la BD
    public void actualizarStockProducto(int codigo, int stock) {
        dbutils = new DBUtils();
        String sqlUpdate = "update producto set stock = ? where id_producto = ?";
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sqlUpdate);
            pst.setInt(1, stock);
            pst.setInt(2, codigo);
            int fila = pst.executeUpdate();
            if (fila == 1) {
                System.out.println("La actualizacion fue correcta!.");
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
    
    //metodo para actualizar producto 
    public void actualizarProducto(Producto p) {
        dbutils = new DBUtils();
        String sql = "update producto set nombre_Producto = ? , peso = ?, precio_venta = ?, stock = ?, fecha_vencimiento = ?, imagen = ?, id_categoria = ?, id_tipo = ?, id_proveedor = ? where id_producto = ?";
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            pst.setString(1, p.getNombre_Producto());
            pst.setString(2, p.getPeso());
            pst.setDouble(3, p.getPrecio_venta());
            pst.setInt(4, p.getStock());
            pst.setString(5, p.getFecha_vencimieto());
            pst.setBytes(6, p.getImagen());
            pst.setInt(7, p.getId_categoria());
            pst.setInt(8, p.getId_tipo());
            pst.setInt(9, p.getId_proveedor());
            pst.setInt(10, p.getId_Producto());
            int result = pst.executeUpdate();
            if (result == 1) {
                System.out.println("Actualización del producto ha funcionado correctamente");
            } else {
                System.out.println("Hubo un error por favor verifica el método actualizar producto");
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
