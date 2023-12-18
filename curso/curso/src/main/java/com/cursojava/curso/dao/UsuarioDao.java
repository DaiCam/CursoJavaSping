package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;

import java.util.List;

//DAO hace referencia a todas las clases que se van a conectar con la BBDD

//obliga a tener una funcion que se llame getUsuarios -->usuarioController
public interface UsuarioDao {
    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registrar(Usuario usuario);

    boolean verificarCredenciales(Usuario usuario);
}
