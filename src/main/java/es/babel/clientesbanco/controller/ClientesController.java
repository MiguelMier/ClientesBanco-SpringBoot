package es.babel.clientesbanco.controller;

import es.babel.clientesbanco.fakebd.FakeBD;
import es.babel.clientesbanco.service.ClienteService;
import es.babel.clientesbanco.service.CuentaService;
import es.babel.clientesbanco.service.MovimientoService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientesController {

    private ClienteService clienteService;
    private CuentaService cuentaService;
    private MovimientoService movimientoService;
    private FakeBD fakeBD;

    public ClientesController(IOperacionesCuentas operacionesCuentas) {
        this.operacionesCuentas = operacionesCuentas;
        this.fakeBD = new FakeBD();
    }
}
