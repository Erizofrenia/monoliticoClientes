package com.erick.monoclientesmvc.controller;

import com.erick.monoclientesmvc.model.Cliente;
import com.erick.monoclientesmvc.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(
        origins = "http://localhost:4200",
        allowedHeaders = "*"
)
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> all() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> get(@PathVariable Integer id) {
        return clienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
        Cliente saved = clienteService.save(cliente);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getIdCliente())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Integer id,
                                          @RequestBody Cliente cliente) {

        return clienteService.findById(id).map(existing -> {

            existing.setNombre(cliente.getNombre());
            existing.setTelefono(cliente.getTelefono());
            existing.setCorreo(cliente.getCorreo());
            existing.setUsuario(cliente.getUsuario());
            existing.setFechaNacimiento(cliente.getFechaNacimiento());

            Cliente updated = clienteService.save(existing);
            return ResponseEntity.ok(updated);

        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        if (!clienteService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
