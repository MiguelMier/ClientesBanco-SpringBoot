package es.babel.clientesbanco.service.interfaces;

import es.babel.clientesbanco.model.Movimiento;

import java.util.List;

public interface IMovimientoService {

    List<Movimiento> obtenerMovimientosPorCuenta(Long cuentaId);
    void registrarMovimiento(String iban, double cantidad);
}
