$(document).ready(function() {

});

async function iniciarSesion(){

let datos = {};

datos.email=document.getElementById('txtEmail').value;
datos.password=document.getElementById('txtPassword').value;

const request = await fetch('login', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    //esta funcion agarra objetos de javascript y los convierte a un string de json
    body: JSON.stringify(datos)
  });
  const respuesta = await request.text();
  if(respuesta == 'OK'){
    window.location.href = '/usuarios.html'
  } else {
    alert("Datos ingresados incorrectos. Por favor intente nuevamente");
  }

}