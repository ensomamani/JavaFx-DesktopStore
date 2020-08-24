/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceDAO;

import model.DetallePedido;

/**
 *
 * @author Enso
 */
public interface DetallePedidoDAO extends CRUD<DetallePedido>{
    
    public int getNewId(DetallePedido model);
}
