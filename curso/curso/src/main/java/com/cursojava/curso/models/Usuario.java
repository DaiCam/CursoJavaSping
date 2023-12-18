package com.cursojava.curso.models;
//en el modelo van a estar todas las entidades

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Entity //hace referencia a la BBDD
@Table(name="usuarios") //para que sepa a que tabla debe ir
@ToString @EqualsAndHashCode
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter @Column(name="id")
    private Long id;
    @Setter @Column(name="nombre")
    private String nombre;
    @Setter @Column(name="apellido")
    private String apellido;
    @Setter @Column(name="email")
    private String email;
    @Setter @Column(name="telefono")
    private String telefono;
    @Setter @Column(name="password")
    private String password;

    public Usuario(){}
    public Usuario(Long id, String nombre, String apellido, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }
}

