$(document).ready(function() {

});

async function registrarUsuario(){

let datos = {};
datos.nombre=document.getElementById('txtNombre').value;
datos.apellido=document.getElementById('txtApellido').value;
datos.email=document.getElementById('txtEmail').value;
datos.password=document.getElementById('txtPassword').value;

let repetirPassword = document.getElementById('txtRepetirPassword').value;

if(repetirPassword != datos.password){
   alert('Las contraseñas son diferentes');
   return;
}

const request = await fetch('usuarios', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    //esta funcion agarra objetos de javascript y los convierte a un string de json
    body: JSON.stringify(datos)
  });
  alert("La cuenta fue creada con exito!");
  window.location.href = '/login.html' // no me dirige al login
}
