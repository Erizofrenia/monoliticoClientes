package com.erick.monoclientesmvc.service;

import com.erick.monoclientesmvc.model.Cliente;
import com.erick.monoclientesmvc.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository repo){
        this.clienteRepository = repo;
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Integer id){
        return clienteRepository.findById(id);
    }

    public Cliente save(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public void delete(Integer id){
        clienteRepository.deleteById(id);
    }

}
