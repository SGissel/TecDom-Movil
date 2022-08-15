package com.example.loginxd.model;

public class Clientes {
    String Fecha,IP,Modelo,Nombre,Telefono;
    public Clientes(){
    }

    public Clientes(String fecha, String IP, String modelo, String nombre, String telefono) {
        this.Fecha = fecha;
        this.IP = IP;
        this.Modelo = modelo;
        this.Nombre = nombre;
        this.Telefono = telefono;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
}
