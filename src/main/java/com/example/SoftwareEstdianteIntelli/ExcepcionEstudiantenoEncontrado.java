package com.example.SoftwareEstdianteIntelli;

class ExcepcionEstudiantenoEncontrado extends  RuntimeException{

    ExcepcionEstudiantenoEncontrado(Long id){
        super("No se logro encontrar el estudiante "+id);
    }
}
