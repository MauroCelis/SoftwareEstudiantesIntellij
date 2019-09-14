package com.example.SoftwareEstdianteIntelli;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Estudiante {
    private @Id @GeneratedValue Long id;
    private String nombre;
    private  String apellido;
    private  String nota;

    private Estudiante()
    {

    }

    public Estudiante(String nombre, String apellido, String nota) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nota = nota;
    }
}

