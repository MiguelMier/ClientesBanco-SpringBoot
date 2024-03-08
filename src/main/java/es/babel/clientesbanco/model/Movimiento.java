package es.babel.clientesbanco.model;

public class Movimiento {
    private Long id;
    private double cantidad;
    private Cuenta cuentaBancaria;

    public Movimiento() {
    }

    public Movimiento(double cantidad) {
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
