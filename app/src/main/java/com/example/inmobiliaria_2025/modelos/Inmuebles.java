package com.example.inmobiliaria_2025.modelos;

import java.io.Serializable;

public class Inmuebles implements Serializable{

   private  int idInmueble;
    private  String  direccion;
    private  String  uso;
    private  String tipo;
    private  int ambientes;
    private Double superficie;
    private Double latitud;
    private String valor;
    private String imagen;
    private boolean disponible;
    private String longitud;
    private int idPropietario;
    private Propietarios duenio;



    public Inmuebles() {
    }

    public Inmuebles(int idInmueble, String direccion, String uso, int ambientes, String tipo, Double superficie, Double latitud, String imagen, String valor, boolean disponible, int idPropietario, String longitud, Propietarios duenio) {
        this.idInmueble = idInmueble;
        this.direccion = direccion;
        this.uso = uso;
        this.ambientes = ambientes;
        this.tipo = tipo;
        this.superficie = superficie;
        this.latitud = latitud;
        this.imagen = imagen;
        this.valor = valor;
        this.disponible = disponible;
        this.idPropietario = idPropietario;
        this.longitud = longitud;
        this.duenio = duenio;

    }

    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Double superficie) {
        this.superficie = superficie;
    }

    public int getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public Propietarios getDuenio() {
        return duenio;
    }

    public void setDuenio(Propietarios duenio) {
        this.duenio = duenio;
    }


}
