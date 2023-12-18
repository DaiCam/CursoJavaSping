package com.cursojava.curso.controllers;

//los controladores manejan las URL

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//tengo que decirle que va a ser un controlador con una anotacion
@RestController
//@RequestMapping("/api") //darle un nombre a la clase controladora
public class UsuarioController {

    @Autowired //hace que la clase UsuarioDaoImp, se cree un objeto y la guarda dentro de la variable
    private UsuarioDao usuarioDao;

    //probando GetMapping http://localhost:8080/api/hello
    @GetMapping("/hello")
    public String hello(){
        return "hola como va";
    }

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name){

        return "Hola " + name + " como estás?";
    }

    @GetMapping("/multiplicar/{num1}/{num2}")
    public int calcular(@PathVariable int num1, @PathVariable int num2){
        return multiplicar(num1, num2);
    }
    public int multiplicar(int num1, int num2){
        return num1*num2;
    }

    //para indicarle la URL que deberia ser (Request Mapping). Estoy llamando a la clase
    //mi URL base localhost:8080

    @RequestMapping(value = "usuarios/{id}") //como se llama la URL localhost:8080/usuario123 ejemplo
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Daiana");
        usuario.setApellido("Campott");
        usuario.setEmail("dcampott@buenosaires.gob.ar");
        usuario.setTelefono("403040");
        usuario.setPassword("123456");
        return usuario;
        //nos trae un JSON
    }

    @RequestMapping(value = "usuarios", method = RequestMethod.GET) //como se llama la URL localhost:8080/usuarios ejemplo
    //@GetMapping("/usuarios") con get no me trae la lista a la plantilla
    public List<Usuario> getUsuarios(){
        return usuarioDao.getUsuarios();
    }

    @RequestMapping(value = "usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){
        usuarioDao.registrar(usuario);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) {
       if(usuarioDao.verificarCredenciales(usuario)){
           return "OK";
       }
       return "FAIL";
    }

    @GetMapping("/usuarios_buscar/{id}")
    public Usuario usuarios(@PathVariable Long id) {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario(123L, "Daiana", "Campott", "dcampott@buenosaires.gob.ar", "403040"));
        usuarios.add(new Usuario(345L, "Juan", "Balugano", "jbalugano@gmail.com", "501020"));
        usuarios.add(new Usuario(456L, "Mercedes", "Casas", "mcasas@hotmail.com", "352515"));

        for (Usuario user : usuarios) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }




    //hacer un metodo para cada una de las acciones de HTTP (GET, POST, PUT, DELETE) -->Request Methods

    @RequestMapping(value = "usuario123") //como se llama la URL localhost:8080/usuario ejemplo
    public Usuario editar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Daiana");
        usuario.setApellido("Campott");
        usuario.setEmail("dcampott@buenosaires.gob.ar");
        usuario.setTelefono("403040");
        return usuario;
    }

    @RequestMapping(value = "usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id){
        usuarioDao.eliminar(id);

    }

    @RequestMapping(value = "usuario345") //como se llama la URL localhost:8080/usuario ejemplo
    public Usuario buscar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Daiana");
        usuario.setApellido("Campott");
        usuario.setEmail("dcampott@buenosaires.gob.ar");
        usuario.setTelefono("403040");
        return usuario;
    }
}
