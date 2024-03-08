package es.babel.clientesbanco.controller;

import es.babel.clientesbanco.fakebd.FakeBD;
import es.babel.clientesbanco.model.Cliente;
import es.babel.clientesbanco.model.Cuenta;
import es.babel.clientesbanco.model.Movimiento;
import es.babel.clientesbanco.service.ClienteService;
import es.babel.clientesbanco.service.CuentaService;
import es.babel.clientesbanco.service.MovimientoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientesController {


    private ClienteService clienteService;
    private CuentaService cuentaService;
    private MovimientoService movimientoService;
    private FakeBD fakeBD;

    public ClientesController() {
        this.fakeBD = new FakeBD();
        this.clienteService = new ClienteService(fakeBD);
        this.cuentaService = new CuentaService(fakeBD);
        this.movimientoService = new MovimientoService(fakeBD);

    }

    @GetMapping("/clientes")
    public List<Cliente> obtenerTodosClientes() {
        return clienteService.obtenerTodosClientes();
    }

    @PostMapping("/clientes")
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.crearCliente(cliente);
    }

    @GetMapping("/clientes/{clienteId}")
    public Cliente obtenerClientePorId(@PathVariable String dni) {
        return clienteService.obtenerClientePorId(dni);
    }

    @GetMapping("/cuentas/{clienteId}")
    public List<Cuenta> obtenerCuentasPorCliente(@PathVariable String dni) {
        return cuentaService.obtenerCuentasPorCliente(dni);
    }

    @PostMapping("/cuentas")
    public Cuenta crearCuentaBancaria(@RequestBody Cuenta cuentaBancaria) {
        return cuentaService.crearCuentaBancaria(cuentaBancaria);
    }

    @GetMapping("/movimientos/{cuentaId}")
    public List<Movimiento> obtenerMovimientosPorCuenta(@PathVariable Long cuentaId) {
        return movimientoService.obtenerMovimientosPorCuenta(cuentaId);
    }

    @PostMapping("/movimientos/{cuentaId}")
    public void registrarMovimiento(@PathVariable String dni, @RequestBody Movimiento movimiento) {
        movimientoService.registrarMovimiento(dni, movimiento.getCantidad());
    }
}
