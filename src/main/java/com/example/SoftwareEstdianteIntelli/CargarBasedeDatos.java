package com.example.SoftwareEstdianteIntelli;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class CargarBasedeDatos {

    @Bean
    CommandLineRunner iniciarBasedeDatos(EstudianteRepositorio repositorio){
        return args -> {
            log.info("Precargar "+repositorio.save(new Estudiante("Carlos","Cabrera","5")));
            log.info("Precargar "+repositorio.save(new Estudiante("Carlos","Cabrera","5")));

        };
    }
}
