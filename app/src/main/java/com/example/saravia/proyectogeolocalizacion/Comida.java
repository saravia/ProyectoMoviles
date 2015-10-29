package com.example.saravia.proyectogeolocalizacion;

public class Comida {
    private String Nombre;
    private String Fecha;


    public Comida(String nombre, String ingredientep) {
        Nombre = nombre;
        Fecha = ingredientep;

    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getIngrediente() {
        return Fecha;
    }

    public void setIngrediente(String ingrediente) {
        Fecha = ingrediente;
    }


}