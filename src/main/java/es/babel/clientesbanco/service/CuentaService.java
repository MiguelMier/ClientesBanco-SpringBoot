package es.babel.clientesbanco.service;

import es.babel.clientesbanco.fakebd.FakeBD;
import es.babel.clientesbanco.model.Cliente;
import es.babel.clientesbanco.model.Cuenta;
import es.babel.clientesbanco.model.Movimiento;
import es.babel.clientesbanco.service.interfaces.ICuentaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService implements ICuentaService {

    private FakeBD fakeBD;

    public CuentaService(FakeBD fakeBD){
        this.fakeBD = fakeBD;
    }
    @Override
    public List<Cuenta> obtenerCuentasPorCliente(String dni) {
        return null;
    }

    @Override
    public List<Cuenta> obtenerCuentas() {

        return fakeBD.getCuentas();
    }

    @Override
    public Cuenta crearCuentaBancaria(Cuenta cuentaBancaria) {
        fakeBD.getCuentas().add(cuentaBancaria);
        return cuentaBancaria;
    }

    @Override
    public void realizarIngreso(String dni, double cantidad) {
        Cliente clienteEncontrado = null;

        for (Cliente cliente : fakeBD.getClientes()) {
            if (cliente.getDni().equals(dni)) {
                clienteEncontrado = cliente;
                break;
            }
        }

        if (clienteEncontrado != null) {
            Cuenta cuentaBancaria = clienteEncontrado.getCuentas().get(0);
            cuentaBancaria.setSaldo(cuentaBancaria.getSaldo() + cantidad);
            fakeBD.getMovimientos().add(new Movimiento(cantidad, cuentaBancaria.getIban()));
        }
    }

    @Override
    public void realizarRetirada(String dni, double cantidad) {
        Cliente clienteEncontrado = null;

        for (Cliente cliente : fakeBD.getClientes()) {
            if (cliente.getDni().equals(dni)) {
                clienteEncontrado = cliente;
                break;
            }
        }

        if (clienteEncontrado != null) {
            Cuenta cuentaBancaria = clienteEncontrado.getCuentas().get(0);

            if (cuentaBancaria.getSaldo() >= cantidad) {
                cuentaBancaria.setSaldo(cuentaBancaria.getSaldo() - cantidad);
                fakeBD.getMovimientos().add(new Movimiento(-cantidad, cuentaBancaria.getIban()));
            }
        }
    }

    @Override
    public void realizarTransferencia(String dniOrigen, String dniDestino, double cantidad) {
        Cliente clienteOrigenEncontrado = null;
        Cliente clienteDestinoEncontrado = null;

        for (Cliente cliente : fakeBD.getClientes()) {
            if (cliente.getDni().equals(dniOrigen)) {
                clienteOrigenEncontrado = cliente;
            } else if (cliente.getDni().equals(dniDestino)) {
                clienteDestinoEncontrado = cliente;
            }

            if (clienteOrigenEncontrado != null && clienteDestinoEncontrado != null) {
                break;
            }
        }

        if (clienteOrigenEncontrado != null && clienteDestinoEncontrado != null) {
            Cuenta cuentaOrigen = clienteOrigenEncontrado.getCuentas().get(0);
            Cuenta cuentaDestino = clienteDestinoEncontrado.getCuentas().get(0);

            if (cuentaOrigen.getSaldo() >= cantidad) {
                cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - cantidad);
                cuentaDestino.setSaldo(cuentaDestino.getSaldo() + cantidad);

                fakeBD.getMovimientos().add(new Movimiento(-cantidad, cuentaOrigen.getIban()));
                fakeBD.getMovimientos().add(new Movimiento(cantidad, cuentaDestino.getIban()));
            }
        }
    }
}
