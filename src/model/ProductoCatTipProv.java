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
public class ProductoCatTipProv {
    private int id;
    private String nombreProducto;
    private String peso;
    private double precioVenta;
    private int stock;
    private String nombreCategoria;
    private String tipoProducto;
    private String nombreProveedor;
    private String fechaVencimiento;
    private byte[] imagen;

    public ProductoCatTipProv() {
    }
    

    public ProductoCatTipProv(int id, String nombreProducto, String peso, double precioVenta, int stock, String nombreCategoria, String tipoProducto, String nombreProveedor, String fechaVencimiento, byte[] imagen) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.peso = peso;
        this.precioVenta = precioVenta;
        this.stock = stock;
        this.nombreCategoria = nombreCategoria;
        this.tipoProducto = tipoProducto;
        this.nombreProveedor = nombreProveedor;
        this.fechaVencimiento = fechaVencimiento;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public byte[] getImagen() {
        return imagen;
    }
    
    public void setImagen(byte[] imagen){
        this.imagen = imagen;
    }
    
    
}
