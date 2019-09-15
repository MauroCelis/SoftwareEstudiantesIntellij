package com.example.SoftwareEstdianteIntelli;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
class EstudianteControlador  {

    private final EstudianteRepositorio repositorio;

    private final  EnsambladorDeRecursosDeEstudiante ensamblador;

    public EstudianteControlador(EstudianteRepositorio repositorio, EnsambladorDeRecursosDeEstudiante ensamblador) {
        this.repositorio = repositorio;
        this.ensamblador = ensamblador;
    }

/*
    @GetMapping("/estudiantes")
    List<Estudiante> todo(){
        return  repositorio.findAll();
    }
*/
/*    @GetMapping("/estudiantes")
    Resources<Resource<Estudiante>> todo(){
        List<Resource<Estudiante>> estudiantes=repositorio.findAll().stream()
                .map(estudiante ->new Resource<>(estudiante,
                        linkTo(methodOn(EstudianteControlador.class).uno(estudiante.getId())).withSelfRel(),
                        linkTo(methodOn(EstudianteControlador.class).todo()).withRel("estudiantes")))
                .collect(Collectors.toList());
        return  new Resources<>(estudiantes,linkTo(methodOn(EstudianteControlador.class)).withSelfRel());
    }
*/
    @GetMapping("/estudiantes")
    Resources<Resource<Estudiante>> todo(){

        List<Resource<Estudiante>> estudiante=repositorio.findAll().stream()
                .map(ensamblador::toResource).collect(Collectors.toList());
        return new Resources<>(
                estudiante,linkTo(methodOn(EstudianteControlador.class).todo()).withSelfRel());


    }
/*
    @PostMapping("/estudiantes")
    Estudiante nuevoEstudiante(@RequestBody Estudiante estudiante){
        return repositorio.save(estudiante);
    }*/

    @PutMapping("/estudiantes")
    ResponseEntity<?> nuevoEstudiante(@RequestBody Estudiante nuevoEstudiante) throws URISyntaxException{

        Resource<Estudiante> recurso=ensamblador.toResource(repositorio.save(nuevoEstudiante));
        return ResponseEntity.created(new URI(recurso.getId().expand().getHref())).body(recurso);
    }
/*
    @GetMapping("/estudiante/{id}")
    Estudiante uno(@PathVariable Long id){
        return repositorio.findById(id).orElseThrow(()-> new  ExcepcionEstudiantenoEncontrado(id));
    }
*/
/*    @GetMapping("/estudiante/{id}")
    Resource<Estudiante> uno(@PathVariable Long id){
        Estudiante estudiante=repositorio.findById(id)
                .orElseThrow(()-> new ExcepcionEstudiantenoEncontrado(id));
        return new Resource<>(
                estudiante,linkTo(methodOn(EstudianteControlador.class).uno(id)).withSelfRel(),
                linkTo(methodOn(EstudianteControlador.class).todo()).withRel("estudiantes")
        );
    }
    */

    @GetMapping("/estudiante/{id}")
    Resource<Estudiante> uno(@PathVariable Long id){

        Estudiante estudiante=repositorio.findById(id)
                .orElseThrow(()->new ExcepcionEstudiantenoEncontrado(id));
        return ensamblador.toResource(estudiante);
    }




/*
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

        Resource<Estudiante> recurso=ensamblador.toResource()
    }*/
    @PutMapping("/estudiante/{id}")
    ResponseEntity<?> reemplazarEstudiante(@RequestBody Estudiante nuevoEstudiante, @PathVariable Long id) throws URISyntaxException {
        Estudiante estudianterCambiado=repositorio.findById(id)
                .map(estudiante -> {
                    estudiante.setNombre(nuevoEstudiante.getNombre());
                    estudiante.setApellido(nuevoEstudiante.getApellido());
                    estudiante.setNota(nuevoEstudiante.getNota());
                    return repositorio.save(estudiante);
                }).orElseGet(()->{
            nuevoEstudiante.setId(id);
            return repositorio.save(nuevoEstudiante);
        });

        Resource<Estudiante> recurso=ensamblador.toResource(estudianterCambiado);

        return ResponseEntity.created(new URI(recurso.getId().expand().getHref())).body(recurso);
    }

    /*
    @DeleteMapping("/estudiantes/{id}")
    void borrarEstudiante(@PathVariable Long id){
        repositorio.deleteById(id);
    }
    */
    @DeleteMapping("estudiantes/{id}")
    ResponseEntity<?> borrarEstudiante(@PathVariable Long id){
        repositorio.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
