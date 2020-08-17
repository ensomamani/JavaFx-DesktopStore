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
import model.PcCliente;

/**
 *
 * @author Enso
 */
public class pcClienteDao {

    private PreparedStatement pst = null;
    private DBUtils dbutils = null;
    private Connection cnx = null;
    private ResultSet rs = null;
    private PcCliente model = null;

    public ArrayList<PcCliente> consultar() throws SQLException {
        ArrayList<PcCliente> pcList = new ArrayList<>();
        dbutils = new DBUtils();
        String sql = "select * from pc_cliente";
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                model = new PcCliente(rs.getInt("id_pc"), rs.getString("nombre_pc"));
                pcList.add(model);
            }
            System.out.println("se listo las pc cliente");
        } catch (SQLException e) {
            dbutils.procesarExcepcion(e);
        } finally {
            dbutils.closeConnection(cnx, pst, rs);
        }
        return pcList;
    }

    //metodo para consultar si una pc existe
    public ArrayList<PcCliente> getPcCliente(PcCliente model) {
        ArrayList<PcCliente> data = new ArrayList<>();
        dbutils = new DBUtils();
        String sql = "select * from pc_cliente where id = ?";
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            pst.setString(1, model.getNombre_pc());
            rs = pst.executeQuery();
            while (rs.next()) {
                model.setId_pc(rs.getInt("Id_Pc"));
                model.setNombre_pc(rs.getString("nombre_PC"));
                data.add(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(pcClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbutils.closeConnection(cnx, pst, rs);
            } catch (SQLException ex) {
                Logger.getLogger(pcClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return data;
    }

    public boolean isNamePc(String name) {
        dbutils = new DBUtils();
        String sql = "select * from pc_cliente where nombre_Pc = ?";
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            pst.setString(1, name);
            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Esta pc ya se encuentra registrada en la base de datos", "Conección fallida", JOptionPane.WARNING_MESSAGE);
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(pcClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbutils.closeConnection(cnx, pst, rs);
            } catch (SQLException ex) {
                Logger.getLogger(pcClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return false;
    }

    //guardar las pc en base datos que se encuentren en la red
    public void savePcCliente(PcCliente model) {
        dbutils = new DBUtils();
        String sql = "insert into pc_Cliente values(?,?)";
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            pst.setInt(1, model.getId_pc());
            pst.setString(2, model.getNombre_pc());
            int fileUpdate = pst.executeUpdate();
            if (fileUpdate == 1) {
                System.out.println("Se inserto una pc cliente correctamente");
            } else {
                System.out.println("Fallo la inserccion de una pc cliente");
            }
        } catch (SQLException ex) {
            Logger.getLogger(pcClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbutils.closeConnection(cnx, pst);
            } catch (SQLException ex) {
                Logger.getLogger(pcClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public boolean isSavePcCliente() {
        dbutils = new DBUtils();
        model = new PcCliente();
        String sql = "insert into pc_Cliente values(?,?)";
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            pst.setInt(1, model.getId_pc());
            pst.setString(2, model.getNombre_pc());
            int fileUpdate = pst.executeUpdate();
            if (fileUpdate == 1) {
                System.out.println("Se inserto una pc cliente correctamente");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(pcClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbutils.closeConnection(cnx, pst);
            } catch (SQLException ex) {
                Logger.getLogger(pcClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    //generar codigo automaticamente
    public int getLastCodeFromTable(PcCliente model) {
        dbutils = new DBUtils();
        String sql = "select * from pc_cliente order by Id_Pc desc";
        try {
            cnx = dbutils.getConnection();
            pst = cnx.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                model.setId_pc(rs.getInt("Id_Pc"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(pcClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model.getId_pc() + 1;
    }
}
