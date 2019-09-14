package com.example.SoftwareEstdianteIntelli;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class EstudianteNoEncontradoConsejo {

    @ResponseBody
    @ExceptionHandler(ExcepcionEstudiantenoEncontrado.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String EstudianteNoEncontradoManejador(ExcepcionEstudiantenoEncontrado ex){
        return ex.getMessage();
    }
}
