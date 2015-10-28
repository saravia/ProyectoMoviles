package com.example.saravia.proyectogeolocalizacion;

public class Evento {
    private String Nombre;
    private String Lugar;
    private String Fecha;
    private String Hora;
    private String Descripcion;
    public Evento(String Nombre, String Lugar, String Fecha, String Hora, String Descripcion){
        this.Nombre=Nombre;
        this.Lugar=Lugar;
        this.Fecha=Fecha;
        this.Hora=Hora;
        this.Descripcion=Descripcion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String Lugar) {
        this.Lugar = Lugar;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

}
