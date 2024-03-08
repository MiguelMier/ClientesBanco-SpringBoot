package es.babel.clientesbanco.service;

import es.babel.clientesbanco.fakebd.FakeBD;
import es.babel.clientesbanco.model.Movimiento;
import es.babel.clientesbanco.service.interfaces.IMovimientoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoService implements IMovimientoService {

    private FakeBD fakeBD;

    public MovimientoService(FakeBD fakeBD){
        this.fakeBD = fakeBD;
    }

    @Override
    public List<Movimiento> obtenerMovimientosPorCuenta(Long cuentaId) {
        return null;
    }

    @Override
    public void registrarMovimiento(String iban, double cantidad) {

    }
}
