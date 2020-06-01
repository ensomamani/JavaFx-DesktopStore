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
public class CategoriaProducto {
    private int idCategoria;
    private String nombre_categoria;

    public CategoriaProducto() {
    }
    
    
    public CategoriaProducto(int idCategoria, String nombre_categoria) {
        this.idCategoria = idCategoria;
        this.nombre_categoria = nombre_categoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }
    
    
    
}
