/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceDAO;

/**
 *
 * @author Enso
 */
public interface ProductoDAO {
    public void updateStockProduct(int id, int cantidad);
    public int getQuantityStock(int id);
}
