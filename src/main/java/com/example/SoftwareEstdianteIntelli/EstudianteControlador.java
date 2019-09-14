package com.example.SoftwareEstdianteIntelli;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class EstudianteControlador  {
    private final EstudianteRepositorio repositorio;

    public EstudianteControlador(EstudianteRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/estudiantes")
    List<Estudiante> todo(){
        return  repositorio.findAll();
    }

    @PostMapping("/estudiantes")
    Estudiante nuevoEstudiante(@RequestBody Estudiante estudiante){
        return repositorio.save(estudiante);
    }

    @GetMapping("/estudiante/{id}")
    Estudiante uno(@PathVariable Long id){
        return repositorio.findById(id).orElseThrow(()-> new  ExcepcionEstudiantenoEncontrado(id));
    }

    @PutMapping("/estudiante/{id}")
    Estudiante reemplazarEstudiante(@RequestBody Estudiante nuevoEstudiante, @PathVariable Long id)
    {
        return repositorio.findById(id).map(estudiante -> {
            estudiante.setNombre(nuevoEstudiante.getNombre());
            estudiante.setApellido(nuevoEstudiante.getApellido());
            estudiante.setNota(nuevoEstudiante.getNota());
            return repositorio.save(estudiante);
        }).orElseGet(()->{
            nuevoEstudiante.setId(id);
            return repositorio.save(nuevoEstudiante);
        });
    }

    @DeleteMapping("/estudiantes/{id}")
    void borrarEstudiante(@PathVariable Long id){
        repositorio.deleteById(id);
    }
}
