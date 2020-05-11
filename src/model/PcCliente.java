/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Enso
 */
public class PcCliente {
    private int id_pc;
    private  String nombre_pc;
    
    //Constructor

    public PcCliente() {
    }

    public PcCliente(String nombre_pc) {
        this.nombre_pc = nombre_pc;
    }
    
  
    
    public PcCliente(int id_pc, String nombre_pc) {
        this.id_pc = id_pc;
        this.nombre_pc = nombre_pc;
    }
    
    //getter
    public int getId_pc() {
        return id_pc;
    }

    public String getNombre_pc() {
        return nombre_pc;
    }
    
    //setter
    public void setId_pc(int id_pc) {
        this.id_pc = id_pc;
    }

    public void setNombre_pc(String nombre_pc) {
        this.nombre_pc = nombre_pc;
    }  
}
