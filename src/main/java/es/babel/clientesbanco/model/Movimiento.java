package es.babel.clientesbanco.model;

public class Movimiento {
    private String codigo;
    private double cantidad;
    private Cuenta cuentaBancaria;

    public Movimiento() {
    }

    public Movimiento(double cantidad) {
        this.cantidad = cantidad;
    }

    public Movimiento(double cantidad, String codigo) {
        this.cantidad = cantidad;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Cuenta getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(Cuenta cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }
}
