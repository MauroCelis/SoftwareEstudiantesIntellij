package com.example.SoftwareEstdianteIntelli;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
class Estudiante {
    private @Id @GeneratedValue Long id;
    private String nombre;
    private  String apellido;
    private  String nota;

    Estudiante()
    {

    }

    Estudiante(String nombre, String apellido, String nota) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nota = nota;
    }

    public String getNombre() {
        return this.nombre+" "+this.apellido;
    }

    public  void setNombre(){
        String[] partes=nombre.split(" ");
        this.nombre=partes[0];
        this.apellido=partes[1];
    }
}

