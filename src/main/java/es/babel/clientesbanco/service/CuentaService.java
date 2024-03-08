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
        Cliente cliente = buscarClientePorDni(dni);
        if(cliente != null){
            return cliente.getCuentas();
        }

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
        Cliente clienteEncontrado = buscarClientePorDni(dni);

        if (clienteEncontrado != null) {
            Cuenta cuentaBancaria = clienteEncontrado.getCuentas().get(0);
            realizarMovimiento(cantidad, cuentaBancaria);
        }
    }

    @Override
    public void realizarRetirada(String dni, double cantidad) {
        Cliente clienteEncontrado = buscarClientePorDni(dni);

        if (clienteEncontrado != null) {
            Cuenta cuentaBancaria = clienteEncontrado.getCuentas().get(0);
            if (cuentaBancaria.getSaldo() >= cantidad) {
                realizarMovimiento(-cantidad, cuentaBancaria);
            }
        }
    }

    @Override
    public void realizarTransferencia(String dniOrigen, String dniDestino, double cantidad) {
        Cliente clienteOrigen = buscarClientePorDni(dniOrigen);
        Cliente clienteDestino = buscarClientePorDni(dniDestino);

        if (clienteOrigen != null && clienteDestino != null) {
            Cuenta cuentaOrigen = clienteOrigen.getCuentas().get(0);
            Cuenta cuentaDestino = clienteDestino.getCuentas().get(0);

            if (cuentaOrigen.getSaldo() >= cantidad) {
                cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - cantidad);
                cuentaDestino.setSaldo(cuentaDestino.getSaldo() + cantidad);

                realizarMovimiento(-cantidad, cuentaOrigen);
                realizarMovimiento(cantidad, cuentaDestino);

                if (!sonMismaEntidadBancaria(cuentaOrigen, cuentaDestino)) {
                    aplicarInteresTransferenciaExterna(cantidad, cuentaDestino);
                }
            }
        }
    }

    private void realizarMovimiento(double cantidad, Cuenta cuenta) {
        cuenta.setSaldo(cuenta.getSaldo() + cantidad);
        fakeBD.getMovimientos().add(new Movimiento(cantidad, cuenta.getIban()));
    }

    private Cliente buscarClientePorDni(String dni) {
        for (Cliente cliente : fakeBD.getClientes()) {
            if (cliente.getDni().equals(dni)) {
                return cliente;
            }
        }
        return null;
    }

    private boolean sonMismaEntidadBancaria(Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        return cuentaOrigen.getIban().startsWith("ES") && cuentaDestino.getIban().startsWith("ES");
    }

    private void aplicarInteresTransferenciaExterna(double cantidad, Cuenta cuentaDestino) {
        double interes = 3.99;
        realizarMovimiento(-interes, cuentaDestino);
        fakeBD.getMovimientos().add(new Movimiento(-interes, cuentaDestino.getIban()));
    }

}
