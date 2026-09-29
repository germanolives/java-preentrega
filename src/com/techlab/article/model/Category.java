package com.techlab.article.model;

public class Category {
    private String code;
    private String name;
    private String description;

    public Category (String code, String name, String description) {
        this.code = validateString (code, "Código");
        this.name = validateString (name, "Nombre");
        this.description = validateString (description, "Descripción");
    }

    private String validateString (String code, String message) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("El valor de '" + message + "' del producto no puede estar vacío");
        }
        return code;
    }

    public void setCode (String code) {
        this.code = validateString (code, "Código");
    }

    public void setName (String name) {
        this.name = validateString (name, "Nombre");
    }

    public void setDescription (String description) {
        this.description = validateString (description, "Descripción");
    }

    public String getCode () {
        return this.code;
    }

    public String getName () {
        return this.name;
    }

    public String getDescription () {
        return this.description;
    }

    @Override
    public String toString () {
        return "Código: " + this.code +
                " | Nombre: " + this.name +
                " | Descripción: " + this.description;
    }

}
