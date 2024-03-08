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
    public Cuenta crearCuentaBancaria(Cuenta cuentaBancaria) {
        fakeBD.getCuentas().add(cuentaBancaria);
        return cuentaBancaria;
    }

    @Override
    public void realizarIngreso(String dni, double cantidad) {
        Optional<Cliente> clienteOptional = fakeBD.getClientes().stream()
                .filter(cliente -> cliente.getDni().equals(dni))
                .findFirst();

        clienteOptional.ifPresent(cliente -> {
            Cuenta cuentaBancaria = cliente.getCuentas().get(0); // Obtén la cuenta asociada al cliente
            cuentaBancaria.setSaldo(cuentaBancaria.getSaldo() + cantidad);
            fakeBD.getMovimientos().add(new Movimiento(cantidad,cuentaBancaria.getIban()));
        });
    }

    @Override
    public void realizarRetirada(String dni, double cantidad) {
        Optional<Cliente> clienteOptional = fakeBD.getClientes().stream()
                .filter(cliente -> cliente.getDni().equals(dni))
                .findFirst();

        clienteOptional.ifPresent(cliente -> {
            Cuenta cuentaBancaria = cliente.getCuentas().get(0); // Obtén la cuenta asociada al cliente

            if (cuentaBancaria.getSaldo() >= cantidad) {
                cuentaBancaria.setSaldo(cuentaBancaria.getSaldo() - cantidad);
                fakeBD.getMovimientos().add(new Movimiento(-cantidad,cuentaBancaria.getIban()));
            }
        });
    }

    @Override
    public void realizarTransferencia(String dniOrigen, String dniDestino, double cantidad) {
        Optional<Cliente> clienteOrigenOptional = fakeBD.getClientes().stream()
                .filter(cliente -> cliente.getDni().equals(dniOrigen))
                .findFirst();
        Optional<Cliente> clienteDestinoOptional = fakeBD.getClientes().stream()
                .filter(cliente -> cliente.getDni().equals(dniDestino))
                .findFirst();

        if (clienteOrigenOptional.isPresent() && clienteDestinoOptional.isPresent()) {
            Cuenta cuentaOrigen = clienteOrigenOptional.get().getCuentas().get(0);
            Cuenta cuentaDestino = clienteDestinoOptional.get().getCuentas().get(0);

            if (cuentaOrigen.getSaldo() >= cantidad) {
                cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - cantidad);
                cuentaDestino.setSaldo(cuentaDestino.getSaldo() + cantidad);

                fakeBD.getMovimientos().add(new Movimiento(-cantidad, cuentaOrigen.getIban()));
                fakeBD.getMovimientos().add(new Movimiento(cantidad, cuentaDestino.getIban()));
            }
        }
    }
}
