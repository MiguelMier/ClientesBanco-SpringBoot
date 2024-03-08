package es.babel.clientesbanco.service;

import es.babel.clientesbanco.model.Cuenta;

import java.util.List;

public interface ICuentaService {

    List<Cuenta> obtenerCuentasPorCliente(String dni);
    Cuenta crearCuentaBancaria(Cuenta cuentaBancaria);
    void realizarIngreso(String iban, double cantidad);
    void realizarRetirada(String iban, double cantidad);
    void realizarTransferencia(String ibanOrigen, String ibanDestino, double cantidad);
}
