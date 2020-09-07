/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import InterfaceDAO.DetallePedidoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DBUtils;
import model.DetallePedido;

/**
 *
 * @author Enso
 */
public class DetallePedidoDAOImpl implements DetallePedidoDAO{
    private PreparedStatement pst = null;
    private DBUtils dbutils = null;
    private Connection cnx = null;
    private ResultSet rs = null;
    @Override
    public List<DetallePedido> listAll() {
        return null;
    }

    @Override
    public DetallePedido searchId(int id) {
        return null;
    }

    @Override
    public void register(DetallePedido model) {
        dbutils = new DBUtils();
        String sql = "insert into detalle_pedido values(?,?,?,?,?,?)";
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            pst.setInt(1, model.getIdDetallePedido());
            pst.setInt(2, model.getCantidad());
            pst.setDouble(3, model.getSubtotal());
            pst.setString(4, model.getEstado());
            pst.setInt(5, model.getIdPedido());
            pst.setInt(6, model.getIdProducto());
            int fileUpdate = pst.executeUpdate();
            if (fileUpdate == 1) {
                System.out.println("¡BIEN! el detalle de pedido se registro correctamente");
            } else {
                System.out.println("ERROR el detalle de pedido no se registro correctamente");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetallePedidoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbutils.closeConnection(cnx, pst);
            } catch (SQLException ex) {
                Logger.getLogger(DetallePedidoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(DetallePedido model) {
        
    }

    @Override
    public void delete(int id) {
        
    }

    @Override
    public int getNewId(DetallePedido model) {
        dbutils = new DBUtils();
        String sql = "select * from detalle_pedido order by id_detped desc";
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                model.setIdDetallePedido(rs.getInt("id_detped"));
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
        return model.getIdDetallePedido() + 1;
    }
    
}
