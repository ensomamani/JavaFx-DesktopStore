/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.image.Image;

/**
 *
 * @author Enso
 */
public class PedidoDetPedProd {
    private Image imagen;
    private String nombre_Producto;
    private int cantidadOrden;
    private double precioProducto;
    private double subtotalPedido;

    public PedidoDetPedProd() {
    }
    

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public String getNombre_Producto() {
        return nombre_Producto;
    }

    public void setNombre_Producto(String nombre_Producto) {
        this.nombre_Producto = nombre_Producto;
    }

    public int getCantidadOrden() {
        return cantidadOrden;
    }

    public void setCantidadOrden(int cantidadOrden) {
        this.cantidadOrden = cantidadOrden;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public double getSubtotalPedido() {
        return subtotalPedido;
    }

    public void setSubtotalPedido(double subtotalPedido) {
        this.subtotalPedido = subtotalPedido;
    }
    
    
}
