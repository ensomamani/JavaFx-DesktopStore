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
public class IngresoProducto {
    private int id_ingreso;
    private String fecha_ingreso;
    private String hora_ingreso;
    private int stock_ingreso;
    private String estado;
    private int id_usuario;
    private int id_producto;

    public IngresoProducto() {
    }

    
    public IngresoProducto(int id_ingreso, String fecha_ingreso, String hora_ingreso, int stock_ingreso, String estado, int id_usuario, int id_producto) {
        this.id_ingreso = id_ingreso;
        this.fecha_ingreso = fecha_ingreso;
        this.hora_ingreso = hora_ingreso;
        this.stock_ingreso = stock_ingreso;
        this.estado = estado;
        this.id_usuario = id_usuario;
        this.id_producto = id_producto;
    }

    public int getId_ingreso() {
        return id_ingreso;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public String getHora_ingreso() {
        return hora_ingreso;
    }

    public int getStock_ingreso() {
        return stock_ingreso;
    }

    public String getEstado() {
        return estado;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_ingreso(int id_ingreso) {
        this.id_ingreso = id_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public void setHora_ingreso(String hora_ingreso) {
        this.hora_ingreso = hora_ingreso;
    }

    public void setStock_ingreso(int stock_ingreso) {
        this.stock_ingreso = stock_ingreso;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
    
    
}
