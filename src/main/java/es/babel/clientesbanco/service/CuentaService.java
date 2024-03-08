package es.babel.clientesbanco.service;

import es.babel.clientesbanco.fakebd.FakeBD;
import es.babel.clientesbanco.model.Cuenta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService implements  ICuentaService{

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
    public void realizarIngreso(String iban, double cantidad) {

    }

    @Override
    public void realizarRetirada(String iban, double cantidad) {

    }

    @Override
    public void realizarTransferencia(String ibanOrigen, String ibanDestino, double cantidad) {

    }
}
