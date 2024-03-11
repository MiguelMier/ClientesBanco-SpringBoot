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
@RequestMapping("/clientes")
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

    @GetMapping
    public List<Cliente> obtenerTodosClientes() {
        return clienteService.obtenerTodosClientes();
    }

    @PostMapping("/add")
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.crearCliente(cliente);
    }

    @GetMapping("/{clienteId}")
    public Cliente obtenerClientePorId(@RequestBody String dni) {
        return clienteService.obtenerClientePorId(dni);
    }


}
