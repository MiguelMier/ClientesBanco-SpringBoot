package es.babel.clientesbanco.service;

import es.babel.clientesbanco.fakebd.FakeBD;
import es.babel.clientesbanco.model.Cliente;
import es.babel.clientesbanco.service.interfaces.IClienteService;
import es.babel.clientesbanco.utils.LogUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {

    private FakeBD fakeBD;
    private final LogUtils logger = new LogUtils();

    public ClienteService(FakeBD fakeBD){
        this.fakeBD = fakeBD;
    }

    @Override
    public List<Cliente> obtenerTodosClientes() {
        return fakeBD.getClientes();
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        fakeBD.getClientes().add(cliente);
        return cliente;
    }

    @Override
    public Cliente obtenerClientePorId(String dni) {
        for (Cliente cliente : fakeBD.getClientes()) {
            if (cliente.getDni().equals(dni)) {
                logger.loginfo("Cliente obtenido: " + cliente.toString());
                return cliente;
            }
        }
        logger.logEror(" --> Error encontrando el cliente pasado");
        return null;
    }

}
