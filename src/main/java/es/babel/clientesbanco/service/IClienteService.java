package es.babel.clientesbanco.service;

import es.babel.clientesbanco.model.Cliente;

import java.util.List;

public interface IClienteService {

    List<Cliente> obtenerTodosClientes();
    Cliente crearCliente(Cliente cliente);
    Cliente obtenerClientePorId(String dni);

}
