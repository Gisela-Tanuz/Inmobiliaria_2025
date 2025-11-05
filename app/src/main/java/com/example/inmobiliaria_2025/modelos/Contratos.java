package com.example.inmobiliaria_2025.modelos;

import java.io.Serializable;
import java.util.Date;

public class Contratos implements Serializable {

    private int idContrato;
    private String fechaFinalizacion;
    private String fechaInicio;
    private double montoAlquiler;
    private boolean estado;
    private Inmuebles inmueble;
    private int idInmueble;
    private Inquilinos inquilino;
    private int idInquilino;

    public Contratos() {
    }

    public Contratos(int idContrato, String fechaFinalizacion, String fechaInicio, double montoAlquiler, boolean estado, Inmuebles inmueble, int idInmueble, Inquilinos inquilino, int idInquilino) {
        this.idContrato = idContrato;
        this.fechaFinalizacion = fechaFinalizacion;
        this.fechaInicio = fechaInicio;
        this.montoAlquiler = montoAlquiler;
        this.estado = estado;
        this.inmueble = inmueble;
        this.idInmueble = idInmueble;
        this.inquilino = inquilino;
        this.idInquilino = idInquilino;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public String getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(String fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public double getMontoAlquiler() {
        return montoAlquiler;
    }

    public void setMontoAlquiler(double montoAlquiler) {
        this.montoAlquiler = montoAlquiler;
    }

    public Inmuebles getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmuebles inmueble) {
        this.inmueble = inmueble;
    }

    public Inquilinos getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilinos inquilino) {
        this.inquilino = inquilino;
    }

    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public int getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(int idInquilino) {
        this.idInquilino = idInquilino;
    }
}
