package es.babel.clientesbanco.controller;

import es.babel.clientesbanco.fakebd.FakeBD;
import es.babel.clientesbanco.model.Cuenta;
import es.babel.clientesbanco.service.ClienteService;
import es.babel.clientesbanco.service.CuentaService;
import es.babel.clientesbanco.service.MovimientoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentasController {

    private CuentaService cuentaService;
    private final FakeBD fakeBD = new FakeBD();

    public CuentasController() {
        this.cuentaService = new CuentaService(fakeBD);
    }

    @GetMapping
    public List<Cuenta> obtenerTodasCuentas() {
        return cuentaService.obtenerCuentas();
    }

    @GetMapping("/{clienteId}")
    public List<Cuenta> obtenerCuentasPorCliente(@RequestBody String dni) {
        return cuentaService.obtenerCuentasPorCliente(dni);
    }

    @PostMapping("/add")
    public Cuenta crearCuentaBancaria(@RequestBody Cuenta cuentaBancaria) {
        return cuentaService.crearCuentaBancaria(cuentaBancaria);
    }

    @PostMapping("/ingreso")
    public void realizarIngreso(@RequestBody String dni, @RequestBody double cantidad){
        cuentaService.realizarIngreso(dni, cantidad);
    }
}
