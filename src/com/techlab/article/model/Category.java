package com.techlab.article.model;

public class Category {
    private String code;
    private String name;
    private String description;

    public Category (String code, String name, String description) {
        setCode (code);
        setName (name);
        setDescription (description);
    }

    public final void setCode (String code) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("El código no puede estar vacío");
        }
        this.code = code;
    }

    public final void setName (String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.name = name;
    }

    public final void setDescription (String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }
        this.description = description;
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
