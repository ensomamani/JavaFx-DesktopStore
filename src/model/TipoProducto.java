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
public class TipoProducto {
    private int id_tipo;
    private String nombre_tipo;

    public TipoProducto() {
    }

    public TipoProducto(int id_tipo, String nombre_tipo) {
        this.id_tipo = id_tipo;
        this.nombre_tipo = nombre_tipo;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public String getNombre_tipo() {
        return nombre_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public void setNombre_tipo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }
    
    
}
