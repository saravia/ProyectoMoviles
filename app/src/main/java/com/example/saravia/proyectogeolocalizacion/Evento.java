package com.example.saravia.proyectogeolocalizacion;

public class Evento {
    private String Nombre;
    private String IngredienteyTimecop;


    public Evento(String nombre, String ingredientep) {
        Nombre = nombre;
        IngredienteyTimecop = ingredientep;

    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getIngrediente() {
        return IngredienteyTimecop;
    }

    public void setIngrediente(String ingrediente) {
        IngredienteyTimecop = ingrediente;
    }


}