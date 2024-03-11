package es.babel.clientesbanco.controller;

import es.babel.clientesbanco.fakebd.FakeBD;
import es.babel.clientesbanco.model.Movimiento;
import es.babel.clientesbanco.service.ClienteService;
import es.babel.clientesbanco.service.CuentaService;
import es.babel.clientesbanco.service.MovimientoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientosController {

    private MovimientoService movimientoService;
    private final FakeBD fakeBD = new FakeBD();

    public MovimientosController() {
        this.movimientoService = new MovimientoService(fakeBD);

    }

    @GetMapping("/porcuenta/{cuentaId}")
    public List<Movimiento> obtenerMovimientosPorCuenta(@RequestBody String iban) {
        return movimientoService.obtenerMovimientosPorCuenta(iban);
    }

    @PostMapping("/registrar/{cuentaId}")
    public void registrarMovimiento(@RequestBody String dni, @RequestBody Movimiento movimiento) {
        movimientoService.registrarMovimiento(dni, movimiento.getCantidad());
    }


}
