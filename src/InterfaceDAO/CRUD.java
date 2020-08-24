/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceDAO;

import java.util.List;

/**
 *
 * @author Enso
 */
public interface CRUD<T> {
    List<T> listAll();
    T searchId(int id);
    void register(T model);
    void update(T model);
    void delete(int id);        
}
