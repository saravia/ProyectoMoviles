package com.example.saravia.proyectogeolocalizacion;

public class Amigos {
    private String Nombre;
    private String Num;

    public Amigos(String nombre, String num) {
        Nombre = nombre;
        Num = num;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getNum() {
        return Num;
    }

    public void setNum(String num) {
        Num = num;
    }
}
