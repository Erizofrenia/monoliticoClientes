package com.erick.monoclientesmvc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "CLIENTES")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Long idCliente;
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    @Column(name = "TELEFONO", nullable = false, length = 15)
    private Long telefono;
    @Column(name = "CORREO", nullable = false, length = 30)
    private String correo;
    @Column(name = "USUARIO", nullable = false, length = 25)
    private String usuario;
    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    public Long getIdCliente(){
        return idCliente;
    }
    public void setIdCliente(Long idCliente){
        this.idCliente = idCliente;
    }

    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public Long getTelefono(){
        return telefono;
    }
    public void setTelefono(Long telefono){
        this.telefono = telefono;
    }

    public String getCorreo(){
        return correo;
    }
    public void setCorreo(String correo){
        this.correo = correo;
    }

    public String getUsuario(){
        return usuario;
    }
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    
    public LocalDate getFechaNacimiento(){
        return fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
    }

}
