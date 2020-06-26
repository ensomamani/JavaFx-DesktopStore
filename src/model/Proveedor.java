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
public class Proveedor {
    private int id_proveedor;
    private String nombre_proveedor;
    private String telefono;
    private String celular;

    public Proveedor() {
    }

    public Proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    
    public Proveedor(int id_proveedor, String nombre_proveedor, String telefono, String celular) {
        this.id_proveedor = id_proveedor;
        this.nombre_proveedor = nombre_proveedor;
        this.telefono = telefono;
        this.celular = celular;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    
    
    
}
