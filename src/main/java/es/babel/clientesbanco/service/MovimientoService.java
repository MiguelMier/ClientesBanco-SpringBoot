package es.babel.clientesbanco.service;

import es.babel.clientesbanco.fakebd.FakeBD;
import es.babel.clientesbanco.model.Cuenta;
import es.babel.clientesbanco.model.Movimiento;
import es.babel.clientesbanco.service.interfaces.IMovimientoService;
import es.babel.clientesbanco.utils.LogUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoService implements IMovimientoService {

    private FakeBD fakeBD;

    public MovimientoService(FakeBD fakeBD){
        this.fakeBD = fakeBD;
    }

    public List<Movimiento> obtenerMovimientosPorCuenta(String iban) {
        Cuenta cuentaBancaria = null;

        for (Cuenta cuenta : fakeBD.getCuentas()) {
            if (cuenta.getIban().equals(iban)) {
                cuentaBancaria = cuenta;
                LogUtils.logInfo(" --> Movimientos obtenidos de la cuenta: " + iban);
                break;
            }
        }

        if (cuentaBancaria == null) {
            LogUtils.logEror(" ERROR: cuenta no encontrada");
            throw new RuntimeException("Cuenta no encontrada por IBAN: " + iban);
        }

        return cuentaBancaria.getMovimientos();
    }

    public void registrarMovimiento(String iban, double cantidad) {
        Cuenta cuentaBancaria = null;

        for (Cuenta cuenta : fakeBD.getCuentas()) {
            if (cuenta.getIban().equals(iban)) {
                cuentaBancaria = cuenta;
                LogUtils.logInfo(" --> Movimientos registrados de la cuenta: " + iban);
                break;
            }
        }

        if (cuentaBancaria == null) {
            LogUtils.logEror(" ERROR: cuenta no encontrada");
            throw new RuntimeException("Cuenta no encontrada por IBAN: " + iban);
        }

        Movimiento movimiento = new Movimiento(cantidad);
        cuentaBancaria.agregarMovimiento(movimiento);
        fakeBD.getMovimientos().add(movimiento);
    }

    public List<Movimiento> obtenerMovimientos(){
        LogUtils.logInfo(" --> Movimientos obtenidos");
        return fakeBD.getMovimientos();
    }
}
