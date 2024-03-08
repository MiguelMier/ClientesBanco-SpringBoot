package es.babel.clientesbanco.controller;

import es.babel.clientesbanco.service.IOperacionesCuentas;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientesController {

    private IOperacionesCuentas operacionesCuentas;

    public ClientesController(IOperacionesCuentas operacionesCuentas) {
        this.operacionesCuentas = operacionesCuentas;
    }
}
