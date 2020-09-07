/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import InterfaceDAO.PedidoDAO;
import Utilidades.ControladorGeneral;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.DBUtils;
import model.Pedido;
import model.PedidoDetPedProd;
import model.Producto;

/**
 *
 * @author Enso
 */
public class PedidoDAOImpl implements PedidoDAO{
    private PreparedStatement pst = null;
    private DBUtils dbutils = null;
    private Connection cnx = null;
    private ResultSet rs = null;
    //private Pedido model = null;

    @Override
    public List<Pedido> listAll() {
        return null;
    }

    @Override
    public Pedido searchId(int id) {
        return null;
    }

    @Override
    public void register(Pedido model) {
        dbutils =new DBUtils();
        String sql = "insert into pedido values(?,?,?,?,?)";
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            pst.setInt(1, model.getIdPedido());
            pst.setString(2, model.getEstado());
            pst.setString(3, model.getHora());
            pst.setString(4, model.getFecha());
            pst.setInt(5, model.getIdPc());
            int fileUpdate = pst.executeUpdate();
            if (fileUpdate == 1) {
                System.out.println("Se registro correctamente el pedido");
            } else {
                System.out.println("ERROR, no se pudo registrar el pedido");
            }           
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbutils.closeConnection(cnx, pst);
            } catch (SQLException ex) {
                Logger.getLogger(PedidoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(Pedido model) {
        
    }

    @Override
    public void delete(int id) {
        
    }

    @Override
    public int getNewId() {
        Pedido model = new Pedido();
        dbutils = new DBUtils();
        String sql = "select * from pedido order by id_pedido desc";
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                model.setIdPedido(rs.getInt("id_pedido"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbutils.closeConnection(cnx, pst, rs);
            } catch (SQLException ex) {
                Logger.getLogger(PedidoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return model.getIdPedido();
    }
    

    @Override
    public List<PedidoDetPedProd> searchPedidosForId(int id) {
        List<PedidoDetPedProd> listData = new ArrayList<>();
        String sql = "select pro.imagen,pro.nombre_Producto, det.cantidad, pro.precio_venta, det.subtotal from pedido p inner join detalle_pedido det on p.Id_Pedido = det.id_pedido inner join producto pro on pro.Id_Producto = det.Id_Producto where p.id_pedido = ?";
        dbutils = new DBUtils();
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {                
                PedidoDetPedProd model = new PedidoDetPedProd();
                model.setImagen(ControladorGeneral.bytesToImage(rs.getBytes("pro.imagen")));
                model.setNombre_Producto(rs.getString("pro.nombre_Producto"));
                model.setCantidadOrden(rs.getInt("det.cantidad"));
                model.setPrecioProducto(rs.getDouble("pro.precio_venta"));
                model.setSubtotalPedido(rs.getDouble("det.subtotal"));
                listData.add(model);
            }
        } catch (SQLException ex) {
            dbutils.procesarExcepcion(ex);
        } finally {
            try {
                dbutils.closeConnection(cnx, pst, rs);
            } catch (SQLException ex) {
                Logger.getLogger(PedidoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listData;
    }
}
