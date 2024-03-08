package es.babel.clientesbanco.model;


import java.util.ArrayList;
import java.util.List;

public class Cuenta {

    private Cliente cliente;
    private String iban;
    private double saldo;
    private List<Movimiento> movimientos;

    public Cuenta(){
        this.movimientos = new ArrayList<>();

    }

    public Cuenta(String iban, double saldo) {
        this.iban = iban;
        this.saldo = saldo;
        this.movimientos = new ArrayList<>();
    }

    public void agregarMovimiento(Movimiento movimiento) {
        movimientos.add(movimiento);
        movimiento.setCuentaBancaria(this);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
