package es.babel.clientesbanco.fakebd;

import es.babel.clientesbanco.model.Cliente;
import es.babel.clientesbanco.model.Cuenta;
import es.babel.clientesbanco.model.Movimiento;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FakeBD {
    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Cuenta> cuentas = new ArrayList<>();
    private final List<Movimiento> movimientos = new ArrayList<>();

    public FakeBD() {
        // Crear 5 clientes
        Cliente cliente1 = new Cliente("Juan", "12345678A");
        Cliente cliente2 = new Cliente("Maria", "87654321B");
        Cliente cliente3 = new Cliente("Carlos", "98765432C");
        Cliente cliente4 = new Cliente("Laura", "56789012D");
        Cliente cliente5 = new Cliente("Pedro", "34567890E");

        // Crear 5 cuentas
        Cuenta cuenta1 = new Cuenta("ES12345678901234567890", 1000.0);
        Cuenta cuenta2 = new Cuenta("ES98765432109876543210", 500.0);
        Cuenta cuenta3 = new Cuenta("ES11112222333344445555", 1500.0);
        Cuenta cuenta4 = new Cuenta("ES55554444333322221111", 200.0);
        Cuenta cuenta5 = new Cuenta("ES99998888777766665555", 800.0);

        // Asociar cuentas a clientes
        cliente1.agregarCuenta(cuenta1);
        cliente2.agregarCuenta(cuenta2);
        cliente3.agregarCuenta(cuenta3);
        cliente4.agregarCuenta(cuenta4);
        cliente5.agregarCuenta(cuenta5);

        // Agregar clientes y cuentas a las listas
        clientes.add(cliente1);
        clientes.add(cliente2);
        clientes.add(cliente3);
        clientes.add(cliente4);
        clientes.add(cliente5);

        cuentas.add(cuenta1);
        cuentas.add(cuenta2);
        cuentas.add(cuenta3);
        cuentas.add(cuenta4);
        cuentas.add(cuenta5);

        // Realizar un par de transacciones
        Movimiento transaccion1 = new Movimiento(200.0);
        cuenta1.agregarMovimiento(transaccion1);
        movimientos.add(transaccion1);

        Movimiento transaccion2 = new Movimiento(-50.0);
        cuenta2.agregarMovimiento(transaccion2);
        movimientos.add(transaccion2);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

}
