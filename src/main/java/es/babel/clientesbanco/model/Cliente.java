package es.babel.clientesbanco.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String nombre;
    private String dni; // actua como id si fuera necesario
    private List<Cuenta> cuentas;

    public Cliente() {
        this.cuentas = new ArrayList<>();

    }

    public Cliente(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
        this.cuentas = new ArrayList<>();
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
}
