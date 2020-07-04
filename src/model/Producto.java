/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.image.Image;
import javax.swing.JOptionPane;

/**
 *
 * @author Enso
 */
public class Producto {
    private int id_Producto;
    private String nombre_Producto;
    private String peso;
    private double precio_venta;
    private int stock;
    private String fecha_vencimieto;
    private byte[] imagen;
    private String estado;
    private int id_categoria;
    private int id_tipo;
    private int id_proveedor;

    
    //CONSTRUCTORES
    
     public Producto() {
    }

    public Producto(int id_Producto, String nombre_Producto, int stock) {
        this.id_Producto = id_Producto;
        this.nombre_Producto = nombre_Producto;
        this.stock = stock;
    }
        
    public Producto(int id_Producto, String nombre_Producto, String peso, double precio_venta, int stock, String fecha_vencimieto, byte[] imagen, int id_categoria, int id_tipo, int id_proveedor) {
        this.id_Producto = id_Producto;
        this.nombre_Producto = nombre_Producto;
        this.peso = peso;
        this.precio_venta = precio_venta;
        this.stock = stock;
        this.fecha_vencimieto = fecha_vencimieto;
        this.imagen = imagen;
        this.id_categoria = id_categoria;
        this.id_tipo = id_tipo;
        this.id_proveedor = id_proveedor;
    }
       
    public Producto(int id_Producto, String nombre_Producto, String peso, double precio_venta, int stock, String fecha_vencimieto, byte[] imagen, String estado, int id_categoria, int id_tipo, int id_proveedor) {
        this.id_Producto = id_Producto;
        this.nombre_Producto = nombre_Producto;
        this.peso = peso;
        this.precio_venta = precio_venta;
        this.stock = stock;
        this.fecha_vencimieto = fecha_vencimieto;
        this.imagen = imagen;
        this.estado = estado;
        this.id_categoria = id_categoria;
        this.id_tipo = id_tipo;
        this.id_proveedor = id_proveedor;
    }
    
    //getter

    public int getId_Producto() {
        return id_Producto;
    }

    public String getNombre_Producto() {
        return nombre_Producto;
    }

    public String getPeso() {
        return peso;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public int getStock() {
        return stock;
    }

    public String getFecha_vencimieto() {
        return fecha_vencimieto;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public String getEstado() {
        return estado;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }
    
    //setter

    public void setId_Producto(int id_Producto) {
        this.id_Producto = id_Producto;
    }

    public void setNombre_Producto(String nombre_Producto) {
        this.nombre_Producto = nombre_Producto;
    }

    public void setPeso(String peso) {
        this.peso = peso;

    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public void setStock(int stock) {
        this.stock = stock; 
    }

    public void setFecha_vencimieto(String fecha_vencimieto) {
        this.fecha_vencimieto = fecha_vencimieto;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }
    
}
