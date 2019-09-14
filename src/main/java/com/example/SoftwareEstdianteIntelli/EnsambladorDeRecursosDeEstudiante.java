package com.example.SoftwareEstdianteIntelli;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
class EnsambladorDeRecursosDeEstudiante implements ResourceAssembler<Estudiante, Resource<Estudiante>> {
    @Override
    public Resource<Estudiante> toResource(Estudiante estudiante) {
        return new Resource<>(
                estudiante,linkTo(methodOn(EstudianteControlador.class).uno(estudiante.getId())).withSelfRel(),
                linkTo(methodOn(EstudianteControlador.class).todo()).withRel("estudiantes")
        );
    }
}
