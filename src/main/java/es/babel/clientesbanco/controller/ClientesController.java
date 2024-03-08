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

    @GetMapping("/cuentas")
    public List<Cuenta> obtenerTodasCuentas() {
        return cuentaService.obtenerCuentas();
    }

    @PostMapping("/clientes/add")
    public Cliente crearCliente(@RequestParam("cliente") Cliente cliente) {
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

    @PostMapping("/cuentas/add")
    public Cuenta crearCuentaBancaria(@RequestBody Cuenta cuentaBancaria) {
        return cuentaService.crearCuentaBancaria(cuentaBancaria);
    }

    @GetMapping("/movimientos/porcuenta/{cuentaId}")
    public List<Movimiento> obtenerMovimientosPorCuenta(@PathVariable String iban) {
        return movimientoService.obtenerMovimientosPorCuenta(iban);
    }

    @PostMapping("/movimientos/{cuentaId}")
    public void registrarMovimiento(@PathVariable String dni, @RequestBody Movimiento movimiento) {
        movimientoService.registrarMovimiento(dni, movimiento.getCantidad());
    }

    @PostMapping("movimientos/ingreso")
    public void realizarIngreso(@PathVariable String dni, @PathVariable double cantidad){
        cuentaService.realizarIngreso(dni, cantidad);
    }
}
