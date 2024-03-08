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
    private final FakeBD fakeBD = new FakeBD();;

    public ClientesController() {
        //this.fakeBD= new FakeBD();
        this.clienteService = new ClienteService(fakeBD);
        this.cuentaService = new CuentaService(fakeBD);
        this.movimientoService = new MovimientoService(fakeBD);

    }

    @GetMapping("/clientes")
    public List<Cliente> obtenerTodosClientes() {
        return clienteService.obtenerTodosClientes();
    }

    @PostMapping("/clientes/add")
    public Cliente crearCliente(@RequestParam("cliente") Cliente cliente) {
        return clienteService.crearCliente(cliente);
    }

    @GetMapping("/clientes/{clienteId}")
    public Cliente obtenerClientePorId(@RequestParam("cliente") String dni) {
        return clienteService.obtenerClientePorId(dni);
    }

    @GetMapping("/cuentas")
    public List<Cuenta> obtenerTodasCuentas() {
        return cuentaService.obtenerCuentas();
    }

    @GetMapping("/cuentas/{clienteId}")
    public List<Cuenta> obtenerCuentasPorCliente(@RequestParam("cliente") String dni) {
        return cuentaService.obtenerCuentasPorCliente(dni);
    }

    @PostMapping("/cuentas/add")
    public Cuenta crearCuentaBancaria(@RequestBody Cuenta cuentaBancaria) {
        return cuentaService.crearCuentaBancaria(cuentaBancaria);
    }

    @GetMapping("/movimientos/porcuenta/{cuentaId}")
    public List<Movimiento> obtenerMovimientosPorCuenta(@RequestParam("IBAN") String iban) {
        return movimientoService.obtenerMovimientosPorCuenta(iban);
    }

    @PostMapping("/movimientos/{cuentaId}")
    public void registrarMovimiento(@RequestParam("cliente") String dni, @RequestParam("movimiento") Movimiento movimiento) {
        movimientoService.registrarMovimiento(dni, movimiento.getCantidad());
    }

    @PostMapping("movimientos/ingreso")
    public void realizarIngreso(@RequestParam("cliente") String dni, @RequestParam("cantidad") double cantidad){
        cuentaService.realizarIngreso(dni, cantidad);
    }
}
