import { Component, OnInit } from '@angular/core';
import { Cliente } from '../models/cliente.model';
import { ClientesService } from '../services/clientes.service';
import {HttpClient} from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-clientes',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './clientes.component.html',
  styleUrl: './clientes.component.css'
  })
export class ClientesComponent implements OnInit {
  clientes: Cliente[] = [];
  clienteForm: Cliente = this.clienteVacio();
  editando = false;

  constructor(private clientesService: ClientesService) {}

  ngOnInit() {
    this.cargarClientes();
  }

  cargarClientes() {
    this.clientesService.getClientes().subscribe(data => {
      this.clientes = data;
    })
  }

  guardar(){
    if(this.editando){
      this.clientesService.actualizarCliente(this.clienteForm).subscribe(() => {
        this.resetForm();
        this.cargarClientes();
      });
    } else{
      this.clientesService.crearCliente(this.clienteForm).subscribe(() =>{
        this.resetForm();
        this.cargarClientes()
      });
    }
  }

  editar(cliente: Cliente){
    this.clienteForm = { ...cliente};
    this.editando = true;
  }

  borrar(id: number){
    if(confirm('Borraras el cliente permanentemente ¿estas seguro?')){
      this.clientesService.borrarCliente(id).subscribe(() =>{
        this.cargarClientes();
      })
    }
  }

  resetForm(){
    this.clienteForm = this.clienteVacio();
    this.editando = false;
  }


  private clienteVacio(): Cliente{
    return{
      idCliente: 0,
      nombre: '',
      telefono: 0,
      correo: '',
      usuario: '',
      fechaNacimiento: ''
    };
  }
}

