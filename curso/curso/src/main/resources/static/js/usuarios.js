/*libreria de javascript, lo que hace es seleccionar la tabla, le pone funcionalidad
convirtiendo como en una tabla que tenga paginacion y un monton de funcionalidades mas.
Call the dataTables jQuery plugin
dice que se ejecute todo el codigo dentro, una vez que se haya cargado la pagina*/
$(document).ready(function() {
  cargarUsuarios();
  $('#usuarios').DataTable();
});

//funcion a la que llama
//hacer llamado al servidor (usando fetch)
//async hace referencia que la funcion es asincronica
async function cargarUsuarios(){
const request = await fetch('usuarios', {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  });
  const usuarios = await request.json();

  let listadoHtml = '';

  for(usuario of usuarios){

    let botonEliminar = '<a href="#" onclick ="eliminarUsuario(' + usuario.id + ')" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>';

  let telefonoTexto = usuario.telefono == null ? '-' : usuario.telefono

  let usuarioHtml='<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+' '+usuario.apellido+'</td><td>'
                  +usuario.email+'</td><td>'
                  + telefonoTexto +'</td><td>' + botonEliminar + '</td></tr>';

  listadoHtml += usuarioHtml;

  }

  //console.log(usuarios);


document.querySelector('#usuarios tbody').outerHTML = listadoHtml; //ver
}

async function eliminarUsuario(id){

   if(!confirm('¿Quieres eliminar este usuario?')){
      return;
     }
const request = await fetch('usuarios/' + id, {
    method: 'DELETE',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  });
  location.reload();
}
