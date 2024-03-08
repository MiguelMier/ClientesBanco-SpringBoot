package es.babel.clientesbanco.service;

public interface IOperacionesCuentas {

    void listarCuentas();
    void darAltaCuenta();
    void listarOperaciones();
    void realizarIngreso();
    void realizarRetirada();
    void realizarTransferencia();

}
