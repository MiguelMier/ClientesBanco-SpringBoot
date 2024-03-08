package es.babel.clientesbanco.model;


import java.util.ArrayList;
import java.util.List;

public class Cuenta {

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

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
}
