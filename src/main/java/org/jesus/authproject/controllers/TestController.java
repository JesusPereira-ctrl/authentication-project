package org.jesus.authproject.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/private")
    public String rutaPrivada() {
        return "ruta privada - esta ruta solo la puede ver el ADMIN";
    }

    @GetMapping("/public")
    public String rutaPublica() {
        return "ruta publica - esta ruta solo la pueden ver los usuarios con role USER";
    }
}
