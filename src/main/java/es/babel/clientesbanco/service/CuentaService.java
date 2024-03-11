package es.babel.clientesbanco.service;

import es.babel.clientesbanco.fakebd.FakeBD;
import es.babel.clientesbanco.model.Cliente;
import es.babel.clientesbanco.model.Cuenta;
import es.babel.clientesbanco.model.Movimiento;
import es.babel.clientesbanco.service.interfaces.ICuentaService;
import es.babel.clientesbanco.utils.LogUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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
            LogUtils.logInfo(" --> Cuentas obtenidas del cliente: " + dni);
            return cliente.getCuentas();
        }
        LogUtils.logEror("ERROR: Cuentas no encontradas");
        return null;
    }

    @Override
    public List<Cuenta> obtenerCuentas() {
        LogUtils.logInfo(" --> Cuentas obtenidas");
        return fakeBD.getCuentas();
    }

    @Override
    public Cuenta crearCuentaBancaria(Cuenta cuentaBancaria) {
        fakeBD.getCuentas().add(cuentaBancaria);
        LogUtils.logInfo(" --> Cuenta creada correctamente");
        return cuentaBancaria;
    }

    @Override
    public void realizarIngreso(String dni, double cantidad) {
        Cliente clienteEncontrado = buscarClientePorDni(dni);

        if (clienteEncontrado != null) {
            Cuenta cuentaBancaria = clienteEncontrado.getCuentas().get(0);
            realizarMovimiento(cantidad, cuentaBancaria);
            LogUtils.logInfo(" --> Ingreso realizado correctamente en la cuenta de: "+ dni
                    + " con cantidad: " + cantidad);
        }
    }

    @Override
    public void realizarRetirada(String dni, double cantidad) {
        Cliente clienteEncontrado = buscarClientePorDni(dni);

        if (clienteEncontrado != null) {
            Cuenta cuentaBancaria = clienteEncontrado.getCuentas().get(0);
            if (cuentaBancaria.getSaldo() >= cantidad) {
                realizarMovimiento(-cantidad, cuentaBancaria);
                LogUtils.logInfo(" --> Retirada realizada correctamente en la cuenta de: "+ dni
                        + " con cantidad: " + cantidad);
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

                LogUtils.logInfo(" --> Transferencia realizada correctamente desde la cuenta de: "+ dniOrigen
                        + " a la cuenta de: " + dniDestino
                        + " con cantidad: " + cantidad);

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
        LogUtils.logInfo(" --> Intereses aplicados correctamente (3.99€)");
    }

}
