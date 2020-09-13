/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceDAO;

import java.util.List;
import model.Pedido;
import DTO.PedidoDTO;

/**
 *
 * @author Enso
 */
public interface PedidoDAO extends CRUD<Pedido>{
    
    public int getNewId();
    public List<PedidoDTO> searchPedidosForId(int id);
    public List<PedidoDTO> searchPedidosStateAndNamePc();
}
