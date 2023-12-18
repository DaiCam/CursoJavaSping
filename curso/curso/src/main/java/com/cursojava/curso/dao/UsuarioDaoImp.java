package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//esta clase va a implementar la interface de UsuarioDao
@Repository //hace referencia de tener la funcionalidad de poder acceder al repositorio de la BBDD
@Transactional //le da como funcionalidad a esta clase por poder armar las consultas de sql a la BBDD
public class UsuarioDaoImp implements UsuarioDao{

    //se utiliza para inyectar un EntityManager, que es la interfaz principal
    // en JPA (Java Persistence API) para interactuar con la capa de persistencia de datos
    //puedes realizar operaciones de persistencia (como consultas o modificaciones en la base de datos)
    //Se usa ahora @Autowired
    @PersistenceContext
    EntityManager entityManager; //conexion con la BBDD

    @Override
    @Transactional
    public List getUsuarios() {
        //consulya a la base de datos
        String query = "FROM Usuario"; //nombre de la clase
        //TypedQuery<Usuario> typedQuery = entityManager.createQuery(query, Usuario.class);
        return entityManager.createQuery(query).getResultList();

        /*Usar TypedQuery te permite especificar el tipo de resultado de manera segura,
        evitando así el "unchecked cast". En este caso, el tipo de resultado es Usuario,
         por lo que el cast se realiza de manera implícita y segura.
         */
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public boolean verificarCredenciales(Usuario usuario) {
        String query = "FROM Usuario WHERE email = :email AND password = :password";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .setParameter("password", usuario.getPassword())
                .getResultList();

        return !lista.isEmpty();
    }
}
